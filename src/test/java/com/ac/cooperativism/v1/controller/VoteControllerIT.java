package com.ac.cooperativism.v1.controller;

import com.ac.cooperativism.v1.domain.model.Vote;
import com.ac.cooperativism.v1.domain.repository.SessionRepository;
import com.ac.cooperativism.v1.domain.repository.TopicRepository;
import com.ac.cooperativism.v1.domain.repository.VoteRepository;
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
public class VoteControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/v1/votes";

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void mustReturnStatus400WhenUserUnable() throws Exception {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        Vote vote = TestUtil.buildVoteToSave();
        vote.setDocument("143-243-987-78");

        given()
                .pathParam("document", "143-243-987-78")
                .body(objectMapper.writeValueAsString(vote))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post("/{document}")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", equalTo(String.format("Document %s is not available for voting", "143-243-987-78")));
    }

    @Test
    public void mustReturnStatus400WhenSomeFieldIsNull() throws Exception {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .pathParam("document", "12343281407")
                .body(objectMapper.writeValueAsString(TestUtil.buildCreateWrongVote()))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post("/{document}")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", equalTo("One or more fields are invalid. Fill it in correctly and try again."));
    }

    @Test
    public void mustReturnStatus400WhenUserAlreadyVoted() throws Exception {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        Vote vote = TestUtil.buildVoteToSave();

        given()
                .pathParam("document", "12343281407")
                .body(objectMapper.writeValueAsString(vote))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post("/{document}")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", equalTo(String.format("The associate with document %s has already voted for topic %d", "12343281407", vote.getTopic().getId())));
    }

    private void prepareData() {
        topicRepository.saveAll(TestUtil.populateTopicTable());
        sessionRepository.saveAll(TestUtil.populateSessionTable());
        voteRepository.saveAll(TestUtil.populateVoteTable());
    }
}
