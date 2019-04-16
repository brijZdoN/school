package beyou.school.Models;

public class StuQmodel
{
    public StuQmodel(){}
    private String question ,optionA,optionB,optionC,optionD,type2imageuri1,type2imageuri2,type3imageuri,us1,us2;
    private int answer,VIEW_TYPE;

    public StuQmodel(String question, String optionA, String optionB, String optionC, String optionD, int answer, int VIEW_TYPE, String type2imageuri1, String type2imageuri2, String type3imageuri)
    {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer=answer;
        this.VIEW_TYPE=VIEW_TYPE;
        this.type2imageuri1=type2imageuri1;
        this.type2imageuri2=type2imageuri2;
        this.type3imageuri=type3imageuri;
    }

    public StuQmodel(String ques, String a, String b, String c, String d, int ans, int viewType, String u1, String u2)
    {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer=answer;
        this.VIEW_TYPE=VIEW_TYPE;
        this.us1=u1;
        this.us2=u2;
    }

    public String getUs1() {
        return us1;
    }

    public void setUs1(String us1) {
        this.us1 = us1;
    }

    public String getUs2() {
        return us2;
    }

    public void setUs2(String us2) {
        this.us2 = us2;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
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

    public int getAnswer() { return answer; }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getVIEW_TYPE() {return VIEW_TYPE; }

    public void setVIEW_TYPE(int VIEW_TYPE) { this.VIEW_TYPE = VIEW_TYPE; }

    public String getType2imageuri1() { return type2imageuri1; }

    public void setType2imageuri1(String type2imageuri1) { this.type2imageuri1 = type2imageuri1; }

    public String getType2imageuri2() { return type2imageuri2; }

    public void setType2imageuri2(String type2imageuri2) { this.type2imageuri2 = type2imageuri2; }

    public String getType3imageuri() { return type3imageuri; }

    public void setType3imageuri(String type3imageuri) { this.type3imageuri = type3imageuri; }
}
