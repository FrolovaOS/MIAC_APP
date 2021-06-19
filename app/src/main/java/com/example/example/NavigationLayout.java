package com.example.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class NavigationLayout extends ConstraintLayout
{
    TextView name,lastName, email, snills, recomendation;
    public NavigationLayout(Context context, RelativeLayout parent)
    {
        super(context);
        initView(context,parent);
    }

    public void initView(final Context context,RelativeLayout parent)
    {
        // надуваем любой xml файл разметки
        name = (TextView) findViewById(R.id.textView3);
        lastName = (TextView) findViewById(R.id.textView);
        email = (TextView) findViewById(R.id.textView5);
        snills = (TextView) findViewById(R.id.textView4);
        recomendation = (TextView) findViewById(R.id.textView6);
        View view= LayoutInflater.from(context).inflate(R.layout.view_drawer_layout,parent,true);

    }
}