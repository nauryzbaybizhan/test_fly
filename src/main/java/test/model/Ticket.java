package test.model;

import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("origin")
    public String origin;

    @SerializedName("origin_name")
    public String originName;

    @SerializedName("destination_name")
    public String destinationName;

    @SerializedName("departure_date")
    public String departureDate;

    @SerializedName("departure_time")
    public String departureTime;

    @SerializedName("arrival_date")
    public String arrivalDate;

    @SerializedName("arrival_time")
    public String arrivalTime;

    @SerializedName("carrier")
    public String carrier;

    @SerializedName("stops")
    public int stops;

    @SerializedName("price")
    public double price;

    @Override
    public String toString() {
        return "Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}
