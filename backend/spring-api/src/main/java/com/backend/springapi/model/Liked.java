package com.backend.springapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.ToString;

//Liked class for the checkbox section of our survey form 

@Embeddable
@ToString
public class Liked {
    @JsonProperty("students")
    private boolean students;
    @JsonProperty("location")
    private boolean location;
    @JsonProperty("campus")
    private boolean campus;
    @JsonProperty("atmosphere")
    private boolean atmosphere;
    @JsonProperty("dormRooms")
    private boolean dormRooms;
    @JsonProperty("sports")
    private boolean sports;

}

