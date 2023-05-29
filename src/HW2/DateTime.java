package HW2;

public class DateTime extends Date {
    private int hour;
    private int minute;

    public DateTime(int month, int day, int year, int hour, int minute) {
        super(month, day, year);
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateTime)) {
            return false;
        }
        return super.equals(obj) && this.hour == ((DateTime) obj).hour && this.minute == ((DateTime) obj).minute;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 3600 + hour * 60 + minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
        if (hour > 23) {
            this.hour = 0;
        }
    }

    public void setMinute(int minute) {
        this.minute = minute;
        if (minute > 59) {
            this.minute = 0;
        }
    }
}
