package com.example.gardenry;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        //findViewById(R.id.navigation_my_plants).callOnClick();





        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_explore, R.id.navigation_home, R.id.navigation_my_plants)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController((BottomNavigationView) findViewById(R.id.nav_view), navController);

        String fragmentToDisplay = getIntent().getStringExtra("fragmentToDisplay");
        if(fragmentToDisplay!=null && fragmentToDisplay.equals("MyPlantsFragment")){
            findViewById(R.id.navigation_my_plants).performClick();
            //findViewById(R.id.navigation_my_plants).performClick();
            //FragmentTransaction to show Fragment A
                /*Fragment fragment = new Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.nav_host_fragment_activity_main, Gardenry.MyPlantsFragment);
                transaction.commit();
                break;*/
            /*case FirstActivity.FRAGMENTB:
                //FragmentTransaction to show Fragment B
                break;
            case FirstActivity.FRAGMENTC:
                //FragmentTransaction to show Fragment C
                break;*/
        }
    }

    int c=2;

    @Override
    public void onBackPressed() {
        c--;
        if(c==0){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);}
        else Toast.makeText(getApplicationContext(),"Press again to exit!", Toast.LENGTH_SHORT).show();
    }
}