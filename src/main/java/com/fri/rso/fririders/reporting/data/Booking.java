package com.fri.rso.fririders.reporting.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class Booking implements PdfSerializable{
    private int id;
    private int idAccommodation;
    private String idUser;
    private Date fromDate;
    private Date toDate;

    public Booking(){
        this.id = 0;
        this.idAccommodation = 0;
        this.idUser = null;
        this.fromDate = null;
        this.toDate = null;
    }

    public Booking(int id, int idAccommodation, String idUser, Date fromDate, Date toDate) {
        this.id = id;
        this.idAccommodation = idAccommodation;
        this.idUser = idUser;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccommodation() {
        return idAccommodation;
    }

    public void setIdAccommodation(int idAcommodation) {
        this.idAccommodation = idAcommodation;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public Map<String, Function<Booking,String>> serializationData() {
        Map<String,Function<Booking,String>> data = new LinkedHashMap<>();
        final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        data.put("ID" , b -> String.valueOf(b.getId()));
//        data.put("Accommodation ID", b -> String.valueOf(b.getIdAccommodation()));
        data.put("User ID", Booking::getIdUser);
        data.put("From date", b -> df.format(b.getFromDate()));
        data.put("Till date", b -> df.format(b.getToDate()));
        return data;
    }
}
