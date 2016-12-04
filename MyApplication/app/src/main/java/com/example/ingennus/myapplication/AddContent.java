package com.example.ingennus.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Ingennus on 2016/12/2.
 */

public class AddContent extends Activity implements View.OnClickListener{

    private String val;
    private Button savebtn,deletebtn;
    private EditText extext;
    private ImageView c_img;
    private VideoView v_video;
    private notedb notedb;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super .onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);
        val = getIntent().getStringExtra("flag");
        savebtn = (Button)findViewById(R.id.save);
        deletebtn = (Button)findViewById(R.id.delete);
        extext = (EditText)findViewById(R.id.extext);
        c_img = (ImageView)findViewById(R.id.c_img);
        v_video = (VideoView)findViewById(R.id.c_video);
        savebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
        notedb = new notedb(this);
        dbWriter = notedb.getWritableDatabase();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.save:
                addDB();
                finish();
                break;
            case R.id.delete:
                finish();
                break;
        }
    }

    public void addDB(){
        ContentValues cv = new ContentValues();
        cv.put (notedb.CONTENT,extext.getText().toString());
        cv.put(notedb.TIME,getTime());
        dbWriter.insert(notedb.TABLE_NAME,null,cv);
    }

    private String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str =format.format(date);
        return str;
    }
}
