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
public class InvalidTextComponentLeafOperationException extends Exception {

    public InvalidTextComponentLeafOperationException() {
        super();
    }

    public InvalidTextComponentLeafOperationException(String message) {
        super(message);
    }

    public InvalidTextComponentLeafOperationException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
    
}
