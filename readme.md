# Que es

Este es un sistema basico que cuenta con personas, alumnos y carreras. Cada Alumno es una Persona, pero no toda Persona
es un Alumno, y todo Alumno esta asociado con una Carrera.

<img align="center" src="https://user-images.githubusercontent.com/51339020/112504917-08960a80-8d6b-11eb-8fc7-c986f4718226.png" />

## Como funciona

Hay dos formas de usar la aplicacion, para ver los datos, podemos verlos directamente por el navegador, y si ademas
queremos hacer otras operaciones como alta, baja o modificacion, podemos usar la API.

### Navegador

Contamos con 3 funcionalidades, ver la lista desordenada, ver la lista ordenada, ver un objeto en particular. Para
mostrar las urls, utilizaremos "/alumnos", pero tambien podemos utilizar "/personas" y "/carreras"

para ver la lista desordenada:

```
http://localhost:8080/alumnos
```

para ver la lista ordenada:

```
http://localhost:8080/alumnos/sorted
```

para ver un objeto en particular:

```
http://localhost:8080/alumnos?id={id}
```

### API

Desde la api podemos utilizar get, post, put y delete. A continuacion se muestran ejemplos utilizando alumnos, pero
tambien funciona de la misma manera para personas y carreras.

#### GET

todos los alumnos:

```
http://localhost:8080/api/v1/alumnos
```

un solo alumno:

```
http://localhost:8080/api/v1/alumnos/{id}
```

#### POST

```
http://localhost:8080/api/v1/alumnos
```

JSON BODY:

```
    {
        "nombre": "nombre",
        "apellido": "apellido",
        "edad": 23,
        "email": "email@gmail.com",
        "legajo": "123",
        "carrera_id": 1
    }
```

#### PUT

```
http://localhost:8080/api/v1/alumnos/{id}
```

JSON BODY:

```
    {
        "nombre": "nombre",
        "apellido": "apellido",
        "edad": 23,
        "email": "email@gmail.com",
        "legajo": "123",
        "carrera_id": 1
    }
```

#### DELETE

```
http://localhost:8080/api/v1/alumnos/{id}
```
