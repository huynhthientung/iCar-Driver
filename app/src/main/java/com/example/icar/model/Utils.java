package com.example.icar.model;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static Driver driver;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final FirebaseUser mUser = mAuth.getCurrentUser();
    private final DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private static String uid = null;
    private static Uri photoUrl = null;
    private static String driverName = "";

    private Utils() {
        if (null == uid) {
            uid = mUser.getUid();
        }
        if (null == photoUrl) {
            photoUrl = mUser.getPhotoUrl();
        }
        if (null == driver) {
            initDriver();
        }
    }

    private void initDriver() {
        root.child("Drivers").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                driver = snapshot.getValue(Driver.class);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public String getUid() {
        return uid;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getDriverName(String uid) {
        root.child("Drivers").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                Driver driver = snapshot.getValue(Driver.class);
                if (driver.uid.equals(uid)) {
                    driverName = driver.full_name;
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
        return driverName;
    }
    public static Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }
}
