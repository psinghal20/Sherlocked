package com.example.psing.sherlocked;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.psing.sherlocked.db.databasehelper;

import java.util.ArrayList;

public class SeasonActivity extends AppCompatActivity {
    databasehelper mydb;
    ListView lvseason;
    EditText etinfo;
    Button binsert;
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);
        lvseason=(ListView)findViewById(R.id.lvsesason);
        binsert=(Button)findViewById(R.id.binsert);
        etinfo=(EditText)findViewById(R.id.etinfo);
        mydb=new databasehelper(this);
        adddata();
        updateUI();
    }
    public void adddata(){
        binsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinserted=mydb.insertdata(etinfo.getText().toString());
                if(isinserted==true)
                    Toast.makeText(SeasonActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SeasonActivity.this,"Data NOT Inserted",Toast.LENGTH_LONG).show();
                updateUI();
            }
        });
    }
    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor cursor = db.query(mydb.tablename, new String[]{databasehelper.col1,databasehelper.col2}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(databasehelper.col2);
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_seasonlist,
                    R.id.task_title,
                    taskList);
            lvseason.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }
}
