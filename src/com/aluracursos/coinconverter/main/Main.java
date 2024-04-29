package com.aluracursos.coinconverter.main;

import com.aluracursos.coinconverter.models.Converter;
import com.aluracursos.coinconverter.models.ExchangeRate;
import com.aluracursos.coinconverter.models.HttpMethods;
import com.aluracursos.coinconverter.models.WrongInputManager;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HttpMethods httpMethods = new HttpMethods();
        Scanner scanner = new Scanner(System.in);
        WrongInputManager wrongInputManager = new WrongInputManager();

        int option = 0;
        double amount = 0;
        boolean defaultOption = false;
        var baseCoin = "";
        var targetCoin = "";


        System.out.println("\nBienvenido/a al Conversor de Monedas\n");
        try {
            while (option != 6){
                System.out.println("""
                        *************************************
                        1) Dolar[USD] ==> Peso Argentino[ARS]
                        2) Peso Argentino[ARS] ==> Dolar[USD]
                        3) Euro[EUR] ==> Peso Argentino[ARS]
                        4) Peso Argentino[ARS] ==> Euro[EUR]
                        5) Sleccionar otras monedas
                        6) Salir
                        *************************************
                        """);

                System.out.println("Elija una pocion valida:");
                var inputScanner = scanner.next();

                if(wrongInputManager.readOption(inputScanner)){  //Verifica que la opcion ingresada por el usuario sea correcta
                    option = Integer.parseInt(inputScanner);     //Seteo la variable option si la opcion ingresada por el usuario es valida
                    if (option != 6){                            //Si la opcion ingresada es el 6 me salteo todo el codigo del if
                        if (option == 5){
                            var stopWhile = false;
                            while(!stopWhile){
                                System.out.println("Ingrese el codio de la moneda base que desea convertir:");
                                baseCoin = scanner.next();
                                if (wrongInputManager.readCoin(baseCoin)){
                                    stopWhile = true;
                                }
                            }
                        }
                        var stopWhile = true;
                        while (stopWhile){
                            System.out.println("Ingrese el valor que desa convertir:");
                            inputScanner = scanner.next();
                            if (wrongInputManager.readAmount(inputScanner)){
                                amount = Double.parseDouble(inputScanner);
                                stopWhile = false;
                            }
                        }

                    }
                }

                switch (option){
                    case 1:
                        baseCoin = "USD";
                        targetCoin = "ARS";
                        break;
                    case 2:
                        baseCoin = "ARS";
                        targetCoin = "USD";
                        break;
                    case 3:
                        baseCoin = "EUR";
                        targetCoin = "ARS";
                        break;
                    case 4:
                        baseCoin = "ARS";
                        targetCoin = "EUR";
                        break;
                    case 5:
                        var stopWhile = false;
                        while(!stopWhile){
                            System.out.println("Ingrese el codio de la moneda objetivo:");
                            targetCoin = scanner.next();
                            if (wrongInputManager.readCoin(targetCoin)){
                                stopWhile = true;
                            }
                        }
                        break;
                    case 6:
                        System.out.println("\n¡Gracias por utilizar este conversor de monedas!");
                        System.out.println("\nFinalizando programa...");
                        break;
                    default:
                        System.out.println("La opcion ingreada es incorrecta. Por favor intentelo de nuevo.\n");
                        defaultOption = true;
                }

                if (option != 6 & !defaultOption){
                    ExchangeRate getResponse = httpMethods.get(baseCoin, targetCoin, amount);
                    if (Objects.equals(getResponse.result(), "error")){
                        System.out.println("\nError: " + getResponse.errorType() + "\n");
                    } else {
                        Converter converter = new Converter(getResponse);
                        System.out.println(converter.convert(amount));
                    }
                }
            }
        }catch (RuntimeException e){
            System.out.println("Error " + e.getMessage());
        }
    }
}
