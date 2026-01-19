 # Proyecto: Simulación de Centro de Copias (Concurrencia en Java)

Este proyecto es una simulación de un sistema de centro de copias donde múltiples hilos (**Estudiantes**) compiten por un número limitado de recursos compartidos (**Máquinas de fotocopias**). Se ha desarrollado para la asignatura de **Programación de Servicios y Procesos (PSP)**.

## Escenario de la simulación
- **Entidades**: 5 Estudiantes independientes (Hilos).
- **Recursos**: 2 Máquinas de copias disponibles.
- **Ciclo de Vida**: Cada estudiante estudia, va al centro de copias y solicita una máquina, realiza sus copias y la libera para volver a estudiar.
- **Duración**: La simulación se ejecuta de forma ininterrumpida durante **20 segundos**.

## Conceptos Técnicos Aplicados

### 1. Programación Multihilo
Uso de la interfaz `Runnable` para definir el comportamiento de los hilos y la clase `Thread` para gestionar su ejecución concurrente.

### 2. Sincronización y Monitores
Se ha implementado un **Monitor** (`CentroCopias.java`) que garantiza la **Exclusión mutua**:
- **`synchronized`**: Para asegurar que solo un hilo modifique el estado de las máquinas a la vez.
- **`wait()`**: Los estudiantes entran en espera pasiva si no hay máquinas disponibles (evitando el consumo innecesario de CPU o *espera activa*).
- **`notifyAll()`**: Se notifica a todos los hilos en espera cuando una máquina queda libre.

### 3. Gestión de Recursos (Pool)
En lugar de un simple contador, se gestiona un **array de booleanos** para identificar qué máquina específica (Máquina 1 o Máquina 2) está utilizando cada estudiante.

### 4. Control de Ciclo de Vida
- **Interrupciones**: Uso de `interrupt()` para finalizar los hilos de forma ordenada tras el tiempo establecido.
- **Sincronización de cierre**: Uso de `join()` en el hilo principal para asegurar que las estadísticas finales se muestren solo cuando todos los hilos hayan terminado su ejecución.


