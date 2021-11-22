package com.ac.cooperativism.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problem")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400")
    private Integer status;

    @ApiModelProperty(example = "It is only possible to create one session per topic.")
    private String detail;

    @ApiModelProperty(example = "It is only possible to create one session per topic.")
    private String userMessage;

    @ApiModelProperty(example = "2021-11-22T13:44:29.349653Z")
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "List of objects or fields that generated an error (optional)")
    private List<Object> objects;

    @ApiModel("ObjectProblem")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "description")
        private String name;

        @ApiModelProperty(example = "Topic description is required")
        private String userMessage;

    }

}
