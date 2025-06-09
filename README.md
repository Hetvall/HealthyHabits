# 🌱 HealthyHabits API

Una API REST para gestionar hábitos saludables construida con **Spring Boot 3** y **MySQL**.

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Tecnologías](#️-tecnologías)
- [Prerrequisitos](#-prerrequisitos)
- [Configuración de Base de Datos](#️-configuración-de-base-de-datos)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Ejemplos con CURL](#-ejemplos-con-curl)
- [Pruebas con Postman](#-pruebas-con-postman)
- [Validaciones](#-validaciones)
- [Manejo de Errores](#️-manejo-de-errores)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Solución de Problemas](#-solución-de-problemas)

## 📖 Descripción

HealthyHabits es una API REST que permite a los usuarios gestionar sus hábitos saludables. Los usuarios pueden crear, leer, actualizar y eliminar hábitos organizados por tipo y frecuencia.

### Características principales:
- ✅ CRUD completo para hábitos
- ✅ Validaciones robustas con anotaciones personalizadas
- ✅ Manejo centralizado de errores
- ✅ Filtros por tipo y frecuencia
- ✅ Base de datos MySQL
- ✅ Documentación completa con CURL y Postman

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **Spring Boot Validation**
- **MySQL 8.0**
- **Lombok**
- **Gradle 8.x**

## 📋 Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- ☕ **Java 17** o superior
- 🐬 **MySQL 8.0** o superior
- 🔧 **IntelliJ IDEA** (recomendado) o cualquier IDE de Java
- 📮 **Postman** (opcional, para pruebas)

## 🗄️ Configuración de Base de Datos

### 1. Instalar MySQL

**Windows:**
```bash
# Descargar desde: https://dev.mysql.com/downloads/installer/
# O usar MySQL Workbench
```

**macOS:**
```bash
brew install mysql
brew services start mysql
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
```

### 2. Crear la Base de Datos

```sql
-- Conectarse a MySQL como root
mysql -u root -p

-- Crear la base de datos (nombre actualizado)
CREATE DATABASE healthyHabits_db;

-- Verificar que se creó correctamente
SHOW DATABASES;

-- Usar la base de datos
USE healthyHabits_db;

-- Salir de MySQL
EXIT;
```

### 3. Configuración Actual

El proyecto está configurado con las siguientes credenciales en `application.properties`:

```properties
spring.application.name=HealthyHabits

# Configuración de base de datos MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/healthyHabits_db
spring.datasource.username=root
spring.datasource.password=Manchester17
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración de Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Configuración adicional
spring.jpa.open-in-view=false
server.port=8080
```

> 📝 **Importante:** Si tu password de MySQL es diferente a `Manchester17`, modifica el archivo `src/main/resources/application.properties`

## 🚀 Instalación y Ejecución

### Opción 1: Desde IntelliJ IDEA (Recomendado)

1. **Abrir el proyecto:**
   - `File > Open` → Seleccionar la carpeta `HealthyHabits`
   - Esperar a que Gradle descargue las dependencias

2. **Ejecutar la aplicación:**
   - Buscar la clase `HealthyHabitsApplication`
   - Click derecho → `Run 'HealthyHabitsApplication'`
   - O usar el botón ▶️ verde

3. **Verificar ejecución:**
   - Buscar en los logs: `Tomcat started on port 8080`
   - La aplicación estará en: http://localhost:8080

### Opción 2: Desde Terminal

```bash
# 1. Navegar al directorio del proyecto
cd HealthyHabits

# 2. Compilar el proyecto
./gradlew clean build

# 3. Ejecutar la aplicación
./gradlew bootRun

# 4. Verificar que está corriendo
curl http://localhost:8080/habits
```

### 4. Verificación Rápida

```bash
# Debería retornar un array vacío: []
curl -X GET http://localhost:8080/habits
```

## 🌐 Endpoints de la API

### Base URL: `http://localhost:8080`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/habits` | Crear un nuevo hábito |
| `GET` | `/habits` | Obtener todos los hábitos |
| `GET` | `/habits/{id}` | Obtener un hábito por ID |
| `PUT` | `/habits/{id}` | Actualizar un hábito |
| `DELETE` | `/habits/{id}` | Eliminar un hábito |
| `GET` | `/habits/tipo/{tipo}` | Filtrar hábitos por tipo |
| `GET` | `/habits/frecuencia/{frecuencia}` | Filtrar hábitos por frecuencia |

### Modelo de Datos

```json
{
  "id": 1,
  "nombre": "Ejercicio matutino",
  "tipo": "Salud Física",
  "frecuencia": "Diario",
  "objetivo": "Hacer 30 minutos de ejercicio todas las mañanas"
}
```

## 💻 Ejemplos con CURL

### 📝 1. Crear Hábitos (POST)

```bash
# Crear hábito de ejercicio
curl -X POST http://localhost:8080/habits \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ejercicio matutino",
    "tipo": "Salud Física",
    "frecuencia": "Diario",
    "objetivo": "Hacer 30 minutos de ejercicio cardiovascular todas las mañanas"
  }'

# Crear hábito mental
curl -X POST http://localhost:8080/habits \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Meditación diaria",
    "tipo": "Mental",
    "frecuencia": "Diario",
    "objetivo": "Meditar 15 minutos al día para reducir el estrés"
  }'

# Crear hábito de alimentación
curl -X POST http://localhost:8080/habits \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Beber agua",
    "tipo": "Alimentación",
    "frecuencia": "Semanal",
    "objetivo": "Tomar 8 vasos de agua diarios durante toda la semana"
  }'
```

### 📋 2. Obtener Todos los Hábitos (GET)

```bash
# Listar todos los hábitos
curl -X GET http://localhost:8080/habits

# Con información detallada de la respuesta
curl -X GET http://localhost:8080/habits -v
```

### 🔍 3. Obtener Hábito por ID (GET)

```bash
# Obtener hábito con ID 1
curl -X GET http://localhost:8080/habits/1

# Obtener hábito con ID 2
curl -X GET http://localhost:8080/habits/2

# Probar con ID inexistente (retorna error 404)
curl -X GET http://localhost:8080/habits/999
```

### ✏️ 4. Actualizar Hábitos (PUT)

```bash
# Actualizar hábito con ID 1
curl -X PUT http://localhost:8080/habits/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ejercicio intensivo",
    "tipo": "Salud Física",
    "frecuencia": "Mensual",
    "objetivo": "Ejercicio de alta intensidad una vez al mes"
  }'

# Actualizar hábito con ID 2
curl -X PUT http://localhost:8080/habits/2 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Meditación avanzada",
    "tipo": "Mental",
    "frecuencia": "Semanal",
    "objetivo": "Practicar meditación mindfulness 3 veces por semana"
  }'
```

### 🗑️ 5. Eliminar Hábitos (DELETE)

```bash
# Eliminar hábito con ID 3
curl -X DELETE http://localhost:8080/habits/3

# Eliminar con información detallada
curl -X DELETE http://localhost:8080/habits/1 -v

# Probar eliminar hábito inexistente (error 404)
curl -X DELETE http://localhost:8080/habits/999
```

### 🔎 6. Filtrar por Tipo (GET)

```bash
# Filtrar hábitos por tipo "Salud Física"
curl -X GET "http://localhost:8080/habits/tipo/Salud%20Física"

# Filtrar hábitos por tipo "Mental"
curl -X GET http://localhost:8080/habits/tipo/Mental

# Filtrar hábitos por tipo "Alimentación"
curl -X GET "http://localhost:8080/habits/tipo/Alimentación"
```

### 📅 7. Filtrar por Frecuencia (GET)

```bash
# Filtrar hábitos con frecuencia "Diario"
curl -X GET http://localhost:8080/habits/frecuencia/Diario

# Filtrar hábitos con frecuencia "Semanal"
curl -X GET http://localhost:8080/habits/frecuencia/Semanal

# Filtrar hábitos con frecuencia "Mensual"
curl -X GET http://localhost:8080/habits/frecuencia/Mensual
```

### ❌ 8. Comandos para Probar Validaciones

```bash
# Error: nombre vacío
curl -X POST http://localhost:8080/habits \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "",
    "tipo": "Mental",
    "frecuencia": "Diario",
    "objetivo": "Test objetivo"
  }'

# Error: tipo inválido
curl -X POST http://localhost:8080/habits \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Test hábito",
    "tipo": "TipoInválido",
    "frecuencia": "Diario",
    "objetivo": "Test objetivo"
  }'

# Error: frecuencia inválida
curl -X POST http://localhost:8080/habits \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Test hábito",
    "tipo": "Mental",
    "frecuencia": "FrecuenciaInválida",
    "objetivo": "Test objetivo"
  }'
```

## 📮 Pruebas con Postman

### 🚀 Método 1: Importar CURL a Postman

1. **Abrir Postman**
2. **Click en "Import"** (botón naranja)
3. **Seleccionar "Raw text"**
4. **Pegar cualquier comando CURL** (ejemplo):

```bash
curl --location 'http://localhost:8080/habits' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "Ejercicio matutino",
    "tipo": "Salud Física",
    "frecuencia": "Diario",
    "objetivo": "Hacer 30 minutos de ejercicio todas las mañanas"
}'
```

5. **Click "Continue" → "Import"**

### 📋 Método 2: Configuración Manual

#### **1. ➕ CREAR HÁBITO (POST)**
- **URL:** `http://localhost:8080/habits`
- **Method:** `POST`
- **Headers:** `Content-Type: application/json`
- **Body (raw/JSON):**
```json
{
  "nombre": "Ejercicio matutino",
  "tipo": "Salud Física",
  "frecuencia": "Diario",
  "objetivo": "Hacer 30 minutos de ejercicio todas las mañanas"
}
```

#### **2. 📋 VER TODOS LOS HÁBITOS (GET)**
- **URL:** `http://localhost:8080/habits`
- **Method:** `GET`
- **Headers:** (ninguno necesario)

#### **3. 🔍 VER HÁBITO POR ID (GET)**
- **URL:** `http://localhost:8080/habits/1`
- **Method:** `GET`

#### **4. ✏️ ACTUALIZAR HÁBITO (PUT)**
- **URL:** `http://localhost:8080/habits/1`
- **Method:** `PUT`
- **Headers:** `Content-Type: application/json`
- **Body (raw/JSON):**
```json
{
  "nombre": "Ejercicio intensivo",
  "tipo": "Salud Física",
  "frecuencia": "Mensual",
  "objetivo": "Ejercicio de alta intensidad una vez al mes"
}
```

#### **5. 🗑️ ELIMINAR HÁBITO (DELETE)**
- **URL:** `http://localhost:8080/habits/1`
- **Method:** `DELETE`

### 📁 Colección Recomendada para Postman

Crea carpetas organizadas:
- 📁 **CRUD Básico**
  - POST - Crear Hábito
  - GET - Listar Todos
  - GET - Ver por ID
  - PUT - Actualizar
  - DELETE - Eliminar
- 📁 **Filtros**
  - GET - Por Tipo
  - GET - Por Frecuencia
- 📁 **Validaciones**
  - POST - Error Nombre Vacío
  - POST - Error Tipo Inválido
  - POST - Error Frecuencia Inválida

### 🧪 Secuencia de Prueba Recomendada

1. **GET** `/habits` (verificar vacío)
2. **POST** crear 3 hábitos diferentes
3. **GET** `/habits` (verificar que se crearon)
4. **GET** `/habits/1` (ver uno específico)
5. **PUT** `/habits/1` (actualizar)
6. **GET** `/habits/tipo/Mental` (filtrar)
7. **DELETE** `/habits/2` (eliminar)
8. **GET** `/habits` (verificar estado final)

## ✅ Validaciones

### Validaciones Implementadas:

- **nombre:** No puede estar vacío o ser null
- **tipo:** Solo acepta: `"Salud Física"`, `"Mental"`, `"Alimentación"`
- **frecuencia:** Solo acepta: `"Diario"`, `"Semanal"`, `"Mensual"`
- **objetivo:** No puede estar vacío o ser null

### Anotaciones Personalizadas:

- `@ValidTipo` - Valida tipos permitidos
- `@ValidFrecuencia` - Valida frecuencias permitidas
- `@NotNull` y `@NotBlank` - Campos obligatorios

## ⚠️ Manejo de Errores

### Respuestas de Error Estandarizadas:

```json
{
  "timestamp": "2024-01-09T17:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "El tipo debe ser: Salud Física, Mental o Alimentación",
  "path": "/habits"
}
```

### Códigos de Estado HTTP:

- **200** - Operación exitosa
- **201** - Recurso creado exitosamente
- **400** - Error de validación
- **404** - Recurso no encontrado
- **500** - Error interno del servidor

## 📁 Estructura del Proyecto

```
src/main/java/com/HealthyHabits/HealthyHabits/
├── HealthyHabitsApplication.java          # Clase principal
├── controller/
│   └── HabitController.java               # Controlador REST
├── dto/
│   └── HabitDTO.java                      # Data Transfer Object
├── entity/
│   └── Habit.java                         # Entidad JPA
├── exception/
│   ├── GlobalExceptionHandler.java        # Manejo global de errores
│   └── ErrorResponse.java                 # Respuesta de error estandarizada
├── repository/
│   └── HabitRepository.java               # Repositorio JPA
├── service/
│   └── HabitService.java                  # Lógica de negocio
└── validation/
    ├── ValidTipo.java                     # Anotación personalizada
    ├── TipoValidator.java                 # Validador de tipo
    ├── ValidFrecuencia.java               # Anotación personalizada
    └── FrecuenciaValidator.java           # Validador de frecuencia

src/main/resources/
├── application.properties                 # Configuración de la aplicación
└── static/                               # Recursos estáticos (vacío)
```

## 🔧 Solución de Problemas

### Puerto 8080 en uso

```bash
# Windows - Encontrar proceso usando puerto 8080
netstat -ano | findstr :8080

# Matar proceso específico (reemplazar PID)
taskkill /F /PID <PID>

# O cambiar puerto en application.properties
server.port=8081
```

### Error de conexión a MySQL

1. **Verificar que MySQL esté corriendo:**
```bash
# Windows
net start mysql80

# macOS
brew services start mysql

# Linux
sudo systemctl start mysql
```

2. **Verificar credenciales** en `application.properties`

3. **Verificar que la base de datos existe:**
```sql
mysql -u root -p
SHOW DATABASES;
```

### Error "Access denied for user 'root'"

- Cambiar la contraseña en `application.properties`
- O resetear la contraseña de MySQL:
```bash
mysql -u root -p
ALTER USER 'root'@'localhost' IDENTIFIED BY 'Manchester17';
FLUSH PRIVILEGES;
```

### Dependencias no encontradas

```bash
# Limpiar y reconstruir
./gradlew clean build --refresh-dependencies
```

## 📞 Soporte

Si encuentras algún problema:

1. Verifica que MySQL esté corriendo
2. Verifica las credenciales en `application.properties`
3. Revisa los logs de la aplicación en IntelliJ
4. Asegúrate de que el puerto 8080 esté libre

---

¡Proyecto creado con ❤️ usando Spring Boot 3 y MySQL! 🚀 