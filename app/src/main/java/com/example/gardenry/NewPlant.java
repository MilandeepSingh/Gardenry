package com.example.gardenry;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class NewPlant extends AppCompatActivity {

    Spinner spinner;
    ArrayList<String> plantNames;
    Button btnAdd;
    AppCompatEditText edtQty;
    FirebaseFirestore firebaseFirestore;
    ArrayAdapter aa;
    ArrayList<PlantInfo> plantInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plant);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_new_plant);
        setSupportActionBar(toolbar);

        btnAdd = findViewById(R.id.btn_add_new_plant);
        edtQty = findViewById(R.id.edt_qty_new_plant);
        edtQty.setActivated(true);
        edtQty.requestFocus();

        spinner = findViewById(R.id.spr_plant_name_new_plant);
        firebaseFirestore = FirebaseFirestore.getInstance();

//        db.addPlant(new Plant(R.drawable.guava, "Guava", 3));
//        db.addPlant(new Plant(R.drawable.basil, "Basil", 2));
//        db.addPlant(new Plant(R.drawable.ticoma, "Ticoma", 2));
//        db.addPlant(new Plant(R.drawable.eggplant, "Eggplant", 8));


        plantNames = new ArrayList<>();
        firebaseFirestore.collection("All Plants").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            plantNames.add(document.toObject(MyPlant.class).getName());
                        }
                        aa.notifyDataSetChanged();
                    }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.d(TAG, "Task failed");
            }
        });


        plantNames.add(0, "Select a plant");


        aa = new ArrayAdapter(this, R.layout.spinner_item, plantNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        plantInfos = new ArrayList<>();
//        plantInfos.add(new PlantInfo(R.drawable.guava, "Guava", "Psidium guajava", false, false, true));
//        plantInfos.add(new PlantInfo(R.drawable.basil, "Basil", "Ocimum basilicum", false, false, true));
//        plantInfos.add(new PlantInfo(R.drawable.ticoma, "Ticoma", "Tecoma.", false, true, false));
//        plantInfos.add(new PlantInfo(R.drawable.eggplant, "Eggplant", "Solanum melongena", false, false, true));
//        plantInfos.add(new PlantInfo(R.drawable.orange, "Orange", "Citrus X sinensis", false, true, true));
//        plantInfos.add(new PlantInfo(R.drawable.cucumber, "Cucumber", "Cucumis sativus", false, false, true));
//        plantInfos.add(new PlantInfo(R.drawable.mango, "Mango", "Mangifera indica", false, false, true));
//        plantInfos.add(new PlantInfo(R.drawable.capsicum, "Capsicum", "Capsicum annuum", false, false, true));
//        plantInfos.add(new PlantInfo(R.drawable.chinaberry, "Chinaberry", "Melia azedarach", false, false, true));
//        plantInfos.add(new PlantInfo(R.drawable.pumpkin, "Pumpkin", "Cucurbita pepo", false, false, true));

        for(PlantInfo plant: plantInfos)
        firebaseFirestore.collection("All Plants").document(plant.getName()).set(plant)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Plant added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<String> myPlantNames = new ArrayList<>();
        firebaseFirestore.collection("Users").document(LOGGED_USER.email).collection("My Plants").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            myPlantNames.add(document.toObject(MyPlant.class).getName());
                        }
                    }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.d(TAG, "Task failed");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plantName = spinner.getSelectedItem().toString();
                String strQty = edtQty.getText().toString();
                if(strQty.isEmpty()){
                    //Toast.makeText(getApplicationContext(), "Please specify quantity!", Toast.LENGTH_SHORT).show();
                    edtQty.setError("Please specify quantity!");
                    return;
                }
                int qty = Integer.parseInt(strQty);
                boolean is_Already_added=false;

                for(String name : myPlantNames){
                    if(name.equals(plantName))
                        is_Already_added=true;
                }

                if(plantName.equals("Select a plant")) {
                    Toast.makeText(getApplicationContext(), "Select a plant first...", Toast.LENGTH_SHORT).show();
                    TextView errorText = (TextView)spinner.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Select a plant first...");//changes the selected item text to this
                }

                else if(is_Already_added)
                    Toast.makeText(getApplicationContext(), "Plant already added", Toast.LENGTH_SHORT).show();

                else if(qty<1) {
                    //Toast.makeText(getApplicationContext(), "Invalid quantity...", Toast.LENGTH_SHORT).show();
                    edtQty.setError("Invalid quantity...");
                }

                else {
                    View linearLayout = findViewById(R.id.ll_pbar_add_new_plant);
                    btnAdd.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    firebaseFirestore.collection("Users").document(LOGGED_USER.email)
                            .collection("My Plants").document(plantName)
                            .set(new MyPlant(plantName, qty))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Toast.makeText(getApplicationContext(), "Plant added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        String fragmentToDisplay = "MyPlantsFragment";
                        intent.putExtra("fragmentToDisplay", fragmentToDisplay);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling_new_plant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_my_plants_new_plant) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            String fragmentToDisplay = "MyPlantsFragment";
            intent.putExtra("fragmentToDisplay", fragmentToDisplay);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_homepage_new_plant) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            //startActivity(new Intent(g, MyPlantsFragment.class);
            return true;
        }
        else if(id == R.id.menu_all_plants_new_plant){
            startActivity(new Intent(getApplicationContext(), AllPlants.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        String fragmentToDisplay = "MyPlantsFragment";
        intent.putExtra("fragmentToDisplay", fragmentToDisplay);
        startActivity(intent);
    }



}