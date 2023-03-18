package com.project.ScalableMergeSentences.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Sentence {
    @Id
    private String id;
    private ArrayList<String> sentences = new ArrayList<String>();
    private String mainSentence;
    private boolean success;
}
