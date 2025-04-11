# 🚀 Appgate Social Media Analyzer

Análisis de menciones en redes sociales (Facebook y Twitter).

---

## 📦 Descripción del Proyecto

Esta aplicación expone un endpoint REST para analizar menciones sociales, determinando el nivel de riesgo del contenido publicado en Facebook o Twitter, de acuerdo a ciertos criterios simulados.

## ⚙️ Tecnologías usadas

☕ Java 21

🧩 Spring Boot 3.3.4

🐘 PostgreSQL 15 para persistencia de datos

✨ Lombok

📝 SLF4J + MDC logging

🔍 JUnit + Mockito para tests unitarios

📖 Swagger OpenAPI para documentación de la API

🐳 Preparada para contenerización con Docker y docker-compose

## 🗄️ Arquitectura de Contenedores

Al levantar el proyecto con Docker Compose, se levantan:

- **appgate-social-media-analyzer**: Aplicación Java Spring Boot
- **social-postgres**: Base de datos PostgreSQL 15 persistente en volumen Docker

La aplicación espera a que la base de datos esté lista antes de iniciar ✅


# 🚀 Cómo ejecutar

#### ✅ Requisitos previos

- Tener Docker y Docker Compose instalados.

- Git instalado en tu equipo.

### 🖥️ Linux / MacOS
```bash
1. Clonar el repositorio
gh repo clone felipevasquez74/appgate-challenge

2. Ingresar al directorio del proyecto
cd appgate-challenge/

3. Construir y levantar el contenedor
sudo docker-compose up --build
```

### 🖥️ Windows (CMD)
```bash
1. Clonar el repositorio
gh repo clone felipevasquez74/appgate-challenge

2. Ingresar al directorio del proyecto
cd appgate-challenge

3. Construir y levantar el contenedor
docker-compose up --build
```

## 📖 Acceso a la documentación de la API (Swagger)

Una vez levantada la app, accede a:

`http://localhost:8080/swagger-ui/index.html`

Desde Swagger puedes:

Ver la documentación

Probar los endpoints directamente

## 🔌 Endpoints principales

**POST** `http://localhost:8080/api/v1/social-mention/analyze`

Analiza una mención social y devuelve el nivel de riesgo del contenido.

### 📤 Request Body

```json
{
  "message": "Check our promo!",
  "facebookAccount": "fb_account",
  "facebookComments": ["Amazing", "Good deal"]
}
```

```json
{
  "message": "Check our promo!",
  "tweeterAccount": "tw_account",
  "tweeterUrl": https://tweeter.com
}
```

### 📤 Response Body Exitoso

```json
{
  "timestamp": "2025-04-10T16:30:00",
  "status": 200,
  "message": "Risk analysis completed successfully",
  "data": {
    "riskLevel": "LOW_RISK"
  },
  "path": "/api/v1/social-mention/analyze"
}

```

### 📤 Response Body Erroneo

```json
{
  "timestamp": "2025-04-10T16:31:00",
  "status": 400,
  "message": "At least one social account (Twitter or Facebook) must be provided",
  "data": null,
  "path": "/api/v1/social-mention/analyze"
}

```

## 🗃️ Acceso a la base de datos PostgreSQL

La base de datos se levanta automáticamente con Docker Compose.

- **Host:** `localhost`
- **Puerto:** `5432`
- **Base de datos:** `socialdb`
- **Usuario:** `postgres`
- **Contraseña:** `postgres`

### 🔌 Conexión a la base de datos
Puedes conectarte usando:

- Un cliente como **DBeaver**, **pgAdmin**

## 📌 Extensiones futuras sugeridas

🛡️ Resiliencia con Resilience4j (Circuit Breaker, Retry)

☁️ Deployment en cloud (Google Cloud Run, AWS ECS)

## 👨‍💻 Autor

Andres Felipe Vasquez Ortiz - Ingeniero de Software

LinkedIn: https://www.linkedin.com/in/andres-felipe-vasquez-ortiz/



