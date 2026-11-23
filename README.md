# bodegas-backend

Backend del taller de bodegas y productos. Hecho con Java 17, Spring Boot, Spring Data JPA y base de datos H2 en memoria.

Una bodega tiene varios productos y cada producto pertenece a una sola bodega (relación 1:N).

## Requisitos

- Java 17 o superior
- No hace falta instalar Maven, el proyecto trae el wrapper (`mvnw`)

## Cómo ejecutarlo

En Windows:

```
mvnw.cmd spring-boot:run
```

En Linux o Mac:

```
./mvnw spring-boot:run
```

La API queda en `http://localhost:8080`.

Al arrancar se cargan datos de prueba con un `CommandLineRunner` (clase `DatosIniciales`): Bodega Norte, Bodega Sur y tres productos. Como H2 es en memoria, los datos se borran al apagar la aplicación y se vuelven a cargar al iniciarla.

## Endpoints

| Método | Ruta | Acción |
|---|---|---|
| POST | /bodegas | Registrar bodega |
| GET | /bodegas | Consultar bodegas con sus productos |

Ejemplo de POST:

```json
{
  "nombre": "Bodega Centro",
  "ubicacion": "Carrera 7 # 32-16"
}
```

Si el nombre o la ubicación vienen vacíos responde 400.

## Consola H2

`http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:bodegas`
- Usuario: `sa`
- Contraseña: vacía

## Estructura

```
src/main/java/co/edu/javeriana/bodegas
  entity       Bodega, Producto
  repository   BodegaRepository, ProductoRepository
  dto          BodegaDto, ProductoDto
  service      BodegaService
  controller   BodegaController
  config       ModelMapperConfig, CorsConfig, DatosIniciales
  exception    ErrorDto, ManejadorExcepciones
```

El frontend está en el repositorio `bodegas-frontend` y se conecta desde `http://localhost:4200`.
