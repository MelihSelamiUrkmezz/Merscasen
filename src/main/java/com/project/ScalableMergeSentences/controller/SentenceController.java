package com.project.ScalableMergeSentences.controller;

import com.project.ScalableMergeSentences.model.entity.Sentence;
import com.project.ScalableMergeSentences.model.entity.dto.GetAllResultDTO;
import com.project.ScalableMergeSentences.model.entity.dto.RequestSentenceDTO;
import com.project.ScalableMergeSentences.model.entity.dto.ResponseSentenceDTO;
import com.project.ScalableMergeSentences.model.entity.dto.SaveResultDTO;
import com.project.ScalableMergeSentences.service.SentenceService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/sentence")
@CrossOrigin
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

    @PostMapping("/save")
    public ResponseEntity<Void> SaveSentence(@RequestBody SaveResultDTO saveResultDTO){
        sentenceService.SaveSentences(saveResultDTO);

        return  ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ArrayList<GetAllResultDTO>> GetAllSentences(){
        return ResponseEntity.ok(sentenceService.GetAllSentences());
    }

}
