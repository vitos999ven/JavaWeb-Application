package hibernate.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usersInfo")
public class User {
    @Id
    @Column(name="login")
    private String login;
    @Column(name="avatar")
    private long avatar;
    @Column(name="bookmark")
    private int bookmark;

    public User(){}
    
    public User(String login, long avatar, int bookmark){
        this.login = login;
        this.avatar = avatar;
        this.bookmark = bookmark;
    }
   
    public String getLogin() {
        return login;
    }

    
    public void setLogin(String login) {
        this.login = login;
    }

    
    public long getAvatar() {
        return avatar;
    }

    
    public void setAvatar(long avatar) {
        this.avatar = avatar;
    }

    public int getBookmark() {
        return bookmark;
    }

    public void setBookmark(int bookmark) {
        this.bookmark = bookmark;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof User){
            User other = (User)obj;
            return ((this.login.equals(other.login)) && (this.avatar == other.avatar) &&(this.getBookmark() == other.getBookmark()));
        }
        return false;
    }
}
