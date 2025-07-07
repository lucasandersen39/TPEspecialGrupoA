# Swagger
http://localhost:8001/swagger-ui/index.html

# CURLs endpoints:
## Agregar monopatín:   
curl --location 'http://localhost:8001/api/monopatin' \
--header 'Content-Type: application/json' \
--data '{
"estado": 1,
"idParada": 1,
"kmRecorridos": 1.2,
"tiempoUsado": 1
}'   
   
## Ver todos los monopatines:   
curl --location 'http://localhost:8001/api/monopatin'   
   
## Generar reporte de uso (si se añade a la url /true o /false se indica si se quiere con los tiempos de pausa o no):   
curl --location 'http://localhost:8001/api/monopatin/reporte-uso'   
   
## Dado un idMonopatín, indica si existe en la tabla:   
curl --location 'http://localhost:8001/api/monopatin/existe/1'   
   
## Devuelve todos los monopatines disponibles para uso:   
curl --location 'http://localhost:8001/api/monopatin/disponibles'   
   
## Devuelve un monopatín por id:   
curl --location 'http://localhost:8001/api/monopatin/684a520ebf16645c3a404173'   
   
## Actualiza un monopatín por id:   
curl --location --request PUT 'http://localhost:8001/api/monopatin/6859b6b692829f2341c96d99' \
--header 'Content-Type: application/json' \
--data '{
"estado": 2,
"idParada": 1,
"kmRecorridos": 10.5,
"tiempoUsado": 180
}'   
   
## Elimina un monopatín por id:   
curl --location --request DELETE 'http://localhost:8001/api/monopatin/684a584a0740a0a84d337155'   
   
## Pone un monopatín por id en mantenimiento:   
curl --location --request PATCH 'http://localhost:8001/api/monopatin/6859c47fe3e61e11e8ef3bae/mantenimiento'   
   
## Suma tiempo de uso a un monopatín:   
curl --location --request PUT 'http://localhost:8001/api/monopatin/sumarTiempoUsado/686bf22f499d5d7aefc3224a' \
--header 'Content-Type: application/json' \
--data '2.0'   
   
## Suma kilómetros recorridos a un monopatín:   
curl --location --request PUT 'http://localhost:8001/api/monopatin/sumarKm/686bf22f499d5d7aefc3224a' \
--header 'Content-Type: application/json' \
--data '2.0'