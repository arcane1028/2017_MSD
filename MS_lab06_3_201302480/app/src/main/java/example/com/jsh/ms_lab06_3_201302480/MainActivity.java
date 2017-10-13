package example.com.jsh.ms_lab06_3_201302480;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progress;

    private int brightness = 100;
    private TextView seekBarText;

    private RatingBar ratingBar;
    private TextView ratingBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = new ProgressDialog(this);

        seekBarText = (TextView) findViewById(R.id.seekBarText);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBarText = (TextView) findViewById(R.id.ratingBarText);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarText.setText("결과 : " + rating);
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBrightness(progress);
                seekBarText.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setBrightness(int value) {
        if (value < 10)
        {
            value = 10;
        } else if (value > 100)
        {
            value = 100;
        }

        brightness = value;

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float)value/100;
        getWindow().setAttributes(params);
    }

    public void start(View view) {
        progress.setCancelable(true);
        progress.setMessage("다운로드 중입니다.");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setProgress(0);
        progress.setMax(100);
        progress.show();

        final Thread t = new Thread() {
            @Override
            public void run() {
                int time = 0;
                while (time<100){
                    try{
                        sleep(200);
                        time +=5;
                        progress.setProgress(time);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if (time == 100)
                    progress.dismiss();
            }
        };
        t.start();
    }
}
