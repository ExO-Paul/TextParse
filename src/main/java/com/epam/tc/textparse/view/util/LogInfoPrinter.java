/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.view.util;

import org.apache.log4j.Logger;


/**
 *
 * @author ExO
 */
public class LogInfoPrinter implements IPrinter {
    
    private static final Logger LOG = Logger.getLogger(LogInfoPrinter.class);
    
    public LogInfoPrinter(){
        super();
    }
            
    @Override
    public void print(String s) {
        LOG.info(s);
    }

    
}