# API Gestión de usuarios

Api desarrollada en Springboot para la gestión de usuarios.


## Dependencias

| Initializr          | Maven                         |
|---------------------|-------------------------------|
| Spring Web          | Oracle Driver (ojdbc 11)      |
| Springboot Devtools | Oracle pki                    |
| Lombok              | osdt core                     |
| Oracle Driver       | osdt cert                     |
| Spring Data JPA     | Springboot starter validation |


## Endpoints para ambiente Desarrollo

* **POST**: Crear un usuario: http://localhost:8080/api/usuario

* **GET**: Listar los usuarios: http://localhost:8080/api/usuario

* **GET**: Obtener usuario por id: http://localhost:8080/api/usuario/{id}

* **PUT**: Actualizar un usuario: http://localhost:8080/api/usuario/{id}

* **DELETE**: Eliminar un usuario: http://localhost:8080/api/usuario/{id}

* **LOGIN**: Inicio de sesión: http://localhost:8080/api/usuario/login


## Ejemplo de request body para crear un Usuario

```json
{
    "nombre": "Albania",
    "correo": "albania@correo.com",
    "password": "Password123$",
    "roles": [4]
}
```

## Instrucciones para ejecutar en local

* Copiar archivo .env.example y renombrar a .env
* Completar las variables de entorno relacionadas con el nombre de la base de datos, dirección del Wallet descomprimido, nombre de usuario y password.

```
CONNECTION_ALIAS=abc123def456_tp
WALLET_PATH=/ruta/del/wallet
ORACLE_USERNAME=username
ORACLE_PASSWORD=pass123
API_PORT=8080
```

## Se agrega archivo `data.sql` 

Para la carga inicial de datos ubicado en: 
```
src/main/resources/data.sql
```

## Ejemplo de request body - Inicio de Sesión

```json
{
    "correo": "albania@correo.com",
    "password": "Password123$"
}
```
