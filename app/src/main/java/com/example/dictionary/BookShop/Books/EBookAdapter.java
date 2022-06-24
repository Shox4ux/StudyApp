package com.example.dictionary.BookShop.Books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.BookShop.Books.Models.Item;
import com.example.dictionary.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EBookAdapter extends RecyclerView.Adapter<EbookViewHolder>{
    Context context;
    List<Item> list;

    public EBookAdapter(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EbookViewHolder(LayoutInflater.from(context).inflate(R.layout.book_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, int position) {
        holder.bookTitle.setText(list.get(position).getVolumeInfo().getTitle());
        holder.bookAuthor.setText(list.get(position).getVolumeInfo().getAuthors().toString());
        Picasso.get().load(list.get(position).getVolumeInfo().getImageLinks().getThumbnail()).into(holder.bookCover);

    }

    @Override
    public int getItemCount() {
        return list.size();

    }
}
class EbookViewHolder extends RecyclerView.ViewHolder{
    ImageView bookCover;
    TextView bookAuthor,bookTitle,bookPage;

    public EbookViewHolder(@NonNull View itemView) {
        super(itemView);

        bookCover = itemView.findViewById(R.id.book_cover);
        bookAuthor = itemView.findViewById(R.id.book_author);
        bookPage = itemView.findViewById(R.id.book_page);
        bookTitle = itemView.findViewById(R.id.book_title);
    }
}

