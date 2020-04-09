package com.anand.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anand.assignment.common.Common;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Common.setMin(0);
        Common.setMax(9);

        EditText emn=(EditText)findViewById(R.id.mn);
        EditText emx=(EditText)findViewById(R.id.mx);

        emn.setText(""+Common.getMin());
        emx.setText(""+Common.getMax());


        final Button nxt=(Button)findViewById(R.id.rtg);
        Button apl=(Button)findViewById(R.id.apply);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), LandingPage.class);
                startActivity(intent);
            }
        });

        apl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText dmn=(EditText)findViewById(R.id.mn);
                EditText dmx=(EditText)findViewById(R.id.mx);
                int mnm=Integer.parseInt(dmn.getText().toString());

                int mxm=Integer.parseInt(dmx.getText().toString());

                if(mnm>=mxm) {
                    Toast.makeText(getApplicationContext(), "Invalid Input! Minimum must be less than Maximum", Toast.LENGTH_LONG).show();

                    dmn.setText(""+Common.getMin()+"");
                    dmx.setText(""+Common.getMax()+"");


                    return;
                }

                {
                    if(mnm>=0 && mnm<=8)
                    Common.setMin(mnm);
                else {
                    dmn.setText(""+Common.getMin()+"");
                    Toast.makeText(getApplicationContext(), "Please Input Minimum from 0-8", Toast.LENGTH_SHORT).show();
                 }
                }
                if(mxm>=1 && mxm<=9)
                    Common.setMax(mxm);
                else
                {   dmx.setText(""+Common.getMax()+"");
                    Toast.makeText(getApplicationContext(),"Please Input Maximum from 1-9",Toast.LENGTH_SHORT).show();
                }

                nxt.setText("Rating "+Common.getMin()+"-"+Common.getMax());

            }
        });
    }
}
