package com.example.bajao;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.os.Bundle;
import android.widget.ListView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView myListViewForSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListViewForSongs = (ListView) findViewById(R.id.mySongListView);

        runtimePermission();
    }

    // for permission to read Music from the Phone

    public void runtimePermission(){
        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                    token.continuePermissionRequest();
            }

        }).check();
    }

    // Function finds All songs from internal memory storage
    // ""RECURSIVELY""

    public ArrayList<File> findSong(File file){
        ArrayList<File> arrayList = new ArrayList<File>();

        File[] files = file.listFiles();

        for( File singleFile : files ){
            if( singleFile.isDirectory() && !singleFile.isHidden() ){
                arrayList.addAll(findSong(singleFile));
            }

            else{
               if(singleFile.getName().endsWith(".mp3") ||singleFile.getName().endsWith(".wav")) {
                   arrayList.add(singleFile);
               }
            }
        }

        return arrayList;
    }

    public void display(){

    }

}
