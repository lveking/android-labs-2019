package edu.hzuapps.androidlabs.soft1714080902114;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Note_title_Activity extends LinearLayout {
    public Note_title_Activity(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_note_title,this);
        Button titleBack = (Button) findViewById(R.id.title_cancel);
        Button titleEdit = (Button) findViewById(R.id.title_save);
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        titleEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"There are many functions here",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
