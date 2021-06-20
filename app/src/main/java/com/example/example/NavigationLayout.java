package com.example.example;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.USER.UserLocal;
import com.example.example.model.Recomendations;
import com.example.example.rubish.AdapterNotesDB;

public class NavigationLayout extends ConstraintLayout
{
    TextView name,lastName, email, snills, recomendation;
    public NavigationLayout(Context context, RelativeLayout parent, Recomendations recomendations)
    {
        super(context);
        initView(context,parent,recomendations);
    }

    public void initView(final Context context,RelativeLayout parent, Recomendations recomendations)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.view_drawer_layout, parent,true);

        name = (TextView) view.findViewById(R.id.textView3);
        name.setText(new String(UserLocal.getLocalUser().getUserFirstName() + " " + UserLocal.getLocalUser().getUserLastName()));

        snills = (TextView) view.findViewById(R.id.textView4);
        snills.setText(UserLocal.getLocalUser().getUserName());
        if (recomendations != null) {
            recomendation = (TextView) view.findViewById(R.id.textView6);
            recomendation.setText(recomendations.getRecomendations().get((recomendations.getRecomendations().size() - 1)).getText());
            email = (TextView) view.findViewById(R.id.textView5);
            email.setText(recomendations.getEmail());
        }
    }
}