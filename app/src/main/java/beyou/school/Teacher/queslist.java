package beyou.school.Teacher;


import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.HashMap;

import beyou.school.ADAPTERS.qlistadapter;

import beyou.school.Models.qmodel;
import beyou.school.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class queslist extends Fragment implements View.OnClickListener{

       RecyclerView recyclerView;
       qlistadapter adapter;
       private ArrayList<qmodel> arrayList;
       Button add,publish,save;
       DatabaseReference reference,activityreference;

       private String s;
       TextView textView;
       private static String  downloadurl;
       private StorageTask storageTask;
       Bundle b;

    public queslist()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_queslist, container, false);
        add=v.findViewById(R.id.addnew);
        add.setOnClickListener(this);
        arrayList=new ArrayList<>();
        publish=v.findViewById(R.id.publishtest);
        save=v.findViewById( R.id.save );
        save.setOnClickListener( this );
        publish.setOnClickListener(this);
        textView=v.findViewById( R.id.tname );
        b=getArguments();
         s=b.getString( "type" );
         textView.setText( b.getString( "name" ) );

        reference= FirebaseDatabase.getInstance().getReference().child( "HomeworkTest");
        activityreference=FirebaseDatabase.getInstance()
                                          .getReference()
                                          .child( "Users" )
                                          .child( FirebaseAuth.getInstance().getCurrentUser().getUid())
                                          .child( "SavedActivity" );

        adapter=new qlistadapter(arrayList,getContext());
        recyclerView=v.findViewById(R.id.tlistrecycle);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        Toast.makeText(getContext(),  b.getString("name"), Toast.LENGTH_SHORT).show();

  if(arrayList.size()>0)
  {
      save.setVisibility( View.VISIBLE );
      publish.setVisibility( View.VISIBLE );


  }
        return v;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.addnew: getActivity().getSupportFragmentManager()
                                            .beginTransaction()
                                            .add(R.id.testactivityid,new addquestion(),addquestion.class.getSimpleName())
                                            .addToBackStack(null)
                                            .commit();
                                             break;
            case R.id.publishtest:
                                  if(arrayList.size()>0)
                                  {
                                      pub();
                                  }
                                  else
                                  {
                                      Toast.makeText( getContext(),
                                           "Please Add Some Questions",
                                            Toast.LENGTH_SHORT ).show();
                                  }
                                  break;
            case R.id.save:
                                  if(arrayList.size()>0)
                                  {
                                      savetest();
                                  }
                                  else
                                  {
                                      Toast.makeText( getContext(),
                                           "Please Add Some Questions",
                                            Toast.LENGTH_SHORT ).show();

                                  }
                                  break;


        }

    }

   private void savetest()
    {
        long root=System.currentTimeMillis();
        String testnode=String.valueOf(root)+b.getString("name");
        HashMap<String,String> map=new HashMap<>(  );
        map.put( "testname",b.getString( "name" ) );
        map.put( "teachername","brij" );
        HashMap<String,qmodel> map2=new HashMap<>(  );
        for(int i=0;i<arrayList.size();i++)
        {   qmodel model=arrayList.get( i );
            map2.put( String.valueOf( i+1 ),model );
        }
        activityreference= activityreference.child( testnode );
        activityreference.setValue( map );
        activityreference.child( "QuestionList" ).setValue( map2 );
        activityreference.child( "size" ).setValue( arrayList.size() );
        getActivity().finish();
    }

    private void pub()
    {
        long root=System.currentTimeMillis();
        String testnode=String.valueOf(root)+b.getString("name");
        HashMap<String,String> map=new HashMap<>(  );
        map.put( "testname",b.getString( "name" ) );
        map.put( "teachername","brij" );
        HashMap<String,qmodel> map2=new HashMap<>(  );
        for(int i=0;i<arrayList.size();i++)
        {   qmodel model=arrayList.get( i );
            map2.put( String.valueOf( i+1 ),model);
        }
      reference= reference.child( testnode );
        reference.setValue( map );
        reference.child( "QuestionList" ).setValue( map2 );
        reference.child( "size" ).setValue( arrayList.size() );
        getActivity().finish();

    }

    public void addnewq(String ques, String a, String b, String c, String d, int ans, int viewType, String type2iuri1, String u1)
   {
                      qmodel model =new qmodel(ques,a,b,c,d,ans,viewType,u1);
                      arrayList.add(model);
                      adapter.notifyDataSetChanged();
                      if(arrayList.size()>0)
                      {
                          publish.setVisibility( View.VISIBLE );
                          save.setVisibility( View.VISIBLE );
                      }
   }



}
