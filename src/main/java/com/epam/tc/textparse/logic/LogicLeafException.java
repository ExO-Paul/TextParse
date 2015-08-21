/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.logic;

/**
 *
 * @author ExO
 */
public class LogicLeafException extends Exception {

    public LogicLeafException() {
    }

    public LogicLeafException(String message) {
        super(message);
    }

    public LogicLeafException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicLeafException(Throwable cause) {
        super(cause);
    }
    
}
