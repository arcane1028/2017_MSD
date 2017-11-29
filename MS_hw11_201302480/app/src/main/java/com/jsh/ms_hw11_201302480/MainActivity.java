package com.jsh.ms_hw11_201302480;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Button requestBtn;
    TextView txtMsg;
    WebView webView;
    Handler handler = new Handler();

    public static String defaultUrl = "https://m.naver.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.input);
        input.setText(defaultUrl);
        txtMsg = (TextView)findViewById(R.id.txtMsg);
        requestBtn = (Button) findViewById(R.id.requestBtn);
        webView = (WebView)findViewById(R.id.webview);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(MainActivity.this, "onReceivedError", Toast.LENGTH_LONG).show();
                super.onReceivedError(view, request, error);
            }
        });

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlStr = input.getText().toString();

                Log.d("MainActivity", "btn");

                ConnectThread thread = new ConnectThread(urlStr);
                thread.start();

                webView.loadUrl(input.getText().toString());
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
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
