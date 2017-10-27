package example.com.jsh.ms_lab08_2_201302480;

/**
 * Created by JSH on 2017-10-27.
 */

public class SingerItem {

    String name;
    String mobile;
    int year;
    int resId;

    public SingerItem(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public SingerItem(String name, String mobile, int year, int resId) {
        this.name = name;
        this.mobile = mobile;
        this.year = year;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
