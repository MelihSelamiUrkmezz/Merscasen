package com.project.ScalableMergeSentences.helper;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MergedSentencesHelper {
    public String result;
    public boolean flag;

    public void main_process(ArrayList<String[]> sentences){

        if(sentences.size()<2){
            String main_sentence="";
            for(String word: sentences.get(0)){
                main_sentence+=word+" ";
            }
            setResult(main_sentence);
            return;
        }

        ArrayList<String> common_words=get_common_words(sentences);

        String[] main_sentences= get_main_sentence(common_words,sentences);
        String temp_results="";
        if(!isFlag())
            temp_results=unified_sentence(main_sentences,sentences);

        else
            temp_results = String.join(" ", main_sentences);

        sentences.remove(0);
        sentences.remove(0);
        sentences.add(0,temp_results.split(" "));

        setFlag(false);
        main_process(sentences);

    }

    public  ArrayList<String> get_common_words(ArrayList<String[]> sentences) {

        Map<String, Integer> wordCountMap = new TreeMap<>();
        ArrayList<String[]> temp = new ArrayList<>();
        temp.add(sentences.get(0));
        temp.add(sentences.get(1));

        for (String[] sentence : temp) {

            for (String word : sentence) {

                if (wordCountMap.containsKey(word)) {
                    int count = wordCountMap.get(word);
                    wordCountMap.put(word, count + 1);
                } else {
                    wordCountMap.put(word, 1);
                }
            }
        }

        ArrayList<String> common_words = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {

            if (entry.getValue() == temp.size()) {
                common_words.add(entry.getKey());
            }
        }
        return common_words;

    }
    public  String[] get_main_sentence(ArrayList<String> common_words,ArrayList<String[]> sentences){
        ArrayList<String[]> temp = new ArrayList<>();
        temp.add(sentences.get(0));
        temp.add(sentences.get(1));
        int nexIndex = 0;

        for(String word: common_words){

            for(String[] sentence: temp){

                if(sentence[sentence.length-1].equals(word)){
                    setFlag(false);
                    return sentence;
                }
                else if(Arrays.asList(sentence).contains(word)){

                    nexIndex =  Arrays.asList(sentence).indexOf(word); // 1
                    setFlag(true);
                    // , git
                    // ali eve git topu al
                    // eve git ekmek al

                }
            }
        }

        if(isFlag()){

            String[] sentence = temp.get(1);
            String willSlider = "";

            for(int i = nexIndex+1; i < sentence.length; i++){
                willSlider+= sentence[i]+" ";

            }

            willSlider = willSlider.strip();

            String [] willAdded = willSlider.split(" ");

            StringBuilder stringBuilder = new StringBuilder();

            for(var a : temp.get(0)){
                stringBuilder.append(a+" ");

            }

            for(var a : willAdded){
                stringBuilder.append(a+" ");
            }

            return stringBuilder.toString().split(" ");

        }

        return null;
    }

    public  String unified_sentence(String[] main_sentence_array,ArrayList<String[]> sentences){
        ArrayList<String[]> temp = new ArrayList<>();
        temp.add(sentences.get(0));
        temp.add(sentences.get(1));
        String main_sentence="";
        for(String word: main_sentence_array){
            main_sentence+=word+" ";
        }

        for(String[] sentence: temp){

            if(!main_sentence_array.equals(sentence)){

                for(String word: sentence){

                    if(!Arrays.asList(main_sentence_array).contains(word)){
                        main_sentence+=word+" ";
                    }
                }
            }
        }
        return main_sentence;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}


