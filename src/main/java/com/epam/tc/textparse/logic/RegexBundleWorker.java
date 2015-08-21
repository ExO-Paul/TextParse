/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic;

import com.epam.tc.textparse.util.BundleName;

import java.util.ResourceBundle;

/**
 *
 * @author ExO
 */
public class RegexBundleWorker {

    private static final ResourceBundle REGEX_BUNDLE
            = ResourceBundle.getBundle(BundleName.REGEX.getName());

    
    public static String getRegexResource(TextRegexKey key) {
        return REGEX_BUNDLE.getString(key.getKey());
    }
}
