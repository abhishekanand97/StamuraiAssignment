package com.anand.assignment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class History2 extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView ratingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);


            db=new DatabaseHelper(this);
            listItem=new ArrayList<>();
            ratingList=findViewById(R.id.rating_list);

            viewData();
        Button hm=findViewById(R.id.home);
        hm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(History2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /*ratingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text=ratingList.getItemAtPosition(i).toString();
                Toast.makeText(History.this,""+text,Toast.LENGTH_SHORT ).show();
            }
        });*/

    }

        public void viewData()
        {
            Cursor cursor=db.viewData();
            if(cursor.getCount()==0)
                Toast.makeText(this,"No data to show",Toast.LENGTH_SHORT).show();
            else
            {
                while (cursor.moveToNext()){
                    listItem.add(cursor.getString(0));
                }
                adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listItem);
                ratingList.setAdapter(adapter);
            }

        }
}




