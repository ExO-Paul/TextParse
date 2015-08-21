/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import org.apache.log4j.Logger;
/**
 *
 * @author ExO
 */
public class TextReader {
    
    private static final Logger LOG = Logger.getLogger(TextReader.class);
    
    public TextReader(){
        super();
    }

    public String readText(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        CharBuffer charBuffer = CharBuffer.allocate(1024);

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(filePath)
                )) 
        {
            while (bufferedReader.read(charBuffer) >= 0) {
                charBuffer.flip();
                stringBuilder.append(charBuffer);
            }
        } catch (IOException ex) {
            LOG.error("Failed to read from file", ex);
            return null;
        }
        return stringBuilder.toString();
    }
}
