package com.project.ScalableMergeSentences.service;

import com.project.ScalableMergeSentences.helper.MergedSentencesHelper;
import com.project.ScalableMergeSentences.model.entity.Sentence;
import com.project.ScalableMergeSentences.model.entity.dto.RequestSentenceDTO;
import com.project.ScalableMergeSentences.model.entity.dto.ResponseSentenceDTO;
import com.project.ScalableMergeSentences.repository.SentenceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SentenceService {
    private final MergedSentencesHelper helper;
    private final SentenceRepository repository;

    public SentenceService(MergedSentencesHelper helper, SentenceRepository repository) {
        this.helper = helper;
        this.repository = repository;
    }


    public ResponseSentenceDTO MergeSentences(RequestSentenceDTO requestSentenceDTO){
        //split sentences each word
        ArrayList<String[]> words = new ArrayList<>();
        for(String temp: requestSentenceDTO.getSentences()){
            String[] word = temp.split(" ");
            words.add(word);
        }

        //calculated MainSentence
        helper.main_process(words);

        Sentence sentence = new Sentence();
        sentence.setSentences(requestSentenceDTO.getSentences());
        sentence.setMainSentence(helper.getResult());
        sentence.setSuccess(helper.isSuccess());

        repository.save(sentence);

        boolean isSuccess = helper.isSuccess();

        //spring dependencyInjection cozum islemi
        helper.setSuccess(false);

        return new ResponseSentenceDTO(helper.getResult(), isSuccess);

    }
}
