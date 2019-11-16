package com.example.jimv2;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class WorkoutActivity extends SingleFragmentWithBarActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "WorkoutActivity";

    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "createFragment: called.");
        return new WorkoutActivityFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //case R.id.nav_calendar:
            //    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
            //            new MessageFragment()).commit();
            //    break;
            case R.id.nav_workout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WorkoutActivityFragment()).commit();
                break;
            //case R.id.nav_profile:
            //   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
            //           new ProfileFragment()).commit();
            //   break;
        }
        return true;
    }
}

