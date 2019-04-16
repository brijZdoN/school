package beyou.school.Teacher;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import beyou.school.ADAPTERS.ResultAdapter;
import beyou.school.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Result extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<String> arrayList;
    private DatabaseReference reference;
    ResultAdapter adapter;

    public Result() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate( R.layout.fragment_result, container, false );
        arrayList=new ArrayList<>(  );
        adapter=new ResultAdapter( getContext(),arrayList );
        recyclerView=v.findViewById( R.id.resultrecycle );
        RecyclerView.LayoutManager manager=new LinearLayoutManager( getContext() );
        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager( manager );
        reference= FirebaseDatabase.getInstance().getReference().child("Result");
        reference .addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            { String a=dataSnapshot.child( "testname" ).getValue(String.class);
                arrayList.add( a );
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        return v;
    }

}
