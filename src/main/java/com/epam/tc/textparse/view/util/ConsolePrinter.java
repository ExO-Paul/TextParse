/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.view.util;


/**
 *
 * @author ExO
 */
public class ConsolePrinter implements IPrinter {

    public ConsolePrinter(){
        super();
    }
            
    @Override
    public void print(String s) {
        System.out.println(s);
    }

    
}