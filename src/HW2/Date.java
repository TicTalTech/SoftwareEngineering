package HW2;

/**
 * A class that represents a date by its day, month and year
 */
public class Date {
    private int month;
    private int day;
    private int year;

    public Date(int year, int month, int day) {
        setDay(day);
        setMonth(month);
        setYear(year);
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
        if (month >= 1 && month <= 12)
            this.month = month;
        else
            this.month = 1;
    }

    public void setDay(int day) {
        if (day >= 1 && this.day <= 31)
            this.day = day;
        else
            this.day = 1;
    }

    public void setYear(int year) {
        if (year >= -3999 && year <= 3999)
            this.year = year;
        else
            this.year = 0;
    }

    @Override
    public boolean equals(Object date) {
        if (date == null) {
            return false;
        }
        if (!(date instanceof Date)) {
            return false;
        }
        if (hashCode() != date.hashCode()) {
            return false;
        }
        if (((Date) date).getYear() == this.year && ((Date) date).getMonth() == this.month
                && ((Date) date).getDay() == this.day) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + String.format("%02d", day) + "/" + String.format("%02d", month) + "/"
                + String.format("%04d", year);

    }

    @Override
    /**
     * A hash code where the code is a number that represents the days since the minimum date, plus one.
     */
    public int hashCode() {
        return (this.year + 3999) * 365 + (this.month - 1) * 12 + this.day;
    }
}
