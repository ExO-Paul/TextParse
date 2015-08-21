/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic.task;

import com.epam.tc.textparse.entity.InvalidTextComponentLeafOperationException;
import com.epam.tc.textparse.entity.Sentence;
import com.epam.tc.textparse.entity.TextComponent;
import com.epam.tc.textparse.entity.TextLeaf;
import com.epam.tc.textparse.logic.LogicLeafException;

/**
 *
 * @author ExO
 */
public class QuestionWordCounter {

//    private static final String LEAF_OPERATION_EX_MESSAGE = "TextLeaves should not be"
//            + " passed into countWords()";
//    private static final String ZERO_EX_MESSAGE = "Argument length should be"
//            + " greater than 0";

    public QuestionWordCounter() {
    }

    public int countWords(int length, TextComponent component)
            throws LogicLeafException, ZeroLengthException {
        if (length <= 0) {
            throw new ZeroLengthException("Argument length should be greater than 0");
        }
        int result = 0;
        try {
            if (component instanceof Sentence) {
                if (isSentenceQuestion((Sentence) component)) {
                    for (TextComponent w : component.getTextComponents()) {
                        if (!isComponentLeaf(w)) {
                            if (w.getComponentsCount() == length) {
                                result++;
                            }
                        }
                    }

                }
            } else {
                if (!isComponentLeaf(component)) {
                    for (TextComponent c : component.getTextComponents()) {
                        result += countWords(length, c);
                    }
                }
            }

        } catch (InvalidTextComponentLeafOperationException ex) {
            throw new LogicLeafException("Tried to get text components from leaf", ex);
        }

        return result;
    }

    //On my point of view recursive methods are more suitable here 
    //at least they're prettier, so I leave them here.
    //Here is the non-recursive version:
//    public int countWords(int length, TextComponent component)
//            throws LogicLeafException, ZeroLengthException {
//        if (component instanceof TextLeaf) {
//            throw new LogicLeafException(
//                    LEAF_OPERATION_EX_MESSAGE
//            );
//        }
//        if (length <= 0) {
//            throw new ZeroLengthException(
//                    ZERO_EX_MESSAGE
//            );
//        }
//
//        int result = 0;
//        try {
//            if (!isComponentLeaf(component)) {
//                if (!isComponentSentence(component)) {
//                    for (TextComponent textBlock : component.getTextComponents()) {
//                        if (!isComponentLeaf(textBlock)) {
//                            if (!isComponentSentence(textBlock)) {
//                                for (TextComponent sentence : textBlock.getTextComponents()) {
//                                    if (!isComponentLeaf(sentence)) {
//                                        if (isComponentSentence(sentence)) {
//                                            result += countWordsInQuestionSentence(length, (Sentence) sentence);
//                                        }
//                                    }
//                                }
//
//                            } else {
//                                result += countWordsInQuestionSentence(length, (Sentence) textBlock);
//                            }
//                        }
//                    }
//
//                } else {
//                    result += countWordsInQuestionSentence(length, (Sentence) component);
//                }
//            }
//        } catch (InvalidTextComponentLeafOperationException ex) {
//            throw new LogicLeafException(ex);
//        }
//        return result;
//    }
//
//    private int countWordsInQuestionSentence(int length, Sentence sentence)
//            throws LogicLeafException {
//        int result = 0;
//        try {
//            if (isSentenceQuestion(sentence)) {
//                for (TextComponent w : sentence.getTextComponents()) {
//
//                    if (!isComponentLeaf(w)) {
//                        if (w.getComponentsCount() == length) {
//                            result++;
//                        }
//                    }
//
//                }
//            }
//        } catch (InvalidTextComponentLeafOperationException ex) {
//            throw new LogicLeafException(ex);
//        }
//        return result;
//    }
//
//    private boolean isComponentSentence(TextComponent component) {
//        return component instanceof Sentence;
//    }
//
    private boolean isSentenceQuestion(Sentence sentence)
            throws LogicLeafException {
        boolean result = false;
        try{
        for (TextComponent c : sentence.getTextComponents()) {
            if (isComponentLeaf(c) && ((TextLeaf) c).getValue().equals("?")) {
                result = true;
            }
        }
        } catch (InvalidTextComponentLeafOperationException ex){
            throw new LogicLeafException("Tried to get text components from leaf", ex);
        }
        return result;
    }

    private boolean isComponentLeaf(TextComponent component) {
        return component instanceof TextLeaf;
    }
}
