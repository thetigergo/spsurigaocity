package sample;

/**
 *
 * @author Administrator
 */
public class Car {

    private String mModel, mManufacturer, mColor;
    private Integer mYear;
    private Boolean isYesNo;

    public Car(String model, int year, String maker, String color) {
        mModel = model;
        mManufacturer = maker;
        mColor = color;
        mYear = year;
    }

    public Boolean getIsYesNo() {
        return isYesNo;
    }

    public void setIsYesNo(Boolean value) {
        isYesNo = value;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String value) {
        mModel = value;
    }

    public String getManufacturer() {
        return mManufacturer;
    }

    public void setManufacturer(String value) {
        mManufacturer = value;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String value) {
        mColor = value;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int value) {
        mYear = value;
    }
}
