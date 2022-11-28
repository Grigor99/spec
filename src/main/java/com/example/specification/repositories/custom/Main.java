package com.example.specification.repositories.custom;

public class Main {
    private static Integer number = 30;

    public static void main(String[] args) {

        switch (number) {
            case 20 -> System.out.println("20");
            case 10 -> System.out.println("10");
            case null-> System.out.println("");
            default -> System.out.println("def");
        }
    }
}
