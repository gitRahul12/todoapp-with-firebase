package com.rahul.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ViewTask extends AppCompatActivity {
    ImageView deleteNotes;
    private DatabaseReference mDatabase;
    String id;
    TextView viewTitle,viewDesc;
    Button updateTask;

//    List<Notes> list;
//    private Context context;
//
//    public ViewTask(List<Notes> list,Context context) {
//        this.context = context;
//        this.list = list;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        updateTask = findViewById(R.id.updateTask);
        viewTitle = findViewById(R.id.title_update);
        viewDesc = findViewById(R.id.description_update);
        deleteNotes = findViewById(R.id.delete);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent i = getIntent();
        id = i.getStringExtra("id");
        String title = i.getStringExtra("title");
        String description = i.getStringExtra("description");

        viewTitle.setText(title);
        viewDesc.setText(description);

        deleteNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Notes").child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ViewTask.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ViewTask.this,HomeActivity.class));
                        }
                    }
                });
            }
        });


        updateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myTitle = viewTitle.getText().toString();
                String myDescription = viewDesc.getText().toString();

                Intent b=new Intent(ViewTask.this,UpdateTodo.class);
                b.putExtra("id",id);
                b.putExtra("title",myTitle);
                b.putExtra("description",myDescription);
                startActivity(b);
            }
        });
    }
}