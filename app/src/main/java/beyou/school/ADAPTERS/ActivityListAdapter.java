package beyou.school.ADAPTERS;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import beyou.school.Models.Activity_List_Model;
import beyou.school.R;

public class ActivityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context context;
    ArrayList<Activity_List_Model> arrayList;
    public ActivityListAdapter(Context context, ArrayList<Activity_List_Model> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from( parent.getContext() )
                              .inflate( R.layout.activity_single_row,parent,false );
        switch (viewType)
        {
            case 0:return new MyViewholder( v );
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        Activity_List_Model model=arrayList.get( position );
        switch (holder.getItemViewType())
        {
            case 0:
                final MyViewholder viewholder=(MyViewholder)holder;
                    viewholder.testname.setText(model.getTestname());
                    viewholder.publishbtn.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText( context, "Publish test under devepment", Toast.LENGTH_SHORT ).show();
                        }
                    } );
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public class MyViewholder extends RecyclerView.ViewHolder
{   private TextView testname;
    private Button publishbtn;
    public MyViewholder(View itemView) {
        super( itemView );
        testname=itemView.findViewById( R.id.t_name );
        publishbtn=itemView.findViewById( R.id.ac_publish );
    }
}
}
