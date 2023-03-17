package com.project.ScalableMergeSentences.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSentenceDTO {
    private String sentenceOne;
    private String sentenceTwo;
    private String mainSentence;
}
