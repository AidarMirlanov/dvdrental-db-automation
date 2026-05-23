package GsonDemo;

import com.google.gson.Gson;
import pojo.Car;

public class GsonDemo {
    public static void main(String[] args) {

        Car bmwX5 = new Car();

        // Превращаем JAVA в Json
        bmwX5.setColor("red");
        bmwX5.setBrand("BMW ");
        bmwX5.setModel("X5");
        bmwX5.setEngine("4.4");

        System.out.println(bmwX5);

        Gson gson = new Gson();

        String json = gson.toJson(bmwX5);

        System.out.println(json);




        // Превращаем Json в JAVA
        String audiPayload = "{\n" +
                "    \"brand\":\"Audi\",\n" +
                "    \"model\":\"Q7\",\n" +
                "    \"color\":\"White\",\n" +
                "    \"engine\":\"3.0\"\n" +
                "}";

        Car audiQ7 = gson.fromJson(audiPayload, Car.class);

        System.out.println(audiQ7);

    }
}
