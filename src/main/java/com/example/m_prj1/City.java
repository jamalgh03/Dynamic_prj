package com.example.m_prj1;

public class City {
    private String CityName;
    private int Stage ;
    private int HotelCost;

    public City() {

    }

    public City(String cityName, int stage, int hotelCost) {
        CityName = cityName;
        Stage = stage;
        HotelCost = hotelCost;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public int getStage() {
        return Stage;
    }

    public void setStage(int stage) {
        Stage = stage;
    }

    public int getHotelCost() {
        return HotelCost;
    }

    public void setHotelCost(int hotelCost) {
        HotelCost = hotelCost;
    }

}
