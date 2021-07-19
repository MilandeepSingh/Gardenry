package com.example.gardenry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.gardenry.ui.myplants.MyPlantsViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AllPlants extends AppCompatActivity {

    private MyPlantsViewModel myPlantsViewModel;
    RecyclerView plantList;
    static AllPlantsAdapter allPlantsAdapter;
    ArrayList<PlantInfo> plants;
    //MyDbHelper myDbHelper;
    FirebaseFirestore fb;
    FirebaseStorage fs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().hide();

        setContentView(R.layout.activity_all_plants);


            Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_all_plants);
            setSupportActionBar(toolbar);
//          getActivity().setTitle("My Plants");
//            toolbar.setHasOptionsMenu(true);

        //final TextView textView = binding.textNotifications;



        plantList = findViewById(R.id.rcv_plantlist_all_plants);
        fb = FirebaseFirestore.getInstance();
        fs = FirebaseStorage.getInstance();

//        StorageReference sf = fs.getReference();
//        StorageReference imgRef = sf.child("Plant Images/capsicum.jpg");

//        FloatingActionButton fab = binding.fab;
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//               //startActivity(new Intent(getActivity().getApplicationContext(), NewPlant.class));
//
//                //recreate();
//                //Intent intent = getIntent();
//
//
//                //plants = myDbHelper.getAllPlants();
//
//
//                //recreate();
//            }
//        });

//        dbHelper.addPlant(new Plant(R.drawable.guava, "Guava", 3));
//        dbHelper.addPlant(new Plant(R.drawable.basil, "Basil", 2));
//        dbHelper.addPlant(new Plant(R.drawable.ticoma, "Ticoma", 2));
        //dbHelper.addPlant(new Plant(R.drawable.eggplant, "Eggplant", 8));

        //plants = new ArrayList<>();

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

        /*ArrayList<PlantInfo> plantInfos;
        plantInfos = new ArrayList<>();
        plantInfos.add(new PlantInfo("Guava", "Psidium guajava", false, false, true));
        plantInfos.add(new PlantInfo("Basil", "Ocimum basilicum", false, false, true));
        plantInfos.add(new PlantInfo("Ticoma", "Tecoma.", false, true, false));
        plantInfos.add(new PlantInfo("Eggplant", "Solanum melongena", false, false, true));
        plantInfos.add(new PlantInfo("Orange", "Citrus X sinensis", false, true, true));
        plantInfos.add(new PlantInfo("Cucumber", "Cucumis sativus", false, false, true));
        plantInfos.add(new PlantInfo("Mango", "Mangifera indica", false, false, true));
        plantInfos.add(new PlantInfo("Capsicum", "Capsicum annuum", false, false, true));
        plantInfos.add(new PlantInfo("Chinaberry", "Melia azedarach", false, false, true));
        plantInfos.add(new PlantInfo("Pumpkin", "Cucurbita pepo", false, false, true));

        for(PlantInfo plant: plantInfos)
            fb.collection("All Plants").document(plant.getName()).set(plant)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Plant added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
*/

        plants = new ArrayList<>();
        fb.collection("All Plants").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            PlantInfo plantInfo = document.toObject(PlantInfo.class);
                            plants.add(plantInfo);
                        }

                        if(plants.isEmpty()){
                            findViewById(R.id.ll_no_all_plants).setVisibility(View.VISIBLE);
                            findViewById(R.id.ll_anim_loading_all_plants).setVisibility(View.GONE);
                            plantList.setVisibility(View.GONE);
                        }

                        allPlantsAdapter.notifyDataSetChanged();
                    }}).addOnFailureListener(new OnFailureListener() {
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


        allPlantsAdapter = new AllPlantsAdapter(AllPlants.this, plants);
        plantList.setHasFixedSize(true);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager( 3, LinearLayoutManager.VERTICAL);
        plantList.setLayoutManager(gridLayoutManager);

        plantList.setAdapter(allPlantsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling_all_plants, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_my_plants_all_plants) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            String fragmentToDisplay = "MyPlantsFragment";
            intent.putExtra("fragmentToDisplay", fragmentToDisplay);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_homepage_all_plants) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            //startActivity(new Intent(g, MyPlantsFragment.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}