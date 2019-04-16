package beyou.school.ADAPTERS;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import beyou.school.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class HW_Re_Adapter extends RecyclerView.Adapter<HW_Re_Adapter.MyViewholder> {
   Context context;
   ArrayList<String> arrayList;
    public HW_Re_Adapter(Context context, ArrayList<String> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public HW_Re_Adapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType)
    {   View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new MyViewholder(v);
    }


    @Override
    public void onBindViewHolder(HW_Re_Adapter.MyViewholder holder, int position)
    {
        holder.textView.setText(arrayList.get(position));
        holder.textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText( context, "This field is under development ", Toast.LENGTH_SHORT ).show();

            }
        } );

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public MyViewholder(View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.name);
        }


    }
}
