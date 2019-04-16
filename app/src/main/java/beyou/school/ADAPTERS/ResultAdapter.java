package beyou.school.ADAPTERS;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import beyou.school.R;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewholder> {
   Context context;
   ArrayList<String> arrayList;
    public ResultAdapter(Context context, ArrayList<String> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public ResultAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType)
    {   View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new MyViewholder(v);
    }


    @Override
    public void onBindViewHolder(ResultAdapter.MyViewholder holder, int position)
    {
        holder.textView.setText(arrayList.get(position));

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
