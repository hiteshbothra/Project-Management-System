package com.example.hiteshb.projectmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GetProjectList extends AppCompatActivity {

    ListView lv;
    List<String> project;
    List<setGetValues> values;
    ListAdapter adapter;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_project_list);
        helper = new DBHelper(this);
        lv = (ListView)findViewById(R.id.projectList);
        project=new ArrayList<String>();
        values = new ArrayList<setGetValues>();
        helper.open();
        //Log.d("getProj1:", "##");
        values =  helper.getAllEntries();
        //Log.d("getProj2:", "**");
        for(setGetValues data: values)
        {
            String temp1=data.getPreF1();
            project.add(temp1);
            String temp2=data.getPreF2();
            project.add(temp2);
            String temp3=data.getPreF3();
            project.add(temp3);
            String temp4=data.getPreF4();
            project.add(temp4);
            String temp5=data.getPreF5();
            project.add(temp5);
        }
        adapter = new ArrayAdapter(this,android.R.layout.activity_list_item,android.R.id.text1,project);
        lv.setAdapter(adapter);
    }
}
