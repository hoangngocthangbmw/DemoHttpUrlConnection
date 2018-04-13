package com.framgia.thanghn.httpurlconnection;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    public static ProgressBar progressBar;
    private Button btnGet;
    public static TextView txtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        txtList=(TextView)findViewById(R.id.txt);


        btnGet = (Button) findViewById(R.id.button2);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadJsonTask downloadJsonTask = new DownloadJsonTask();
                downloadJsonTask.execute();
            }
        });
    }

    public void startProgress(View view) {
        new Thread(new Task()).start();
    }

    private class Task implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i <= 20; i++) {
                final int value = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(value);
                        if (value == 20) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        }
    }
}