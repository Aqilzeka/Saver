package com.aqil.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button save, alldata;
    EditText ad, soyad, yas, uni;
    private TextView eldeet;
    private String persons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ad = findViewById(R.id.ad);
        soyad = findViewById(R.id.soyad);
        yas = findViewById(R.id.yas);
        uni = findViewById(R.id.uni);
        save = findViewById(R.id.save);
        eldeet = findViewById(R.id.eldeet);
        alldata = findViewById(R.id.hamsinieldeet);
        save.setOnClickListener(MainActivity.this);
        eldeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Boxer");
                parseQuery.getInBackground("BcHlsNQIVr", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){
                            eldeet.setText("Ad: " + object.get("name") + "\n" +
                                    "Soyad: " + object.get("Surname") + "\n" +
                                    "Yas: " + object.get("age")+"\n" +
                                    "Univeristet: " + object.get("University"));
                        }
                    }
                });
            }
        });

        alldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persons = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Boxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if ( e==null ){
                            if (objects.size()>0){
                                for (ParseObject person : objects){
                                    persons = persons + person + "\n";
                                    
                                }

                            }
                        }
                    }
                });
            }
        });


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
