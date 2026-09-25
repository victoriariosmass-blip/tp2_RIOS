package com.aydsii.tp2.dto;

import java.util.Map;

public class ConversionResponseDTO2{
    //para rtas de tipo {"rates": {"ARS": 950.5}} o sea para desanidar
    private Map<String, Double> rates;
    //para rtas de formato directo {"rate": 950.5}
    private Double rate;

    //constructor
    public ConversionResponseDTO2() {}

    //setters, getters
    public Map<String, Double> getRates() { return rates; }
    public void setRates(Map<String, Double> rates) { this.rates = rates; }

    public Double getRate() { return rate; }
    public void setRate(Double rate) { this.rate = rate; }
}