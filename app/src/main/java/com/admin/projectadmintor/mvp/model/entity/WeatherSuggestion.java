package com.admin.projectadmintor.mvp.model.entity;

public class WeatherSuggestion {
    private WeatherSuggestionUv uv;
    private WeatherSuggestionCw cw;
    private WeatherSuggestionDrs drs;
    private WeatherSuggestionTrav trav;
    private WeatherSuggestionComf comf;
    private WeatherSuggestionAir air;
    private WeatherSuggestionSport sport;
    private WeatherSuggestionFlu flu;

    public WeatherSuggestionUv getUv() {
        return this.uv;
    }

    public void setUv(WeatherSuggestionUv uv) {
        this.uv = uv;
    }

    public WeatherSuggestionCw getCw() {
        return this.cw;
    }

    public void setCw(WeatherSuggestionCw cw) {
        this.cw = cw;
    }

    public WeatherSuggestionDrs getDrs() {
        return this.drs;
    }

    public void setDrs(WeatherSuggestionDrs drs) {
        this.drs = drs;
    }

    public WeatherSuggestionTrav getTrav() {
        return this.trav;
    }

    public void setTrav(WeatherSuggestionTrav trav) {
        this.trav = trav;
    }

    public WeatherSuggestionComf getComf() {
        return this.comf;
    }

    public void setComf(WeatherSuggestionComf comf) {
        this.comf = comf;
    }

    public WeatherSuggestionAir getAir() {
        return this.air;
    }

    public void setAir(WeatherSuggestionAir air) {
        this.air = air;
    }

    public WeatherSuggestionSport getSport() {
        return this.sport;
    }

    public void setSport(WeatherSuggestionSport sport) {
        this.sport = sport;
    }

    public WeatherSuggestionFlu getFlu() {
        return this.flu;
    }

    public void setFlu(WeatherSuggestionFlu flu) {
        this.flu = flu;
    }
}
