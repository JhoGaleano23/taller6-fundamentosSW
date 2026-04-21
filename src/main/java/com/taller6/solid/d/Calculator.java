package com.taller6.solid.d;

import com.taller6.solid.d.domain.BinaryOperationInput;
import com.taller6.solid.d.domain.OperationResult;
import com.taller6.solid.d.operations.BinaryOperation;
import com.taller6.solid.d.services.LoggerService;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private final Map<String, BinaryOperation> operations = new HashMap<>();
    private final LoggerService logger;

    public Calculator(LoggerService logger) {
        this.logger = logger;
    }

    public void registerBinary(String symbol, BinaryOperation op) {
        operations.put(symbol, op);
    }

    public OperationResult calculateBinary(String op, BinaryOperationInput input) {
        if (!operations.containsKey(op)) return null;
        OperationResult result = operations.get(op).execute(input);
        logger.log("Ejecutada operación con Símbolo: " + op + " | Resultado: " + result.getValue());
        return result;
    }
}
