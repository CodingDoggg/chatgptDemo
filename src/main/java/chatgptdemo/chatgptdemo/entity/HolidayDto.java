package chatgptdemo.chatgptdemo.entity;

import java.util.Objects;

public class HolidayDto {
    private String countryCode;
    private String countryDesc;
    private String holidayDate;

    private String holidayDesc;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryDesc() {
        return countryDesc;
    }

    public void setCountryDesc(String countryDesc) {
        this.countryDesc = countryDesc;
    }

    public String getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    //override hashcode and equals
    @Override
    public int hashCode() {
        return Objects.hash(countryCode, countryDesc, holidayDate, holidayDesc);
    }

    //override equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HolidayDto)) return false;
        HolidayDto holidayDto = (HolidayDto) obj;
        return Objects.equals(countryCode, holidayDto.countryCode) &&
                Objects.equals(countryDesc, holidayDto.countryDesc) &&
                Objects.equals(holidayDate, holidayDto.holidayDate) &&
                Objects.equals(holidayDesc, holidayDto.holidayDesc);
    }
}
