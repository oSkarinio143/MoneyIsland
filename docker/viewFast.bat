@echo off
set NET_NAME=moneyisland-network
set COMPOSE_FILE=nexus\docker-compose.yml

echo --- Sprawdzanie sieci Docker ---

docker network inspect %NET_NAME% >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo Siec %NET_NAME% nie istnieje. Tworzenie...
    docker network create %NET_NAME%
) ELSE (
    echo Siec %NET_NAME% juz istnieje.
)

echo.
echo --- Uruchamianie infrastruktury (Nexus) ---

docker-compose -f %COMPOSE_FILE% up -d

echo.
echo Gotowe.
pause