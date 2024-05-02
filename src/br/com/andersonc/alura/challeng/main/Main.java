package br.com.andersonc.alura.challeng.main;

import br.com.andersonc.alura.challeng.models.Converter;

import java.util.Scanner;

public class Main {
    private static final String USD = "USD";
    private static final String EUR = "EUR";
    private static final String BRL = "BRL";
    private static final String CNY = "CNY";

    public static void main(String[] args) {
        Converter inconverter = new Converter() {
        };

        Scanner scanner = new Scanner(System.in);

        int option = -1;
        try {
            while (option != 0) {
                displayMenu();
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        convertDisplay(inconverter, USD, EUR, "USD", "EUR", scanner);
                        break;
                    case 2:
                        convertDisplay(inconverter, EUR, USD, "EUR", "USD", scanner);
                        break;
                    case 3:
                        convertDisplay(inconverter, BRL, USD, "BRL", "USD", scanner);
                        break;
                    case 4:
                        convertDisplay(inconverter, USD, BRL, "USD", "BRL", scanner);
                        break;
                    case 5:
                        convertDisplay(inconverter, EUR, BRL, "EUR", "BRL", scanner);
                        break;
                    case 6:
                        convertDisplay(inconverter, BRL, EUR, "BRL", "EUR", scanner);
                        break;

                    case 0:
                        System.out.println("Fechando Programa!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void displayMenu() {
        System.out.println("""
                Escolha uma opção válida:
                [ 1 ] -Converter USD para EUR
                [ 2 ] - Converter EUR para USD
                [ 3 ] - Converter BRL para USD
                [ 4 ] - Converter USD para BRL
                [ 5 ] - Converter EUR para BRL
                [ 6 ] - Converter BRL para EUR
                [ 0 ] -  Sair
                -->
                """);
    }

    public static void convertDisplay(Converter converter, String fromCurrency, String toCurrency, String fromSymbol, String toSymbol, Scanner scanner) {
        System.out.print("Digite o valor em " + fromSymbol + ": ");
        double amount = scanner.nextDouble();
        double convertedAmount = converter.equals(amount, fromCurrency, toCurrency);
        System.out.println(amount + " " + fromSymbol + " = " + convertedAmount + " " + toSymbol);
    }
}