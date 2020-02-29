package codefromvideo.lifecycle;

import org.junit.jupiter.api.*;

public class TestMetLifeCycleMethodes {
    @BeforeAll
    public static void eenmaligeSetup() {
        System.out.println("elke test laat dit als eerste zien in de console");
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("elke test laat dit als laatste zien in de console");
    }

    @BeforeEach
    void beforeEachTest(TestInfo testInfo) {
        System.out.println("elke test laat dit als eerste zien in de console");
        System.out.println(String.format("Testnaam van volgende test is %s", testInfo.getDisplayName()));
    }

    @AfterEach
    void afterEachTest(TestInfo testInfo) {
        System.out.println(String.format("Testnaam van de test die net gedraaid heeft is %s", testInfo.getDisplayName()));
        System.out.println("elke test laat dit als laatste zien in de console");
    }

    @Test
    public void eenTestCase() {
        // ik ben een eenvoudige testcase...
    }
}