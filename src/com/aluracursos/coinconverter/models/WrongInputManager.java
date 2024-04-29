package com.aluracursos.coinconverter.models;

public class WrongInputManager {
    public boolean readOption(String option){
        return option.matches("^([123456])$");
    }

    public boolean readAmount(String amount){
        try{
            Double.parseDouble(amount);
            return true;
        }catch (Exception e){
            System.out.println("Debe ingresar un numero. Por favor intentelo de nuevo.\n\n");
            return false;
        }
    }

    public boolean readCoin(String coin){
        var ERROR_ISO = """
                Codigo ISO 4217 incorrecto. Ej: Codigo ISO del dolar Estadounidense [USD]
                Por favor intentelo de nuevo:
                \n""";

        if(coin.length() != 3){
            System.out.println(ERROR_ISO);
            return false;
        } else if (coin.matches("[A-Z a-z]*")){
            return true;
        }
        System.out.println(ERROR_ISO);
        return false;
    }
}
