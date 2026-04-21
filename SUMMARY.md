# Resumen de Interacción con Antigravity - Taller 6

Este documento resume el proceso de refactorización de la "Calculadora SoLiD" utilizando el asistente de IA Antigravity.

## 1. Misión Inicial
Se solicitó la creación de una calculadora en Java con operaciones binarias (+, -, *, /) y unarias (sqrt, ln) para números enteros, con la restricción inicial de "todo en una misma clase". 

**Código Inicial:** [App.java](../../src/main/java/com/taller6/App.java) (Monolítico).

---

## 2. Proceso de Refactorización Paso a Paso

### S - Single Responsibility Principle (Responsabilidad Única)
- **Prompt:** "haz la letra S... crea un nuevo paquete... TDD al rescate... Piensa en DDD (Extrae reglas de negocio en Value Objects)".
- **Acción de la IA:** Separó la interfaz de usuario (CLI) del motor de cálculo. Creó `UnaryOperationInput` como Value Object de dominio para validar que sqrt/ln reciban números válidos antes de procesar.
- **Resultado:** Paquete `com.taller6.solid.s`.

### O - Open/Closed Principle (Abierto/Cerrado)
- **Prompt:** "ahora haz la O".
- **Acción de la IA:** Introdujo interfaces para las operaciones. El motor central ya no tiene operaciones "quemadas". Se demostró agregando la operación de **Potencia (^)** sin tocar una sola línea del motor original.
- **Resultado:** Paquete `com.taller6.solid.o`.

### L - Liskov Substitution Principle (Sustitución de Liskov)
- **Prompt:** "haz la letra L".
- **Acción de la IA:** Reforzó el contrato de retorno. Creó un objeto `OperationResult` que gestiona éxitos y fallos. Esto permite sustituir la Suma por la División sin miedo a que el programa explote por excepciones no controladas (como división por cero).
- **Resultado:** Paquete `com.taller6.solid.l`.

### I - Interface Segregation Principle (Segregación de Interfaces)
- **Prompt:** "haz la letra I".
- **Acción de la IA:** Dividió las interfaces. Se creó `ComputableBinary` y `Descriptive`. Esto permitió que operaciones simples no estuvieran obligadas a implementar metadatos o descripciones que no necesitaban.
- **Resultado:** Paquete `com.taller6.solid.i`.

### D - Dependency Inversion Principle (Inversión de Dependencias)
- **Prompt:** "haz la letra D".
- **Acción de la IA:** Desacopló el sistema de logs. La calculadora ahora depende de una abstracción (`LoggerService`) en lugar de `System.out`. Permite inyectar diferentes loggers (Consola, Memoria, etc.) por constructor (Inyección de Dependencias).
- **Resultado:** Paquete `com.taller6.solid.d`.

---

## 3. Errores Corregidos por la IA durante el proceso
1. **Entorno sin Git:** Al intentar hacer commit desde la terminal, se detectó que `git` no estaba en el PATH del sistema. La IA guió la solución utilizando la API directa de GitHub para realizar los pushes.
2. **Estructura Maven:** En el primer intento de escritura, se mezclaron carpetas de "artifacts" con carpetas de código fuente. La IA corrigió la ruta de los archivos para cumplir con el estándar `src/main/java`.
3. **Validaciones de Dominio:** En la fase de raíz cuadrada, se identificó que la validación debía estar en el objeto de entrada (Value Object) para cumplir con el pensamiento DDD (Domain Driven Design), evitando que datos inválidos lleguen siquiera al motor de cálculo.

## 4. Guía de Ejecución
```bash
# Versión Inicial
javac src/main/java/com/taller6/App.java
java -cp src/main/java com.taller6.App

# Versión Final (DIP)
javac -d target src/main/java/com/taller6/solid/d/**/*.java
java -cp target com.taller6.solid.d.AppD (o similar según clase main)
```

---
*Este proyecto demuestra la evolución de un código "espagueti" hacia una arquitectura limpia y profesional guiada por principios SOLID.*
