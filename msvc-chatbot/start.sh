#!/bin/sh

export COMPOSE_BAKE=true
# Construir y ejecutar con Docker Compose
sudo docker-compose up --build --remove-orphans
