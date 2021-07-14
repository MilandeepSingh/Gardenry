package com.example.gardenry;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class GardeningTools extends AppCompatActivity {

    MyGardenToolsAdapter myGardenToolsAdapter;
    ListView listView;
    ArrayList<GTool> gTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gardening_tools);
        listView = findViewById(R.id.ll_g_tools);

        gTools = new ArrayList<>();


        gTools.add(new GTool(R.drawable.gloves,"Gloves", getString(R.string.gloves)));

        gTools.add(new GTool(R.drawable.pruners,"Pruning Shears", getString(R.string.pruners)));

        gTools.add(new GTool(R.drawable.loppers,"Loppers", getString(R.string.loppers)));

        gTools.add(new GTool(R.drawable.fork,"Garden Fork", getString(R.string.fork)));

        gTools.add(new GTool(R.drawable.trowel,"Hand Trowel", getString(R.string.trowel)));

        gTools.add(new GTool(R.drawable.spade,"Spade", getString(R.string.spade)));

        gTools.add(new GTool(R.drawable.rake,"Leaf Rake", getString(R.string.rake)));

        gTools.add(new GTool(R.drawable.hoe,"Hoe", getString(R.string.hoe)));



        listView = findViewById(R.id.ll_g_tools);

        myGardenToolsAdapter = new MyGardenToolsAdapter(getApplicationContext(), gTools);

        listView.setAdapter(myGardenToolsAdapter);
    }
}