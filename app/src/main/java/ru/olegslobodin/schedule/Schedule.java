package ru.olegslobodin.schedule;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ScrollView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Schedule extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setButtonsText();
        loadSchedule(1);
    }

    public void setButtonsText() {
        int busNumber = getIntent().getIntExtra("busNumber", 0);
        Button button1 = (Button) findViewById(R.id.button1);
        String fileName = "first_"+busNumber;
        button1.setText(readRawTextFile(getBaseContext(), getResources().getIdentifier(fileName, "raw", "ru.olegslobodin.schedule"), true) + " >>>");
        Button button2 = (Button) findViewById(R.id.button2);
        fileName = "second_"+busNumber;
        button2.setText("<<< " + readRawTextFile(getBaseContext(), getResources().getIdentifier(fileName, "raw", "ru.olegslobodin.schedule"), true));
    }

    public void loadSchedule(int direction) {
        int busNumber = getIntent().getIntExtra("busNumber", 0);
        String prefix = (direction == 1) ? "first_" : "second_";
        String fileName = prefix + busNumber;
        String text = readRawTextFile(getBaseContext(), getResources().getIdentifier(fileName, "raw", "ru.olegslobodin.schedule"), false);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadDataWithBaseURL(null, text, "text/html", "UTF-8", null);
    }

    public static String readRawTextFile(Context context, int resId, boolean onlyFirstLine) {
        InputStream inputStream;
        try {
            inputStream = context.getResources().openRawResource(resId);
        } catch (Exception e) {
            return "";
        }

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String line, result = "";

        try {
            while ((line = buffReader.readLine()) != null) {
                if (onlyFirstLine)
                    return line;
                result += line + "<br>";
            }
        } catch (IOException e) {
            return null;
        }
        return result;
    }

    public void button1_click(View view) {
        loadSchedule(1);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.scrollTo(0, 0);
    }

    public void button2_click(View view) {
        loadSchedule(2);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.scrollTo(0, 0);
    }
}
