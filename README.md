# 🚀 Appgate Social Media Analyzer

Análisis de menciones en redes sociales (Facebook y Twitter).

---

## 📦 Descripción del Proyecto

Esta aplicación expone un endpoint REST para analizar menciones sociales, determinando el nivel de riesgo del contenido publicado en Facebook o Twitter, de acuerdo a ciertos criterios simulados.

## ⚙️ Tecnologías usadas

☕ Java 21

🧩 Spring Boot 3.3.4

✨ Lombok

📝 SLF4J + MDC logging

🔍 JUnit + Mockito para tests unitarios

📖 Swagger OpenAPI para documentación de la API

🐳 Preparada para contenerización con Docker y docker-compose

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


## 📌 Extensiones futuras sugeridas

🗃️ Persistencia real con base de datos (ej: Mysql, MongoDB)

🛡️ Resiliencia con Resilience4j (Circuit Breaker, Retry)

☁️ Deployment en cloud (Google Cloud Run, AWS ECS)

## 👨‍💻 Autor

Andres Felipe Vasquez Ortiz - Ingeniero de Software

LinkedIn: https://www.linkedin.com/in/andres-felipe-vasquez-ortiz/



