package com.example.anew.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

/**
 * Created by NEW on 2017/11/27.
 */

public class FdSet extends Activity {
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myapplication-84cfa.firebaseio.com/FdSet");
    String H;
    String M;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fdset);
        Button fdset = (Button) findViewById(R.id.setadd);
        fdset.setOnClickListener(new ButtonClickListener());
        // Setup Firebase library
        //必須完成Firebase Setup後才能使用
        //取得Firebase連結
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Firebase入面邊個目錄

    }

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText weight = (EditText) findViewById(R.id.weightset);
            final TimePicker timeset = (TimePicker) findViewById(R.id.fdtimeset);
            int h = timeset.getCurrentHour();
            int m = timeset.getCurrentMinute();
            if (h >= 10)
                H =  String.valueOf(h);
            else
                H= "0"+String.valueOf(h);

            if (m >= 10)
                M =  String.valueOf(m);
            else
                M= "0"+String.valueOf(m);

            feedset set = new feedset(H + ":" + M, Integer.parseInt(weight.getText().toString()));
            myRef.push().setValue(set);

            Intent intent = new Intent();
            intent.setClass(FdSet.this, MainActivity.class);
            startActivity(intent);
        }
    }
}

