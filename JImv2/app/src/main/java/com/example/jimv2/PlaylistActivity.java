package com.example.jimv2;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity  {

    private TextView tvtitle;
    private TextView tvdescription;
    private ImageView img;

    //listview variables
    private ArrayList<Song> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songList;

    private ArrayList<PlayListModel> mockData;


    //ListView myListView ;
    //String [] PopTitles;

    //String [] PArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);


        tvtitle = (TextView) findViewById(R.id.tvTitle);
        tvdescription = (TextView) findViewById(R.id.tvDescription);
        img = (ImageView) findViewById(R.id.playlistThumbnail);

        //receive data from the adapter to display in the activity
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        int Thumbnail = intent.getExtras().getInt("Thumbnail");




        //setting values of the playlist info in the new page

        tvtitle.setText(Title);
        tvdescription.setText(Description);
        img.setImageResource(Thumbnail);




        //setting listview in this page
         songList = (ListView) findViewById(R.id.songList);


        mockData=new ArrayList<>();

        final ArrayList<Song> pop=new ArrayList<>();
        pop.add(new Song("Song1 pop","test",1,1));
        pop.add(new Song("Song2 pop","test",1,1));
        pop.add(new Song("Song3 pop","test",1,1));
        mockData.add(new PlayListModel(pop));

        ArrayList<Song> dance=new ArrayList<>();
        dance.add(new Song("Lonely","Alan Walker,Steve Aoki",R.raw.alan_walker_steve_aoki_lonely,R.drawable.lonely_alan));
        dance.add(new Song("Diamond Heart","Alan Walker,Sophia Somajo",R.raw.alan_walker_diamond_heart_ft_sophia_somajo,R.drawable.alanwalkerdiamondheart));
        dance.add(new Song("Routine","Alan Walker,David Whistle",R.raw.alan_walker_x_david_whistle_routine,R.drawable.routinealanwalkerpic));
        mockData.add(new PlayListModel(dance));


        ArrayList<Song> jazz=new ArrayList<>();
        jazz.add(new Song("Song1 jazz","test",1,1));
        jazz.add(new Song("Song2 jazz","test",1,1));
        jazz.add(new Song("Song3 jazz","test",1,1));

        mockData.add(new PlayListModel(jazz));

        ArrayList<Song> hiphop=new ArrayList<>();
        hiphop.add(new Song("Song1 hiphop","test",1,1));
        hiphop.add(new Song("Song2 hiphop","test",1,1));
        hiphop.add(new Song("Song3 hiphop","test",1,1));
        mockData.add(new PlayListModel(hiphop));

        ArrayList<Song> rap=new ArrayList<>();
        rap.add(new Song("Song1 rap","test",1,1));
        rap.add(new Song("Song2 rap","test",1,1));
        rap.add(new Song("Song3 rap","test",1,1));
        mockData.add(new PlayListModel(rap));

        ArrayList<Song> latin=new ArrayList<>();
        latin.add(new Song("Song1 latin","test",1,1));
        latin.add(new Song("Song2 latin","test",1,1));
        latin.add(new Song("Song3 latin","test",1,1));

        mockData.add(new PlayListModel(latin));


        final PlayListModel playListModel = (PlayListModel) mockData.get(intent.getExtras().getInt("playlist"));
        //Toast.makeText(getApplicationContext(),playListModel.songs.get(0).getName(),Toast.LENGTH_SHORT).show();


        adapter = new CustomMusicAdapter(this,R.layout.custom_music_item,playListModel.songs);
        songList.setAdapter(adapter);

        //pass pictures of songs to the next page

        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MusicPlayerActivity.class);
                intent.putExtra("Picture",playListModel.songs.get(i).getPicture());
                intent.putExtra("SongTitle",playListModel.songs.get(i).getName());
                intent.putExtra("SongArtist",playListModel.songs.get(i).getArtist());
               //how to access all songs in the arraylist of the arraylist??
                intent.putExtra("songs",playListModel.songs);
                intent.putExtra("pos",i);

                startActivity(intent);
            }
        });


        //add the same list in every playlist
        /* arrayList = new ArrayList<>();
         arrayList.add(new Song("Lonely","Alan Walker,Steve Aoki",R.raw.alan_walker_steve_aoki_lonely));
         arrayList.add(new Song("Diamond Heart","Alan Walker,Sophia Somajo",R.raw.alan_walker_diamond_heart_ft_sophia_somajo));
         arrayList.add(new Song("Routine","Alan Walker,David Whistle",R.raw.alan_walker_x_david_whistle_routine));
         arrayList.add(new Song("Song Title4","Artist4",4));
         arrayList.add(new Song("Song Title5","Artist5",4));
         arrayList.add(new Song("Song Title6","Artist6",4));
         arrayList.add(new Song("Song Title7","Artist7",4));
         arrayList.add(new Song("Song Title8","Artist8",4));
         arrayList.add(new Song("Song Title9","Artist9",4));
         arrayList.add(new Song("Song Title10","Artist10",4));
         arrayList.add(new Song("Song Title11","Artist11",4));
         arrayList.add(new Song("Song Title12","Artist12",4));

*/

    }
}
