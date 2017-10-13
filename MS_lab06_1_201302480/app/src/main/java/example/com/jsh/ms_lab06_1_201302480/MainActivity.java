package example.com.jsh.ms_lab06_1_201302480;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        fragment2 = new Fragment2();
    }

    public void onFragmentChanged(int index)
    {
        if (index == 0)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
        }
        else if (index==1)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        }
    }
}
