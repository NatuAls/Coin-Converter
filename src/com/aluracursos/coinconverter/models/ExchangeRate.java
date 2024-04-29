package com.aluracursos.coinconverter.models;

import com.google.gson.annotations.SerializedName;

public record ExchangeRate(String base_code,
                           String target_code,
                           double conversion_result,
                           String result,
                           @SerializedName("error-type") String errorType) {
}