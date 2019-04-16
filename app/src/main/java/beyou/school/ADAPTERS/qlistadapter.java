package beyou.school.ADAPTERS;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import beyou.school.Models.qmodel;
import beyou.school.R;

public class qlistadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   ArrayList<qmodel> arrayList;

   Context context;
    public qlistadapter(ArrayList<qmodel> arrayList, Context context)
    {
        this.arrayList=arrayList;
        this.context=context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {View v;
        switch (viewType)
    {
        case 0:v= LayoutInflater.from(parent.getContext()).inflate(R.layout.oneques,parent,false);
            return new Viewholdert1(v);
        case 1:v=LayoutInflater.from( parent.getContext() ).inflate( R.layout.type2singlerow,parent,false );
               return new Viewholdert2( v );
    }
       return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {   qmodel model=arrayList.get(position);
        switch (holder.getItemViewType())
        {
            case 0:
                final Viewholdert1 viewholdert1 =(Viewholdert1)holder;
                   viewholdert1.textView.setText(model.getQuestion());
                   viewholdert1.seq.setText( String.valueOf(position +1)+"-" );
                   viewholdert1.a.setText(model.getOptionA());
                   viewholdert1.b.setText(model.getOptionB());
                   viewholdert1.c.setText(model.getOptionC());
                   viewholdert1.d.setText(model.getOptionD());
                   viewholdert1.delete.setOnClickListener( new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           PopupMenu popupMenu=new PopupMenu( context, viewholdert1.delete );
                                     popupMenu.getMenuInflater().inflate( R.menu.deletemenu,popupMenu.getMenu() );
                                     popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                                         @Override
                                         public boolean onMenuItemClick(MenuItem menuItem)
                                         {
                                             Toast.makeText( context, "delete clicked", Toast.LENGTH_SHORT ).show();
                                             arrayList.remove( position );
                                             notifyDataSetChanged();
                                             return true;
                                         }
                                     } );
                                     popupMenu.show();
                       }
                   } );
                   switch (model.getAnswer())
                   {
                       case 1:
                           viewholdert1.a.setBackgroundColor(Color.GREEN);
                           break;
                       case 2:
                           viewholdert1.b.setBackgroundColor(Color.GREEN);
                           break;
                       case 3:
                           viewholdert1.c.setBackgroundColor(Color.GREEN);
                           break;
                       case 4:
                           viewholdert1.d.setBackgroundColor(Color.GREEN);
                           break;
                   }
                  break;
            case 1:
                final Viewholdert2 viewholdert2 =(Viewholdert2)holder;
                viewholdert2.seq2.setText(String.valueOf( position+1 )+"-" );
                viewholdert2.t2textView.setText(model.getQuestion());
                viewholdert2.t2a.setText(model.getOptionA());
                viewholdert2.t2b.setText(model.getOptionB());
                viewholdert2.t2c.setText(model.getOptionC());
                viewholdert2.t2d.setText(model.getOptionD());
                Picasso.with( context ).load(model.getUs1()).into( viewholdert2.t2image1 );

                viewholdert2.t2delete.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu popupMenu=new PopupMenu( context, viewholdert2.t2delete );
                        popupMenu.getMenuInflater().inflate( R.menu.deletemenu,popupMenu.getMenu() );
                        popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem)
                            {
                                Toast.makeText( context, "delete clicked", Toast.LENGTH_SHORT ).show();
                                arrayList.remove( position );
                                notifyDataSetChanged();
                                return true;
                            }
                        } );
                        popupMenu.show();
                    }
                } );
                switch (model.getAnswer())
                {
                    case 1:
                        viewholdert2.t2a.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        viewholdert2.t2b.setBackgroundColor(Color.GREEN);
                        break;
                    case 3:
                        viewholdert2.t2c.setBackgroundColor(Color.GREEN);
                        break;
                    case 4:
                        viewholdert2.t2d.setBackgroundColor(Color.GREEN);
                        break;
                }
                break;
        }

    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        qmodel model=arrayList.get(position);
        return model.getVIEW_TYPE();
    }
    class Viewholdert1 extends RecyclerView.ViewHolder
    {
       TextView textView,seq;
       RadioButton a,b,c,d;
       ImageView delete;
        public Viewholdert1(View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.qid);
            a=itemView.findViewById(R.id.A);
            b=itemView.findViewById(R.id.B);
            c=itemView.findViewById(R.id.C);
            d=itemView.findViewById(R.id.D);
            seq=itemView.findViewById( R.id.sequence );
            delete=itemView.findViewById( R.id.textViewOptions );
        }
    }
    class Viewholdert2 extends RecyclerView.ViewHolder
    {
        TextView t2textView,seq2;
        ImageView t2image1;
        RadioButton t2a,t2b,t2c,t2d;
        ImageView t2delete;
        public Viewholdert2(View itemView)
        {
            super( itemView );
            seq2=itemView.findViewById( R.id.sequence2 );
            t2textView=itemView.findViewById(R.id.t2qid);
            t2a=itemView.findViewById(R.id.t2A);
            t2b=itemView.findViewById(R.id.t2B);
            t2c=itemView.findViewById(R.id.t2C);
            t2d=itemView.findViewById(R.id.t2D);
            t2delete=itemView.findViewById( R.id.t2textViewOptions );
            t2image1=itemView.findViewById( R.id.t2image1 );


        }
    }

}
