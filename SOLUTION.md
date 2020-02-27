Om met de deur in huis te vallen: In de eerste opzet van de unit test opdracht was nog een foutje geslopen: Namelijk dat het User-object gemocket was. Dit is in dit geval niet nodig en zelfs niet aan te raden omdat de User-class een ValueObject/DTO betreft, dus alleen een data container is en verder geen complex gedrag bevat. In een dergelijk geval is het beter om het object gewoon aan te maken en de set-methoden te gebruiken om de test op te bouwen. 

Onderstaand de basis-opzet zoals deze had moeten zijn:

```java
    @Test
    public void test() {
        // Fout voorbeeld:
        // User userMock = mock(User.class);
        
        // Beter:
        User user = new User();
        user.setId(1);
        user.setEmail("bas@bas.st");
        user.setFirstname("Bas");
        user.setLastname("Stoker");

        LocalDate vanaf = LocalDate.parse("2019-03-29");
        LocalDate tot = LocalDate.parse("2019-04-12");

        ReserveResponse resultaat = unitUnderTest.makeReservation(user, vanaf, tot);

        // hier asserts toevoegen:
    }

```

### Oplossing

De volledige uitgewerkte Unit Testen voor de ReserveLogicImpl.java en UserLogicImpl.java classes is te vinden in de branch 'uitwerking'.
Op deze branch zijn ook aanpassingen gemaakt in de productiecode om alle testen te laten slagen :-)

Hierbij de directe links naar de Unit Testen op die branch: 

JUnit voorbeeld:

http://git.bas.st/bas/unit-test-training/src/branch/uitwerking/src/test/java/unit/test/training/business/ReserveLogicTest.java

JUnit voorbeeld inclusief gebruik Mockito voor het mocken van de database:

http://git.bas.st/bas/unit-test-training/src/branch/uitwerking/src/test/java/unit/test/training/business/UserLogicTest.java

Vooral interessant is de volgende test in UserLogicTest.java:

```java
    @Test
    void haalEenEnkeleGebruikerOpDieNietBestaat_verwachtDatErEenExceptionWordtGegooid() {
        // Geef een mock mee als 'database' aan de UserLogicImpl:
        DataAccessLayer databaseMock = mock(DataAccessLayer.class);
        unitUnderTest.database = databaseMock;

        // Mock ook de ReserveLogic met een default mock, dus zonder bijzonder gedrag verder:
        ReserveLogic reserveLogicMock = mock(ReserveLogic.class);
        unitUnderTest.reserveLogic = reserveLogicMock;

        when(databaseMock.getUsers()).thenReturn(onzeGebruikers);

        assertThrows(IllegalArgumentException.class, () -> {
            unitUnderTest.getUser(85049845);
        });
    }

```

Hierbij wordt getest dat een te verwachttte Exception wordt gegooid als een niet bestaande gebruiker wordt opgevraagd uit de database.


### DISCLAIMER :-)
Er zijn nog steeds een aantal ongeschreven requirements die alleen in de front-end worden afgehandeld (bijv. een startdatum die na de einddatum ligt etc.) maar praktisch gezien zijn de meeste requirements van het Java-gedeelte nu met Unit Tests afgedekt.