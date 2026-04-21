# Taller 6 - Fundamentos de Software

Este repositorio contiene la implementación de una calculadora siguiendo los principios SOLID de forma iterativa.

## Entregables
- **Versión Inicial:** [App.java](src/main/java/com/taller6/App.java)
- **Versión Final Refactorizada:** [Package solid.d](src/main/java/com/taller6/solid/d)
- **Resumen de la Interacción con la IA:** [SUMMARY.md](SUMMARY.md)
- **Evidencia TDD:** [src/test/java/com/taller6/solid](src/test/java/com/taller6/solid)
- **Evidencia DDD:** [Paquetes domain en cada iteración](src/main/java/com/taller6/solid)

## Respuestas del Taller
**1. ¿Qué responsabilidades tiene exactamente esta clase?**
Carga el motor de la calculadora, gestiona el menú de usuario, lee los datos de entrada y ejecuta las operaciones matemáticas. Tiene demasiadas responsabilidades (Rompe SRP).

**2. Si quiero tener 2 menús distintos, ¿qué debería modificar?**
En la versión inicial, habría que duplicar la lógica de `main`. En la versión refactorizada, simplemente se crea una nueva clase de interfaz de usuario que use la clase `Calculator` ya existente.

**3. ¿Cómo adiciono operaciones nuevas (ej. Potencia)?**
En la versión final, solo creas una clase que implemente `BinaryOperation` y la registras. No se rompe nada existente (Cumple OCP).

**4. ¿Qué pasa si ingreso validaciones de dominio (ej. no dividir por cero, no logaritmos negativos)?**
Se colocan en los **Value Objects** de entrada (ej. `UnaryOperationInput`), asegurando que los datos sean válidos antes de que la operación se intente ejecutar (Enfoque DDD).
