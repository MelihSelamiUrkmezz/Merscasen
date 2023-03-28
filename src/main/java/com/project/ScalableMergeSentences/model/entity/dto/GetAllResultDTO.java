package com.project.ScalableMergeSentences.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllResultDTO {
    private String id;
    private ArrayList<String> sentences;
    private String mainSentence;
}
