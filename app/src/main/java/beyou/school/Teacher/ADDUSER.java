package beyou.school.Teacher;


import android.app.ProgressDialog;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import beyou.school.Models.User;
import beyou.school.R;

public class ADDUSER extends AppCompatActivity implements View.OnClickListener
{
    TextInputEditText nme,mail,pass;
    Button signup;
    RadioGroup group;
    RadioButton male,female;
    FirebaseAuth firebaseAuth;
    Spinner spinner;
    String gender="Male";
    String type="";
    String[] userlist={"Teacher","Student"};
    private ProgressDialog dialog;
    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        dialog=new ProgressDialog(this);
        nme=findViewById(R.id.name);
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.password);
        Toolbar toolbar=findViewById(R.id.toolbar);
        signup=findViewById(R.id.signup);
        group=findViewById(R.id.radiogroup);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        spinner=findViewById(R.id.spin);
        signup.setOnClickListener(this);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,userlist);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {   type=userlist[i];

                Toast.makeText(ADDUSER.this, type, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.male:
                        gender="Male";
                        break;
                    case R.id.female:
                        gender="Female";
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.signup:
                String name=nme.getText().toString().trim();
                String email=mail.getText().toString().trim();
                String password=pass.getText().toString().trim();
                usersignup(name,email,password);

                break;
        }

    }

    private void usersignup(final String name, final String email, String password)
    {
        if(checkforvalid(name,email,password))
        {
            dialog.setTitle("Registering Please Wait...");
            dialog.setMessage("processing...");
            //dialog.setCancelable(false);
            dialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                User user=new User(name,email,gender,type);
                                FirebaseDatabase.getInstance().getReference().child("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task)
                                            {
                                                if (task.isSuccessful())
                                                {
                                                    Toast.makeText(ADDUSER.this, "User successfully added", Toast.LENGTH_SHORT).show();
                                                   finish();

                                                    dialog.dismiss();
                                                }
                                                else
                                                {
                                                    Toast.makeText(ADDUSER.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }

                                            }
                                        });
                            }
                            else
                            {
                                dialog.dismiss();
                                Toast.makeText(ADDUSER.this, task.getException().getMessage(), Toast.LENGTH_SHORT)
                                        .show();

                            }


                        }
                    });
        }
    }

    private boolean checkforvalid(String name, String email, String password)
    {
        if (TextUtils.isEmpty(name))
        {
            nme.setError("Enter User Name");
            return false;
        }
        if (TextUtils.isEmpty(email))
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
