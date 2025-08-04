# 🧠 ForoHub

ForoHub es una aplicación backend desarrollada con **Spring Boot** y **PostgreSQL**, diseñada para gestionar foros temáticos con funcionalidades avanzadas como paginación, filtrado dinámico y manejo limpio de datos mediante DTOs.

---

## 🚀 Características

- 🔹 API RESTful estructurada con Spring Boot
- 🔹 Integración con base de datos PostgreSQL
- 🔹 Paginación y filtrado dinámico usando Specifications
- 🔹 Manejo de DTOs para respuestas limpias y seguras
- 🔹 Migración de Gradle a Maven para mejor gestión de dependencias
- 🔹 Arquitectura modular y mantenible

---

## 🛠️ Tecnologías

| Tecnología     | Versión / Uso             |
|----------------|---------------------------|
| Java           | 17+                       |
| Spring Boot    | 3.x                       |
| PostgreSQL     | 15+                       |
| Maven          | Gestión de dependencias   |
| JPA / Hibernate| Persistencia de datos     |

---

## 📦 Estructura del proyecto
src/ ├── main/ │   ├── java/ │   │   └── com.forohub/ │   │       ├── controller/ │   │       ├── service/ │   │       ├── repository/ │   │       ├── model/ │   │       └── dto/ │   └── resources/ │       ├── application.properties │       └── schema.sql


---

## 🧪 Cómo ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/foro-hub.git
   
2. Configura tu base de datos MySQL en application.properties
3. Ejecuta la aplicación desde IntelliJ o con Maven:

mvn spring-boot:run
📌 Próximas mejoras- Autenticación con JWT
- Documentación con Swagger
- Tests unitarios y de integración
📬 ContactoDesarrollado por Alison

