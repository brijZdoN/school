package beyou.school.Teacher;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import beyou.school.ADAPTERS.ActivityListAdapter;
import beyou.school.Models.Activity_List_Model;
import beyou.school.R;
import beyou.school.RecyclerItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFrament extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Activity_List_Model> arrayList;
    private ActivityListAdapter adapter;
    private FloatingActionButton addbtn;
    private DatabaseReference reference;
    private String key;
    public  ActivityFrament() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_activity_frament, container, false);
        addbtn=v.findViewById(R.id.flot);
        reference=FirebaseDatabase.getInstance()
                                  .getReference()
                                  .child( "Users" )
                                  .child( FirebaseAuth.getInstance().getCurrentUser().getUid())
                                  .child( "SavedActivity" );
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(getActivity(),test.class);
                startActivity(intent);
            }
        });
        recyclerView=v.findViewById( R.id.activitylist );
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager( getContext() );
        arrayList=new ArrayList<>();
        reference.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {   if(dataSnapshot!=null)
                {
                      key=dataSnapshot.getKey();
                      Activity_List_Model model=dataSnapshot.getValue(Activity_List_Model.class);
                      model.setKey( key );
                      arrayList.add( model );
                      adapter.notifyDataSetChanged();
                  }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        } );
        adapter=new ActivityListAdapter(getContext(),arrayList);
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( adapter );
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override public void onItemClick(View view, int position)
                    {
                       Activity_List_Model models =arrayList.get( position );
                        queslist ques=new queslist();
                        Bundle bundle=new Bundle();

                        bundle.putString( "type","2" );
                        ques.setArguments( bundle );
                        Intent intent= new Intent(getActivity(),test.class);
                        intent.putExtra("key",models.getKey());
                        intent.putExtra("testname", models.getTestname());
                        intent.putExtra("saved","1");
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position)
                    {

                        // do when needed
                    }
                })
        );
        return v;
    }

}
