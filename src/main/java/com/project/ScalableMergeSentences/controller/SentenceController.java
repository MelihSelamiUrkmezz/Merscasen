package com.project.ScalableMergeSentences.controller;

import com.project.ScalableMergeSentences.model.entity.Sentence;
import com.project.ScalableMergeSentences.model.entity.dto.RequestSentenceDTO;
import com.project.ScalableMergeSentences.model.entity.dto.ResponseSentenceDTO;
import com.project.ScalableMergeSentences.service.SentenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentence")
public class SentenceController {

    private final SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @PostMapping
    public ResponseEntity<ResponseSentenceDTO> PostSentences(@RequestBody RequestSentenceDTO requestSentenceDTO){
        ResponseSentenceDTO response = sentenceService.MergeSentences(requestSentenceDTO);

        return ResponseEntity.ok(response);
    }

}
