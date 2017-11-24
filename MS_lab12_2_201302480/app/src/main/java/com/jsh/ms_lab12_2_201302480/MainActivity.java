package com.jsh.ms_lab12_2_201302480;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText input01;
    TextView txtMsg;

    public static String defaultUrl = "https://m.naver.com";

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "create");

        input01 = (EditText) findViewById(R.id.input01);
        input01.setText(defaultUrl);

        txtMsg = (TextView)findViewById(R.id.txtMsg);

        Button requestBtn = (Button) findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlStr = input01.getText().toString();
                Log.d("MainActivity", "btn");
                ConnectThread thread = new ConnectThread(urlStr);
                thread.start();
            }
        });
    }

    private class ConnectThread extends Thread {
        String urlStr;

        public ConnectThread(String urlStr)
        {
            this.urlStr = urlStr;
        }

        @Override
        public void run() {
            try {
                final String output = request(urlStr);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtMsg.setText(output);
                    }
                });
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        private String request(String urlStr) {
            StringBuilder output = new StringBuilder();
            try{
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection!=null)
                {
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream())
                    );
                    String line = null;
                    while(true)
                    {
                        line = reader.readLine();
                        if (line == null)
                        {
                            break;
                        }
                        output.append(line+"\n");
                    }
                    reader.close();
                    connection.disconnect();
                }
            }catch (Exception e)
            {
                Log.e("HTTP", "Exception in processing response");
                e.printStackTrace();
            }
            return output.toString();
        }
    }
}
