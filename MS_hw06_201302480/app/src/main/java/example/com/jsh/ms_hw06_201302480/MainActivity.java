package example.com.jsh.ms_hw06_201302480;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
;
    WebView webView;
    EditText inputURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(MainActivity.this, "onReceivedError", Toast.LENGTH_LONG).show();
                super.onReceivedError(view, request, error);
            }
        });

        inputURL = (EditText) findViewById(R.id.inputURL);
        Button goToNaverButton = (Button) findViewById(R.id.goToNaver);
        Button goToURLButton = (Button) findViewById(R.id.goToURL);

        goToNaverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String naverUrl = "https://www.naver.com/";
                inputURL.setText(naverUrl);
                webView.loadUrl(naverUrl);
            }
        });
        goToURLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(inputURL.getText().toString());
            }
        });
    }
}
