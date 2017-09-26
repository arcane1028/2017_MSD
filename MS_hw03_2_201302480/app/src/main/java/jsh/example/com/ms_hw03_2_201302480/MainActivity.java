package jsh.example.com.ms_hw03_2_201302480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 101;
    private EditText inputID;
    private EditText inputPassword;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputID = (EditText) findViewById(R.id.inputID);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);


                intent.putExtra("ID", inputID.getText().toString());
                intent.putExtra("PASSWORD", inputPassword.getText().toString());

                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_MENU) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    String menu = bundle.getString("MENU");
                    Toast.makeText(getApplicationContext(), "메뉴 : " + menu, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
