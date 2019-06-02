package com.jiyoung.doitvoca;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;


public class MainActivity extends AppCompatActivity {

    private Button sendbt;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendbt = (Button) findViewById(R.id.button2);


        sendbt.setOnClickListener(new Button.OnClickListener() {
            //버튼 클릭시 디비에 2 입력 
            @Override
            public void onClick(View v) {
                databaseReference.child("message").push().setValue("2");

                //데이터 베이스 이벤트 리스너
                databaseReference.child("message").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Log.d("MainActivity", "Single ValueEventListener : " + snapshot.getValue());
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w( "Failed to read value.", error.toException());
                    }
                });








            }


        });
    }
}

