package com.ac.cooperativism.v1.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendCountVoteModel {

    private Long topicId;
    private Long voteYes;
    private Long voteNo;
}
