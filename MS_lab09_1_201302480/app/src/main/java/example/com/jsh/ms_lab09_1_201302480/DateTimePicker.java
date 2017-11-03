package example.com.jsh.ms_lab09_1_201302480;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import java.util.Calendar;

/**
 * Created by JSH on 2017-11-03.
 */

public class DateTimePicker extends LinearLayout {

    public static interface OnDateTimeChangedListener {
        void onDateTimeChanged(DateTimePicker view, int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute);
    }

    private OnDateTimeChangedListener listener;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private CheckBox enableTimeCheckBox;

    public DateTimePicker(Context context) {
        super(context);
        init(context);
    }

    public DateTimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.datetimepicker, this, true);

        Calendar calendar = Calendar.getInstance();

        final int curYear = calendar.get(Calendar.YEAR);
        final int curMonth = calendar.get(Calendar.MONTH);
        final int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        final int curHour = calendar.get(Calendar.HOUR_OF_DAY);
        final int curMinute = calendar.get(Calendar.MINUTE);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(curYear, curMonth, curDay, new OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                if (listener != null) {
                    listener.onDateTimeChanged(DateTimePicker.this, year, monthOfYear, dayOfMonth, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                }
            }
        });

        enableTimeCheckBox = (CheckBox) findViewById(R.id.enableTimeCheckBox);
        enableTimeCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                timePicker.setEnabled(isChecked);
                timePicker.setVisibility((enableTimeCheckBox).isChecked()? View.VISIBLE:View.INVISIBLE);
            }
        });
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                if (listener != null){
                    listener.onDateTimeChanged(
                            DateTimePicker.this, datePicker.getYear(),datePicker.getMonth(), datePicker.getDayOfMonth(),hourOfDay,minute
                    );
                }
            }
        });

        timePicker.setCurrentHour(curHour);
        timePicker.setCurrentHour(curMinute);
        timePicker.setEnabled(enableTimeCheckBox.isEnabled());
        timePicker.setVisibility(enableTimeCheckBox.isChecked()?View.VISIBLE:View.INVISIBLE);
    }
    public void setOnDateTimeChangedListener(OnDateTimeChangedListener dateTimeChangedListener){
        this.listener = dateTimeChangedListener;
    }
    public int getYear(){
        return datePicker.getYear();
    }
    public int getMonth(){
        return datePicker.getMonth();
    }
    public int getDayOfMonth(){
        return datePicker.getDayOfMonth();
    }
    public int getCurrentHour(){
        return timePicker.getCurrentHour();
    }
    public int getCurrentMinute(){
        return timePicker.getCurrentMinute();
    }
}
