package com.example.anew.myapplication;

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
    TextView textView = (TextView)findViewById(R.id.textView);
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
                timeset.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        // TODO Auto-generated method stub
                        //取得 hour的值，透過TimeFix方法。轉換成String.並初始H。
                        H = TimeFix(hourOfDay);
                        //取得 minute的值，透過TimeFix方法。轉換成String.並初始M。
                        M = TimeFix(minute);
                    }
                });

                feedset set = new feedset(H+":"+M, Integer.parseInt(weight.getText().toString()));
                myRef.push().setValue(set);

                Intent intent = new Intent();
                intent.setClass(FdSet.this, MainActivity.class);
                startActivity(intent);
        }
    }

    private static String TimeFix(int c){
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
