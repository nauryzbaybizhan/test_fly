package test.util;

import test.model.Ticket;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TicketUtil {

    public static String averageFlight(List<Ticket> tickets) {
        var count = tickets.stream().mapToLong(ticket -> getMinutesOfFlight(getDateFromTicketStr(ticket.departureDate, ticket.departureTime),
                getDateFromTicketStr(ticket.arrivalDate, ticket.arrivalTime))).sum();
        return String.valueOf(count / tickets.size());
    }

    public static long percentileFlight(List<Ticket> tickets, double percentile) {
        List<Long> collect = tickets.stream().map(ticket -> getMinutesOfFlight(getDateFromTicketStr(ticket.departureDate, ticket.departureTime),
                getDateFromTicketStr(ticket.arrivalDate, ticket.arrivalTime))).collect(Collectors.toList());
        Collections.sort(collect);
        var index = (int) Math.ceil(percentile / 100.0 * collect.size());
        return collect.get(index-1);
    }

    private static Date getDateFromTicketStr(String date, String time) {
        try {
            var calendar = new GregorianCalendar();
            String[] splitDate = date.split("\\.");
            String[] splitTime = time.split(":");
            calendar.set(Integer.parseInt("20" + splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]),
                    Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]), 0);
            return calendar.getTime();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new Date();
        }
    }

    private static long getMinutesOfFlight(Date departure, Date arrival) {
        long diff = arrival.getTime() - departure.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(diff);
    }
}
