package com.example.gardenry;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.gardenry.ui.myplants.MyPlantsFragment;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomDialogClass extends Dialog implements
        View.OnClickListener {

    public Context c;
    public Dialog d;
    public Button yes, no;
    String name;
    int qty;
    TextView txtName;
    ImageButton imgBtnDel;
    ImageButton imgBtnInfo;
    AppCompatEditText edtQty;
    FirebaseFirestore firebaseFirestore;
    int position;
    int result=0;

    int getResult(){
        return result;
    }

    public CustomDialogClass(Context a, String name, int qty, int position) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.name = name;
        this.qty = qty;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_save_custom_dialog);
        no = (Button) findViewById(R.id.btn_cancel_custom_dialog);
        imgBtnDel = findViewById(R.id.imgbtn_del_custom_dialog);
        imgBtnInfo = findViewById(R.id.imgbtn_info_custom_dialog);
        txtName = findViewById(R.id.txt_name_custom_dialog);
        edtQty = findViewById(R.id.edt_qty_custom_dialog);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        txtName.setText(name);
        edtQty.setText(Integer.toString(qty));
        firebaseFirestore = FirebaseFirestore.getInstance();

        imgBtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, AboutPlant.class);
                intent.putExtra("plant", name);
                c.startActivity(intent);

            }
        });

        imgBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPromptClass cpc = new CustomPromptClass(c, name, position);
                cpc.show();
                dismiss();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_custom_dialog:
                //((Activity)c).finish();
                String strQty = edtQty.getText().toString();
                if(strQty.isEmpty()){
                    Toast.makeText(c, "Please specify quantity!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int qt = Integer.parseInt(strQty);

                if(qt<1)
                Toast.makeText(c, "Invalid quantity...", Toast.LENGTH_SHORT).show();

                else {
                    changeQty(qt);
                }


                break;
            case R.id.btn_cancel_custom_dialog:
                dismiss();
                break;

                /*Not working with image buttons, maybe coz they are inside cardview but, also not working with cardview*/
//            case R.id.imgbtn_del_custom_dialog:
//
//                break;
//
//            case R.id.imgbtn_info_custom_dialog:
//                dismiss();
//                break;

            default:
                break;
        }
        //dismiss();
    }


    void changeQty(int qt){
        firebaseFirestore.collection("Users").document(LOGGED_USER.email)
                .collection("My Plants").document(name)
                .update("qty", qt);
        result=qt;
        MyPlantsFragment.myPlantsAdapter.plants.get(position).setQty(qt);
        MyPlantsFragment.myPlantsAdapter.notifyDataSetChanged();
        dismiss();
    }

}