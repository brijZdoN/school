package beyou.school.Models;

public class testListModel
{  String ques,optionA,optionB,optionC,optionD;
   int VIEW_TYPE;
    public testListModel(){}

    public testListModel(String ques, String optionA, String optionB, String optionC, String optionD, int VIEW_TYPE)
    {
        this.ques = ques;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.VIEW_TYPE = VIEW_TYPE;
    }

    public String getQues()
    {
        return ques;
    }

    public void setQues(String ques)
    {
        this.ques = ques;
    }

    public String getOptionA()
    {
        return optionA;
    }

    public void setOptionA(String optionA)
    {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getVIEW_TYPE() {
        return VIEW_TYPE;
    }

    public void setVIEW_TYPE(int VIEW_TYPE) {
        this.VIEW_TYPE = VIEW_TYPE;
    }
}
