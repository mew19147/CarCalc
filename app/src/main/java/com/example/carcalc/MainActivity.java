package com.example.carcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.euicc.DownloadableSubscription;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText carval;
    private EditText dpay;
    private EditText apr;
    private RadioGroup rgroup;
    private RadioButton loan;
    private RadioButton lease;
    private SeekBar months;
    private TextView monthshow;
    private Button button;
    private TextView total;
    int p =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carval = findViewById(R.id.carval);
        dpay = findViewById(R.id.dpay);
        apr = findViewById(R.id.apr);
        loan = findViewById(R.id.loan);
        lease = findViewById(R.id.lease);
        rgroup = findViewById(R.id.RadioGroup);
        button = findViewById(R.id.button);
        total = findViewById(R.id.monpay);
        months();


    }

    public void months(){
        months =(SeekBar)findViewById(R.id.monthbar);
        monthshow = (TextView)findViewById(R.id.monthshow);
        monthshow.setText("Months: " + months.getProgress() + "/" + months.getMax());
        months.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        monthshow.setText("Months: " + progress + "/" + months.getMax());
                        Toast.makeText(MainActivity.this,"seekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this,"seekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        monthshow.setText("Months: " + progress_value + "/" + months.getMax());
                        Toast.makeText(MainActivity.this,"seekBar in progress", Toast.LENGTH_LONG).show();
                        p= progress_value;
                    }
                }
        );
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                double pay;
                String a = apr.getText().toString();
                Double APR = Double.parseDouble(a);
                String dp = dpay.getText().toString();
                Double DOW = Double.parseDouble(dp);
                String cv = carval.getText().toString();
                Double CAR = Double.parseDouble(cv);
                if(loan.isChecked()){
                    double L =CAR-DOW;
                    double aprmon= (APR/100)/12;
                    double aprmon2 =(1+aprmon);
                    double p2 = -p;
                    double aprmon3 = Math.pow(aprmon2, p2);
                    double tot = aprmon*L/(1-(aprmon3));
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);
                    String totaltot = df.format(tot);
                    total.setText("$ " + totaltot);
                }
                else if(lease.isChecked()){
                    double L =(CAR/3)-DOW;
                    double aprmon= (APR/100)/12;
                    double aprmon2 =(1+aprmon);
                    double p2 = -p;
                    double aprmon3 = Math.pow(aprmon2, p2);
                    double tot = aprmon*L/(1-(aprmon3));
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);
                    String totaltot = df.format(tot);
                    total.setText("$ " + totaltot);
                }
                else{
                    total.setText(" Select: Lease or Loan");
                }


            }
            });
    }
}
