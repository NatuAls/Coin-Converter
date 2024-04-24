package com.aluracursos.coinconverter.models;

public record ExchangeRate(String base_code, String target_code, double conversion_result) {
}