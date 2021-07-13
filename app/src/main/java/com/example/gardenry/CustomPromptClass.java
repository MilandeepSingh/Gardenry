package com.example.gardenry;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.gardenry.ui.myplants.MyPlantsFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import static android.content.ContentValues.TAG;

public class CustomPromptClass extends Dialog implements
        View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button yes, no;
    String name;
    FirebaseFirestore firebaseFirestore;
    int position;


    public CustomPromptClass(Context a, String name, int position) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.name = name;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_prompt);
        yes = (Button) findViewById(R.id.btn_yes_custom_prompt);
        no = (Button) findViewById(R.id.btn_no_custom_prompt);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes_custom_prompt:
                //((Activity)c).finish();
                    firebaseFirestore.collection("Users").document("Irish")
                            .collection("My Plants").document(name)
                            .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "Deleted successfully");
                            MyPlantsFragment.myPlantsAdapter.plants.remove(position);
                            MyPlantsFragment.myPlantsAdapter.notifyItemRemoved(position);
                            MyPlantsFragment.myPlantsAdapter.notifyItemRangeChanged(position, MyPlantsFragment.myPlantsAdapter.getItemCount());
                            dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Log.d(TAG, "Error deleting: "+e.getMessage());
                        }
                    });




                break;
            case R.id.btn_no_custom_prompt:
                dismiss();
                break;
//
//            case R.id.imgbtn_del_custom_dialog:



            default:
                break;
        }
        //dismiss();
    }

}