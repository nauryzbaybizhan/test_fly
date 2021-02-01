package test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import test.model.Ticket;
import test.util.TicketUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Ticket> ticketList = new ArrayList<>();
        var fileName = "tickets.json";
        try {
            var jsonParser = new JsonParser();
            String someJson = Files.readString(Paths.get(fileName));
            var gson = new Gson();
            var ticketObj = jsonParser.parse(someJson).getAsJsonObject();
            var tickets = ticketObj.get("tickets").getAsJsonArray();
            for (JsonElement jsonElement : tickets) {
                var ticket = gson.fromJson(jsonElement, Ticket.class);
                ticketList.add(ticket);
            }
            String s = TicketUtil.averageFlight(ticketList);
            var aveStr = "Среднее время полета между городами Владивосток и Тель-Авив займет " + s + " минут(ы)";
            var percentile = 90;
            var percStr = percentile + "-й процентиль времени полета между городами  Владивосток и Тель-Авив равен "
                    + TicketUtil.percentileFlight(ticketList, percentile);
            System.out.println(aveStr);
            System.out.println(percStr);
        } catch (IOException e) {
            System.out.println("Не найден " + fileName);
            e.printStackTrace();
        } catch (IllegalStateException | NullPointerException e) {
            throw new RuntimeException("Ошибка парсинга", e);
        }
    }
}
