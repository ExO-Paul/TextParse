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
import org.apache.log4j.Logger;

/**
 *
 * @author Pavel
 */
public class WordLetterExcluder {

    public enum WordToExclude {

        FIRST,
        LAST
    }

    public static final Logger LOG = Logger.getLogger(WordLetterExcluder.class);
    
    
//    private static final String LEAF_OPERATION_EX_MESSAGE = "TextLeaves should"
//            + " not be passed into excludeLetterFromWords()";

    public WordLetterExcluder() {
    }

    public void excludeLetterFromWords(WordToExclude exclude, TextComponent component) 
    throws LogicLeafException, NullArgumentException {
        
        if (exclude == null){
            throw new NullArgumentException("Argument exclude should not be null");
        }

        String letter;
        try {
            if (!isComponentLeaf(component)) {
                for (TextComponent c : component.getTextComponents()) {
                    if (c instanceof Word) {
                        switch (exclude) {
                            case FIRST:
                                letter = ((TextLeaf) c.getTextComponent(0)).getValue();
                                for (int i = 1; i < c.getComponentsCount(); i++) {
                                    
                                    if (((TextLeaf) c.getTextComponent(i)).getValue().equals(letter)) {
                                        c.removeTextComponent(i);
                                    }
                                }
                                break;
                            case LAST:
                                letter = ((TextLeaf) c.getTextComponent(c.getComponentsCount() - 1)).getValue();
                                for (int i = c.getComponentsCount() - 2; i >= 0; i--) {
                                    if (((TextLeaf) c.getTextComponent(i)).getValue().equals(letter)) {
                                        c.removeTextComponent(i);
                                    }
                                }
                                break;
                        }
                    } else {
                        excludeLetterFromWords(exclude, c);
                    }

                }
            }
        } catch (InvalidTextComponentLeafOperationException ex) {
            throw new LogicLeafException("Tried to get text components from leaf", ex);
        }
    }
    
    //On my point of view recursive methods are more suitable here 
    //at least they're prettier, so I leave them here.
    
    //Here is the non-recursive version:
    
//    private void excludeLetter(WordToExclude exclude, Word word) throws LogicLeafException {
//        String letter = null;
//
//        try{
//        switch (exclude) {
//            case FIRST:
//                letter = ((TextLeaf) word.getTextComponent(0)).getValue();
//                for (int i = 1; i < word.getComponentsCount(); i++) {
//                    if (((TextLeaf) word.getTextComponent(i)).getValue().equals(letter)) {
//                        word.removeTextComponent(i);
//                    }
//                }
//                break;
//            case LAST:
//                letter = ((TextLeaf) word.getTextComponent(word.getComponentsCount() - 1)).getValue();
//                for (int i = word.getComponentsCount() - 2; i >= 0; i--) {
//                    if (((TextLeaf) word.getTextComponent(i)).getValue().equals(letter)) {
//                        word.removeTextComponent(i);
//                    }
//                }
//                break;
//        }
//        } catch (InvalidTextComponentLeafOperationException ex){
//            throw new LogicLeafException(ex);
//        }
//    }
//
//    public void excludeLetterFromWords(WordToExclude exclude, TextComponent component) 
//            throws LogicLeafException, NullArgumentException {
//        if (component instanceof TextLeaf) {
//            throw new LogicLeafException(
//                    LEAF_OPERATION_EX_MESSAGE
//            );
//        }
//        if (exclude == null){
//            throw new NullArgumentException(
//                    EXCLUDE_NULL_EX_MESSAGE
//            );
//        }
//        String letter;
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
//                                                        excludeLetter(exclude, (Word) sentencePart);
//                                                    }
//                                                }
//                                            }
//                                        } else {
//                                            excludeLetter(exclude, (Word) sentence);
//                                        }
//                                    }
//                                }
//                            } else {
//                                excludeLetter(exclude, (Word) textBlock);
//                            }
//                        }
//                    }
//                } else {
//                    excludeLetter(exclude, (Word) component);
//                }
//            }
//        } catch (InvalidTextComponentLeafOperationException ex) {
//            throw new LogicLeafException (ex);
//        }
//
//    }
//
//    private boolean isComponentWord(TextComponent component) {
//        return component instanceof Word;
//    }
//
    private boolean isComponentLeaf(TextComponent component) {
        return component instanceof TextLeaf;
    }

}
