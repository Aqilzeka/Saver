package com.aqil.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button save;
    EditText ad, soyad, yas, uni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ad = findViewById(R.id.ad);
        soyad = findViewById(R.id.soyad);
        yas = findViewById(R.id.yas);
        uni = findViewById(R.id.uni);
        save = findViewById(R.id.save);
        save.setOnClickListener(MainActivity.this);
    }


    @Override
    public void onClick(View v) {

        try {


            final ParseObject boxer = new ParseObject("Boxer");
            boxer.put("name", ad.getText().toString());
            boxer.put("Surname", soyad.getText().toString());
            boxer.put("age", Integer.parseInt(yas.getText().toString()));
            boxer.put("University", uni.getText().toString());
            boxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, boxer.get("name") + " \n" + boxer.get("Surname") + "\n" + boxer.get("age")
                                + "\n" + boxer.get("University"), FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                    }
                }
            });
        } catch (Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
        }
    }
}
