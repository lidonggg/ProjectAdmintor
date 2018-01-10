package com.admin.projectadmintor.mvp.model.entity;

public class WeatherForecast {
    private String pop;
    private String date;
    private String hum;
    private String uv;
    private String vis;
    private WeatherForecastAstro astro;
    private String pres;
    private String pcpn;
    private WeatherForecastTmp tmp;
    private WeatherForecastCond cond;
    private WeatherForecastWind wind;

    public String getPop() {
        return this.pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return this.hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getUv() {
        return this.uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getVis() {
        return this.vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public WeatherForecastAstro getAstro() {
        return this.astro;
    }

    public void setAstro(WeatherForecastAstro astro) {
        this.astro = astro;
    }

    public String getPres() {
        return this.pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getPcpn() {
        return this.pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public WeatherForecastTmp getTmp() {
        return this.tmp;
    }

    public void setTmp(WeatherForecastTmp tmp) {
        this.tmp = tmp;
    }

    public WeatherForecastCond getCond() {
        return this.cond;
    }

    public void setCond(WeatherForecastCond cond) {
        this.cond = cond;
    }

    public WeatherForecastWind getWind() {
        return this.wind;
    }

    public void setWind(WeatherForecastWind wind) {
        this.wind = wind;
    }
}
