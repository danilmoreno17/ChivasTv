package tqmgpartners.com.chivastv.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tqmgpartners.com.chivastv.Entities.clsEditorial;
import tqmgpartners.com.chivastv.R;
import tqmgpartners.com.chivastv.act_Sipnosis;

/**
 * Created by user on 19/02/2017.
 */

public class adaPPVAdapter extends RecyclerView.Adapter<adaPPVAdapter.MyViewHolder> implements View.OnClickListener{
    private ArrayList<clsEditorial> _movieList;
    private View _itemView;
    private View.OnClickListener _listener;
    private Context _objContext;
    public adaPPVAdapter(ArrayList<clsEditorial> _movieList){this._movieList=_movieList;}
    @Override
    public adaPPVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        _objContext= parent.getContext();
        _itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ppv_list_row,parent,false);
        // _itemView.setOnClickListener(this);
        return new adaPPVAdapter.MyViewHolder(_itemView);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this._listener = listener;
    }

    @Override
    public void onBindViewHolder(adaPPVAdapter.MyViewHolder holder,final int position) {
        try{

            final clsEditorial movie = _movieList.get(position);
            holder._txtTitle.setText(movie.get_strTitle());
            if(movie.get_strPromoImages().length()>0)
                Picasso.with(_objContext).load(movie.get_strPromoImages()).into(holder._imgBillboard);
            else
                Picasso.with(_objContext).load(R.drawable.dummy_pic).into(holder._imgBillboard);
            holder._imgBillboard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(_objContext, _movieList.get(position).get_strTitle() , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(_objContext, act_Sipnosis.class);
                    intent.putExtra("EDITORIAL",movie);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    _objContext.startActivity(intent);

                }

            });

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return _movieList.size();
    }

    @Override
    public void onClick(View v) {
        if(_listener != null)
            _listener.onClick(v);
    }
    public class MyViewHolder extends  RecyclerView.ViewHolder{

        private TextView _txtTitle;
        private ImageView _imgBillboard;


        public MyViewHolder(View view){
            super(view);

            _txtTitle = (TextView)view.findViewById(R.id.categories);
            _imgBillboard = (ImageView)view.findViewById((R.id.billboard));

        }
    }
}
