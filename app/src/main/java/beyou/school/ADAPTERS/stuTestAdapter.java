package beyou.school.ADAPTERS;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import beyou.school.Models.AnswerListModel;
import beyou.school.Models.testListModel;
import beyou.school.R;

public class stuTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{  ArrayList<testListModel> arrayList;
ArrayList<AnswerListModel> ansList;
   Context context;
    public stuTestAdapter(Context context, ArrayList<testListModel> arrayList, ArrayList<AnswerListModel> ansList)
    {
        this.context=context;
        this.arrayList=arrayList;
        this.ansList=ansList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;
        switch (viewType)
        {
            case 0:
                v= LayoutInflater.from( parent.getContext()).inflate( R.layout.anstype1layout,parent,false );
                return new SimpleTypeHolder( v );
            case 1:
                v=LayoutInflater.from( parent.getContext() ).inflate( R.layout.anstype2layout,parent,false );
                return new ImageTypeHolder( v );

        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        testListModel model=arrayList.get( position );
      switch (holder.getItemViewType())
      {
          case 0:
                 SimpleTypeHolder viewholder=(SimpleTypeHolder)holder;
                 viewholder.seq.setText( String.valueOf(position +1)+"-" );
                 viewholder.ques.setText( model.getQues() );
                 viewholder.optnA.setText( model.getOptionA());
                 viewholder.optnB.setText( model.getOptionB());
                 viewholder.optnC.setText( model.getOptionC());
                 viewholder.optnD.setText( model.getOptionD());
                 viewholder.group.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
                     @Override
                     public void onCheckedChanged(RadioGroup radioGroup, int id)
                     {
                         RadioButton ab = (RadioButton) radioGroup.findViewById(R.id.tA);
                         RadioButton bb = (RadioButton) radioGroup.findViewById(R.id.tB);
                         RadioButton cb = (RadioButton) radioGroup.findViewById(R.id.tC);
                         RadioButton db = (RadioButton) radioGroup.findViewById(R.id.tD);


                         AnswerListModel model;
                         switch (id)
                         {
                             case -1:
                                 break;
                             case R.id.tA:

                                 changecolor(ab,bb,cb,db);
                                         model=new AnswerListModel( position+1,1 );
                                         ansList.add( model );
                                         break;

                             case R.id.tB:
                                 changecolor(bb,ab,cb,db);
                                         model=new AnswerListModel( position+1,2 );
                                         ansList.add( model );
                                         break;

                             case R.id.tC:
                                 changecolor(cb,ab,bb,db);
                                         model=new AnswerListModel( position+1,2 );
                                         ansList.add( model );
                                         break;

                             case R.id.tD:
                                 changecolor(db,ab,bb,cb);
                                         model=new AnswerListModel( position+1,2 );
                                         ansList.add( model );
                                         break;
                         }




                     }
                 } );
                 break;
          case 1:
              ImageTypeHolder iviewholder=(ImageTypeHolder)holder;
              iviewholder.seq.setText( String.valueOf(position +1)+"-" );
              iviewholder.ques.setText( model.getQues() );
              iviewholder.optnA.setText( model.getOptionA());
              iviewholder.optnB.setText( model.getOptionB());
              iviewholder.optnC.setText( model.getOptionC());
              iviewholder.optnD.setText( model.getOptionD());
             // Picasso.with( context ).load( model.getUri )

      }


    }

    private void changecolor(RadioButton abt, RadioButton bbt, RadioButton cbt, RadioButton dbt)
    {
        abt.setBackgroundColor( Color.GREEN );
        bbt.setBackgroundColor( Color.WHITE );
        cbt.setBackgroundColor( Color.WHITE );
        dbt.setBackgroundColor( Color.WHITE );


    }


    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        testListModel model=arrayList.get( position );

        return model.getVIEW_TYPE();
    }

    public class SimpleTypeHolder extends RecyclerView.ViewHolder
    {   private TextView seq,ques;
        private RadioButton optnA,optnB,optnC,optnD;
        private RadioGroup group;
        public SimpleTypeHolder(View itemView)
        {
            super( itemView );
            seq=itemView.findViewById( R.id.sequ );
            ques=itemView.findViewById( R.id.tid );
            optnA=itemView.findViewById( R.id.tA );
            optnB=itemView.findViewById( R.id.tB );
            optnC=itemView.findViewById( R.id.tC );
            optnD=itemView.findViewById( R.id.tD );
            group=itemView.findViewById( R.id.tgid );
        }
    }
    public class ImageTypeHolder extends RecyclerView.ViewHolder
    {
        private TextView seq,ques;
        private RadioButton optnA,optnB,optnC,optnD;
        private RadioGroup group;
        private ImageView image;

        public ImageTypeHolder(View itemView)
        {
            super( itemView );
            seq=itemView.findViewById( R.id.at2seq );
            ques=itemView.findViewById( R.id.at2qid );
            optnA=itemView.findViewById( R.id.at2A );
            optnB=itemView.findViewById( R.id.at2B );
            optnC=itemView.findViewById( R.id.at2C );
            optnD=itemView.findViewById( R.id.at2D );
            group=itemView.findViewById( R.id.at2gid );
            image=itemView.findViewById( R.id.at2image );

        }
    }
}
