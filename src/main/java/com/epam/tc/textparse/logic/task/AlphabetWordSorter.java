/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic.task;

import com.epam.tc.textparse.entity.InvalidTextComponentLeafOperationException;
import com.epam.tc.textparse.entity.TextComponent;
import com.epam.tc.textparse.entity.TextLeaf;
import com.epam.tc.textparse.entity.Word;
import com.epam.tc.textparse.logic.LogicLeafException;
import com.epam.tc.textparse.logic.TextCompiler;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author ExO
 */
public class AlphabetWordSorter {

    private static final TextCompiler COMPILER = new TextCompiler();
    private static final WordComparator COMPARATOR = new WordComparator();

//    private static final String INVALID_LEAF_EX_MESSAGE = "TextLeaves should "
//            + "not be passed into getWordsSortedByAlphabet()";

    private List<Word> getWordList(TextComponent component) throws LogicLeafException {
        LinkedList<Word> list = new LinkedList<>();
        try {
            if (!isComponentLeaf(component)) {
                for (TextComponent c : component.getTextComponents()) {
                    if (c instanceof Word) {
                        list.add((Word) c);
                    } else {
                        if (!isComponentLeaf(c)) {
                            list.addAll(getWordList(c));
                        }
                    }
                }
            }
        } catch (InvalidTextComponentLeafOperationException ex) {
            throw new LogicLeafException("Tried to get text components from leaf", ex);
        }
        return list;
    }

    public String getWordsSortedByAlphabet(TextComponent component)
            throws LogicLeafException {
        if (component instanceof TextLeaf) {
            throw new LogicLeafException("TextLeaves should not be passed into getWordsSortedByAlphabet()");
        }
        StringBuilder result = new StringBuilder();

        List<Word> list = getWordList(component);

        Collections.sort(list, COMPARATOR);

        char firstChar = COMPILER.compileComponent(list.get(0)).charAt(0);
        String currentWord;
        for (Word w : list) {
            currentWord = COMPILER.compileComponent(w);
            if (currentWord.charAt(0) == firstChar) {
                result.append(currentWord).append(' ');
            } else {
                firstChar = currentWord.charAt(0);
                result.append('\n').append(currentWord).append(' ');
            }
        }
        return result.toString();
    }

    //On my point of view recursive methods are more suitable here 
    //at least they're prettier, so I leave them here.
    
    //Here is the non-recursive version:
    
//    private List<Word> getWordList(TextComponent component) throws LogicLeafException{
//        LinkedList<Word> list = new LinkedList<>();
//        try {
//            if (!isComponentLeaf(component)) {
//                if (!isComponentWord(component)) {
//                    for (TextComponent textBlock : component.getTextComponents()) {
//                        if (!isComponentLeaf(textBlock)) {
//                            if (!isComponentWord(textBlock)) {
//                                for (TextComponent sentence : textBlock.getTextComponents()) {
//                                    if (!isComponentLeaf(sentence)) {
//                                        if (!isComponentWord(sentence)) {
//                                            for (TextComponent sentencePart : sentence.getTextComponents()) {
//                                                if (!isComponentLeaf(sentencePart)) {
//                                                    if (isComponentWord(sentencePart)) {
//                                                        list.add((Word) sentencePart);
//                                                    }
//                                                }
//                                            }
//                                        } else {
//                                            list.add((Word) sentence);
//                                        }
//                                    }
//                                }
//                            } else {
//                                list.add((Word) textBlock);
//                            }
//                        }
//                    }
//                } else {
//                    list.add((Word) component);
//                }
//            }
//        } catch (InvalidTextComponentLeafOperationException ex) {
//            throw new LogicLeafException(ex);
//        }
//        return list;
//    }
//
//
//    public String getWordsSortedByAlphabet(TextComponent component)
//            throws LogicLeafException {
//        if (component instanceof TextLeaf) {
//            throw new LogicLeafException(
//                    INVALID_LEAF_EX_MESSAGE
//            );
//        }
//        StringBuilder result = new StringBuilder();
//        List<Word> list = getWordList(component);
//
//        Collections.sort(list, COMPARATOR);
//
//        char firstChar = COMPILER.compileComponent(list.get(0)).charAt(0);
//        String currentWord;
//        for (Word w : list) {
//            currentWord = COMPILER.compileComponent(w);
//            if (currentWord.charAt(0) == firstChar) {
//                result.append(currentWord).append(' ');
//            } else {
//                firstChar = currentWord.charAt(0);
//                result.append('\n').append(currentWord).append(' ');
//            }
//        }
//
//        return result.toString();
//    }
//    private boolean isComponentWord(TextComponent component) {
//        return component instanceof Word;
//    }

    private boolean isComponentLeaf(TextComponent component) {
        return component instanceof TextLeaf;
    }

}
