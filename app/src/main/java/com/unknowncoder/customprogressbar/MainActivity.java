package com.unknowncoder.customprogressbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.showDialog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, FilePickerActivity.class);
                intent.putExtra(FilePickerActivity.CONFIGS,new Configurations.Builder()
                .setCheckPermission(true)
                .setShowFiles(true)
                .setShowAudios(false)
                .setShowImages(false)
                .setShowVideos(false)
                .setSuffixes("txt","doc","pdf","docx")
                .setSkipZeroSizeFiles(true)
                .build());
                startActivityForResult(intent,101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data!=null){
            ArrayList<MediaFile> mediaFiles=data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
            String path=mediaFiles.get(0).getPath();
            Toast.makeText(MainActivity.this,"Path :- "+path,Toast.LENGTH_SHORT).show();;
        }
    }

    private void alertBox(){
        AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).create();
        View view1= LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_progress_dialog
                ,null,false);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
}