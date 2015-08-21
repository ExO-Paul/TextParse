/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic;

import com.epam.tc.textparse.entity.Paragraph;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author ExO
 */
public class ParagraphParser {

    private final SentenceParser SENTENCE_PARSER;
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(RegexBundleWorker
            .getRegexResource(TextRegexKey.REGEX_SENTENCE),
            Pattern.MULTILINE
    );

    public ParagraphParser(SentenceParser parser) {
        this.SENTENCE_PARSER = parser;
    }

    public Paragraph parse(String paragraph) {

        Matcher sentenceMatcher = SENTENCE_PATTERN.matcher(paragraph);

        Paragraph result = new Paragraph();
        while (sentenceMatcher.find()) {
            result.addTextComponent(SENTENCE_PARSER.parse(sentenceMatcher.group()));
        }

        return result;
    }
}
