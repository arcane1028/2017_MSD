package example.com.jsh.ms_hw07_201302480;

/**
 * Created by JSH on 2017-10-27.
 */

public class SingerItem {

    String name;
    String birth;
    String date;
    int resId;

    public SingerItem(String name, String birth) {
        this.name = name;
        this.birth = birth;
    }

    public SingerItem(String name, String birth, String date, int resId) {
        this.name = name;
        this.birth = birth;
        this.date = date;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
