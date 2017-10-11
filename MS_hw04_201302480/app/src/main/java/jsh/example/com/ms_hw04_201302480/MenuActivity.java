package jsh.example.com.ms_hw04_201302480;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 101;
    Button cameraButton;
    Button locationButton;
    Button calendarButton;
    Button goToLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cameraButton = (Button) findViewById(R.id.cameraButton);
        locationButton = (Button) findViewById(R.id.locationButton);
        calendarButton = (Button) findViewById(R.id.calendarButton);
        goToLoginButton = (Button) findViewById(R.id.goToLoginButton);

        processIntent(getIntent());

        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(MenuActivity.this, Manifest.permission.RECEIVE_SMS);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    //TODO 권한 승인 된 경우
                } else {
                    //아래 if 한번거절하면 true 됨
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MenuActivity.this, Manifest.permission.RECEIVE_SMS)) {
                        //TODO 권한 거절된 경우
                    } else {
                        ActivityCompat.requestPermissions(MenuActivity.this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
                    }
                }
            }
        });
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            Toast.makeText(getApplicationContext(), "Username : " + bundle.getString("ID")
                    + " Password : " + bundle.getString("PASSWORD"), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                //Todo 수정
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);

                    intent.putExtra("Permission", "카메라");
                    intent.putExtra("Check", true);

                    startActivityForResult(intent, REQUEST_CODE_MENU);
                } else {
                    //TODO 거절 응답 0
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_MENU) {
            //Todo 수정
            if (data != null) {
                Bundle bundle = data.getExtras();
                String menu = bundle.getString("MENU");
                Toast.makeText(getApplicationContext(), "메뉴 : " + menu, Toast.LENGTH_LONG).show();
            }

        }
    }

}
