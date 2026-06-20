# JAZ S27281 NBP

Aplikacja Spring Boot oblicza sredni kurs wybranej waluty na podstawie danych z API NBP dla podanego przedzialu dat. Kazde poprawne zapytanie jest zapisywane w bazie H2.

## Uruchomienie aplikacji

Aplikacje nalezy uruchomic z klasy:

```text
C:\Users\cypri\Repozytoria\JAZ\JAZ\jaz-s27281-nbp\jaz-s27281-nbp\src\main\java\com\api\nbp\NbpApplication.java
```

W IntelliJ IDEA otworz plik `NbpApplication.java` i kliknij zielony przycisk uruchomienia przy metodzie:

```java
public static void main(String[] args)
```

Nie uruchamiaj pliku:

```text
src/main/java/org/example/Main.java
```

Port aplikacji jest ustawiony w `src/main/resources/application.yaml`:

```text
8081
```

## Endpoint API

Aplikacja wystawia jeden endpoint GET:

```text
GET http://localhost:8081/api/exchange-rates/{currency}?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD
```

Parametry:

```text
currency  - kod waluty, np. EUR, USD, CHF
startDate - data poczatkowa zakresu w formacie YYYY-MM-DD
endDate   - data koncowa zakresu w formacie YYYY-MM-DD
```

Przyklad:

```text
http://localhost:8081/api/exchange-rates/EUR?startDate=2024-01-02&endDate=2024-01-05
```

Przykladowa odpowiedz:

```json
{
  "id": 1,
  "currency": "EUR",
  "startDate": "2024-01-02",
  "endDate": "2024-01-05",
  "averageRate": 4.352225,
  "requestedAt": "2026-06-20T10:24:19.2128103"
}
```

## Swagger

Dokumentacja REST API w Swaggerze jest dostepna po uruchomieniu aplikacji pod adresem:

```text
http://localhost:8081/swagger-ui.html
```

Specyfikacja OpenAPI JSON:

```text
http://localhost:8081/v3/api-docs
```

## Baza danych

Aplikacja uzywa pamieciowej bazy H2:

```text
jdbc:h2:mem:nbp
```

Dane zapisywane przy kazdym poprawnym zapytaniu:

```text
id
currency
startDate
endDate
averageRate
requestedAt
```
