@echo off
set NEXUS_FILE=nexus\docker-compose.deploy.yml
set CONTAINER_NAME=shared-deployer

docker-compose -f %NEXUS_FILE% down --remove-orphans
docker-compose -f %NEXUS_FILE% up -d --force-recreate

:check_logs
docker-compose -f %NEXUS_FILE% logs %CONTAINER_NAME% 2>&1 | find "BUILD SUCCESS" > nul
if %errorlevel% equ 0 (
    goto kill_and_continue
)

docker-compose -f %NEXUS_FILE% logs %CONTAINER_NAME% 2>&1 | find "BUILD FAILURE" > nul
if %errorlevel% equ 0 (
    echo.
    echo [ERROR] Wykryto BLAD BUDOWANIA!
    goto error_exit
)

timeout /t 2 /nobreak > nul
goto check_logs

:kill_and_continue
docker-compose -f %NEXUS_FILE% stop %CONTAINER_NAME%

docker-compose up --build -d

pause
exit /b 0

:error_exit
pause
exit /b 1