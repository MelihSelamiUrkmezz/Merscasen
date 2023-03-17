package com.project.ScalableMergeSentences.controller;

import com.project.ScalableMergeSentences.model.entity.Sentence;
import com.project.ScalableMergeSentences.model.entity.dto.RequestSentenceDTO;
import com.project.ScalableMergeSentences.model.entity.dto.ResponseSentenceDTO;
import com.project.ScalableMergeSentences.repository.SentenceRepository;
import com.project.ScalableMergeSentences.helper.MergedSentencesHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentence")
public class SentenceController {
    private final MergedSentencesHelper mergedSentencesService;
    private final SentenceRepository sentenceRepository;

    public SentenceController(MergedSentencesHelper mergedSentencesService, SentenceRepository sentenceRepository){
        this.mergedSentencesService = mergedSentencesService;
        this.sentenceRepository = sentenceRepository;
    }


    @PostMapping
    public ResponseEntity<ResponseSentenceDTO> PostSentences(@RequestBody RequestSentenceDTO requestSentenceDTO){
        Sentence sentence = new Sentence();
        sentence.getSentences().add(requestSentenceDTO.getSentenceOne());
        sentence.getSentences().add(requestSentenceDTO.getSentenceTwo());
        sentence.setMainSentence(requestSentenceDTO.getMainSentence());
        Sentence sentece1 = sentenceRepository.save(sentence);

        System.out.println(sentece1.getId());

        ResponseSentenceDTO responseSentenceDTO = new ResponseSentenceDTO(requestSentenceDTO.getMainSentence());
        return ResponseEntity.ok(responseSentenceDTO);
    }


}
