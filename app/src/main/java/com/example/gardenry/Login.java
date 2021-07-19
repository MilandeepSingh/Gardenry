package com.example.gardenry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
    EditText email, password;
    Button sign;
    TextView reg;
    private ImageButton sign_in_1, sign_out_1;
    private int RC_SIGN_IN=100;
    private GoogleSignInClient mGooglesignin;
    private String TAG = "Login";
    FirebaseAuth mauth;

    void account() {
        sign_out_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGooglesignin.signOut();
                Toast.makeText(Login.this, "You are Logged out", Toast.LENGTH_SHORT).show();
                sign_out_1.setVisibility(View.INVISIBLE);

            }
        });
    }

    private void signIn() {
        Intent signinIntent = mGooglesignin.getSignInIntent();
        startActivityForResult(signinIntent, RC_SIGN_IN);


        //Toast.makeText(getApplicationContext(), "Successfully signed in", Toast.LENGTH_SHORT).show();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(getApplicationContext(), "Successfully signed in_", Toast.LENGTH_SHORT).show();

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            handleSignInResult(task);

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acco = GoogleSignIn.getLastSignedInAccount(this);
            if (acco != null) {
                String personName = acco.getDisplayName();
                String personEmail = acco.getEmail();
                //Uri personphoto=acco.getPhotoUrl();
                email.setText(personEmail);
                LOGGED_USER.email=personEmail;
                Toast.makeText(Login.this, personName +"\n"+ personEmail,Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        catch (ApiException e) {
//            Toast.makeText(Login.this, "Signed In failed", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();

        }
    }



    private FirebaseAuth auth_1;

    void log() {
        String e_mail = email.getText().toString();
        String Se = password.getText().toString();
        auth_1.signInWithEmailAndPassword(e_mail, Se).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(Login.this, "You are logged in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGooglesignin = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        sign_in_1 = findViewById(R.id.sign_in);
        mauth = FirebaseAuth.getInstance();
        sign_out_1 = findViewById(R.id.sign_out);
        sign_in_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            };
        });
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        sign = findViewById(R.id.cirLoginButton);
        reg = findViewById(R.id.reg_1);
        account();

        auth_1 = FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);


            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e_mail = email.getText().toString();
                String Se = password.getText().toString();
                if (email.getText().toString().isEmpty()) {
                    email.setError("Fill Your email!");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Fill Your password!");
                } else {
                    //Toast.makeText(Login.this, e_mail + "\n" + Se, Toast.LENGTH_SHORT).show();
                    LOGGED_USER.email=e_mail;
                    log();


                }

            }
        });

    }
    public void onLoginClick (View view){
        startActivity(new Intent(getApplicationContext(), Register.class));
    }
}

/*
package com.example.gardenry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
    EditText email, password;
    Button sign;
    TextView reg;
    private ImageButton sign_in_1, sign_out_1;
    private int RC_SIGN_IN;
    private GoogleSignInClient mGooglesignin;
    private String TAG = "Login";
    FirebaseAuth mauth;

    void account() {

        sign_in_1 = findViewById(R.id.sign_in);
        mauth = FirebaseAuth.getInstance();
        sign_out_1 = findViewById(R.id.sign_out);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGooglesignin = GoogleSignIn.getClient(this, gso);
        sign_in_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                //Toast.makeText(getApplicationContext(), "Successfully signed in_", Toast.LENGTH_SHORT).show();

            }
        });
        sign_out_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGooglesignin.signOut();
                //Toast.makeText(Login.this, "You are Logged out", Toast.LENGTH_SHORT).show();
                sign_out_1.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void signIn() {
        Intent signinIntent = mGooglesignin.getSignInIntent();
        startActivityForResult(signinIntent, RC_SIGN_IN);
        //Toast.makeText(getApplicationContext(), "Successfully signed in", Toast.LENGTH_SHORT).show();
    }

    protected void onActivity(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(Login.this, "Signed In successfully", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(Login.this, "Signed In failed", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mauth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Successful", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mauth.getCurrentUser();
                    updateUI(user);
                } else {
                    Toast.makeText(Login.this, "Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser fUser) {
        sign_out_1.setVisibility(View.VISIBLE);
        GoogleSignInAccount acco = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acco != null) {
            String personName = acco.getDisplayName();
            String personEmail = acco.getEmail();

            Toast.makeText(Login.this, personName +"\n"+ personEmail,Toast.LENGTH_SHORT).show();
        }
    }


    private FirebaseAuth auth_1;

    void log() {
        String e_mail = email.getText().toString();
        String Se = password.getText().toString();
        auth_1.signInWithEmailAndPassword(e_mail, Se).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(Login.this, "You are logged in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        sign = findViewById(R.id.cirLoginButton);
        reg = findViewById(R.id.reg_1);
        account();

        auth_1 = FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);


            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e_mail = email.getText().toString();
                String Se = password.getText().toString();
                if (email.getText().toString().isEmpty()) {
                    email.setError("Fill Your email!");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Fill Your password!");
                } else {
                    //Toast.makeText(Login.this, e_mail + "\n" + Se, Toast.LENGTH_SHORT).show();
                    LOGGED_USER.email=e_mail;
                    log();


                }

            }
        });

    }
        public void onLoginClick (View view){
            startActivity(new Intent(getApplicationContext(), Register.class));
    }
}*/
