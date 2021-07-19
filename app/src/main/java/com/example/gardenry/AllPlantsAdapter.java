package com.example.gardenry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class AllPlantsAdapter extends RecyclerView.Adapter<AllPlantsAdapter.ViewHolder> {

    Context context;
    ArrayList<PlantInfo> plants;

    public AllPlantsAdapter(Context context, ArrayList<PlantInfo> plants) {
        this.context = context;
        this.plants = plants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.plant_item_all_plants, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        PlantInfo plant = plants.get(position);

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
                            if(position==(getItemCount()-1)){
                                ((AllPlants)context).findViewById(R.id.ll_anim_loading_all_plants).setVisibility(View.GONE);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show();
                    holder.plantImage.setVisibility(View.VISIBLE);
                    holder.progressBar.setVisibility(ProgressBar.GONE);
                    holder.plantImage.setBackground(context.getDrawable(R.drawable.dashboard_item_5_background));
                }

            });
        }catch (IOException e){
            e.printStackTrace();
        }

        holder.plantName.setText(plant.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AboutPlant.class);
                intent.putExtra("plant", plant.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
//                CustomDialogClass cdd=new CustomDialogClass(context, plant.getName(), plant.getQty(), position);
//                cdd.show();


//                int result = cdd.getResult();
//                if(result!=0) {
//                    plants.get(position).setQty(result);
//                    notifyDataSetChanged();
//                }
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
        public ProgressBar progressBar;

        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
                plantImage = itemView.findViewById(R.id.img_image_all_plants);
                plantName = itemView.findViewById(R.id.txt_name_all_plants);
                progressBar = itemView.findViewById(R.id.pbar_image_all_plants);


        }
    }
}
