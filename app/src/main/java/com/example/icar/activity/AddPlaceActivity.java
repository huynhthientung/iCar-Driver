package com.example.icar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icar.R;
import com.example.icar.model.Tracking;
import com.example.icar.model.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;

public class AddPlaceActivity extends AppCompatActivity {
    private final String TAG = "BOOKING_KEY";
    private String bookingKey;
    private TextView txtBookingkey;
    private Spinner spProvince;
    private Button btnAdd;
    private CheckBox cb_isFinished;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private String[] provinces;
    private int idProvince = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bookingKey = getIntent().getExtras().getString(TAG);
        txtBookingkey = findViewById(R.id.textView_BookingKey);
        spProvince = findViewById(R.id.spinner_province);
        btnAdd = findViewById(R.id.button_Add);
        cb_isFinished = findViewById(R.id.checkbox_isFinished);
        provinces = getResources().getStringArray(R.array.provinces);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.provinces, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProvince.setAdapter(adapter);
        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idProvince = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        txtBookingkey.setText(bookingKey);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    private void add() {
        String location = provinces[idProvince];
        Calendar calendar = Calendar.getInstance();
        String date = calendar.getTime().toString();
        String uid = Utils.getInstance().getUid();
        Tracking tracking = new Tracking(bookingKey, uid, date, location, cb_isFinished.isChecked());
        root.child("Tracking").child(bookingKey).child(String.valueOf(calendar.getTimeInMillis())).setValue(tracking)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddPlaceActivity.this, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}