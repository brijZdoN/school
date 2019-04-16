package beyou.school;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import beyou.school.SIGNIN.SignIn;
import beyou.school.STUDENT.StudentActivity;

public class SplashScreen extends AppCompatActivity {

    private String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );
       /* SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Name","Harneet");
        editor.apply();*/
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name", "");



        new Handler().postDelayed( new Runnable() {
            @Override
            public void run()
            {
               /* if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                { userid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child( "Users")
                            .child(userid ).addListenerForSingleValueEvent( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            String s=dataSnapshot.child("usertype" ).getValue().toString();
                            if(s.equals( "Teacher" ))
                            {
                                Intent intent=new Intent( SplashScreen.this,MainActivity.class);
                                startActivity( intent );
                                finish();
                            }
                            else if(s.equals( "Student" ))
                            {
                                Intent intent=new Intent( SplashScreen.this, StudentActivity.class );
                                startActivity(intent);
                                finish();
                            }





                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {
                            Intent intent=new Intent( SplashScreen.this, SignIn.class );
                            startActivity( intent );
                            finish();

                        }
                    } );

                }
                else
                {
                    Intent intent=new Intent( SplashScreen.this, SignIn.class );
                    startActivity( intent );
                    finish();
                }*/
                Intent intent=new Intent( SplashScreen.this, SignIn.class );
                startActivity( intent );
                finish();
            }

        } ,2000);

    }
}
