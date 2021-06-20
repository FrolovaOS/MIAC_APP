package com.example.example;

import android.annotation.SuppressLint;
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

import com.example.example.model.Measurement;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterNotesDB extends RecyclerView.Adapter<AdapterNotesDB.RecipesViewHolder> {

    protected final Context mContext;
    private ArrayList<Measurement> notes;

    protected AdapterNotesDB(Context context, ArrayList<Measurement> _notes) {
        this.mContext = context;
        notes = _notes;
    }

    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.measurement_vie, parent, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        final Measurement note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {
        TextView data,saturation, lowPress, highPress, pulse,type;
        ImageView delete;
        Note note;
        ArrayList<String> notes1;
        ContentValues cv = new ContentValues();
        private Measurement note2;

        public RecipesViewHolder(View view) {

            super(view);
            saturation = itemView.findViewById(R.id.saturation);
            type = itemView.findViewById(R.id.type);
            data = itemView.findViewById(R.id.data);
            lowPress = itemView.findViewById(R.id.lowPressure1);
            highPress = itemView.findViewById(R.id.highPressure1);
            pulse = itemView.findViewById(R.id.pulse10);
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

        public void bind(Measurement rec) {
            this.note2 = rec;
                saturation.setText(new String("Сатурация = "+rec.getSaturation() + "%"));
            lowPress.setText(new String("Нижнее давление = "+rec.getPressure_low()));
            highPress.setText(new String("Верхнее давление = "+rec.getPressure_high()));
            pulse.setText(new String("Пульс = "+rec.getPulse()));

            @SuppressLint("SimpleDateFormat") SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSS");
            String date = "";
            try {
                Date date1 = dt.parse(rec.getDataCreate());
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dt1 = new SimpleDateFormat("dd.MM.yyyy 'в' hh:mm");
                date=dt1.format(date1);
                System.out.println("---------------------------"+date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            type.setText(new String("Обстоятельства измерения: "+rec.getType()));

            InputStream inputStream = null;
        }
    }
}
