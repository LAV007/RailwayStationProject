package sample.patternDAO.layerEntity;

import java.util.Objects;

public class Ticket {

    private int id;
    private String date;
    private String time;
    private String station;

    public Ticket(String date, String time, String station) {
        this.date = date;
        this.time = time;
        this.station = station;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Objects.equals(date, ticket.date) &&
                Objects.equals(time, ticket.time) &&
                Objects.equals(station, ticket.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, station);
    }

    @Override
    public String toString() {
        return "Ticket: " + date + time + station;
    }
}
