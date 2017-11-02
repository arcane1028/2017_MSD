package example.com.jsh.ms_hw07_201302480;

/**
 * Created by JSH on 2017-10-27.
 */

public class SingerItem {
    private String name;
    private String birth;
    private String phone;
    private int resId;

    public SingerItem(String name, String birth, String date, int resId) {
        this.name = name;
        this.birth = birth;
        this.phone = date;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) { this.phone = phone; }

}
