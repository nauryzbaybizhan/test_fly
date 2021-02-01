package test.util;

import test.model.Ticket;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketUtil {

    public static String averageFlight(List<Ticket> tickets) {
        long count = tickets.stream().mapToLong(ticket -> getMinutesOfFlight(getDateFromTicketStr(ticket.departureDate, ticket.departureTime),
                getDateFromTicketStr(ticket.arrivalDate, ticket.arrivalTime))).sum();
        return String.valueOf(count / tickets.size());
    }

    public static Date getDateFromTicketStr(String date, String time) {
        try {
            Calendar calendar = new GregorianCalendar();
            String[] splitDate = date.split("\\.");
            String[] splitTime = time.split(":");
            calendar.set(Integer.parseInt("20" + splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]),
                    Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]), 0);
            return calendar.getTime();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new Date();
        }
    }

    public static long getMinutesOfFlight(Date departure, Date arrival) {
        long diff = arrival.getTime() - departure.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(diff);
    }
}
