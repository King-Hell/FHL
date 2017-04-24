package com.sdu.litong.fhl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;


import android.view.*;
import android.widget.*;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    private EditText editText;
    private ListView listView;
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listview);

        adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, data);

        listView.setAdapter(adapter);
    }

    public void scan(String target) throws IOException {


        InputStream context = getApplicationContext().getAssets().open("data.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(context, "GBK"));
        String scan;
        while ((scan = br.readLine()) != null) {
            if (!scan.equals("") && scan.charAt(0) > 57) {
                if (scan.contains(target)) {
                    data.add(scan);
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        try {
            data.clear();
            scan(editText.getText().toString());
            adapter.notifyDataSetChanged();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "未找到文件", Toast.LENGTH_SHORT).show();
        }

    }
}
