# ExpManager

## Descripción
ExpManager es una aplicación de gestión de expedientes, miembros de un consejo y reuniones, desarrollada en Java utilizando JavaFX para la interfaz gráfica y Hibernate para la conexión a una base de datos PostgreSQL. La aplicación permite registrar y gestionar expedientes, miembros, reuniones, minutas de reuniones y las acciones tomadas en los expedientes.

## Características
- Gestión de expedientes
- Registro de miembros del consejo
- Creación y seguimiento de reuniones
- Minutas de reuniones
- Acciones sobre expedientes

## Tecnologías
- **Java**
- **JavaFX**
- **Hibernate**
- **PostgreSQL**

## Instalación
### Prerrequisitos
- Java 18 o superior
- PostgreSQL
- Maven

### Pasos
1. Clona este repositorio:
    ```sh
    git clone https://github.com/XGabito12/PooProject.git
    ```
2. Configura la base de datos PostgreSQL con las siguientes credenciales:
    - Usuario: `postgres`
    - Contraseña: `postgres`
    - Base de datos: `ExpManager`
3. Modifica el archivo `application.properties` con tus credenciales de base de datos:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_base_de_datos
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    ```
4. Compila y ejecuta el proyecto:
    ```sh
    mvn clean install
    java -jar target/pooproject-0.0.1-SNAPSHOT.jar
    ```

## Uso
1. Inicia la aplicación.
2. Navega a través de las opciones del menú para gestionar expedientes, miembros, reuniones y minutas.

## Contribuir
Las contribuciones son bienvenidas. Por favor, sigue estos pasos:
1. Haz un fork de este repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-feature`).
3. Realiza los cambios necesarios y confirma los cambios (`git commit -am 'Añadir nueva feature'`).
4. Sube los cambios a tu repositorio fork (`git push origin feature/nueva-feature`).
5. Crea un Pull Request.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE](LICENSE) para más detalles.
