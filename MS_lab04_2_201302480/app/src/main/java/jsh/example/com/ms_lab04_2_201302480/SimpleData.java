package jsh.example.com.ms_lab04_2_201302480;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JSH on 2017-09-25.
 */

public class SimpleData implements Parcelable {
    private int number;
    private String message;

    public SimpleData(int num, String msg){
        number = num;
        message = msg;
    }
    public SimpleData(Parcel src){
        number = src.readInt();
        message = src.readString();
    }
    @SuppressWarnings("unchecked")
    public static final Creator<SimpleData> CREATOR = new Creator<SimpleData>() {
        @Override
        public SimpleData createFromParcel(Parcel source) {
            return new SimpleData(source);
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
