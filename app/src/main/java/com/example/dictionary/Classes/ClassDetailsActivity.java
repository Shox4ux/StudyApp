package com.example.dictionary.Classes;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dictionary.Classes.Model.Comment;
import com.example.dictionary.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ClassDetailsActivity extends AppCompatActivity {

    ImageView imagePost,imageUserPost,imageCurrentUser;
    TextView postDesc,postDate,postTitle;
    String postKey;
    EditText typePost;
    Button addPost;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    RecyclerView recyclerView;
    List<Comment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();


        setContentView(R.layout.activity_class_detail);
        imagePost = findViewById(R.id.post_image_detail);
        imageUserPost = findViewById(R.id.image_detail);
        imageCurrentUser = findViewById(R.id.image_detail_current_user);

        typePost = findViewById(R.id.comment_edit_detail);
        addPost = findViewById(R.id.comment_button_detail);

        postDesc= findViewById(R.id.description_detail);
        postDate = findViewById(R.id.date_detail);
        postTitle = findViewById(R.id.title_detail);

        Glide.with(this).load(getIntent().getExtras().getString("post"))
                .into(imagePost);
        Glide.with(this).load(getIntent().getExtras().getString("userPhoto"))
                .into(imageUserPost);
        Glide.with(this).load(user.getPhotoUrl().toString())
                .into(imageCurrentUser);

        String desc = getIntent().getExtras().getString("description");
        long time = getIntent().getExtras().getLong("time");
        String title = getIntent().getExtras().getString("title");
        postDesc.setText(desc);
        postDate.setText(TimeToString(time));
        postTitle.setText(title);
        postKey = getIntent().getExtras().getString("key");


        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPost.setVisibility(View.INVISIBLE);
                DatabaseReference reference = database.getReference("Comment").child(postKey).push();
                String comContent = typePost.getText().toString();
                String uimg = user.getPhotoUrl().toString();
                String uname = user.getDisplayName();
                String uid = user.getUid();
                Comment comment = new Comment(comContent,uid,uimg,uname);

                reference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showMessage("Comment added");
                        typePost.setText("");
                        addPost.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("Comment failed: "+e);
                    }
                });
            }
        });


        setRecycler();
    }

    private void setRecycler() {
        DatabaseReference reference = database.getReference("Comment").child(postKey);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snap:snapshot.getChildren()){
                    Comment comment = snap.getValue(Comment.class);
                    list.add(comment);
                }
                recyclerView = findViewById(R.id.comment_recycler);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(ClassDetailsActivity.this));
                recyclerView.setAdapter(new CommentAdapter(ClassDetailsActivity.this,list));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    private String TimeToString(long time){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
        return date;
    }
}