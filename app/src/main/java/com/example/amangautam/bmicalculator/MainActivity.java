package com.example.amangautam.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    EditText height, weight;
    RadioGroup rgHeight, rgWeight;
    RadioButton rbHeight, rbWeight;
    Button button;
    Toolbar toolbar;
    int rh, rw;
    double bmi, dh, dw;
    String sh, sw, h, w;
    TextView th, tw, tb, ts;
    DecimalFormat precision = new DecimalFormat("#.##");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        th = findViewById(R.id.text1);
        tw = findViewById(R.id.text2);
        tb = findViewById(R.id.text3);
        button = findViewById(R.id.btnClick);
        ts = findViewById(R.id.text4);
        rgHeight = findViewById(R.id.radio_grp_h);
        rgWeight = findViewById(R.id.radio_grp_w);


        rgHeight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rh = rgHeight.getCheckedRadioButtonId();
                rbHeight = findViewById(rh);
                sh = rbHeight.getText().toString();
            }
        });

        rgWeight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rw = rgWeight.getCheckedRadioButtonId();
                rbWeight = findViewById(rw);
                sw = rbWeight.getText().toString();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h = height.getText().toString();
                w = weight.getText().toString();

                 if((w == null || w.equals(""))&& (h==null || h.equals(""))) {
                     Toast.makeText(MainActivity.this, "PLEASE ENTER YOUR HEIGHT AND WEIGHT", Toast.LENGTH_SHORT).show();
                 } else if ((w == null || w.equals("") )&& (h!=null || !h.equals(""))){
                         Toast.makeText(MainActivity.this, "PLEASE ENTER YOUR WEIGHT", Toast.LENGTH_SHORT).show();
                     }
                    else if((h==null || h.equals("") )&& (w!=null || !w.equals(""))){
                         Toast.makeText(MainActivity.this, "PLEASE ENTER YOUR HEIGHT", Toast.LENGTH_SHORT).show();
                     }

                 else {
                     dh = Double.parseDouble(h);
                     dw = Double.parseDouble(w);
                     if (sh == getString(R.string.cm) && sw == getString(R.string.kg)) {
                         bmi = (10000 * dw) / (dh * dh);
                     } else if (sh == getString(R.string.feets) && sw == getString(R.string.lb)) {
                         bmi = (4.88 * dw) / (dh * dh);
                     } else if (sh == getString(R.string.cm) && sw == getString(R.string.lb)) {
                         bmi = (10000 * dw) / (2.20 * dh * dh);
                     } else {
                         bmi = (10.76 * dw) / (dh * dh);
                     }
                     th.setText("Your Height is : " + h + sh);
                     tw.setText("Your Weight is : " + w + sw);
                     tb.setText("Your BMI Value is : " + precision.format(bmi));

                     if (bmi < 18.5) {
                         ts.setText("YOU ARE UNDER WEIGHT");
                     } else if (bmi > 18.5 && bmi < 24.9) {
                         ts.setText("YOU ARE HEALTHY");
                     } else if (bmi > 25 && bmi < 29.9) {
                         ts.setText("YOU ARE OVER-WEIGHT");
                     } else if (bmi > 30 && bmi < 39.9) {
                         ts.setText("YOU ARE OBESSED");
                     } else if (bmi>39.9){
                         ts.setText("YOU ARE MORBIDLY OBESSED");
                     }
                     else{
                         ts.setText("INVALID HEIGHT OR WEIGHT");
                     }
                 }

                height.setText(null);
                 weight.setText(null);
                 rbHeight.setChecked(false);
                 rbWeight.setChecked(false);


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chart:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.desc:
                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                break;
            case R.id.bio :
                startActivity(new Intent(MainActivity.this, FourthActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
      builder.setIcon(R.drawable.logo);
      builder.setTitle("   EXIT ? ");
      builder.setMessage("  EXIST THIS APP");
      builder.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
          MainActivity.this.finish();
          }
      });
        builder.setNegativeButton(" CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
