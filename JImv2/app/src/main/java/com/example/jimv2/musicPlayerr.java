package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class musicPlayerr extends AppCompatActivity {

    List<SongTypes> lstType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lstType = new ArrayList<>();
        lstType.add(new SongTypes("Beast Mode","Beast Mode","Unleash the Beast.",R.drawable.beast));
        lstType.add(new SongTypes("Do You Even Lift Bro","Do You Even Lift Bro","A consistent and noticeable rhythmic elements",R.drawable.shrekss));
        lstType.add(new SongTypes("Jazzercise","Jazzercise","A distinctive tone colors & performance techniques",R.drawable.jazzercise));
        //lstType.add(new SongTypes("HipHop Music","HipHop","A stylized rhythmic music",R.drawable.hiphoporiginal));
        //lstType.add(new SongTypes("Rap Music","Rap","A rapid, slangy and boastful rhyming pattern intoned by a vocalist",R.drawable.raporiginal));
        lstType.add(new SongTypes("Redpill Mix","Latin","A wide variety of styles with highly syncretic waves",R.drawable.dope));



        RecyclerView MyRv = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstType);
        MyRv.setLayoutManager(new GridLayoutManager(this,2));
        MyRv.setAdapter(myAdapter);







    }
}
