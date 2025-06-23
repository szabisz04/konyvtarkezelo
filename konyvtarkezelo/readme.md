   Könyvtár Kezelő Rendszer - Beadandó
Ez a projekt a Programozási Környezetek kurzus beadandó feladata, amely egy CRUD (Create, Read, Update, Delete) funkcionalitású Spring Boot webalkalmazás. A rendszer könyvtárak és a hozzájuk tartozó könyvek nyilvántartását valósítja meg.

Teljesített Követelmények
Elégséges:

[x] Önálló munka, verziókövetett forráskód (folyamatos commitokkal).

[x] Spring Boot Web alkalmazás.

[x] Működő GitHub Actions CI build (ci.yml).

[x] Az artifact elérhető a CI build után (GitHub Artifacts).

Közepes:

[x] JUnit tesztek a service rétegre.

[x] JaCoCo konfigurálva a pom.xml-ben.

[x] In memory H2 adatbázis (dev profil).

[x] Spring Data JPA használata.

Jó:

[x] Checkstyle használata sikeres builddel.

[x] ~100%-os tesztlefedettség a LibraryService-en (a követelmény 45%).

Jeles:

[x] ~100%-os tesztlefedettség a LibraryService-en (a követelmény 60%).

Plusz Pontok:

[x] Dockerben futtatott relációs adatbázis (PostgreSQL).

[x] Dockerben futtatott service.

Technológiai Stakk
Java 17

Spring Boot 2.7.5

Maven

Spring Data JPA

H2 (fejlesztéshez)

PostgreSQL (produkcióhoz)

Lombok

JUnit 5 & Mockito (tesztelés)

JaCoCo (tesztlefedettség)

Checkstyle (kódminőség)

Docker & Docker Compose

GitHub Actions (CI)

Hogyan Futtassuk?
1. Helyi Fejlesztői Mód (H2 Adatbázissal)
   Klónozd a repository-t.

Nyisd meg a projektet egy IDE-ben (pl. IntelliJ IDEA).

Futtasd a LibraryApplication.java osztályt.

Az alkalmazás a http://localhost:8080 címen lesz elérhető.

A H2 adatbázis konzol a http://localhost:8080/h2-console címen érhető el (JDBC URL: jdbc:h2:mem:testdb).

2. Produkciós Mód (Docker Compose & PostgreSQL)
   Győződj meg róla, hogy a Docker és a Docker Compose telepítve van a gépeden.

A projekt gyökérkönyvtárában add ki a következő parancsot:

docker-compose up --build

Ez felépíti az alkalmazás image-ét, elindít egy PostgreSQL konténert, majd elindítja az alkalmazás konténerét, amely a prod profillal fut.

Az alkalmazás a http://localhost:8080 címen lesz elérhető.

API Végpontok
Az alkalmazás a /api útvonalon keresztül érhető el.

Könyvtárak (/api/libraries)
Metódus

Útvonal

Leírás

Példa Body

GET

/

Összes könyvtár lekérdezése

-

GET

/{id}

Egy könyvtár lekérdezése ID alapján

-

POST

/

Új könyvtár létrehozása

{ "name": "Városi Könyvtár", "address": "Fő utca 1." }

PUT /{id}

Meglévő könyvtár frissítése

{ "name": "Új Név", "address": "Új Cím" }

DELETE /{id}

Könyvtár törlése

-