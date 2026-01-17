@echo off
set VIEW_FILE=nexus\docker-compose.view.yml

docker-compose -f %VIEW_FILE% up -d --wait
docker-compose up --build -d

pause