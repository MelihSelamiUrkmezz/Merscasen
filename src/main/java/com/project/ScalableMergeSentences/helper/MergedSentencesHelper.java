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

    public boolean isFlag2() {
        return flag2;
    }

    public void setFlag2(boolean flag2) {
        this.flag2 = flag2;
    }

    public boolean flag2= false;
    public boolean success = false;



    public void main_process(ArrayList<String[]> sentences){

        if(sentences.size()<2){
            String main_sentence="";
            for(String word: sentences.get(0)){
                main_sentence+=word+" ";
            }
            setResult(main_sentence.strip());
            return;
        }

        ArrayList<String> common_words=get_common_words(sentences);

        // not exist common
        if(common_words.isEmpty()){
            String temp_results = "";

            ArrayList<String[]> temp = new ArrayList<>();
            temp.add(sentences.get(0));
            temp.add(sentences.get(1));

            for(String[] words: temp){

                temp_results += String.join(" ", words);
                temp_results += " ";
            }

            temp_results = temp_results.strip();


            sentences.remove(0);
            sentences.remove(0);
            sentences.add(0,temp_results.split(" "));
            setFlag(false);
            main_process(sentences);
            return;
        }

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
        setSuccess(true);
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
                }
                else if(compare_unified_word_isTrue(word, wordCountMap) != null){

                    String[] response = compare_unified_word_isTrue(word, wordCountMap);

                    wordCountMap.remove(response[1]);
                    wordCountMap.put(response[0],2);
                }
                else {
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

//                if(sentence[sentence.length-1].equals(word)){
//                    setFlag(false);
//                    return sentence;
//                }
                if(compare_unified_word(sentence[sentence.length-1], word)!=null){
                    if(sentence[sentence.length-1].equals(word)){
                        setFlag(false);
                        return sentence;
                    }
                    setFlag(false);
                    setFlag2(true);

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

        if(isFlag2()){
            for (int i = 0; i < main_sentence_array.length-1; i++) {
                main_sentence+=main_sentence_array[i]+" ";
            }
        }else{

            for(String word: main_sentence_array){
                main_sentence+=word+" ";
            }


        }


        for(String[] sentence: temp){

            if(!main_sentence_array.equals(sentence)){



                for(String word: sentence){
                    if(isFlag2()){
                        main_sentence+=word+" ";
                    }
                    else if(!Arrays.asList(main_sentence_array).contains(word)){
                        main_sentence+=word+" ";
                    }
                }
            }
        }

        setFlag2(false);
        return main_sentence;
    }

    public String[] compare_unified_word(String word1, String word2){
        String bigWord = "";
        String smallWord = "";
        if(word1.length() >= word2.length()){
            bigWord = word1;
            smallWord = word2;
        }else{
            bigWord = word2;
            smallWord = word1;
        }

        int count = 0;

        //çarşıya
        //ça

        int index = bigWord.indexOf(smallWord);
        if(index == 0){

                String [] result =  new String[2];
                result[0] = bigWord;
                result[1] = smallWord;
                return result ;

        }
            return  null;
    }

    public String [] compare_unified_word_isTrue(String word,  Map<String, Integer> wordCountMap){

        String [] result =  new String[2];
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {

           result = compare_unified_word(entry.getKey(), word);

           if(result!=null){
               return  result;
           }
        }
        return null;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}


