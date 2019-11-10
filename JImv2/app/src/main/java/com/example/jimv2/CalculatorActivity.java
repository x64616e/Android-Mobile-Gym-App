package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    private Button doneButton;
    private TextView outputText;
    private static final String TAG = "CalculatorActivity";
    private double weight;
    private double[] plateSizes = {45, 35, 25, 10, 5, 2.5};
    double barWeight = 45;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        doneButton = (Button) findViewById(R.id.calcDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLanding();
            }
        });
        outputText = (TextView) findViewById(R.id.textOutput);
        outputText.setText("Error: No Intent");

        if(getIntent().hasExtra("com.example.jimv2.PASSWEIGHT")) {
            weight = getIntent().getExtras().getDouble("com.example.jimv2.PASSWEIGHT");
            int[] arrangement = calculate(weight);
            String output = ("Target: " + weight + " lb. \n");
            output += ("Bar Weight: " + barWeight + " lb. \n");
            for(int i = 0; i < plateSizes.length; i++) {
                if (arrangement[i] > 0) {
                    output += (plateSizes[i] + " lb. plates: " + arrangement[i] + "\n");
                }
            }
            outputText.setText(output);
        }

    }

    private int[] calculate(double input) {
        int[] plateCount = new int[plateSizes.length];
        double remainingWeight = input - barWeight;
        for(int i = 0; i < plateSizes.length; i++) {
            while(remainingWeight / plateSizes[i] > 0) {
                plateCount[i]++;
                remainingWeight -= plateSizes[i];
            }
        }
        return plateCount;
    }

    public void backToLanding(){
    finish();
    }
}
