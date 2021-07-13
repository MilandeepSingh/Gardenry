package com.example.gardenry.ui.explore;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.gardenry.AboutUs;
import com.example.gardenry.Composting;
import com.example.gardenry.GardeningTools;
import com.example.gardenry.R;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {

    private ExploreViewModel exploreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        exploreViewModel =
                new ViewModelProvider(this).get(ExploreViewModel.class);


        View root = inflater.inflate(R.layout.fragment_explore, container, false);

        ImageSlider imageSlider = root.findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.lantana, "The mighty lantana", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.hand_pollination, "Hand pollinating cucumbers", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.peacocks, "When nature visits you", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.pumpkins, "When you can't wait to harvest", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.sunbird, "Sunbird peeking at you", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.night, "Ever observed your garden at night", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.compost_on_hand, "Imagine the ecstasy", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.airlayering, "Successful air-layering", ScaleTypes.CENTER_CROP));
//        slideModels.add(new SlideModel("", "3 image"));
//        slideModels.add(new SlideModel("", "4 image"));

        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);


        ImageButton gTools = root.findViewById(R.id.imgbtn_tools_explore);

        gTools.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    gTools.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorItem1End)));
                    gTools.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    gTools.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlurr)));
                    gTools.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorItem1End)));
                    startActivity(new Intent(getContext(), GardeningTools.class));
                }
                return true;
            }
        });

        ImageButton aboutUs = root.findViewById(R.id.imgbtn_about_us_explore);

        aboutUs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    aboutUs.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorItem4End)));
                    aboutUs.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    aboutUs.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlurr)));
                    aboutUs.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorItem4End)));
                    startActivity(new Intent(getContext(), AboutUs.class));
                }
                return true;
            }
        });


        ImageButton composting = root.findViewById(R.id.imgbtn_composting_explore);

        composting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    composting.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorItem2End)));
                    composting.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    composting.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlurr)));
                    composting.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorItem2End)));
                    startActivity(new Intent(getContext(), Composting.class));
                }
                return true;
            }
        });


        //final TextView textView = binding.textDashboard;
        exploreViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            // textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}