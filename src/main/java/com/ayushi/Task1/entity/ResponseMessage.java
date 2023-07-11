package com.ayushi.Task1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    @JsonProperty("Success")
    private boolean Success;
    @JsonProperty("Message")
    private String message;
}
