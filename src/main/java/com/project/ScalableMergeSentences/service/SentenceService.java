package com.project.ScalableMergeSentences.service;

import com.project.ScalableMergeSentences.helper.MergedSentencesHelper;
import com.project.ScalableMergeSentences.model.entity.dto.RequestSentenceDTO;
import com.project.ScalableMergeSentences.model.entity.dto.ResponseSentenceDTO;
import com.project.ScalableMergeSentences.repository.SentenceRepository;

public class SentenceService {
    private final MergedSentencesHelper helper;
    private final SentenceRepository repository;

    public SentenceService(MergedSentencesHelper helper, SentenceRepository repository) {
        this.helper = helper;
        this.repository = repository;
    }


    public ResponseSentenceDTO MergeSentences(RequestSentenceDTO requestSentenceDTO){
        return null;
    }
}
