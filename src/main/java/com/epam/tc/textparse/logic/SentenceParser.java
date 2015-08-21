/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic;

import com.epam.tc.textparse.entity.Glyph;
import com.epam.tc.textparse.entity.Sentence;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author ExO
 */
public class SentenceParser {

    private final WordParser WORD_PARSER;
    private static final Pattern WORD_PATTERN = Pattern.compile(RegexBundleWorker
            .getRegexResource(TextRegexKey.REGEX_WORD),
            Pattern.MULTILINE
    );
    private static final Pattern GLYPH_PATTERN = Pattern.compile(RegexBundleWorker
            .getRegexResource(TextRegexKey.REGEX_GLYPH),
            Pattern.MULTILINE
    );

    public SentenceParser(WordParser parser) {
        this.WORD_PARSER = parser;
    }

    public Sentence parse(String sentence) {

        Matcher wordMatcher = WORD_PATTERN.matcher(sentence);

        Sentence result = new Sentence();
        int firstGlyph = 0;
        String symbol = null;
        while (wordMatcher.find()) {
            for (int i = firstGlyph; i < wordMatcher.start(); i++) {
                symbol = sentence.substring(i, i + 1);
                if (GLYPH_PATTERN.matcher(symbol).matches()) {
                    result.addTextComponent(new Glyph(symbol));
                }
            }
            firstGlyph = wordMatcher.end();
            result.addTextComponent(WORD_PARSER.parse(wordMatcher.group()));
        }
        for (int i = firstGlyph; i < sentence.length(); i++) {
            symbol = sentence.substring(i, i + 1);
            if (GLYPH_PATTERN.matcher(symbol).matches()) {
                result.addTextComponent(new Glyph(symbol));
            }
        }
        return result;
    }
}
