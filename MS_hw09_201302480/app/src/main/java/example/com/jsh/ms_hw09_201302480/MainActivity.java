package example.com.jsh.ms_hw09_201302480;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomView myView = new CustomView(this);

        setContentView(myView);


    }
}
