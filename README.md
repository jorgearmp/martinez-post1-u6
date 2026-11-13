# ACTIVIDAD POST-CONTENIDO — UNIDAD 6
## DIAGNÓSTICO Y REFACTORIZACIÓN DE ANTIPATRONES: SISTEMA DE GESTIÓN DE PEDIDOS
**Estudiante:** Jorge Armando Martínez Peinado (Cód. 1152162)  
**Curso:** Patrones de Diseño de Software (1156701-A)  
**Institución:** Universidad Francisco de Paula Santander (UFPS)  

---

## 1. DIAGNÓSTICO DE ANTIPATRONES EN EL CÓDIGO ORIGINAL

| Antipatrón Identificado | Manifestación en el Código Original | Tratamiento Aplicado en la Refactorización |
| :--- | :--- | :--- |
| **God Object (Clase Dios)** | La clase `GestorPedidos` acumulaba 6 razones distintas para cambiar: validación de clientes, control de mora financiera, cálculo de descuentos por tipo de cliente, cálculo de impuestos, validación de stock y persistencia. | Se descompuso en servicios altamente cohesivos: `ValidadorStockService`, `HistorialMoraClienteService` y `LiquidadorPrecioService`. |
| **Spaghetti Code** | Múltiples niveles de condicionales `if-else` anidados para evaluar estados y categorías en un solo bloque. | Se aplanó la lógica con cláusulas de guarda (*Guard Clauses*) y separación de algoritmos. |
| **Lava Flow** | Código de validación antiguo sin uso y variables zombis en el flujo de pedidos. | Eliminación de código muerto respaldado por pruebas de caracterización. |

---

## 2. INSTRUCCIONES DE EJECUCIÓN
```bash
cd pedidos-service
mvn clean compile
mvn exec:java
```
