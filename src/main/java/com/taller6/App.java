package com.taller6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Calculadora SoLiD
 * 
 * Esta implementación utiliza principios SOLID incluso estando en una sola clase:
 * - Single Responsibility: Cada operación está encapsulada (en el mapa).
 * - Open/Closed: Se pueden añadir nuevas operaciones sin modificar el bucle principal.
 * - Dependency Inversion: El motor de la calculadora depende de abstracciones (Funciones).
 */
public class App {

    // Interfaces funcionales para representar las operaciones (Abstracción)
    @FunctionalInterface
    interface BinaryOperation extends BiFunction<Integer, Integer, Double> {}

    @FunctionalInterface
    interface UnaryOperation extends Function<Integer, Double> { }

    // Registro de operaciones (Open/Closed Principle)
    private static final Map<String, BinaryOperation> BINARY_OPS = new HashMap<>();
    private static final Map<String, UnaryOperation> UNARY_OPS = new HashMap<>();

    static {
        // Operaciones Binarias
        BINARY_OPS.put("+", (a, b) -> (double) a + b);
        BINARY_OPS.put("-", (a, b) -> (double) a - b);
        BINARY_OPS.put("*", (a, b) -> (double) a * b);
        BINARY_OPS.put("/", (a, b) -> {
            if (b == 0) throw new ArithmeticException("No se puede dividir por cero");
            return (double) a / b;
        });

        // Operaciones Unarias
        UNARY_OPS.put("sqrt", a -> Math.sqrt(a));
        UNARY_OPS.put("ln", a -> {
            if (a <= 0) throw new ArithmeticException("Logaritmo natural no definido para <= 0");
            return Math.log(a);
        });
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("   Calculadora SoLiD - Taller 6");
        System.out.println("========================================");
        
        boolean running = true;
        while (running) {
            System.out.println("\nOperaciones disponibles:");
            System.out.println("Binarias: +, -, *, /");
            System.out.println("Unarias:  sqrt, ln");
            System.out.println("Escriba 'salir' para finalizar.");
            
            System.out.print("\nSeleccione una operación: ");
            String input = scanner.next().toLowerCase();

            if (input.equals("salir")) {
                running = false;
                continue;
            }

            try {
                if (BINARY_OPS.containsKey(input)) {
                    System.out.print("Ingrese el primer número entero: ");
                    int num1 = scanner.nextInt();
                    System.out.print("Ingrese el segundo número entero: ");
                    int num2 = scanner.nextInt();
                    
                    double result = BINARY_OPS.get(input).apply(num1, num2);
                    System.out.printf("Resultado (%d %s %d) = %.4f\n", num1, input, num2, result);
                
                } else if (UNARY_OPS.containsKey(input)) {
                    System.out.print("Ingrese el número entero: ");
                    int num = scanner.nextInt();
                    
                    double result = UNARY_OPS.get(input).apply(num);
                    System.out.printf("Resultado (%s de %d) = %.4f\n", input, num, result);
                
                } else {
                    System.out.println("Error: Operación no reconocida.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese números enteros válidos.");
                scanner.next(); // Limpiar el buffer
            } catch (ArithmeticException e) {
                System.out.println("Error Aritmético: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }

        System.out.println("¡Gracias por usar la Calculadora SoLiD!");
        scanner.close();
    }
}
