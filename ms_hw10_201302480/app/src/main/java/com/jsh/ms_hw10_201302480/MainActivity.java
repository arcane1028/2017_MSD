package com.jsh.ms_hw10_201302480;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<PannelItem> panels;
    Button start;
    Button stop;

    PanelAdapter adapter;
    ProgressRunnable runnable;
    Thread thread1;
    Handler handler;
    boolean isRunning;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panels = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        start = (Button) findViewById(R.id.startButton);
        stop = (Button) findViewById(R.id.stopButton);

        panels.add(new PannelItem("김준수", "010-1000-1000", "서울시", R.drawable.customer));
        panels.add(new PannelItem("이희선", "010-2000-2000", "부산시", R.drawable.customer));
        panels.add(new PannelItem("최민수", "010-3000-3000", "대전시", R.drawable.customer));


        adapter = new PanelAdapter(getApplicationContext());
        adapter.addItem(panels.get(0));
        listView.setAdapter(adapter);

        handler = new Handler();
        runnable = new ProgressRunnable();
        isRunning = false;
        index = 0;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = true;
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (isRunning) {
                            handler.post(runnable);
                            Thread.sleep(1000);
                        } else {
                            Thread.sleep(500);
                        }
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", "Exception");
                }
            }
        });
        thread1.start();
    }


    private class ProgressRunnable implements Runnable {
        @Override
        public void run() {

            adapter.removeItem(0);
            if (index == 2) {
                index = 0;
            } else {
                index++;
            }
            adapter.addItem(panels.get(index));
            adapter.notifyDataSetChanged();
        }
    }
}
