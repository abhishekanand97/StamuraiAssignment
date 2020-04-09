package com.anand.assignment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.anand.assignment.common.Common;


public class LandingPage extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);


        final SeekBar seekBar = (SeekBar)findViewById(R.id.sbar);


        Button nxt=(Button)findViewById(R.id.nxt);

        Common.setRating(Common.getMin());
        seekBar.setMax(10);
        seekBar.setProgress(0);


        final TextView tv = findViewById(R.id.txtVw2p);
        final TextView sc = findViewById(R.id.scale);
        tv.setText(Common.getRating()+"");
        sc.setText(sc.getText().toString()+Common.getMin()+" to "+Common.getMax());

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), MainActivity.class);
                seekBar.setProgress(Integer.parseInt(tv.getText().toString()));
                Common.setRating(seekBar.getProgress());


                startActivity(intent);
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // When seek bar progress is changed, change image alpha value.

                double mnms =(1.0*progress)/10;

                int interval=(Common.getMax()-Common.getMin());
                int val=Common.getMin()+(int)Math.round(mnms*interval);

                Common.setRating(val);
                tv.setText("Rating: "+Common.getRating());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // When seek bar start slip.
                //tv.setText("Start Slip.");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // When seek bar stop slip.
                //tv.setText("Stop Slip.");
            }
        });



    }

}
