package com.example.example;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;

public class AdapterNotesDB extends RecyclerView.Adapter<AdapterNotesDB.RecipesViewHolder> {

    protected final Context mContext;
    private ArrayList<Note> notes;

    protected AdapterNotesDB(Context context, ArrayList<Note> _notes) {
        this.mContext = context;
        notes = _notes;
    }

    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.notes, parent, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        final Note note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {
        TextView note_text;
        ImageView delete;
        Note note;
        ArrayList<String> notes1;
        ContentValues cv = new ContentValues();
        private Note note2;

        public RecipesViewHolder(View view) {

            super(view);
            note_text = itemView.findViewById(R.id.note_text);
            delete = itemView.findViewById(R.id.delete);

            delete.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //удалить из бд
                            notes.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        }
                    });

        }

        public void bind(Note rec) {
            String text;
            this.note2 = rec;
            note_text.setText(rec.toString());

            InputStream inputStream = null;
        }
    }
}
