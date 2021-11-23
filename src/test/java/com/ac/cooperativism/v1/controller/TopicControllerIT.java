package com.ac.cooperativism.v1.controller;

import com.ac.cooperativism.v1.domain.model.Topic;
import com.ac.cooperativism.v1.domain.repository.TopicRepository;
import com.ac.cooperativism.v1.utils.DatabaseCleaner;
import com.ac.cooperativism.v1.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class TopicControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/v1/topics";

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void mustReturnStatus201WhenCreatingTopics() throws Exception {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        Topic topic = TestUtil.buildCreateTopic();

        given()
                .body(objectMapper.writeValueAsString(topic))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("description", equalTo(topic.getDescription()));
    }

    @Test
    public void mustReturnStatus400WhenDescriptionFieldIsNull() throws Exception {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .body(objectMapper.writeValueAsString(TestUtil.buildCreateWrongTopic()))
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
