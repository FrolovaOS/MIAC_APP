package com.example.example.rubish;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.MainActivity;
import com.example.example.Note;
import com.example.example.R;
import com.example.example.USER.UserLocal;
import com.example.example.model.Measurement;
import com.example.example.model.User;
import com.example.example.service.MeasurementServer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.internal.observers.CallbackCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class AdapterNotesDB extends RecyclerView.Adapter<AdapterNotesDB.RecipesViewHolder> {

    private MeasurementServer server;
    protected final Context mContext;
    private ArrayList<Measurement> notes;
    CompositeDisposable compositeDisposable;

    protected AdapterNotesDB(Context context, ArrayList<Measurement> _notes) {
        server = new MeasurementServer();
        this.mContext = context;
        notes = _notes;
        compositeDisposable = new CompositeDisposable();
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
        TextView data, saturation, lowPress, highPress, pulse;
        ;
        ImageView delete;
        Measurement note;
        ArrayList<String> notes1;
        ContentValues cv = new ContentValues();
        private Measurement note2;

        public RecipesViewHolder(View view) {

            super(view);
            saturation = itemView.findViewById(R.id.saturation);
            data = itemView.findViewById(R.id.data);
            lowPress = itemView.findViewById(R.id.lowPressure1);
            highPress = itemView.findViewById(R.id.highPressure1);
            pulse = itemView.findViewById(R.id.pulse10);
            delete = itemView.findViewById(R.id.delete);

            delete.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int position = getAdapterPosition();
                            server.getRestApi().deleteNode(UserLocal.getLocalUser().getAccess_token(), position)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new CallbackCompletableObserver(
                                            throwable -> {
//                            TODO вывести сообщение об ошибке
                                            },
                                            () -> {
//                          TODO всё получилось
                                            }
                                    ));


                            notes.remove(position);
                            notifyItemRemoved(getAdapterPosition());
                        }
                    });

        }

        //WTF??? TODO
        public void bind(Measurement rec) {
            this.note2 = rec;
            if (rec.getSaturation() != null)
                saturation.setText(new String("Saturation = " + rec.getSaturation() + "%"));
            lowPress.setText(new String("Low pressure = " + rec.getLowPressure()));
            highPress.setText(new String("High pressure = " + rec.getHighPressure()));
            pulse.setText(new String("Pulse = " + rec.getPulse()));
            data.setText(rec.getTimestamp());

            InputStream inputStream = null;
        }

        //Это всё для получения листа, куда его деть обсудим вместе
        public void getList() {
            compositeDisposable.add(server.getRestApi().getAllNote(UserLocal.getLocalUser().getAccess_token())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BiConsumer<List<Measurement>, Throwable>() {
                        @Override
                        public void accept(List<Measurement> u, Throwable throwable) throws Exception {
                            if (throwable != null) {
                                System.out.println("ERROR");
                            } else {
                                notes = new ArrayList<>(u);
                            }
                        }
                    }));
        }


    }
}
