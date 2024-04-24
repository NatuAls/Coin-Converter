package com.aluracursos.coinconverter.models;

public class Converter {
    private String baseCoin;
    private String targetCoin;
    private double result;

    public Converter(ExchangeRate exchangeRate){
        this.baseCoin = exchangeRate.base_code();
        this.targetCoin = exchangeRate.target_code();
        this.result = exchangeRate.conversion_result();
    }

    public String convert(double amount){
        return "El valor %.2f [%s] corresponde al valor final de ==> %.2f [%S]\n"
                .formatted(amount, this.baseCoin, this.result, this.targetCoin);
    }
}