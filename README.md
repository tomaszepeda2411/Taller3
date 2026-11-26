# Taller3
Aplicación de consola desarrollada en Java que simula una competencia de monstruos en distintos minijuegos temáticos. El sistema gestiona la carga y validación de archivos de entrada, sesiones de jugadores, selección estratégica de monstruos y habilidades especiales, historial de partidas y ranking global persistente.

## Estructura del Proyecto
```text
Taller3/
├── src/
│   ├── dominio/
│   │   ├── Monstruo.java
│   │   ├── Zombi.java
│   │   ├── Vampiro.java
│   │   ├── Momia.java
│   │   ├── HombreLobo.java
│   │   ├── Mordedor.java
│   │   ├── Volador.java
│   │   ├── Regenerable.java
│   │   ├── Furioso.java
│   │   ├── VisitorMinijuego.java
│   │   ├── Minijuego.java
│   │   ├── MinijuegoBase.java
│   │   ├── CarreraCementerio.java
│   │   ├── CazaCalabazas.java
│   │   ├── LanzamientoLapidas.java
│   │   ├── SobrevivirAmanecer.java
│   │   ├── Jugador.java
│   │   └── Partida.java
│   └── logica/
│       ├── Sistema.java
│       ├── SistemaImpl.java
│       └── Main.java
├── Monstruos.txt
├── Registros.txt
└── README.md
```
## Arquitectura y Diseño Orientado a Objetos
### Separación de Capas (dominio y lógica):
- Logica: contiene la interfaz Sistema, su implementación SistemaImpl (gestión de estado, cálculos y ordenamiento) y Main (interacción por consola y lectura de archivos).
- Dominio: contiene únicamente las entidades del modelo, jerarquías de herencia e interfaces de comportamiento, sin lógica de entrada/salida (Scanner o lectura de archivos).

### Patrón Visitor (VisitorMinijuego):
Cada minijuego implementa VisitorMinijuego para calcular el potencial de cada tipo de monstruo (visitarZombi, visitarVampiro, visitarMomia, visitarHombreLobo) mediante doble despacho (monstruo.aceptar(minijuego)), evitando el uso de instanceof o condicionales por tipo.

### Interfaces de Habilidades y Polimorfismo:
Las habilidades (Mordedor, Volador, Regenerable, Furioso) se modelan como interfaces independientes implementadas según las capacidades de cada monstruo. Si la habilidad elegida coincide con la habilidad premiada del minijuego, el puntaje obtiene un multiplicador de x1.5.

### Estructuras de Datos y Ordenamiento Manual:
Se emplean ArrayList para colecciones principales (jugadores, monstruos, minijuegos) y LinkedList para el historial cronológico de la sesión.
El ranking global se ordena manualmente mediante algoritmo burbuja (de mayor a menor puntaje, desempatando alfabéticamente por nombre de jugador).

### Requisitos y Ejecución
Ejecución:

- Abrir el proyecto en Eclipse (o compilar desde src).
- Asegurar que Monstruos.txt y Registros.txt se encuentren en la raíz del proyecto (Taller3/).
- Ejecutar logica.Main.
