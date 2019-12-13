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
        lstType.add(new SongTypes("Dance Music","Dance","Music composed specifically to facilitate or accompany dancing",R.drawable.djdance));
        lstType.add(new SongTypes("Pop Music","Pop","A consistent and noticeable rhythmic elements",R.drawable.poporiginal));
        lstType.add(new SongTypes("Jazz Music","Blues","A distinctive tone colors & performance techniques",R.drawable.jazz2));
        lstType.add(new SongTypes("HipHop Music","HipHop","A stylized rhythmic music",R.drawable.hiphoporiginal));
        lstType.add(new SongTypes("Rap Music","Rap","A rapid, slangy and boastful rhyming pattern intoned by a vocalist",R.drawable.raporiginal));
        lstType.add(new SongTypes("Latin Music","Latin","A wide variety of styles with highly syncretic nature",R.drawable.latinoriginal2));



        RecyclerView MyRv = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstType);
        MyRv.setLayoutManager(new GridLayoutManager(this,2));
        MyRv.setAdapter(myAdapter);







    }
}
