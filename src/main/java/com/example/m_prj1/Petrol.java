package com.example.m_prj1;

public class Petrol {
    private	int Cost = Integer.MAX_VALUE;
    private	String Path = null;

    public Petrol() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Petrol(int cost, String path) {
        super();
        Cost = cost;
        Path = path;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }


}
