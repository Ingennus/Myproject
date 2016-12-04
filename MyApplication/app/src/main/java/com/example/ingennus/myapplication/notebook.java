package com.example.ingennus.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class notebook extends AppCompatActivity implements View.OnClickListener {

    private Button textbtn,imgbtn,videobtn;
    private ListView lv;
    private Intent i;
    private MyAdapter adapter;
    private notedb notedb;
    private SQLiteDatabase dbReader;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);
        initView();
    }

    public void initView(){
        lv = (ListView) findViewById(R.id.list);
        textbtn = (Button) findViewById(R.id.text);
        imgbtn = (Button) findViewById(R.id.img);
        videobtn = (Button) findViewById(R.id.video);
        textbtn.setOnClickListener(this);
        imgbtn.setOnClickListener(this);
        videobtn.setOnClickListener(this);
        notedb = new notedb(this);
        dbReader = notedb.getReadableDatabase();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent i = new Intent(notebook.this,SelectAct.class);
                i.putExtra(notedb.ID,cursor.getInt(cursor.getColumnIndex(notedb.ID)));
                i.putExtra(notedb.CONTENT,cursor.getString(cursor.getColumnIndex(notedb.CONTENT)));
                i.putExtra(notedb.TIME,cursor.getString(cursor.getColumnIndex(notedb.TIME)));
                i.putExtra(notedb.PATH,cursor.getString(cursor.getColumnIndex(notedb.PATH)));
                i.putExtra(notedb.VIDEO,cursor.getString(cursor.getColumnIndex(notedb.VIDEO)));
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View v) {
        i = new Intent(this,AddContent.class);
        switch (v.getId()) {
            case R.id.text:
                i.putExtra("flag","1");
                startActivity(i);
                break;

            case R.id.img:
                i.putExtra("flag","2");
                startActivity(i);
                break;

            case R.id.video:
                i.putExtra("flag","3");
                startActivity(i);
                break;
        }
    }
    public void selectDB() {
        Cursor cursor = dbReader.query(notedb.TABLE_NAME, null, null, null, null, null, null);
        adapter = new MyAdapter(this, cursor);
        lv.setAdapter(adapter);
    }
    @Override
    protected void onResume(){
        super.onResume();
        selectDB();
    }

}

