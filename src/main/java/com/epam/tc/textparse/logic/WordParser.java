/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic;

import com.epam.tc.textparse.entity.Glyph;
import com.epam.tc.textparse.entity.Word;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ExO
 */
public class WordParser {

    private static final Pattern GLYPH_PATTERN = Pattern.compile(RegexBundleWorker
            .getRegexResource(TextRegexKey.REGEX_GLYPH),
            Pattern.MULTILINE
    );

    public WordParser() {
    }

    public Word parse(String word) {

        Matcher glyphMatcher = GLYPH_PATTERN.matcher(word);

        Word result = new Word();
        while (glyphMatcher.find()) {
            result.addTextComponent(new Glyph(glyphMatcher.group()));
        }

        return result;
    }
}
