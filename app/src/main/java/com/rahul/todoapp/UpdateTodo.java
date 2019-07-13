package com.rahul.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateTodo extends AppCompatActivity {
    EditText updateTtl,updateDesc;
    Button update;
    ImageView deleteNotes;
    private DatabaseReference mDatabase;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);

        updateTtl = findViewById(R.id.title_update);
        updateDesc = findViewById(R.id.description_update);
        update = findViewById(R.id.updateNotes);
        deleteNotes = findViewById(R.id.delete);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent i = getIntent();
        id = i.getStringExtra("id");
        String title = i.getStringExtra("title");
        String description = i.getStringExtra("description");

        updateTtl.setText(title);
        updateDesc.setText(description);

        deleteNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Notes").child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UpdateTodo.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateTodo.this,HomeActivity.class));
                        }
                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTask();
            }
        });
    }
    public void updateTask(){
        String myTitle =updateTtl.getText().toString();
        String myDescription=updateDesc.getText().toString();
        Notes listdata = new Notes(myTitle,myDescription,id);
        mDatabase.child("Notes").child(id).setValue(listdata).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateTodo.this, "Notes Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
    }
}
