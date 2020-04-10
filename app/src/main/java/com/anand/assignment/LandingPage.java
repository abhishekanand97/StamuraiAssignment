package com.anand.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anand.assignment.common.Common;

import java.text.DateFormat;
import java.util.Date;


public class LandingPage extends AppCompatActivity  {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);


        final SeekBar seekBar = (SeekBar)findViewById(R.id.sbar);


        Button nxt=(Button)findViewById(R.id.nxt);
        Button hstry=(Button)findViewById(R.id.btnhs);
        Common.setRating(Common.getMin());
        seekBar.setMax(10);
        seekBar.setProgress(0);


        final TextView tv = findViewById(R.id.txtVw2p);
        final TextView sc = findViewById(R.id.scale);
        tv.setText(Common.getRating()+"");
        sc.setText(sc.getText().toString()+" "+Common.getMin()+" to "+Common.getMax());
          db=new DatabaseHelper(this);

        hstry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LandingPage.this, History2.class);
                startActivity(intent);
            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data="On a scale of "+Common.getMin()+" to "+ Common.getMax()+" it was rated "+ Common.getRating()+ " at "+ DateFormat.getDateTimeInstance().format(new Date());

                if(db.insertData(data)==true)
                {
                    Toast.makeText(getApplicationContext(),"Rating Recorded Successfully", Toast.LENGTH_LONG).show();
                }

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                double mnms =(1.0*progress)/10;

                int interval=(Common.getMax()-Common.getMin());
                int val=Common.getMin()+(int)Math.round(mnms*interval);

                Common.setRating(val);
                tv.setText("Rating: "+Common.getRating());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

}
