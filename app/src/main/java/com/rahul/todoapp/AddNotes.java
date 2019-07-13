package com.rahul.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNotes extends AppCompatActivity {

    Button addNotes;
    EditText titl, description;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        addNotes = findViewById(R.id.addNotes);
        titl = findViewById(R.id.title);
        description = findViewById(R.id.description);

        addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
    }

    public void addTask() {
        String myTitle = titl.getText().toString();
        String myDescription = description.getText().toString();
        String id = mDatabase.push().getKey();
        Notes listdata = new Notes(myTitle, myDescription, id);
        if ((TextUtils.isEmpty(myTitle)) && (TextUtils.isEmpty(myDescription))) {
            Toast.makeText(this, "No Task is Added ??", Toast.LENGTH_SHORT).show();
        } else {
            mDatabase.child("Notes").child(id).setValue(listdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(AddNotes.this, "Task Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }

            });
        }
    }
}
