package com.aluracursos.coinconverter.main;

import com.aluracursos.coinconverter.models.Converter;
import com.aluracursos.coinconverter.models.ExchangeRate;
import com.aluracursos.coinconverter.models.HttpMethods;
import com.aluracursos.coinconverter.models.WrongInputManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var httpMethods = new HttpMethods();
        var scanner = new Scanner(System.in);
        var wrongInputManager = new WrongInputManager();
        var option = 0;
        double amount = 0;
        ExchangeRate getResponse;
        Converter converter;

        System.out.println("\nBienvenido/a al Conversor de Moneda\n");
        try {
            while (option != 8){
                System.out.println("""
                        *************************************
                        
                        1) Dolar[USD] ==> Peso Argentino[ARS]
                        2) Peso Argentino[ARS] ==> Dolar[USD]
                        3) Euro[EUR] ==> Peso Argentino[ARS]
                        4) Peso Argentino[ARS] ==> Euro[EUR]
                        5) Dolar[USD] ==> Euro[EUR]
                        6) Dolar[USD] ==> Libra Esterlina[GBP]
                        7) Euro[EUR] ==> Libra Esterlina[GBP]
                        8) Salir
                        
                        *************************************
                        """);

                System.out.println("Elija una pocion valida:");
                var inputScanner = scanner.next();

                if(wrongInputManager.option(inputScanner)){
                    option = Integer.parseInt(inputScanner);
                    if (option != 8){
                        System.out.println("Ingrese el valor que desa convertir:");
                        inputScanner = scanner.next();
                        if (wrongInputManager.amount(inputScanner)){
                            amount = Double.parseDouble(inputScanner);
                        }
                        else {
                            option = 0;
                        }
                    }
                }

                switch (option){
                    case 1:
                        getResponse = httpMethods.get("USD", "ARS", amount);
                        converter = new Converter(getResponse);
                        System.out.println(converter.convert(amount));
                        break;
                    case 2:
                        getResponse = httpMethods.get("ARS", "USD", amount);
                        converter = new Converter(getResponse);
                        System.out.println(converter.convert(amount));
                        break;
                    case 3:
                        getResponse = httpMethods.get("EUR", "ARS", amount);
                        converter = new Converter(getResponse);
                        System.out.println(converter.convert(amount));
                        break;
                    case 4:
                        getResponse = httpMethods.get("ARS", "EUR", amount);
                        converter = new Converter(getResponse);
                        System.out.println(converter.convert(amount));
                        break;
                    case 5:
                        getResponse = httpMethods.get("USD", "EUR", amount);
                        converter = new Converter(getResponse);
                        System.out.println(converter.convert(amount));
                        break;
                    case 6:
                        getResponse = httpMethods.get("USD", "GBP", amount);
                        converter = new Converter(getResponse);
                        System.out.println(converter.convert(amount));
                        break;
                    case 7:
                        getResponse = httpMethods.get("EUR", "GBP", amount);
                        converter = new Converter(getResponse);
                        System.out.println(converter.convert(amount));
                        break;
                    case 8:
                        System.out.println("\n¡Gracias por utilizar este conversor de moneda!");
                        System.out.println("\nFinalizando programa...");
                        break;
                    default:
                        System.out.println("La opcion ingreada es incorrecta. Por favor intentelo de nuevo.");
                }
            }
        }catch (RuntimeException e){
            System.out.println("Error " + e.getMessage());
        }
    }
}