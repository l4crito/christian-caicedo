Las listas dentro de ChatService representan un repositorio
Toda la logica de la app esta en Chatservice(donde se encuentran ciertas pruebas unitarias)

Cada vez que se envia un mensaje a un grupo, tambien se notifica por websocket

Existe data mockeada con 3 usuarios [0,1,2], un  grupo (0) y un mensaje por cada usuario 
[{usuario:0,mensaje:0},{usuario:1,mensaje:1},{usuario:2,mensaje:2}]


El usuario 0 tiene agregado a usuario 1
El usuario 1 no tiene agregado a usuario 0


Si el usuario 0 pide los mensajes del grupo 0, cuando vea un mensaje del usuario 1 contact=true se visualizara en sus mensajes
ya que lo tiene agregados
```
curl --location --request GET 'localhost:8080/chat/mensajes?grupo=0&usuario=0'
```

Si el usuario 1 pide los mensajes del grupo 0, cuando vea un mensaje del usuario 0 o 2 contact=false se visualizara en sus mensajes
ya que no  los tiene agregado
```
curl --location --request GET 'localhost:8080/chat/mensajes?grupo=0&usuario=0'
```

Crear un usuario, Respuesta Objeto Usuario
un usuario se añade como contacto a el/ella mismo cuando se crea
```
curl --location --request POST 'localhost:8080/usuario/crear' \
--header 'Content-Type: application/json' \
--data-raw '{
     "name":"Diana Torres",
     "status":"Realizando la prueba de xcale",
     "photo":"alguna url de la photo"
}
    
'
```

Agregar contacto
```
curl --location --request POST 'localhost:8080/usuario/agregarContacto?usuario=0&contacto=1'
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
curl --location --request GET 'localhost:8080/chat/mensajes?grupo=0&usuario=0'
```