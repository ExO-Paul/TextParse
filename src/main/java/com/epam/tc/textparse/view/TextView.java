/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.view;

import com.epam.tc.textparse.controller.TextController;
import com.epam.tc.textparse.controller.TextControllerCommand;
import com.epam.tc.textparse.controller.bean.TextParamName;
import com.epam.tc.textparse.controller.bean.TextControllerRequest;
import com.epam.tc.textparse.entity.Text;
import com.epam.tc.textparse.logic.task.WordLetterExcluder;
import com.epam.tc.textparse.util.BundleName;
import com.epam.tc.textparse.view.util.IPrinter;
import com.epam.tc.textparse.view.util.PrinterCreator;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 *
 * @author Pavel
 */
public class TextView {

    private static final TextController CONTROLLER = new TextController();
    private static final IPrinter PRINTER = PrinterCreator.createLogInfoPrinter();

    private static final Logger LOG = Logger.getLogger(TextView.class);

    private static final ResourceBundle STRINGS_BUNDLE
            = ResourceBundle.getBundle(BundleName.STRINGS.getName(), Locale.US);

    public TextView() {
        super();
    }

    private TextControllerRequest getSearchWordLengthRequest() {
        TextControllerRequest request = new TextControllerRequest();
        request.addParam(TextParamName.SEARCH_WORD_LENGTH, 3);

        return request;
    }

    private TextControllerRequest getLetterToExcludeControllerRequest() {
        TextControllerRequest request = new TextControllerRequest();
        request.addParam(TextParamName.LETTER_TO_EXCLUDE, WordLetterExcluder.WordToExclude.FIRST);

        return request;
    }

    public void run() {

        String textString = (String) CONTROLLER
                .execute(TextControllerCommand.READ_TEXT);

        if (nullCheck(textString)) {

            TextControllerRequest request = new TextControllerRequest();

            request.addParam(TextParamName.TEXT_STRING, textString);

            Text text = (Text) CONTROLLER.execute(TextControllerCommand.PARSE_TEXT,
                    request);

            nullCheck(text);

            String compiledText = (String) CONTROLLER
                    .execute(TextControllerCommand.COMPILE_TEXT, text);

            nullCheck(compiledText);

            PRINTER.print(STRINGS_BUNDLE.getString(TextStringKey.COMPILED_TEXT.getKey())
                    + compiledText);

            Integer count = (Integer) CONTROLLER
                    .execute(TextControllerCommand.COUNT_WORDS_IN_QUESTIONS,
                            text,
                            getSearchWordLengthRequest());

            nullCheck(count);
            PRINTER.print(STRINGS_BUNDLE.getString(TextStringKey.FOUND_WORDS.getKey())
                    + count);

            String sortedWords = (String) CONTROLLER
                    .execute(TextControllerCommand.SORT_IN_ALPHA_ORDER, text);

            nullCheck(sortedWords);

            PRINTER.print(STRINGS_BUNDLE.getString(TextStringKey.SORTED_WORDS.getKey())
                    + sortedWords);

            nullCheck(CONTROLLER.execute(
                    TextControllerCommand.EXCLUDE_LETTER_FROM_WORDS,
                    text,
                    getLetterToExcludeControllerRequest()));

            compiledText = (String) CONTROLLER
                    .execute(TextControllerCommand.COMPILE_TEXT, text);

            nullCheck(compiledText);

            PRINTER.print(STRINGS_BUNDLE.getString(TextStringKey.TEXT_WITHOUT.getKey())
                    + compiledText);
        }
    }

    //Well, instead of this, 
    private boolean nullCheck(Object obj) {
        if (obj == null) {
            LOG.error("Controller returned null, "
                    + "please check your TextController.execute() parameters");
            return false;
        }
        return true;
    }

}
