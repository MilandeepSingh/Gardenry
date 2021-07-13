package com.example.gardenry.ui.myplants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.gardenry.AllPlants;
import com.example.gardenry.MyPlant;
import com.example.gardenry.MyPlantsAdapter;
import com.example.gardenry.NewPlant;
import com.example.gardenry.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MyPlantsFragment extends Fragment {

    private MyPlantsViewModel myPlantsViewModel;
    RecyclerView plantList;
    public static MyPlantsAdapter myPlantsAdapter;
    ArrayList<MyPlant> plants;
    //MyDbHelper myDbHelper;
    FirebaseFirestore fb;
    FirebaseStorage fs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myPlantsViewModel =
                new ViewModelProvider(this).get(MyPlantsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_my_plants, container, false);

        Toolbar toolbar= (Toolbar)root.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        getActivity().setTitle("My Plants");
        setHasOptionsMenu(true);

        //final TextView textView = binding.textNotifications;
        myPlantsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //textView.setText(s);
            }
        });


        plantList = root.findViewById(R.id.rcv_plantlist_my_plants);
        fb = FirebaseFirestore.getInstance();
        fs = FirebaseStorage.getInstance();

//        StorageReference sf = fs.getReference();
//        StorageReference imgRef = sf.child("Plant Images/capsicum.jpg");

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

               startActivity(new Intent(getActivity().getApplicationContext(), NewPlant.class));

                //recreate();
                //Intent intent = getIntent();


                //plants = myDbHelper.getAllPlants();


                //recreate();
            }
        });

//        dbHelper.addPlant(new Plant(R.drawable.guava, "Guava", 3));
//        dbHelper.addPlant(new Plant(R.drawable.basil, "Basil", 2));
//        dbHelper.addPlant(new Plant(R.drawable.ticoma, "Ticoma", 2));
        //dbHelper.addPlant(new Plant(R.drawable.eggplant, "Eggplant", 8));

        plants = new ArrayList<>();

//        plants.add(new MyPlant("Guava", 3));
//        plants.add(new MyPlant("Chinaberry", 5));
//        plants.add(new MyPlant("Ticoma", 2));
//        plants.add(new MyPlant("Pumpkin", 5));
//        plants.add(new MyPlant("Basil", 9));
//        plants.add(new MyPlant("Mango", 1));
//        plants.add(new MyPlant("Capsicum", 14));
//        plants.add(new MyPlant("Cucumber", 4));
//        plants.add(new MyPlant("Eggplant", 8));
//        plants.add(new MyPlant("Orange", 7));


        fb.collection("Users").document("Irish").collection("My Plants").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            MyPlant plant = document.toObject(MyPlant.class);
                            //Log.d(TAG, plant.getName()+plant.getImage()+plant.getQty());
                            plants.add(plant);
                        }

                        myPlantsAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.d(TAG, "Task failed");
            }
        });


//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                                plants.add(document.);
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });


        myPlantsAdapter = new MyPlantsAdapter(getContext(), plants);
        plantList.setHasFixedSize(true);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager( 3, LinearLayoutManager.VERTICAL);
        plantList.setLayoutManager(gridLayoutManager);

        plantList.setAdapter(myPlantsAdapter);


        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_my_plants, menu);
    }

    public void onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_view_all_plants_my_plants) {
            startActivity(new Intent(getContext(), AllPlants.class));
            return true;
        } else if (id == R.id.menu_about_us_my_plants) {
            //startActivity(new Intent(getContext(), AboutUs.class));
            return true;
        } else if (id == R.id.menu_request_new_plant_my_plants) {
            //startActivity(new Intent(getContext(), RequestPlant.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}