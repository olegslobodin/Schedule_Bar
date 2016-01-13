package ru.olegslobodin.schedule;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        if (mainLayout != null)
            mainLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    int notExisting[] = {17};
                    buttonsGenerate(31, notExisting);
                }
            });

        RelativeLayout mainLayout_landscape = (RelativeLayout) findViewById(R.id.mainLayout_landscape);
        if (mainLayout_landscape != null)
            mainLayout_landscape.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    int notExisting[] = {17};
                    buttonsGenerate(31, notExisting);
                }
            });
    }

    public void buttonsGenerate(int count, int notExisting[]) {
        System.out.println("BUTTONS GENERATE");
        int notExistsCount = 0;
        for (int i = 1; i <= count; i++) {
            boolean exists = true;
            for (int j = 0; j < notExisting.length; j++)
                if (i == notExisting[j]) {
                    exists = false;
                    notExistsCount++;
                    break;
                }
            if (exists) {
                RelativeLayout buttonsLayout = (RelativeLayout) findViewById(R.id.buttonsLayout);
                BusButton busButton = new BusButton(this, i, i - notExistsCount, buttonsLayout);
                final int busNumber = i;
                busButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Schedule.class);
                        intent.putExtra("busNumber", busNumber);
                        startActivity(intent);
                    }
                });
                buttonsLayout.addView(busButton);
            }
        }
    }
}
