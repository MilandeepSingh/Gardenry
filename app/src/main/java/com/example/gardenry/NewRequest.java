package com.example.gardenry;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class NewRequest extends AppCompatActivity {
    AppCompatEditText pltname, sciname;
    Button btnRequest;
    LinearLayout linearLayout;
    FirebaseFirestore fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);
        pltname = findViewById(R.id.edt_name_new_request);
        sciname = findViewById(R.id.edt_desc_new_request);
        btnRequest = findViewById(R.id.btn_add_new_request);
        linearLayout = findViewById(R.id.ll_pbar_add_new_request);
        Toolbar tb = findViewById(R.id.toolbar_new_request);
        setSupportActionBar(tb);
        fb = FirebaseFirestore.getInstance();
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NewRequest.this, "Fetching..", Toast.LENGTH_SHORT).show();
                insertdata();
            }
        });
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.request_menu,menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.A:
                Toast.makeText(this, "A", Toast.LENGTH_SHORT).show();
                openRequestStatus();
                break;
            case R.id.B:
                Toast.makeText(this, "B", Toast.LENGTH_SHORT).show();
               // opendashboard();
                break;
            default:
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    private void openRequestStatus() {
        Intent intent = new Intent(this,RequestStatus.class);
        startActivity(intent);
    }

    private void insertdata() {
        String name = pltname.getText().toString();
        String desc = sciname.getText().toString();

        if(name.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter plant name please!", Toast.LENGTH_SHORT).show();
            return;
        }
        btnRequest.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        Date date = new Date();
        long timeMilli = date.getTime();
        String strDateTimeMillies = Long.toString(timeMilli);
        Request request = new Request(name, desc,strDateTimeMillies);
        fb.collection("Users").document(LOGGED_USER.email)
                .collection("Requests").document(strDateTimeMillies)
                .set(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), RequestStatus.class));
                        btnRequest.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.GONE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });

//        fb.collection("Users").document("Irish")
//                .collection("Requests").document(name)
//                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                Request request1 = documentSnapshot.toObject(Request.class);
//                Toast.makeText(getApplicationContext(), request1.desc, Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), RequestStatus.class));
    }
}