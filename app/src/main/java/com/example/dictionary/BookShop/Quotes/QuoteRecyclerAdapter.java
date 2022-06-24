package com.example.dictionary.BookShop.Quotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;

import java.util.List;

public class QuoteRecyclerAdapter extends RecyclerView.Adapter<QuoteRecyclerAdapter.ViewHolder>{
    Context context;
    List<QuoteResponse> list;
    CopyListener listener;

    public QuoteRecyclerAdapter(Context context, List<QuoteResponse> list, CopyListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.quote_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(list.get(position).getText());
        holder.author.setText(list.get(position).getAuthor());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCopyClicked(list.get(holder.getAdapterPosition()).getText());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView text,author;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text =itemView.findViewById(R.id.text_quote);
            author = itemView.findViewById(R.id.author_quote);
            button = itemView.findViewById(R.id.button_copy);

        }


    }
}
