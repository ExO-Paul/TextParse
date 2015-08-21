/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.entity;


/**
 *
 * @author ExO
 */
public class CodeString extends TextLeaf implements TextBlock {

    private String codeString = "";

    public CodeString(String codeString) {
        this.codeString = codeString;
    }
    
    @Override
    public String getValue() {
        return codeString + "\n";
    }


}
