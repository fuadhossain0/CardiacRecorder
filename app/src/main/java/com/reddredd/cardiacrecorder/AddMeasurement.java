package com.reddredd.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class AddMeasurement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurement);

        EditText systolicPressure = findViewById(R.id.systolicPressure);
        EditText diastolicPressure = findViewById(R.id.diastolicPressure);
        EditText heartRate = findViewById(R.id.heartRate);
        EditText dateInp = findViewById(R.id.date);
        EditText timeInp = findViewById(R.id.time);
        EditText commentInp = findViewById(R.id.comment);
        MaterialButton addBtn = findViewById(R.id.addM);
        MaterialButton cancelBtn = findViewById(R.id.cancel);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(getApplicationContext());


                String sysP = systolicPressure.getText().toString();
                String diaP = diastolicPressure.getText().toString();
                String heartR = heartRate.getText().toString();
                String date = dateInp.getText().toString();
                String time = timeInp.getText().toString();
                String comment = commentInp.getText().toString();

                //Measurement.measurementArrayList.add(new Measurement(date, time, sysP, diaP, heartR, comment));
                Measurement measurement = new Measurement(date, time, sysP, diaP, heartR, comment);
                sqLiteManager.addMeasurementToDatabase(measurement);

                Toast.makeText(getApplicationContext(), "Measurement saved!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        });

    }
}