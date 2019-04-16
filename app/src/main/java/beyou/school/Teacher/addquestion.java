package beyou.school.Teacher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beyou.school.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class addquestion extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    ProgressDialog  dialog;
    private static final int PICK_ACTION_IMAGE1 = 1000;
    private static final int PICK_ACTION_IMAGE2 =10001 ;
    //viewtypeImage
    private EditText Q,A,B,C,D;
    ImageView image1,image2;
    Button btn;
    private  int set1=0,set2=0;
    Spinner SPIN;
    private String type2iuri1,u1;
    private Uri uri;
    private StorageReference storageReference;
    private StorageTask storageTask;
    //viewtpe 1
    private EditText q,a,b,c,d;
    private Button button;
    private Spinner spinner,typespinner;
    private static int VIEW_TYPE=1;
    private int ans=1;
    private String[] anslist={"1","2","3","4"};
    private String [] typelist={"text","images"};
    public addquestion() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_addquestion, container, false);
          //for viewstub 1 that is of text type
          final ViewStub stub2=(ViewStub)v.findViewById( R.id.stub2 );
                         View y=stub2.inflate();
                         stub2.setVisibility( View.GONE );
                         inintstub2( y );
          //viewstub for image type
          final ViewStub stub=(ViewStub)v.findViewById( R.id.stub );
                          View x=stub.inflate();
                          inintstub(x);
                          stub.setVisibility( View.GONE );
          storageReference= FirebaseStorage.getInstance().getReference().child( "upload" );
           typespinner=v.findViewById( R.id.qtypespin );
           final List<String> list = new ArrayList<>( Arrays.asList(typelist));
           ArrayAdapter<String> typeadapter=new ArrayAdapter<>
                                                ( getActivity()
                                                  ,android.R.layout.simple_spinner_dropdown_item
                                                  ,typelist
                                                );
         //*{
           // @Override
           // public boolean isEnabled(int position){
              //  if(position == 0)
             //   {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                //    return false;
              //  }
              //  else
             //   {
             //       return true;
            //    }
           // }
           // @Override
           // public View getDropDownView(int position, View convertView,
              //                          ViewGroup parent) {
             //   View view = super.getDropDownView(position, convertView, parent);
            //    TextView tv = (TextView) view;
             //   if(position == 0){
                    // Set the hint text color gray
               //     tv.setTextColor( Color.GRAY);
             //   }
               // else {
             //       tv.setTextColor(Color.BLACK);
           //     }
         //       return view;
        //    }
       // };*/
          typespinner.setAdapter( typeadapter );
          typespinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i==0)
                {   VIEW_TYPE=0;
                    stub.setVisibility( View.VISIBLE );
                    stub2.setVisibility( View.GONE );
                }
                else
                    if(i==1)
                    {
                        VIEW_TYPE=1;
                        stub.setVisibility( View.GONE );
                        stub2.setVisibility( View.VISIBLE );
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        } );

        ArrayAdapter<String> adapter=new ArrayAdapter<>
                                         ( getActivity()
                                           ,android.R.layout.simple_spinner_dropdown_item
                                           ,anslist
                                         );
         spinner.setAdapter(adapter);
         spinner.setOnItemSelectedListener(this );
         SPIN.setAdapter( adapter );
         SPIN.setOnItemSelectedListener( this);

        return v;
    }

    private void inintstub(View x)
    {
        q=x.findViewById(R.id.heading);
        a=x.findViewById(R.id.headingoptiona);
        b=x.findViewById(R.id.headingoptionb);
        c=x.findViewById(R.id.headingoptionc);
        d=x.findViewById(R.id.headingoptiond);
        spinner=x.findViewById( R.id.ansspin);
        button=x.findViewById(R.id.addone);
        button.setOnClickListener( this );
    }
    private void inintstub2(View y)
    {   Q=y.findViewById( R.id.Qheading );
        A=y.findViewById( R.id.ansA );
        B=y.findViewById( R.id.ansB );
        C=y.findViewById( R.id.ansC );
        D=y.findViewById( R.id.ansD );
        image1=y.findViewById( R.id.image1 );
        image1.setOnClickListener( this );
       /* image2=y.findViewById( R.id.image2 );
        image2.setOnClickListener( this );*/
        btn=y.findViewById( R.id.addans );
        btn.setOnClickListener( this );
        SPIN=y.findViewById( R.id.ANSspin);
        dialog=new ProgressDialog(getContext());


    }

    @Override
    public void onClick(View view)
    {
       switch (view.getId())
       {
           case R.id.addone:   add();
                               break;
           case R.id.image1:   getImage("image1");
                               break;
          /* case R.id.image2:   getImage( "image2" );
                               break;*/
           case R.id.addans://underdevelopment
               if(!(uri==null ))
               {
                   addimagetype(uri);
               }
                               break;
       }

    }

    private void addimagetype(Uri uri1)
    {
        String q1=Q.getText().toString().trim();
        String a1=A.getText().toString().trim();
        String b1=B.getText().toString().trim();
        String c1=C.getText().toString().trim();
        String d1=D.getText().toString().trim();
        if(q1.isEmpty() ||a1.isEmpty()||b1.isEmpty()||c1.isEmpty()||d1.isEmpty())
        {
            Toast.makeText( getContext(), "Please Fill All the column", Toast.LENGTH_SHORT ).show();
        }
        else
        {
            Toast.makeText( getContext(), uri1.toString(), Toast.LENGTH_SHORT ).show();
            Toast.makeText( getContext(), q1+a1+b1+c1+d1, Toast.LENGTH_SHORT ).show();
            Log.d("inside Addquestion","setqtype2called");
            setqtype2(q1,a1,b1,c1,d1,uri1);
        }

    }

    private void setqtype2(String q1, String a1, String b1, String c1, String d1, Uri uri1)
    {
        queslist f=(queslist)getActivity()
                .getSupportFragmentManager()
                .findFragmentByTag(queslist.class.getSimpleName());
        f.addnewq( q1, a1, b1, c1, d1, ans,VIEW_TYPE ,uri1.toString(),u1);
        Toast.makeText( getContext(), q1+a1+b1+c1+d1, Toast.LENGTH_SHORT ).show();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .remove( addquestion.this )
                .commit();
    }


    private void add()
    {

        String q1=q.getText().toString().trim();
        String a1=a.getText().toString().trim();
        String b1=b.getText().toString().trim();
        String c1=c.getText().toString().trim();
        String d1=d.getText().toString().trim();
        if(q1.isEmpty() ||a1.isEmpty()||b1.isEmpty()||c1.isEmpty()||d1.isEmpty())
        {
            Toast.makeText( getContext(), "Please Fill All the column", Toast.LENGTH_SHORT ).show();
        }
        else
        {
               setqtype1(q1,a1,b1,c1,d1);
        }
    }

    private void setqtype1( String Qu,String A1,String A2,String A3,String A4)
    {           queslist f=(queslist)getActivity()
                           .getSupportFragmentManager()
                           .findFragmentByTag(queslist.class.getSimpleName());
                            f.addnewq( Qu, A1, A2, A3, A4, ans,VIEW_TYPE ,type2iuri1,u1);
                            getActivity().getSupportFragmentManager()
                           .beginTransaction()
                           .remove( addquestion.this )
                           .commit();
    }

    private void getImage(String i1)
    {
        String a=i1;
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if(i1=="image1")
        {
            startActivityForResult( intent, PICK_ACTION_IMAGE1 );
        }
        else if(i1=="image2")
        {
            startActivityForResult( intent, PICK_ACTION_IMAGE2 );
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_ACTION_IMAGE1 &&resultCode==RESULT_OK &&data!=null &&data.getData()!=null)
        {
            uri=data.getData();
           // Picasso.with(getContext()).load(uri1).into(image1);
            dialog.show();
            dialog.setCancelable( false );
           // uploadImage1(uri);
            new MyTask().execute(  uri );

        }
        else if(requestCode==PICK_ACTION_IMAGE2 &&resultCode==RESULT_OK &&data!=null &&data.getData()!=null)
        {
            //uri2=data.getData();
           //Picasso.with( getContext()).load( uri2 ).into( image2 );
            //dialog.show();
            dialog.setCancelable( false );
            //uploadImage2( uri2 );

        }
    }



    private void uploadImage1(Uri uri1)
    {
      StorageReference ref=storageReference.child( String.valueOf( System.currentTimeMillis() ) );
        storageTask=ref.putFile( uri1 ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                u1=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                Picasso.with(getContext()).load(u1).into(image1);
                set1=1;
                dialog.hide();
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Unable to upload", Toast.LENGTH_SHORT)
                     .show();
                dialog.hide();
            }
        } ).addOnProgressListener( new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
            {
              // progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                dialog.setMessage("Uploading Please Wait... ");



            }
        } );
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        ans= Integer.parseInt( anslist[i] );
        Toast.makeText(getContext(), String.valueOf( ans ), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
   private class MyTask extends AsyncTask<Uri,String,String>
    {
        @Override
        protected String doInBackground(Uri... uris)
        {
            Uri uri1=uris[0];

            StorageReference ref=storageReference.child( String.valueOf( System.currentTimeMillis() ) );
            storageTask=ref.putFile( uri1 ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    Task<Uri> urlTask=taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    u1 = urlTask.getResult().toString();
                    set1=1;
                    dialog.hide();
                }
            } ).addOnFailureListener( new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Unable to upload", Toast.LENGTH_SHORT)
                            .show();
                    dialog.hide();
                }
            } ).addOnProgressListener( new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                {
                    // progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    dialog.setMessage("Uploading Please Wait... ");



                }
            } );

            return "successfull";
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }



        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute( s );
            Picasso.with(getContext()).load(uri).into(image1);
        }
    }


}
