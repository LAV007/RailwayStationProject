package sample.patternDAO.layerEntity;

import java.util.Objects;

public class Request {
    int passengerId;
    int ticketId;

    public Request(int passengerId, int ticketId) {
        this.passengerId = passengerId;
        this.ticketId = ticketId;
    }

    public Request() {
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return passengerId == request.passengerId &&
                ticketId == request.ticketId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerId, ticketId);
    }

    @Override
    public String toString() {
        return "Request{" +
                "passengerId=" + passengerId +
                ", ticketId=" + ticketId +
                '}';
    }
}
