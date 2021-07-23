/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Represents the emotion state returned from the python analyzer program
 * @author morgan
 */
public class EmotionState {
    
    private Map<Double, String> internalRepresentation;
    private String top, nexttop;
    private int timestamp;
    
    public EmotionState(String ir)
    {
        internalRepresentation = new HashMap<Double, String>();
        // Convert from string to sorted dictionary. Top value is first
        // Positional Data for reference:
        // 0 - Angry, 1 - Disgust, 2 - Fear, 3 - Happy, 4 - Neutral, 5 - Sad, 6 - Surprise
       
        // Tokenize
        String[] tokens = ir.split(",");
        timestamp = Integer.parseInt(tokens[0]);
        internalRepresentation.put(Double.parseDouble(tokens[1]), "Anger");
        internalRepresentation.put(Double.parseDouble(tokens[2]), "Disgust");
        internalRepresentation.put(Double.parseDouble(tokens[3]), "Fear");
        internalRepresentation.put(Double.parseDouble(tokens[4]), "Happy");
        internalRepresentation.put(Double.parseDouble(tokens[5]), "Neutral");
        internalRepresentation.put(Double.parseDouble(tokens[6]), "Sad");
        internalRepresentation.put(Double.parseDouble(tokens[7]), "Surprise");
        
        // Sort, get top and next top variables set
        TreeMap<Double, String> rankedInternal = new TreeMap<Double, String>(internalRepresentation);
        Double topKey = rankedInternal.lastKey();
        top = rankedInternal.get(topKey);
        
        Double secondTopKey = rankedInternal.lowerKey(topKey);
        nexttop = rankedInternal.get(secondTopKey);
        
    }
    
    public String getTopPrediction()
    {
        return top;
    }
    
    public String getNextTopPrediction()
    {
        return nexttop;
    }
    
    public Map<Double, String> getInternalRepresentation()
    {
        return internalRepresentation;
    }
    
    
    
}
