/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.controller;

import com.epam.tc.textparse.controller.bean.TextParamName;
import com.epam.tc.textparse.controller.bean.TextControllerRequest;
import com.epam.tc.textparse.entity.Text;
import com.epam.tc.textparse.entity.TextComponent;
import com.epam.tc.textparse.logic.LogicLeafException;
import com.epam.tc.textparse.logic.task.AlphabetWordSorter;
import com.epam.tc.textparse.logic.task.NullArgumentException;
import com.epam.tc.textparse.logic.ParagraphParser;
import com.epam.tc.textparse.logic.task.QuestionWordCounter;
import com.epam.tc.textparse.logic.SentenceParser;
import com.epam.tc.textparse.logic.TextCompiler;
import com.epam.tc.textparse.logic.TextParser;
import com.epam.tc.textparse.util.BundleName;
import com.epam.tc.textparse.util.TextReader;
import com.epam.tc.textparse.logic.task.WordLetterExcluder;
import com.epam.tc.textparse.logic.WordParser;
import com.epam.tc.textparse.logic.task.ZeroLengthException;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author Pavel
 */
public class TextController {

    private static final AlphabetWordSorter ALPHA_SORTER = new AlphabetWordSorter();
    private static final QuestionWordCounter WORD_COUNTER = new QuestionWordCounter();
    private static final WordLetterExcluder LETTER_EXCLUDER = new WordLetterExcluder();

    private static final TextReader TEXT_READER = new TextReader();
    private static final TextParser TEXT_PARSER
            = new TextParser(new ParagraphParser(new SentenceParser(new WordParser())));
    private static final TextCompiler TEXT_COMPILER = new TextCompiler();

    private static final Logger LOG = Logger.getLogger(TextController.class);

    private static final ResourceBundle SETTINGS_BUNDLE
            = ResourceBundle.getBundle(BundleName.SETTINGS.getName());

    public TextController() {
        super();
    }

    public Object execute(TextControllerCommand command, TextComponent component,
                          TextControllerRequest parameters) {
        if (command == null || component == null || parameters == null) {
            return null;
        }
        try {
            switch (command) {
                case COUNT_WORDS_IN_QUESTIONS:
                    return WORD_COUNTER.countWords((Integer) parameters.getParam(TextParamName.SEARCH_WORD_LENGTH),
                            component);
                case EXCLUDE_LETTER_FROM_WORDS:
                    LETTER_EXCLUDER.excludeLetterFromWords((WordLetterExcluder.WordToExclude) parameters
                                    .getParam(TextParamName.LETTER_TO_EXCLUDE),
                            component);
                    return true;
                default:
                    return null;
            }
        } catch (LogicLeafException ex) {
            LOG.error("Error occured while executing command", ex);
        } catch (NullArgumentException ex) {
            LOG.error("Null argument passed into excludeLetterFromWords()", ex);
        } catch (ZeroLengthException ex) {
            LOG.error("Zero length value passed to countWords()", ex);
        }
        return null;
    }

    public Object execute(TextControllerCommand command,
                          TextControllerRequest parameters) {
        if (command == null || parameters == null) {
            return null;
        }
        switch (command) {
            case PARSE_TEXT:
                Text text = TEXT_PARSER.parse((String) parameters
                        .getParam(TextParamName.TEXT_STRING));
                return text;
            default:
                return null;
        }
    }

    public Object execute(TextControllerCommand command, TextComponent component) {
        if (command == null || component == null) {
            return null;
        }
        try {
            switch (command) {
                case COMPILE_TEXT:
                    return TEXT_COMPILER.compileComponent(component);
                case SORT_IN_ALPHA_ORDER:
                    return ALPHA_SORTER.getWordsSortedByAlphabet(component);

                default:
                    return null;
            }
        } catch (LogicLeafException ex) {
            LOG.error("Error occured while executing command", ex);
        }
        return null;
    }

    public Object execute(TextControllerCommand command) {

        switch (command) {
            case READ_TEXT:
                return TEXT_READER.readText(
                        SETTINGS_BUNDLE.getString(
                                TextSettingsKey.SETTINGS_FILEPATH.getKey()
                        )
                );
            default:
                return null;
        }
    }

}
