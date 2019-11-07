package com.example.jimv2;

import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class WorkoutActivity extends SingleFragmentActivity {
    private static final String TAG = "WorkoutActivity";
    private TextView date;
    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment: called.");
        date  = (TextView) findViewById(R.id.currentDate);

        return new WorkoutActivityFragment();
    }
}

