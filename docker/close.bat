@echo off

docker-compose down
docker-compose -f nexus\docker-compose.view.yml down -v
docker network rm moneyisland-network

pause