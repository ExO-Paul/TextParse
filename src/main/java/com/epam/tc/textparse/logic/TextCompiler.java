/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic;

import com.epam.tc.textparse.entity.Text;
import com.epam.tc.textparse.entity.TextBlock;
import com.epam.tc.textparse.entity.TextComponent;
import com.epam.tc.textparse.entity.InvalidTextComponentLeafOperationException;
import com.epam.tc.textparse.entity.Paragraph;
import com.epam.tc.textparse.entity.TextLeaf;
import com.epam.tc.textparse.entity.Word;
import org.apache.log4j.Logger;

/**
 *
 * @author ExO
 */
public class TextCompiler {

    public String compileComponent(TextComponent component) throws LogicLeafException{
        StringBuilder result = new StringBuilder();
        if (component instanceof TextLeaf) {
            return ((TextLeaf) component).getValue();
        } else {
            try {
                for (TextComponent c : component.getTextComponents()) {
                    result.append(compileComponent(c));
                    if (c instanceof Paragraph) {
                        result.append("\n");
                    }
                }
            } catch (InvalidTextComponentLeafOperationException ex) {
                throw new LogicLeafException("Trying to get components from TextLeaf", ex);
            }
            return result.toString();
        }
    }

    //On my point of view recursive methods are more suitable here 
    //at least, they're prettier, so I leave them here.
    
    //Here is the non-recursive version:
    
//    public String compileComponent(TextComponent component) throws LogicLeafException {
//        StringBuilder result = new StringBuilder();
//        String value = null;
//        try {
//            if ((value = getValueIfLeaf(component)) == null) {
//                for (TextComponent textBlock : component.getTextComponents()) {
//                    if ((value = getValueIfLeaf(textBlock)) == null) {
//                        for (TextComponent sentence : textBlock.getTextComponents()) {
//                            if ((value = getValueIfLeaf(sentence)) == null) {
//                                for (TextComponent sentencePart : sentence.getTextComponents()) {
//                                    if ((value = getValueIfLeaf(sentencePart)) == null) {
//                                        for (TextComponent glyph : sentencePart.getTextComponents()) {
//                                            result.append(getValueIfLeaf(glyph));
//                                        }
//                                    } else {
//                                        result.append(value);
//                                    }
//
//                                }
//                            } else {
//                                result.append(value);
//                            }
//                        }
//                        result.append("\n");
//                    } else {
//                        result.append(value);
//                    }
//                }
//            } else {
//                result.append(value);
//            }
//        } catch (InvalidTextComponentLeafOperationException ex) {
//            throw new LogicLeafException(ex);
//        }
//        return result.toString();
//    }
//
//    private String getValueIfLeaf(TextComponent component) {
//        if (component instanceof TextLeaf) {
//            return ((TextLeaf) component).getValue();
//        } else {
//            return null;
//        }
//    }
}
