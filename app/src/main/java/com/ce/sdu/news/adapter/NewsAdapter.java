package com.ce.sdu.news.adapter;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ce.sdu.news.R;
import com.ce.sdu.news.model.NewsItem;
import com.squareup.picasso.Picasso;
import java.util.List;
/**
 * Created by Rauan on 09/02/17.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsItem> list;
    static private Context context;
    public NewsAdapter(List<NewsItem> list,Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem newsItem = list.get(position);
        //holder.imgMsg.setImageResource(newsItem.getResId());
        Picasso.with(context).load(newsItem.getResId()).into(holder.imgMsg);
        holder.txtMsg.setText(newsItem.getTitle());
        holder.txtDesc.setText(newsItem.getDesc());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgMsg;
        TextView txtMsg;
        TextView txtDesc;
        CardView cv;
        public ViewHolder(View itemView){
            super(itemView);
            imgMsg = (ImageView) itemView.findViewById(R.id.imgMsg);
            txtMsg = (TextView) itemView.findViewById(R.id.txtMesg);
            txtDesc = (TextView) itemView.findViewById(R.id.txtDesc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Long",Toast.LENGTH_SHORT).show();
                }
            });
            cv = (CardView) itemView.findViewById(R.id.cv);
        }
    }
}