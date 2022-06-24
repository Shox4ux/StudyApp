package com.example.dictionary.VocabularyShop.Complex.Comlex.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.VocabularyShop.Complex.Comlex.Model.Word;

import java.util.ArrayList;

public class A1_Adapter extends RecyclerView.Adapter<ViewHolder>{
    Context context;
    ArrayList<Word> words;


    public A1_Adapter(Context context, ArrayList<Word> words) {
        this.context = context;
        this.words = words;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.word_list_a1,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.word.setText(words.get(position).getWord());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShow(words,position,context);

            }
        });
    }
    private void onShow(ArrayList<Word> word,int position,Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.alert_a1, null);
        TextView mainWord, wordTrans, wordTran, example,uz;
        mainWord = view.findViewById(R.id.main_word_alert_a1);
        wordTrans = view.findViewById(R.id.transcription_alert_a1);
        wordTran = view.findViewById(R.id.translation_alert_a1);
        example = view.findViewById(R.id.example_alert_a1);
        uz = view.findViewById(R.id.uz_alert_a1);

        mainWord.setText(word.get(position).getWord());
        wordTrans.setText(word.get(position).getTranscription());
        wordTran.setText(word.get(position).getTranslation());
        example.setText(word.get(position).getExample());
        uz.setText(word.get(position).getUz());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.card_alert_a1);
        alertDialog.show();
    }


    @Override
    public int getItemCount() {
        return words.size();
    }
}


class ViewHolder extends RecyclerView.ViewHolder {
    TextView word;
    CardView cardView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        word = itemView.findViewById(R.id.word_btn_a1);
        cardView = itemView.findViewById(R.id.button_layout_a1);

    }







}
