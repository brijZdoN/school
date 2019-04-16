package beyou.school.Teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import beyou.school.R;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportFragmentManager().beginTransaction().add(R.id.testactivityid,new testname()).commit();
    }
}
