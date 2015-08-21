/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic;

import com.epam.tc.textparse.entity.CodeString;
import com.epam.tc.textparse.entity.Text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author ExO
 */
public class TextParser {

    private final ParagraphParser PARAGRAPH_PARSER;
    private static final Pattern PARA_PATTERN = Pattern.compile(RegexBundleWorker
            .getRegexResource(TextRegexKey.REGEX_PARAGRAPH),
            Pattern.MULTILINE
    );
    private static final Pattern CODE_PATTERN = Pattern.compile(RegexBundleWorker
            .getRegexResource(TextRegexKey.REGEX_CODE),
            Pattern.MULTILINE
    );

    public TextParser(ParagraphParser parser) {
        this.PARAGRAPH_PARSER = parser;
    }

    public Text parse(String text) {

        Matcher paraMatcher = PARA_PATTERN.matcher(text);

        Text resultText = new Text();

        String matchString;
        while (paraMatcher.find()) {
            matchString = paraMatcher.group();
            if (CODE_PATTERN.matcher(matchString).matches()) {
                resultText.addTextComponent(new CodeString(matchString));
            } else {
                resultText.addTextComponent(PARAGRAPH_PARSER.parse(matchString));
            }
        }

        return resultText;
    }
}
