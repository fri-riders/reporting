package com.fri.rso.fririders.reporting.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class Accommodation  implements PdfSerializable{

     private Long id;

    private String name;
    private String location;
    private String description;
    private int capacity;
    private Double pricePerDay;

    public Accommodation() {
    }

    public Accommodation(long id, String name, String location, String description, int capacity, Double pricePerDay) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
        this.pricePerDay = pricePerDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", pricePerDay=" + pricePerDay +
                '}';
    }

    @Override
    public Map<String, Function<Accommodation,String>> serializationData() {
        Map<String,Function<Accommodation,String>> data = new LinkedHashMap<>();
        data.put("ID" , a -> a.getId().toString());
        data.put("Name", Accommodation::getName);
        data.put("Location", Accommodation::getLocation);
        data.put("Description", Accommodation::getDescription);
        data.put("Capacity", a -> String.valueOf(a.getCapacity()+ " rooms"));
        data.put("Price per day", a -> a.getPricePerDay().toString() + " €");
        return data;
    }
}
