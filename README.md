
# 💲 MoneyIsland
Projekt skupia się na warstwie **Backend oraz DevOps** które są tworzone przeze mnie, czasami wspomagałem się pomocą AI. **Frontend jest wygenerowany**, z moimi małymi poprawkami.

Zarządzanie finansami, podgląd majątku budżetem, historia wydatków, zarządzanie kredytami, ustawianie celów finansowych. W przyszłości planowane jest dodanie bloku w temacie inwestycji.

> 🎥 Zalecam spojrzeć na filmiki, szybkie przejście przez wszystkie elementy z krótkim opisem i podkreśleniem najważniejszych funkcji
- 💻 **Prezentacja projektu (2 minuty):** [Oglądaj tutaj](https://www.youtube.com/watch?v=OTZrS3h9c0s)  
- 🛠️ **Prezentacja kodu (12 minut):** [Oglądaj tutaj](https://www.youtube.com/watch?v=Pk_RIsDHsBQ)


## 🚀 Wykorzystane
- **Java**
- **Spring**
- **SQL (Hibernate/JPA)**
- **Microservices**
- **Gateway API, Spring Cloud Gateway (Netty)**
- **Hexagonal + DDD**
- **Nexus (Zawiera bibliotekę współdzieloną)**
- **Kafka**
- **Docker**
- Dodatkowo podobnie jak w poprzednim projekcie: SpringMVC, SpringJpa, SpringSecurity, TokenyJWT, XSS FIlter, CspNonce Filter

## ⚡ Uruchomienie
Projekt nie wymaga dodatkowej konfiguracji (Wszystko jest w pliku .env):  

**1. Przejście do folderu docker**
```bash
cd docker
```
**2a. Pełne środowisko** – wszystkie kontenery (~120s)
```bash
.\view.bat
```
>Alternatywnie bez wykorzystania skryptu (Skrypt wykorzystuje ponieważ potrzebuje dwóch docker-compose, pierwszy uruchamia nexusa zanim drugi zacznie budować kontenery)
```bash
docker-compose -f docker-compose.view.yml up -d --wait
docker-compose up -d
```

**2b. Tylko infrastruktura** – serwisy uruchamiane w IDE, nexus lokalnie (~50s)
```bash
.\viewFast.bat
```

**3. URL w przeglądarce** - http://localhost:8080/oskarinio143/moneyisland

Dane konta admina
```bash
ADMIN_USERNAME=admin
ADMIN_PASSWORD=1234
```
