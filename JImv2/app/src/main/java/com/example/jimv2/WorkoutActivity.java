package com.example.jimv2;

import androidx.fragment.app.Fragment;

public class WorkoutActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new WorkoutActivityFragment();
    }
}

