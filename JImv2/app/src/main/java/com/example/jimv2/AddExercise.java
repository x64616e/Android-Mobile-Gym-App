package com.example.jimv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;


public abstract class AddExercise extends SingleFragmentActivity {
    private static final String TAG = "AddExercise";

    public static Intent newIntent(Context packageContext, long date) {
        Intent intent = new Intent(packageContext, AddExercise.class);
        intent.putExtra("com.example.jimv2.PASSDATE", date);
        return intent;
    }

    @Override
    public void setArguments(Bundle bundle){

    }
    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment: called.");
        return new AddExerciseFragment();
    }
}

