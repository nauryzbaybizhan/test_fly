package test;

import com.google.gson.*;
import test.model.Ticket;
import test.util.TicketUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Ticket> ticketList = new ArrayList<>();
        String fileName = "tickets.json";
        try {
            JsonParser jsonParser = new JsonParser();
            String someJson = Files.readString(Paths.get(fileName));
            Gson gson = new Gson();
            JsonObject ticketObj = jsonParser.parse(someJson).getAsJsonObject();
            JsonArray tickets = ticketObj.get("tickets").getAsJsonArray();
            for (JsonElement jsonElement : tickets) {
                Ticket ticket = gson.fromJson(jsonElement, Ticket.class);
                ticketList.add(ticket);
            }
            String s = TicketUtil.averageFlight(ticketList);
            String ave = "Среднее время полета между городами Владивосток и Тель-Авив займет " + s + " минут(ы)";
            System.out.println(ave);
            try (PrintWriter printWriter = new PrintWriter("result.txt")) {
                printWriter.write(ave);
            }
        } catch (IOException e) {
            System.out.println("Не найден " + fileName);
            e.printStackTrace();
        } catch (IllegalStateException | NullPointerException e) {
            throw new RuntimeException("Ошибка парсинга", e);
        }
    }
}
