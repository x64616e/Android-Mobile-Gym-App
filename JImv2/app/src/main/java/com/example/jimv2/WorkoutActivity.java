package com.example.jimv2;

import android.util.Log;

import androidx.fragment.app.Fragment;

public class WorkoutActivity extends SingleFragmentActivity {
    private static final String TAG = "WorkoutActivity";

    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment: called.");
        return new WorkoutActivityFragment();
    }
}

