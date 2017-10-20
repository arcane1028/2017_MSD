package example.com.jsh.ms_hw06_201302480;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    WebView webView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = this.getSupportActionBar();
        actionBar.show();

        webView = (WebView)findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.goToNaver:
                webView.loadUrl("https://www.naver.com/");
                return true;
            case R.id.goToURL:
                webView.loadUrl(editText.getText().toString());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
