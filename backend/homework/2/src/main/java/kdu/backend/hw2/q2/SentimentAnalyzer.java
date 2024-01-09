package kdu.backend.hw2.q2;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentimentAnalyzer {

    private static final Logger logger = LogManager.getLogger(SentimentAnalyzer.class);
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length]; // output
        int len  = featureSet.length;
        for(int i=0;i<len;i++){
            for(String feature:featureSet[i]){
                int val = getOpinionOnFeature(review, feature, posOpinionWords, negOpinionWords);
                featureOpinions[i] = val;
                if(val!=0) break;
            }
        }
        return featureOpinions;
    }

    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        // your code
        int opinion = checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);
        if(opinion!=0){
            return opinion;
        }
        opinion = checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);
        return opinion;
    }
    private static int checkForWasPhrasePattern(String review, String
            feature, String[] posOpinionWords, String[] negOpinionWords){
        int opinion = 0;
        String pattern = feature + " was ";
        for(String posOpinion:posOpinionWords){
            if(review.contains(pattern+posOpinion)){
                opinion = 1;
                return opinion;
            }
        }
        for(String negOpinion: negOpinionWords){
            if(review.contains(pattern+negOpinion)){
                opinion = -1;
                return opinion;
            }
        }
        return opinion;
    }

    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        String[] sentences = review.split("\\.");
        int opinion = 0;
        for(String sentence:sentences){
            for(String posOpinion:posOpinionWords){
                if(sentence.contains(posOpinion+" "+feature)){
                    opinion = 1;
                    return opinion;
                }
            }
            for(String negOpinion: negOpinionWords){
                if(sentence.contains(negOpinion+" "+feature)){
                    opinion = -1;
                    return opinion;
                }
            }
        }
        return opinion;
    }

    public static void main(String[] args){
        String[][] featureSet = {{ "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },
                { "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
        String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious" };
        String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };

        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";

        int[] featureOpinions = detectProsAndCons(review.toLowerCase(),featureSet,posOpinionWords,negOpinionWords);
        String str = "Opinions on Features: " + Arrays.toString(featureOpinions);
        logger.info(str);
    }
}
