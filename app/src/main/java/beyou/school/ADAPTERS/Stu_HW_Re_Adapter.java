package beyou.school.ADAPTERS;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import beyou.school.Models.stuHomemodel;
import beyou.school.R;

public class Stu_HW_Re_Adapter extends RecyclerView.Adapter<Stu_HW_Re_Adapter.MyViewholder> {
   Context context;
   ArrayList<stuHomemodel> arrayList;
    public Stu_HW_Re_Adapter(Context context, ArrayList<stuHomemodel> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public Stu_HW_Re_Adapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType)
    {   View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new MyViewholder(v);
    }


    @Override
    public void onBindViewHolder(Stu_HW_Re_Adapter.MyViewholder holder, int position)
    {
        stuHomemodel model=arrayList.get( position );
        holder.testname.setText( model.getTestname() );
        holder.teachrname.setText( model.getTeachername() );

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder
    {
        TextView testname,teachrname,dueDate;
        public MyViewholder(View itemView)
        {
            super(itemView);
            testname=itemView.findViewById(R.id.name);
            teachrname=itemView.findViewById( R.id.teachername );
            dueDate=itemView.findViewById( R.id.duedate );

        }


    }
}
