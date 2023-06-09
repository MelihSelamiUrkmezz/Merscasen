package com.project.ScalableMergeSentences.service;

import com.project.ScalableMergeSentences.helper.MergedSentencesHelper;
import com.project.ScalableMergeSentences.model.entity.Sentence;
import com.project.ScalableMergeSentences.model.entity.dto.GetAllResultDTO;
import com.project.ScalableMergeSentences.model.entity.dto.RequestSentenceDTO;
import com.project.ScalableMergeSentences.model.entity.dto.ResponseSentenceDTO;
import com.project.ScalableMergeSentences.model.entity.dto.SaveResultDTO;
import com.project.ScalableMergeSentences.repository.SentenceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
public class SentenceService {
    private final MergedSentencesHelper helper;
    private final SentenceRepository repository;

    public SentenceService(MergedSentencesHelper helper, SentenceRepository repository) {
        this.helper = helper;
        this.repository = repository;
    }

    public ArrayList<GetAllResultDTO> GetAllSentences(){

        var sentences = repository.findAll();

        ArrayList<GetAllResultDTO> results = new ArrayList<GetAllResultDTO>();

        sentences.forEach(s -> {
            GetAllResultDTO temp = new GetAllResultDTO();
            temp.setId(s.getId());
            temp.setSentences(s.getSentences());
            temp.setMainSentence(s.getMainSentence());

            results.add(temp);
        });

        return results;
    }
    public ResponseSentenceDTO MergeSentences(RequestSentenceDTO requestSentenceDTO){
        //split sentences each word
        ArrayList<String[]> words = new ArrayList<>();
        for(String temp: requestSentenceDTO.getSentences()){
            String[] word = temp.split(" ");
            words.add(word);
        }

        long startTime = System.nanoTime();
        //calculated MainSentence
        helper.main_process(words);
        long endTime = System.nanoTime();

        long time  = endTime - startTime;
        double calculateTimeNanoSecond = time;
        double calculateTimeMiliSecond = (double)time / 1_000_000.0;


//        Sentence sentence = new Sentence();
//        sentence.setSentences(requestSentenceDTO.getSentences());
//        sentence.setMainSentence(helper.getResult());
//        sentence.setSuccess(helper.isSuccess());
//        sentence.setCalculateTimeMiliSecond(calculateTimeMiliSecond);
//
//
//        repository.save(sentence);

        boolean isSuccess = helper.isSuccess();

        //spring dependencyInjection cozum islemi
        helper.setSuccess(false);

        return new ResponseSentenceDTO(helper.getResult(), isSuccess, calculateTimeNanoSecond, calculateTimeMiliSecond);

    }
    public void SaveSentences(SaveResultDTO saveResultDTO){
        Sentence sentence = new Sentence();
        sentence.setSentences(saveResultDTO.getSentences());
        sentence.setMainSentence(saveResultDTO.getMainSentence());
        sentence.setSuccess(saveResultDTO.isSuccess());
        sentence.setCalculateTimeMiliSecond(saveResultDTO.getCalculateTimeMiliSecond());
        repository.save(sentence);
    }
}
