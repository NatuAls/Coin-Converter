package com.aluracursos.coinconverter.models;

public class WrongInputManager {
    public boolean option(String option){
        return option.matches("^([12345678])$");
    }

    public boolean amount(String amount){
        try{
            Double.parseDouble(amount);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
