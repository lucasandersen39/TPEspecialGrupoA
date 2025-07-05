# ./mongo/Dockerfile
FROM mongo:4.4.28

# Crear carpeta de logs y ajustar permisos
RUN mkdir -p /var/log/mongodb && chown -R mongodb:mongodb /var/log/mongodb

# CMD con --quiet y log en archivo (nada a stdout)
CMD ["mongod", "--bind_ip_all", "--quiet", "--logpath", "/var/log/mongodb/mongod.log", "--logappend"]
