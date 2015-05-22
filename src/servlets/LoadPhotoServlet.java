package servlets;


import hibernate.logic.User;
import hibernate.util.Factory;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class LoadPhotoServlet extends HttpServlet{
 
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
	}  
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        if (!request.isUserInRole("user")) return;
        String name = request.getUserPrincipal().getName();
        List<User> users = new ArrayList();
        try {
            users = Factory.getInstance().getUserDAO().getAllUsers();
        } catch (SQLException ex) {
            Logger.getLogger(LoadPhotoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Long> ids = new ArrayList();
        for (User user: users){
            ids.add(user.getAvatar());
        }
        long id = 1;
        while(true){
            boolean perm = true;
            for (Long i: ids){
                if (i == id){
                    perm = false;
                }
            }
            if (perm){
                break;
            }
            id++;
        }
        
        try{
            FileItem fileItem = null;
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(3*1024*1024);
            File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(tempDir);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1024 * 1024 * 10);
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
		if (item.isFormField()) {			    	
                    
                } else {
                    fileItem = item;
                }
            }
         			
            BufferedImage lowImage = ImageIO.read(fileItem.getInputStream());
            lowImage = getResizedImage(lowImage);
            GregorianCalendar c = new GregorianCalendar();
            ImageIO.write(lowImage, "jpg", new File(getServletContext().getRealPath("/resources/images/avatar"+id+".jpg")));
            User user = Factory.getInstance().getUserDAO().getUser(name);
            user.setAvatar(id);
            Factory.getInstance().getUserDAO().updateUser(user);
            
        }catch(SQLException e){
        }catch(Exception e){}
    }
    
    
    public BufferedImage getResizedImage(BufferedImage image){
        int height = image.getHeight();
        int width = image.getWidth();
        double ScaleFactor = 0.9;
        BufferedImage newImage = image;
        while(height > 300 || width > 300){
            height = (int)(height*ScaleFactor);
            width = (int)(width*ScaleFactor);
            newImage = resize(newImage, width, height);
        }
        return newImage;
    }
    
    
    public BufferedImage resize(BufferedImage image, int width, int height){
        BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D;
        graphics2D = bufferedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.drawImage(image, 0, 0,width, height, null);//.getScaledInstance(width, height, Image.SCALE_SMOOTH)
	
        if(graphics2D != null) {
            graphics2D.dispose();
        }       
	return bufferedImage;
    }
 
}

