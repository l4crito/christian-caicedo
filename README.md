Existe data mockeada con un usuario, un  grupo y un mensaje

 Crear un usuario, Respuesta Objeto Usuario
```
curl --location --request POST 'localhost:8080/usuario/crear' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Diana Torres",
     "status":"Realizandi la prueba de xcale",
     "photo":"alguna url de la photo"
}
    
'
```

Crear un grupo , Respuesta Objeto grupo
```
curl --location --request POST 'localhost:8080/grupo/crear' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Grupo de desarrolladores"
}
    
'
```
    
'

Agregar un usuario a un grupo, Respuesta 200
Si el usuario o el grupo no existe lanza un badrequest
badrequest
```
curl --location --request POST 'localhost:8080/grupo/unirse' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId":0,
    "groupId":0
}
    
'
```

Enviar mensaje, Respuesta Objeto Mensaje
Si el usuario o el grupo no existe lanza un badrequest
Si el usuario no se encuentra en un grupo lanza un badrequest
```

curl --location --request POST 'localhost:8080/chat/enviar' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId":"0",
    "groupId":"0",
    "message":"Algun mensaje de prueba"
}
    
'
```


para leer los mensajes de un grupo en especifico
```
curl --location --request GET 'localhost:8080/chat/mensajes?grupo=0'
```