package com.example.jimv2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.ArrayList;

import androidx.annotation.NonNull;

public class FireBaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceExercises;
    private List <ExerciseObject> workout= new ArrayList<>();

    public interface DataStatus{
        void DataisLoaded(List<ExerciseObject> workout,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FireBaseDatabaseHelper(FirebaseDatabase mDatabase) {
        this.mDatabase = FirebaseDatabase.getInstance();
        mReferenceExercises = mDatabase.getReference("exercises");
    }

    public void readExercises(final DataStatus dataStatus){
        mReferenceExercises.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                workout.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                keys.add(keyNode.getKey());
                ExerciseObject exercise = keyNode.getValue(ExerciseObject.class);
                workout.add(exercise);
            }
            dataStatus.DataisLoaded(workout,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
