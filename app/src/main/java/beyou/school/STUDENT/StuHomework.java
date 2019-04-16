package beyou.school.STUDENT;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import beyou.school.ADAPTERS.HW_Re_Adapter;
import beyou.school.ADAPTERS.Stu_HW_Re_Adapter;
import beyou.school.Models.stuHomemodel;
import beyou.school.R;
import beyou.school.RecyclerItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class StuHomework extends Fragment
{
   Stu_HW_Re_Adapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<stuHomemodel> arrayList;
    private DatabaseReference reference;
    public StuHomework()
    {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate( R.layout.fragment_stu_homework, container, false );
        recyclerView=v.findViewById( R.id.shomerecycle );
        RecyclerView.LayoutManager manager=new LinearLayoutManager( getContext());
        arrayList=new ArrayList<>();
        adapter=new Stu_HW_Re_Adapter( getContext(),arrayList );
        recyclerView.setLayoutManager( manager );
        recyclerView.setAdapter( adapter );
        reference= FirebaseDatabase.getInstance().getReference().child("HomeworkTest");
        reference .addChildEventListener( new ChildEventListener()
        {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            { String key=dataSnapshot.getKey();
              String tname=dataSnapshot.child( "testname" ).getValue(String.class);
              String tchrname=dataSnapshot.child( "teachername" ).getValue(String.class);
                stuHomemodel model =new stuHomemodel( tname,tchrname,"" );
                model.setKey( key );
                arrayList.add( model );
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot)
            {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            { }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {}
        }
        );
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override public void onItemClick(View view, int position)
                    {   stuHomemodel model=arrayList.get( position );
                        Intent intent=new Intent( getContext(),StuTest.class );
                         intent.putExtra( "testid",model.getKey() );
                         intent.putExtra( "testname",model.getTestname() );
                         startActivity( intent );


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        return v;
    }

}
