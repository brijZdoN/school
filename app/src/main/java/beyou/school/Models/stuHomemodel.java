package beyou.school.Models;

import com.google.firebase.database.Exclude;

public class stuHomemodel
{
    public stuHomemodel()
    {

    }
    String testname,teachername,duedate,key;

    public stuHomemodel(String testname, String teachername, String duedate)
    {
        this.testname = testname;
        this.teachername = teachername;
        this.duedate = duedate;
    }

    public String getTestname()
    {
        return testname;
    }

    public void setTestname(String testname)
    {
        this.testname = testname;
    }

    public String getTeachername()
    {
        return teachername;
    }

    public void setTeachername(String teachername)
    {
        this.teachername = teachername;
    }

    public String getDuedate()
    {
        return duedate;
    }

    public void setDuedate(String duedate)
    {
        this.duedate = duedate;
    }
    @Exclude
    public String getKey()
    {
        return key;
    }
    @Exclude
    public void setKey(String key)
    {
        this.key = key;
    }
}
