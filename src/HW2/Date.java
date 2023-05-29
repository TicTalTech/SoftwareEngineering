package HW2;

public class Date {
    private int month;
    private int day;
    private int year;

    public Date(int month, int day, int year) {
        if (month >= 1 && month <= 12)
            this.month = month;
        else
            this.month = 1;
        if (day >= 1 && this.day <= 31)
            this.day = day;
        else
            this.day = 1;
        if (year >= -3999 && year <= 3999)
            this.year = year;
        else
            this.year = 0;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object date) {
        if ((((Date) date).getYear() == this.year) && (((Date) date).getMonth() == this.month)
                && (((Date) date).getDay() == this.day))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return " " + String.format("%02d", day) + "//" + String.format("%02d", month) + "//"
                + String.format("%04d", year);

    }

    @Override
    public int hashCode() {
        return (this.year + 3999) * 365 + (this.month - 1) * 12 + this.day - 1;
    }
}
