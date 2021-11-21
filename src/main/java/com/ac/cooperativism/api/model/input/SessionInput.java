package com.ac.cooperativism.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
public class SessionInput {

    private OffsetDateTime closeDate;

    @Valid
    @NotNull
    private TopicIdInput topic;
}
