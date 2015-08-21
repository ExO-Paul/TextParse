/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic.task;

/**
 *
 * @author ExO
 */
class IncompatableWordTypeException extends Exception {

    public IncompatableWordTypeException() {
    }

    public IncompatableWordTypeException(String message) {
        super(message);
    }

    public IncompatableWordTypeException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
    
}
