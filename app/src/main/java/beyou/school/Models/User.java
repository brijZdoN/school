package beyou.school.Models;
public class User
{  String username,emailid,gender,usertype;
    public User()
    {

    }

   /* public User(String username, String emailid, String gender) {
        this.username = username;
        this.emailid = emailid;
        this.gender = gender;
    }*/
    public User(String username, String emailid, String gender,String usertype) {
        this.username = username;
        this.emailid = emailid;
        this.gender = gender;
        this.usertype=usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}

