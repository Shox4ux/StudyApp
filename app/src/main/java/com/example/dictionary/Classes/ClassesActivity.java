package com.example.dictionary.Classes;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dictionary.Classes.Adapter.PostRowAdapter;
import com.example.dictionary.Classes.Model.Class;
import com.example.dictionary.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class ClassesActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;


    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;
    PostRowAdapter adapter;
    List<Class> classList;
    RecyclerView recyclerView;

    Dialog popAddClass;
    ProgressBar progressBarCreateWait;
    EditText classTitle,classDescription,classTime;
    ImageView classImage,classOwner,classCreateBTN;
    Uri pickedClassPostImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        popupView();
        setClassPostImage();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Classes");
        recyclerView = findViewById(R.id.class_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        floatingActionButton = findViewById(R.id.class_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popAddClass.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classList = new ArrayList<>();
                for (DataSnapshot snap:snapshot.getChildren()){
                    Class cl = snap.getValue(Class.class);
                    classList.add(cl);

                }
                adapter = new PostRowAdapter(ClassesActivity.this,classList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void setClassPostImage() {
        classImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT>=22){
                    checkAndRequestForPermission();
                }
                else{
                    openGallery();
                }



            }
        });

    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(ClassesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE ) !=  PackageManager.PERMISSION_GRANTED)
        {
            if( ActivityCompat.shouldShowRequestPermissionRationale(ClassesActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(this, "Please accept for required permission", Toast.LENGTH_SHORT).show();
            }
            else {
                ActivityCompat.requestPermissions(ClassesActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                        ,1);
            }
        }
        else {
            openGallery();
        }

    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==1 && data != null){
            pickedClassPostImage = data.getData();
            classImage.setImageURI(pickedClassPostImage);
        }
    }

    private void popupView() {
        popAddClass = new Dialog(this);
        popAddClass.setContentView(R.layout.popup_editclass);
        popAddClass.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        popAddClass.getWindow().getAttributes().gravity = Gravity.CENTER;

        // binding ui items
        classImage = popAddClass.findViewById(R.id.class_view);
        classOwner = popAddClass.findViewById(R.id.class_owner);
        classCreateBTN = popAddClass.findViewById(R.id.class_create_btn);

        progressBarCreateWait = popAddClass.findViewById(R.id.wait_create);

        classTitle = popAddClass.findViewById(R.id.class_title);
        classTime = popAddClass.findViewById(R.id.class_time);
        classDescription = popAddClass.findViewById(R.id.class_body);

        Glide.with(ClassesActivity.this).load(user.getPhotoUrl()).into(classOwner);

        classCreateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarCreateWait.setVisibility(View.VISIBLE);
                classCreateBTN.setVisibility(View.INVISIBLE);

                if (classTitle.getText() != null && classDescription.getText() != null && classTime.getText() != null &&
                        pickedClassPostImage != null){

                    StorageReference reference = FirebaseStorage.getInstance().getReference().child("class_image");
                    StorageReference imageFilePath = reference.child(pickedClassPostImage.getLastPathSegment());
                    imageFilePath.putFile(pickedClassPostImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownloadLink = uri.toString();
                                    Class classL = new Class(classTitle.getText().toString(),
                                            classDescription.getText().toString(),classTime.getText().toString(),user.getUid()
                                            ,user.getPhotoUrl().toString(),imageDownloadLink);
                                    addClass(classL);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    showMessage(e.toString());
                                    progressBarCreateWait.setVisibility(View.INVISIBLE);
                                    classCreateBTN.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    });
                }
                else {
                    showMessage("Please verify all inputs and choose post");
                    progressBarCreateWait.setVisibility(View.INVISIBLE);
                    classCreateBTN.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    private void checkAndCreate() {

    }

    private void addClass(Class classL) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Classes").push();

        String key = myRef.getKey();
        classL.setClassKey(key);
        myRef.setValue(classL).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showMessage("Class created successfully");
                progressBarCreateWait.setVisibility(View.INVISIBLE);
                classCreateBTN.setVisibility(View.VISIBLE);
                popAddClass.dismiss();
            }
        });



    }

    private void showMessage(String message) {
        Toast.makeText(ClassesActivity.this, message, Toast.LENGTH_SHORT).show();
    }


}