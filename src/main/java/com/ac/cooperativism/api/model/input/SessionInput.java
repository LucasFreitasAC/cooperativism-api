package com.ac.cooperativism.api.model.input;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionInput {

    private OffsetDateTime closeDate;

    @Valid
    @NotNull
    private TopicIdInput topic;
}
