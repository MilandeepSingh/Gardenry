package com.example.gardenry.ui.myplants;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyPlantsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyPlantsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications(my plants) fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}