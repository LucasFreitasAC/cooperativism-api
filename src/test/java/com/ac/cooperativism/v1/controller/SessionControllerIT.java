package com.ac.cooperativism.v1.controller;

import com.ac.cooperativism.v1.domain.model.Session;
import com.ac.cooperativism.v1.domain.repository.SessionRepository;
import com.ac.cooperativism.v1.domain.repository.TopicRepository;
import com.ac.cooperativism.v1.utils.DatabaseCleaner;
import com.ac.cooperativism.v1.utils.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class SessionControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/v1/sessions";

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void mustReturnStatus201WhenCreatingSessions() throws Exception {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        Session session = TestUtil.buildSession();

        given()
                .body(objectMapper.writeValueAsString(session))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void mustReturnStatus400WhenSomeFieldIsNull() throws Exception {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .body(objectMapper.writeValueAsString(TestUtil.buildCreateWrongSession()))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", equalTo("One or more fields are invalid. Fill it in correctly and try again."));
    }

    private void prepareData() {
        topicRepository.saveAll(TestUtil.populateTopicTable());
    }
}
