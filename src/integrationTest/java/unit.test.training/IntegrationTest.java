package unit.test.training;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.embedded.RedisServer;
import unit.test.training.dto.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UnitTestenForTesters.class}, webEnvironment
        = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=1337")
public class IntegrationTest {

    @LocalServerPort
    private int port;

    private static RedisServer redisServer = null;

    private static String API_USER_ROOT;

    private User createRandomUser() {
        User user = new User();
        user.setId(1337);
        user.setFirstname("Nicolaï");
        user.setLastname("Roos");
        return user;
    }

    private String createUserAsUri(User user) {
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .post(API_USER_ROOT);
        return API_USER_ROOT + "/" + response.jsonPath().get("id");
    }

    @BeforeAll
    public static void startRedis() {
        redisServer = RedisServer.builder()
                .port(6379)
                .setting("bind 127.0.0.1") // secure + prevents popups on Windows
                .setting("maxmemory 32M")
                .build();
        redisServer.start();
    }

    @AfterAll
    public static void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
            redisServer = null;
        }
    }

    @BeforeEach
    public void init() {
        API_USER_ROOT = "http://localhost:" + port + "/user/all";
        System.out.println(API_USER_ROOT);
    }

    @Test
    public void whenGetAllUsers_thenOK() {
        Response response = RestAssured.get(API_USER_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
}