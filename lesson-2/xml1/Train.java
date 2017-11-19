package lesson2.xml1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 11/19/2017.
 */
public class Train {

    private int id;
    private String from;
    private String to;
    private String date;
    private long departure;

    public static class Builder {
        Train instance=new Train();

        public Builder setId(int id) {
            instance.id=id;
            return this;
        }

        public Builder setFrom(String from) {
            instance.from=from;
            return this;
        }

        public Builder setTo(String to) {
            instance.to=to;
            return this;
        }

        public Builder setDate(String date) {
            instance.date=date;
            return this;
        }

        public Builder setDeparture(String departure) throws ParseException {
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date tDuration = timeFormat.parse(departure);
            long result = tDuration.getTime();
            instance.departure=result;
            return this;
        }

        public Train build() {
            return instance;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDeparture() {
        return departure;
    }

    public void setDeparture(long departure) {
        this.departure = departure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Train)) return false;

        Train train = (Train) o;

        if (id != train.id) return false;
        if (departure != train.departure) return false;
        if (from != null ? !from.equals(train.from) : train.from != null) return false;
        if (to != null ? !to.equals(train.to) : train.to != null) return false;
        return date != null ? date.equals(train.date) : train.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) (departure ^ (departure >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                ", departure=" + departure +
                '}';
    }
}
