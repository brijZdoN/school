package beyou.school.Models;

public class AnswerListModel
{
    private int q,ans;

    public AnswerListModel(int q, int ans)
    {
        this.q = q;
        this.ans = ans;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }
}
