package com.example.henrikfogbunzel.newfireapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                String name = map.get("Name");
                String contact = map.get("Contact");

                Log.v("Name", name);
                Log.v("Contact", contact);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void buttonClicked(View view){
        EditText editTextName = (EditText) findViewById(R.id.name);
        EditText editTextContact = (EditText) findViewById(R.id.contact);
        myRef = database.getReference();
        myRef.child("Name").setValue(editTextName.getText().toString());
        myRef.child("Contact").setValue(editTextContact.getText().toString());
    }
}
