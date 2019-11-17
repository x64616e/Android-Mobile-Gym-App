package com.example.jimv2;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class WorkoutActivity extends SingleFragmentActivity {
    private static final String TAG = "WorkoutActivity";

    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment: called.");
        return new WorkoutActivityFragment();
    }

    }

