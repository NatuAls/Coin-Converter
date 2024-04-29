package com.aluracursos.coinconverter.models;

public class Converter {
    private String baseCoin;
    private String targetCoin;
    private double conversionResult;

    public Converter(ExchangeRate exchangeRate){
        this.baseCoin = exchangeRate.base_code();
        this.targetCoin = exchangeRate.target_code();
        this.conversionResult = exchangeRate.conversion_result();
    }

    public String convert(double amount){
        return "\nEl valor %.2f [%s] corresponde al valor final de ==> %.2f [%S]\n"
                .formatted(amount, this.baseCoin, this.conversionResult, this.targetCoin);
    }
}