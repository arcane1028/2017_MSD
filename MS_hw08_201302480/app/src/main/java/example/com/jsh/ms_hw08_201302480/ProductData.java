package example.com.jsh.ms_hw08_201302480;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JSH on 2017-11-09.
 */

public class ProductData implements Parcelable {

    private int imageId;
    private String productName;
    private int productPrice;
    private String productText;
    private String productMaker;

    public ProductData(int imageId, String productMaker, String productName, int productPrice, String productText)
    {
        this.imageId = imageId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productText = productText;
        this.productMaker = productMaker;
    }

    protected ProductData(Parcel in) {
        imageId = in.readInt();
        productName = in.readString();
        productPrice = in.readInt();
        productText = in.readString();
        productMaker = in.readString();

    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageId);
        parcel.writeString(productName);
        parcel.writeInt(productPrice);
        parcel.writeString(productText);
        parcel.writeString(productMaker);
    }

    public String getProductText() {
        return productText;
    }

    public void setProductText(String productText) {
        this.productText = productText;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getProductMaker() {
        return productMaker;
    }

    public void setProductMaker(String productMaker) {
        this.productMaker = productMaker;
    }
}
