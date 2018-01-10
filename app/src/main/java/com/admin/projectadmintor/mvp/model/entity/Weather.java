package com.admin.projectadmintor.mvp.model.entity;

public class Weather {
    private String citycode;
    private String rdesc;
    private WeatherSuggestion suggestion;
    private String cityname;
    private WeatherForecast[] forecast;
    private int rcode;

    public String getCitycode() {
        return this.citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getRdesc() {
        return this.rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public WeatherSuggestion getSuggestion() {
        return this.suggestion;
    }

    public void setSuggestion(WeatherSuggestion suggestion) {
        this.suggestion = suggestion;
    }

    public String getCityname() {
        return this.cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public WeatherForecast[] getForecast() {
        return this.forecast;
    }

    public void setForecast(WeatherForecast[] forecast) {
        this.forecast = forecast;
    }

    public int getRcode() {
        return this.rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }
}
