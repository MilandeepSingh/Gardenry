package com.example.gardenry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class AboutPlant extends AppCompatActivity {
    FirebaseFirestore fb;
    ImageView imageView;
    TextView name, sciName;
    ImageView indoor, ornamental, productive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_plant);

        Intent intent = getIntent();
        String plantName =intent.getStringExtra("plant");

        fb = FirebaseFirestore.getInstance();
        imageView = findViewById(R.id.img_plantimage_about_plant);
        name = findViewById(R.id.txt_name_about_plant);
        sciName = findViewById(R.id.txt_sci_name);
        indoor = findViewById(R.id.img_tick_indoor_about_plants);
        ornamental = findViewById(R.id.img_tick_ornamental_about_plants);
        productive = findViewById(R.id.img_tick_productive_about_plants);

        fb.collection("All Plants").document(plantName).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        PlantInfo plantInfo = documentSnapshot.toObject(PlantInfo.class);

                        //Toast.makeText(getApplicationContext(), plantInfo.getSciName(), Toast.LENGTH_SHORT).show();
                        name.setText(plantName);
                        sciName.setText(plantInfo.getSciName());
                        if(plantInfo.isIndoor()) indoor.setImageResource(R.drawable.tick_mark);
                        if(plantInfo.isOrnamental()) ornamental.setImageResource(R.drawable.tick_mark);
                        if(plantInfo.isProductive()) productive.setImageResource(R.drawable.tick_mark);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        StorageReference sf = FirebaseStorage.getInstance().getReference().child("Plant_Images/"+plantName.toLowerCase()+".jpg");
        try
        {
            final File localFile = File.createTempFile(plantName.toLowerCase(), "jpg");
            sf.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(context, "Picture retrieved", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            imageView.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Error occured", Toast.LENGTH_SHORT).show();
                }

            });
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}