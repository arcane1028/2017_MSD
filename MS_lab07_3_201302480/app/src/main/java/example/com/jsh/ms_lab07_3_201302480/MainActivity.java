package example.com.jsh.ms_lab07_3_201302480;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = this.getSupportActionBar();

        actionBar.show();
        actionBar.setSubtitle("옵션바 살펴보기");
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onButton1Clicked(View v){
        actionBar.setLogo(R.drawable.home);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_refresh:
                textView.setText("새로고침 메뉴를 선택했습니다.");
                return true;
            case R.id.menu_search:
                textView.setText("검색 메뉴를 선택했습니다.");
                return true;
            case R.id.menu_settings:
                textView.setText("설정 메뉴를 선택했습니다.");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
