package beyou.school.SIGNIN;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import beyou.school.MainActivity;
import beyou.school.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Varification extends AppCompatActivity implements View.OnClickListener {
    Button verify,refresh;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varification);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        verify=findViewById(R.id.verify);
        refresh=findViewById(R.id.refresh);
        verify.setOnClickListener(this);
        refresh.setOnClickListener(this);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.verify:
                             if (!firebaseUser.isEmailVerified())
                             {
                                 firebaseUser.sendEmailVerification()
                                             .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<Void> task)
                                                 {
                                                     if (task.isSuccessful())
                                                     {
                                                           Toast.
                                                           makeText(Varification.this,
                                                           "Varification Link send to "+firebaseUser.getEmail()
                                                           , Toast.LENGTH_SHORT).show();
                                                     }
                                                     else
                                                         {
                                                             Toast.makeText(Varification.this,
                                                                     task.getException().getMessage(),
                                                                     Toast.LENGTH_SHORT).show();
                                                         }

                                                 }
                                             });
                             }

                             break;
            case R.id.refresh:
                              if(firebaseUser.isEmailVerified())
                              {
                                  Intent intent=new Intent(Varification.this, MainActivity.class);
                                  startActivity(intent);
                                  finish();
                              }
                              else
                              {
                                  Toast.makeText(this, "Email Id not Verified", Toast.LENGTH_SHORT).show();
                              }

                             break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}
