package hibernate.DAO;

import hibernate.logic.Comment;
import java.sql.SQLException;
import java.util.List;



public interface CommentDAO {
    public void addComment(Comment comment) throws SQLException;
    public List<Comment> getAllComments(String user) throws SQLException;
    public void deleteAllComments(String user) throws SQLException;
}
