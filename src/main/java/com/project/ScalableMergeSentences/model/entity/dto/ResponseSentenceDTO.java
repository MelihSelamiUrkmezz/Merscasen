package com.project.ScalableMergeSentences.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSentenceDTO {
    private String mainSentence;
    private boolean success;
    private long calculateTimeSecond;
    private double calculateTimeNanoSecond;
    private double calculateTimeMiliSecond;
}
