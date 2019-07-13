package com.rahul.todoapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.myNotesViewHolder> {
        List<Notes> list;
        private Context context;

        public NotesAdapter(List<Notes> list, Context context){
            this.list = list;
            this.context = context;
        }
    @NonNull
    @Override
    public myNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout,parent,false);
        return new myNotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myNotesViewHolder holder, int position) {
        Notes data= list.get(position);
        holder.textView1.setText(data.getTitle());
       // holder.textView2.setText(data.getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myNotesViewHolder extends RecyclerView.ViewHolder{
            TextView textView1,textView2;
            ImageView edit;
            CheckBox checkBox;

       private DatabaseReference mDatabase;
        String id;


        public myNotesViewHolder(@NonNull View layoutView) {
            super(layoutView);
            textView1 =layoutView.findViewById(R.id.textView_title1);
            //textView2 =layoutView.findViewById(R.id.textView_description1);
            edit = layoutView.findViewById(R.id.edit);





            layoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Notes listdata = list.get(getAdapterPosition());
                    Intent i=new Intent(context,ViewTask.class);
                    i.putExtra("id",listdata.id);
                    i.putExtra("title",listdata.title);
                    i.putExtra("description",listdata.description);
                    context.startActivity(i);
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Notes listdata = list.get(getAdapterPosition());
                    Intent i =new Intent(context,UpdateTodo.class);
                    i.putExtra("id",listdata.id);
                    i.putExtra("title",listdata.title);
                    i.putExtra("description",listdata.description);
                    context.startActivity(i);
                }
            });



//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    if (isChecked){
//                        mDatabase.child("Notes").child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if(task.isSuccessful()){
//
//                                }
//                            }
//                        });
//                    }
//                }
//            });
        }

    }
}
