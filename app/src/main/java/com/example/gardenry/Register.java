package com.example.gardenry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {
EditText username,password,confirm_password,Email;
    Button click;
    FirebaseFirestore f2;
    private FirebaseAuth auth;
    void fix(){
        String name=username.getText().toString();
        String secure=password.getText().toString();
        String secure2=confirm_password.getText().toString();
        String mail=Email.getText().toString();
        auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(mail,secure).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    void authenticat(){
        String name=username.getText().toString();
        String secure=password.getText().toString();
        String secure2=confirm_password.getText().toString();
        String mail=Email.getText().toString();
        auth=FirebaseAuth.getInstance();
        f2=FirebaseFirestore.getInstance();
        Client details= new Client(name,secure,mail);
        f2.collection("Users").document(mail).set(details).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                //Toast.makeText(getApplicationContext(),"Account created Successfully",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(getApplicationContext(),"Failure Occured",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.editTextName);
        password=findViewById(R.id.editTextPassword_1);
        confirm_password=findViewById(R.id.editTextConfirmPassword);
        Email=findViewById(R.id.editTextEmail_1);
        click=findViewById(R.id.cirRegisterButton);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String secure=password.getText().toString();
                String secure2=confirm_password.getText().toString();
                String mail=Email.getText().toString();
                if(username.getText().toString().isEmpty())
                {
                    username.setError("Fill Your username!");
                }
                else if(password.getText().toString().isEmpty()){
                    password.setError("Fill Your password!");
                }
                else if(confirm_password.getText().toString().isEmpty()){
                    confirm_password.setError("Confirm password!");
                }
                else if(Email.getText().toString().isEmpty()){
                    Email.setError("Fill Your Email!");
                }
                else if(!(confirm_password.getText().toString().equals(password.getText().toString()))){
                    confirm_password.setError("Passwords are not same!");
                }
                else{
                    //Toast.makeText(Register.this,name+"\n"+secure+"\n"+mail,Toast.LENGTH_SHORT).show();
                    fix();
                    authenticat();
                    Intent intent = new Intent(Register.this,Login.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void onRegisterClick(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
    }
}