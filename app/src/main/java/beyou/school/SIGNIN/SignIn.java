package beyou.school.SIGNIN;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import beyou.school.MainActivity;
import beyou.school.R;
import beyou.school.STUDENT.StudentActivity;
import beyou.school.SplashScreen;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity  implements View.OnClickListener {
    TextInputEditText mail,pass;
    Button signin,signup;
    FirebaseAuth firebaseAuth;
    TextView forgot;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth=FirebaseAuth.getInstance();
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.password);
        dialog=new ProgressDialog(SignIn.this);
        signin=findViewById(R.id.signin);
        //signup=findViewById(R.id.signup);
        signin.setOnClickListener(this);
       // signup.setOnClickListener(this);
        forgot=findViewById(R.id.reset);
        forgot.setOnClickListener(this);
        if (firebaseAuth.getCurrentUser() != null && firebaseAuth.getCurrentUser().isEmailVerified())
        {
            Intent intent = new Intent(SignIn.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.signin:
                             String email=mail.getText().toString().trim();
                             String password=pass.getText().toString().trim();
                             signingin(email,password);
                           break;
           /* case R.id.signup: Intent intent=new Intent(this,SignUp.class);
                              startActivity(intent);
                              break;*/
            case R.id.reset:
                            mail.setError(null);
                            pass.setError(null);
                             getSupportFragmentManager()
                                     .beginTransaction()
                                     .replace(android.R.id.content,new ForgotPassword())
                                     .addToBackStack(null)
                                     .commit();
        }
    }

    private void signingin(final String email, final String password)
    {

        boolean b=checkforvalid(email,password);
        if(b)
        {
            dialog.setTitle("Logging in");
            dialog.setMessage("Please wait...");
            dialog.show();
            firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful())
                                {

                                    signintoMainPAge(email,password);

                                }
                                else
                                    {
                                        dialog.dismiss();
                                        Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            });

        }

    }

    private void signintoMainPAge(String email, String password)
    {
        String school="school";
        SharedPreferences preferences=getSharedPreferences( school, Context.MODE_PRIVATE );
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString( "emailId",email );
        editor.putString( "Password",password );
        editor.commit();
        String  userid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance()
                .getReference()
                .child( "Users")
                .child(userid ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String s=dataSnapshot.child("usertype" ).getValue().toString();
                if(s.equals( "Teacher" ))
                {   dialog.dismiss();
                    Intent intent=new Intent( SignIn.this,MainActivity.class);
                    intent.putExtra( "type",s );
                    startActivity( intent );
                    finish();
                }
                else if(s.equals( "Admin" ))
                {
                    dialog.dismiss();
                    Intent intent=new Intent( SignIn.this,MainActivity.class);
                    intent.putExtra( "type",s );
                    startActivity( intent );
                    finish();
                }
                else if(s.equals( "Student" ))
                {
                    dialog.dismiss();
                    Intent intent=new Intent( SignIn.this, StudentActivity.class );
                    startActivity(intent);
                    finish();
                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                dialog.dismiss();

            }
        } );
    }

    private boolean checkforvalid(String email, String password)
    {   if (TextUtils.isEmpty(email))
           {
               mail.setError("Enter Email address");
               return  false;
           }
         if (TextUtils.isEmpty(password))
         {
             pass.setError("Enter Password");
             return false;
         }
         if (password.length()<8)
           {
               pass.setError("Min 8 characters");
               return false;

           }
        return true;
    }
}
