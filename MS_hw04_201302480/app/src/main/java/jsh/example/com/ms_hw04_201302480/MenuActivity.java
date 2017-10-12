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
    Button locationButton;
    Button cameraButton;
    Button calendarButton;
    Button goToLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        locationButton = (Button) findViewById(R.id.locationButton);
        cameraButton = (Button) findViewById(R.id.cameraButton);
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

        addPermissionEvent(locationButton, Manifest.permission.ACCESS_COARSE_LOCATION, 1);
        addPermissionEvent(cameraButton, Manifest.permission.CAMERA, 2);
        addPermissionEvent(calendarButton, Manifest.permission.READ_CALENDAR, 3);
    }

    private void addPermissionEvent(final Button button, final String permission,
                                    final int requestCode) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(MenuActivity.this, permission);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            requestCodeToString(requestCode)+" 권한 승인됨", Toast.LENGTH_LONG).show();
                } else {
                    //아래 if 한번거절하면 true 됨
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MenuActivity.this, permission)) {
                        ActivityCompat.requestPermissions(MenuActivity.this, new String[]{permission}, requestCode);
                    } else {
                        ActivityCompat.requestPermissions(MenuActivity.this, new String[]{permission}, requestCode);
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

        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("Permission", requestCode);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            intent.putExtra("Check", -1);
        } else {
            intent.putExtra("Check", 0);
        }
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_MENU) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                int permission = bundle.getInt("Permission");
                Toast.makeText(getApplicationContext(),  requestCodeToString(permission) +
                        "권한 "+ resultCodeToString(resultCode)+"됨, resultCode : " +resultCode
                        + ", " + bundle.getString("Message"), Toast.LENGTH_LONG).show();
            }
        }
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
