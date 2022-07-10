package com.reddredd.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private recyclerAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.measurementListView);
        //Measurement.measurementArrayList = new ArrayList<>();
        setMeasurements();
        setAdapter();

        MaterialButton addNewMeasurementBtn = findViewById(R.id.addNewMeasurement);
        addNewMeasurementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AddMeasurement.class));
            }
        });
    }

    private void setAdapter() {
        setOnClickListener();
        recyclerAdapter adapter = new recyclerAdapter(Measurement.measurementArrayList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new recyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), MeasurementDetail.class);
                intent.putExtra("id", Measurement.measurementArrayList.get(position).getId());
                startActivity(intent);
            }
        };
    }

    private void setMeasurements() {
        /*Measurement.measurementArrayList.add(new Measurement("6 July, 2022", "10:34 AM", "110", "70", "65", "Good reading so far."));
        Measurement.measurementArrayList.add(new Measurement("10 July, 2022", "07:43 PM", "110", "70", "65", "Good reading so far."));
        Measurement.measurementArrayList.add(new Measurement("69 July, 6969", "69:69 PM", "69", "69", "69", "Good reading so far 69."));
        Measurement.measurementArrayList.add(new Measurement("69 July, 6969", "69:69 PM", "69", "69", "69", "Good reading so far 69."));
        Measurement.measurementArrayList.add(new Measurement("69 July, 6969", "69:69 PM", "69", "69", "69", "Good reading so far 69."));
        Measurement.measurementArrayList.add(new Measurement("69 July, 6969", "69:69 PM", "69", "69", "69", "Good reading so far 69."));*/
    }

}