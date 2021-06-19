package com.example.example.rubish;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.R;
import com.example.example.USER.UserLocal;
import com.example.example.model.MeasurementAdd;
import com.example.example.service.MeasurementServer;

import java.io.InputStream;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.observers.CallbackCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class AdapterNotesDB extends RecyclerView.Adapter<AdapterNotesDB.RecipesViewHolder> {

    private MeasurementServer server;
    protected final Context mContext;
    private ArrayList<Measurement> measurements = new ArrayList<>();
    CompositeDisposable compositeDisposable;

    public void setMeasurements(ArrayList<Measurement> measurements){
        this.measurements=measurements;
    }
    protected AdapterNotesDB(Context context, ArrayList<Measurement> _notes) {
        server = new MeasurementServer();
        this.mContext = context;
        measurements = _notes;
        compositeDisposable = new CompositeDisposable();
    }


//    public void getList() {
//        compositeDisposable.add(server.getRestApi().getAllNote(UserLocal.getKey(), UserLocal.getId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BiConsumer<TupayaHuyna, Throwable>() {
//                    @Override
//                    public void accept(TupayaHuyna u, Throwable throwable) throws Exception {
//                        if (throwable != null) {
//                            System.out.println("ERROR");
//                            throwable.printStackTrace();
//                        } else {
//                            measurements = new ArrayList<>(u.getMeasurements());
//                            this.notifyDataSetChanged();
//                        }
//                    }
//                }));
//    }
    //Это всё для получения листа, куда его деть обсудим вместе


    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.measurement_vie, parent, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        final Measurement note = measurements.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return measurements == null ? 0 : measurements.size();
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {
        TextView data, saturation, lowPress, highPress, pulse;

        ImageView delete;
        MeasurementAdd note;
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
                            if (measurements.size() !=0 ) {
                                int position = getAdapterPosition();
                                server.getRestApi().deleteNode(UserLocal.getLocalUser().getAccess_token(), position)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new CallbackCompletableObserver(
                                                throwable -> {
//                            TODO вывести сообщение об ошибке
                                                },
                                                () -> {
                                                    measurements.remove(position);
                                                }
                                        ));

                            notifyItemRemoved(getAdapterPosition());
                        }
                        }
                    });

        }

        //WTF??? TODO
        public void bind(Measurement rec) {
            this.note2 = rec;
            saturation.setText(new String("Saturation = " + rec.getSaturation() + "%"));
            lowPress.setText(new String("Low pressure = " + rec.getPressure_low()));
            highPress.setText(new String("High pressure = " + rec.getPressure_high()));
            pulse.setText(new String("Pulse = " + rec.getPulse()));
            data.setText(rec.getDataCreate());

            InputStream inputStream = null;
        }




    }
}
