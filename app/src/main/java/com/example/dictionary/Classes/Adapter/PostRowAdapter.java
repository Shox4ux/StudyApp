package com.example.dictionary.Classes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dictionary.Classes.ClassDetailsActivity;
import com.example.dictionary.Classes.Model.Class;
import com.example.dictionary.R;

import java.util.List;

public class PostRowAdapter extends RecyclerView.Adapter<ViewHolder>{
    Context context;
    List<Class> list;

    public PostRowAdapter(Context context, List<Class> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_class_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.classTitleRow.setText(list.get(position).getTitle());
        holder.classTimeRow.setText(list.get(position).getClassTimes());
        Glide.with(context).load(list.get(position).getOwnerPhoto()).into(holder.ownerImageRow);
        Glide.with(context).load(list.get(position).getPostImg()).into(holder.postRow);
        holder.postRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClassDetailsActivity.class);
                int position = holder.getAdapterPosition();
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("description",list.get(position).getDescription());
                intent.putExtra("key",list.get(position).getClassKey());
                intent.putExtra("post",list.get(position).getPostImg());
                intent.putExtra("userPhoto",list.get(position).getOwnerPhoto());
                long timestamp = (long) list.get(position).getTimeStamp();
                intent.putExtra("time",timestamp);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class ViewHolder extends RecyclerView.ViewHolder{

    ImageView postRow,ownerImageRow;
    TextView classTitleRow,classTimeRow;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        postRow = itemView.findViewById(R.id.row_class_post);
        ownerImageRow = itemView.findViewById(R.id.row_owner_photo);
        classTimeRow = itemView.findViewById(R.id.row_class_time);
        classTitleRow = itemView.findViewById(R.id.row_class_title);


    }
}
