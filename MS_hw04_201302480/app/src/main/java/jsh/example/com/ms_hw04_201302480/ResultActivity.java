package jsh.example.com.ms_hw04_201302480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    Button goToMenuButton;
    TextView resultText;
    int check, permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        goToMenuButton = (Button) findViewById(R.id.goToMenuButton);
        resultText = (TextView) findViewById(R.id.resultText);

        Bundle bundle =  getIntent().getExtras();


        permission = bundle.getInt("Permission");
        check = bundle.getInt("Check");
        resultText.setText(requestCodeToString(permission) + "권한 "+ resultCodeToString(check)+"됨");
        Toast.makeText(getApplicationContext(),  requestCodeToString(permission) +
                "권한 "+ resultCodeToString(check)+"됨", Toast.LENGTH_LONG).show();

        goToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Permission", permission);
                intent.putExtra("Message", "임의의 메시지");
                setResult(check, intent);
                finish();
            }
        });
    }


    private String resultCodeToString(int resultCode) {
        switch (resultCode) {
            case 0:
                return "거절";

            case -1:
                return "승인";

            default:
                return "에러";
        }
    }

    private String requestCodeToString(int requestCode) {
        switch (requestCode) {
            case 1:
                return "위치";

            case 2:
                return "카메라";

            case 3:
                return "달력";

            default:
                return "디폴트";
        }
    }
}
