package beyou.school.STUDENT;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import beyou.school.ADAPTERS.stuTestAdapter;
import beyou.school.Models.AnswerListModel;
import beyou.school.Models.testListModel;
import beyou.school.R;

public class StuTest extends AppCompatActivity {
   private RecyclerView recyclerView;
   ArrayList<testListModel> arrayList;
   ArrayList<AnswerListModel> ansList;
   stuTestAdapter adapter;
   private Toolbar toolbar;
   private Button submittest;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_stu_test );
        Intent intent=getIntent();
        toolbar=findViewById( R.id.toolbar );
        if(intent!=null)
        {
             String n=intent.getStringExtra( "testname" );
            TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
            toolbar.setTitle( "" );
            mTitle.setText(n);
        }

        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        arrayList=new ArrayList<>( );
        ansList=new ArrayList<>( );
        adapter=new stuTestAdapter(getApplicationContext(),arrayList,ansList);
        recyclerView=findViewById( R.id.testrecycle );
        RecyclerView.LayoutManager manager=new LinearLayoutManager( this );
        recyclerView.setLayoutManager( manager );
        recyclerView.setAdapter( adapter );
        if(intent==null)
        {
            Toast.makeText( this, "Sorry,There is some problem please go back", Toast.LENGTH_SHORT ).show();
        }
        String uid=intent.getStringExtra( "testid" );
        FirebaseDatabase.getInstance()
                        .getReference()
                        .child( "HomeworkTest" )
                        .child( uid )
                        .addValueEventListener( new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                int n=dataSnapshot.child( "size" ).getValue(Integer.class);
                                for(int i=0;i<n;i++)
                                {
                                    String ques=dataSnapshot.child( "QuestionList").child( String.valueOf( i+1 ) ).child( "question" ).getValue(String.class);
                                    String optnA=dataSnapshot.child( "QuestionList").child( String.valueOf( i+1 ) ).child( "optionA" ).getValue(String.class);
                                    String optnB=dataSnapshot.child( "QuestionList").child( String.valueOf( i+1 ) ).child( "optionB" ).getValue(String.class);
                                    String optnC=dataSnapshot.child( "QuestionList").child( String.valueOf( i+1 ) ).child( "optionC" ).getValue(String.class);
                                    String optnD=dataSnapshot.child( "QuestionList").child(String.valueOf( i+1 ) ).child( "optionD" ).getValue(String.class);
                                    int viewtype=dataSnapshot.child("QuestionList").child(String.valueOf( i+1 )).child("view_TYPE").getValue(Integer.class);
                                    testListModel model=new testListModel( ques,optnA,optnB,optnC,optnD,viewtype );
                                    arrayList.add( model );
                                    adapter.notifyDataSetChanged();
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        } );
        submittest=findViewById( R.id.submit );
        submittest.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder( StuTest.this );
                builder.setMessage( "Do you Really want to Submit Test" );
                builder.setCancelable( false );
                builder.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Toast.makeText( StuTest.this, "Test Submitted Successfully"
                             ,Toast.LENGTH_SHORT )
                             .show();
                             finish();

                    }
                } ).setNegativeButton( "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                } );
                builder.create().show();


            }
        } );


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        final boolean ret=false;
        AlertDialog.Builder builder=new AlertDialog.Builder( StuTest.this );
        builder.setCancelable( false );
        builder.setMessage( "Do you Really want to Exit" );
        builder.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();


            }
        } ).setNegativeButton( "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
              return ;
            }
        } );
        builder.create().show();
        return true;
    }

}
