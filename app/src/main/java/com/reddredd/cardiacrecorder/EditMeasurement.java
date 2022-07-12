package com.reddredd.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class EditMeasurement extends AppCompatActivity {

    private EditText sysOut, diasOut, heartOut, dateOut, timeOut, commentOut;
    MaterialButton updateBtn, cancelBtn;
    int id;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_measurement);

        sysOut = findViewById(R.id.systolicPressure);
        diasOut = findViewById(R.id.diastolicPressure);
        heartOut = findViewById(R.id.heartRate);
        dateOut = findViewById(R.id.date);
        timeOut = findViewById(R.id.time);
        commentOut = findViewById(R.id.comment);
        updateBtn = findViewById(R.id.updateM);
        cancelBtn = findViewById(R.id.cancel);

        setMeasurements();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(getApplicationContext());

                String sysP = sysOut.getText().toString();
                String diaP = diasOut.getText().toString();
                String heartR = heartOut.getText().toString();
                String date = dateOut.getText().toString();
                String time = timeOut.getText().toString();
                String comment = commentOut.getText().toString();

                Measurement measurement = new Measurement(date, time, sysP, diaP, heartR, comment);
                measurement.setId(id);
                sqLiteManager.updateNoteInDB(measurement);

                Toast.makeText(getApplicationContext(), "Update Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        });

    }

    private void setMeasurements() {
        if(extras != null)
            id = extras.getInt("id");

        for(Measurement mes : Measurement.measurementArrayList)
        {
            if(mes.getId() == id)
            {
                sysOut.setText(mes.getSystolicPressure());
                diasOut.setText(mes.getDiastolicPressure());
                heartOut.setText(mes.getHeartRate());
                dateOut.setText(mes.getDate());
                timeOut.setText(mes.getTime());
                commentOut.setText(mes.getComment());
            }
        }
    }

}