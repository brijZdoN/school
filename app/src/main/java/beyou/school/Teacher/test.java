package beyou.school.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import beyou.school.R;

public class test extends AppCompatActivity {
    private String testId,testName,saved="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent=getIntent();
        testId=intent.getStringExtra("key");
        testName=intent.getStringExtra("testname");
        saved=intent.getStringExtra("saved");
        if(saved=="0")
        {
            getSupportFragmentManager().beginTransaction().add(R.id.testactivityid,new testname()).commit();

        }
        else
        {
            Bundle bundle =new Bundle();
            bundle.putString("key",testId);
            bundle.putString("testname",testName);
            bundle.putString("saved",saved);
            queslist qu=new queslist();
            qu.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.testactivityid,qu).commit();
        }

    }
}
