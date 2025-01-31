# Workout Planner Http Server

Este proyecto es un servidor HTTP que permite a los usuarios obtener rutinas de entrenamiento dinámicas basadas en tipo (fuerza, cardio, flexibilidad) y nivel (principiante, intermedio, avanzado). También permite servir archivos estáticos como HTML, CSS y JavaScript.

## Estructura del proyecto

```
├── src/
│   ├── main
│   │   ├── java/com/eci/arep/httpserver
│   │   │   ├── HttpServer.java         
│   │   │   ├── WorkoutPlanner.java   
│   │   ├── resources/
│   │   │   ├── img
│   │   │   │   ├── pagina.png             
│   │   │   │   ├── Pruebas.png
│   │   │   │   ├── resultado.png                           
│   │   │   ├── static
│   │   │   │   ├── index.html           
│   │   │   │   ├── style.css            
│   │   │   │   ├── script.js            
│   │   │   │   ├── fondo.jpg            
│   ├── test
│   │   ├── java
│   │   │   ├──WorkoytTest.java  
├── README.md
|__ .gitignore
|__ LICENSE                        
└── pom.xml
```                     

## Para comenzar

Sigue estas instrucciones para obtener una copia del proyecto y ponerlo en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas.

### Prerequisitos

Asegúrate de tener las siguientes herramientas instaladas en tu sistema:

- [Java 8 o superior](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Apache Maven](https://maven.apache.org/)

Para verificar las versiones instaladas:

```bash
java -version
mvn -version
```

### Intalación

Sigue los pasos a continuación para configurar el entorno de desarrollo:

1. Clona este repositorio en tu máquina local, probarlo en power shell o en el ide:

```bash
git clone <https://github.com/YeltzynS/AREP_TALLER01.git>
```

2. Navega al directorio del proyecto:

```bash
cd AREP_TALLER01
```

3. Ejecuta el servidor HTTP:

```bash
mvn exec:java 
```
4. En otra terminal ejecuta:

```bash
mvn test 
```
5. Accede al servidor desde tu navegador 
```bash
http://localhost:8080
```


# Workout Planner

Esta es la página principal en la cual podras ver que rutina se amolda a lo que tu quieres.
Elige el tipo de entrenamiento que quieres y el nivel en el que te encuentras.
![image](src/main/resources/img/pagina.png)

Y el resultado sería la rutina que te recomiendan, como lo puede ser esta:
![image](src/main/resources/img/resultado.png)
### Pruebas

Las pruebas de extremo a extremo verifican el comportamiento general del servidor, incluyendo la API y el servicio de archivos estáticos. Ejecuta las pruebas con:

```bash
mvn test
```
Deberia salir esto: 
![image](src/main/resources/img/Pruebas.png)


## Se construyo con

- [Java SE](https://www.oracle.com/java/technologies/javase-downloads.html) - Lenguaje de programación
- [Maven](https://maven.apache.org/) - Gestión de dependencias


## Autor

- **Yeltzyn Sierra** - 

## License

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE.md](LICENSE.md) para más detalles.
## Versión
Versión 1.0
