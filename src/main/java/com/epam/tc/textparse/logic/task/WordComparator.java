/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic.task;

import com.epam.tc.textparse.entity.InvalidTextComponentLeafOperationException;
import com.epam.tc.textparse.entity.TextLeaf;
import com.epam.tc.textparse.entity.Word;
import java.util.Comparator;
import org.apache.log4j.Logger;

/**
 *
 * @author ExO
 */
public class WordComparator implements Comparator<Word> {

    private static final Logger LOG = Logger.getLogger(WordComparator.class);

    public WordComparator() {
    }

    private int compareGlyph(int position, Word o1, Word o2) throws IncompatableWordTypeException {
        if ((o1.getTextComponent(0) instanceof TextLeaf)
                && (o2.getTextComponent(0) instanceof TextLeaf)) {
            int result = ((TextLeaf) o1.getTextComponent(0)).getValue().compareTo(
                    ((TextLeaf) o2.getTextComponent(0)).getValue());
            if (result != 0) {
                return result;
            } else {
                try {
                    if (position + 1 >= o1.getComponentsCount()) {
                        return -1;
                    } else {
                        if (position + 1 >= o2.getComponentsCount()) {
                            return +1;
                        } else {
                            return compareGlyph(position + 1, o1, o2);
                        }
                    }

                } catch (InvalidTextComponentLeafOperationException ex) {
                    LOG.error("Error while comparing glyphs", ex);
                }
            }
        } else {
            throw new IncompatableWordTypeException("Only sublclasses of TextLeaf are comparable with this instance of WordComparator");
        }
        return 0;
    }

    @Override
    public int compare(Word o1, Word o2) {
        try {
            return compareGlyph(0, o1, o2);
        } catch (IncompatableWordTypeException ex) {
            LOG.error("Incompatible"
                    + " Word type, WordComparator is only suitable for comparing"
                    + " instances of Word class including TextLeaf list", ex);
        }
        return 0;
    }

}
