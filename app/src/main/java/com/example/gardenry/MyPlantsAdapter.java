package com.example.gardenry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyPlantsAdapter extends RecyclerView.Adapter<MyPlantsAdapter.ViewHolder> {

    Context context;
    ArrayList<MyPlant> plants;

    public MyPlantsAdapter(Context context, ArrayList<MyPlant> plants) {
        this.context = context;
        this.plants = plants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.plant_item_my_plants, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        MyPlant plant = plants.get(position);

        StorageReference sf = FirebaseStorage.getInstance().getReference().child("Plant_Images/"+plant.getName().toLowerCase()+".jpg");

        try
        {
            final File localFile = File.createTempFile(plant.getName().toLowerCase(), "jpg");
            sf.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(context, "Picture retrieved", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            holder.plantImage.setImageBitmap(bitmap);
                            holder.plantImage.setVisibility(View.VISIBLE);
                            holder.progressBar.setVisibility(ProgressBar.GONE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show();
                }

            });
        }catch (IOException e){
            e.printStackTrace();
        }
        //holder.plantImage.setImageResource(plant.getImage());
        holder.plantName.setText(plant.getName());
            holder.plantQty.setText(plant.getQty()+"");

//        if(position%2==0){
//            holder.linearLayout.setBackground(holder.view.getResources().getDrawable(R.drawable.rounded_corners));
//        }
//        else holder.linearLayout.setBackground(holder.view.getResources().getDrawable(R.drawable.rounded_corners));//(R.color.white));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogClass cdd=new CustomDialogClass(context, plant.getName(), plant.getQty(), position);
                cdd.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView plantImage;
        public TextView plantName;
        public TextView plantQty;
        public LinearLayout linearLayout;
        public ProgressBar progressBar;

        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
                plantImage = itemView.findViewById(R.id.img_image_my_plants);
                plantName = itemView.findViewById(R.id.txt_name_my_plants);
                plantQty = itemView.findViewById(R.id.txt_qty_my_plants);
                progressBar = itemView.findViewById(R.id.pbar_image_my_plants);
                //linearLayout = itemView.findViewById(R.id.ll_card_plant_item);


        }
    }
}
