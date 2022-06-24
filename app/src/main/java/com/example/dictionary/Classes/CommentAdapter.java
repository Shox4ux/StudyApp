package com.example.dictionary.Classes;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dictionary.Classes.Model.Comment;
import com.example.dictionary.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    Context context;
    List<Comment> list;

    public CommentAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.userName.setText(list.get(position).getUname());
        holder.userComment.setText(list.get(position).getContent());
        holder.comDate.setText(holder.TimeToString( (long) list.get(position).getTimestamp()));
        Glide.with(context).load(list.get(position).getUimg()).into(holder.userImg);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class CommentViewHolder extends RecyclerView.ViewHolder{
    ImageView userImg;
    TextView userName,userComment,comDate;
    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        userComment = itemView.findViewById(R.id.list_item_comment);
        userName = itemView.findViewById(R.id.list_item_uname);
        userImg = itemView.findViewById(R.id.list_item_uimg);
        comDate = itemView.findViewById(R.id.list_item_date);
    }

    public String TimeToString(long time){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("hh:mm",calendar).toString();
        return date;
    }
}
