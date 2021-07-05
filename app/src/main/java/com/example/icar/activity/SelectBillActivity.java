package com.example.icar.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.icar.R;
import com.example.icar.model.Bookings;
import com.example.icar.model.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SelectBillActivity extends AppCompatActivity {

    private ListView lvBookings;
    private ArrayList<String> bookingsArrayList;
    private DatabaseReference root;
    private ArrayAdapter adapter;
    private Bookings bookings;
    private String uid;
    private ArrayList<Bookings> mBookingsArrayList;
    private final String TAG = "BOOKING_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bill);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        uid = Utils.getInstance().getUid();
        lvBookings = findViewById(R.id.listView_bookings);
        bookingsArrayList = new ArrayList<>();
        mBookingsArrayList = new ArrayList<>();
        root = FirebaseDatabase.getInstance().getReference();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bookingsArrayList);
        lvBookings.setAdapter(adapter);
        root.child("Bookings").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                bookings = snapshot.getValue(Bookings.class);
                if (bookings.driverId.equals(uid) && bookings.status) {
                    bookingsArrayList.add(bookings.bookingKey);
                    adapter.notifyDataSetChanged();
                    mBookingsArrayList.add(bookings);
                }

            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        lvBookings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(SelectBillActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AddPlaceActivity.class);
                intent.putExtra(TAG, bookingsArrayList.get(position));
                startActivity(intent);
                finish();
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