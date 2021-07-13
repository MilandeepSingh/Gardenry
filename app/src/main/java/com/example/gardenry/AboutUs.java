package com.example.gardenry;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class AboutUs extends AppCompatActivity {

    ListView groupList;
    AboutUsAdapter aboutUsAdapter;
    ArrayList<GroupMember> groupMembers;
    //FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("About Us"); //getTitle();

        groupMembers = new ArrayList<GroupMember>();
        groupMembers.add(new GroupMember(R.drawable.milandeep_singh, "Milandeep Singh", "milandeepsinghbaddon@gmail.com"));
        groupMembers.add(new GroupMember(R.drawable.karthik_reddy, "Karthik Reddy", "iamkarthikreddy@gmail.com"));
        groupMembers.add(new GroupMember(R.drawable.aryan_makol, "Aryan Makol", "makolvasu@gmail.com"));
        groupMembers.add(new GroupMember(R.drawable.pravin_singh, "Pravin Singh", "isingghhh@gmail.com"));
        groupMembers.add(new GroupMember(R.drawable.anuj_kumar_gupta, "Anuj Kumar Gupta", "anujgupta5686@gmail.com"));
        groupMembers.add(new GroupMember(R.drawable.rohit, "Rohit", "Krohitreddy33@gmail.com"));


        groupList = findViewById(R.id.ll_group_list);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override4
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                startActivity(new Intent(getApplicationContext(), NewPlant.class));
//                //recreate();
//                //Intent intent = getIntent();
//                plants = myDbHelper.getAllPlants();
//                myRecyclerViewAdapter.notifyDataSetChanged();
//                //recreate();
//            }
//        });



        aboutUsAdapter = new AboutUsAdapter(this, groupMembers);
        groupList.setAdapter(aboutUsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_homepage_about_us) {
            startActivity(new Intent(getApplicationContext(), AllPlants.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}