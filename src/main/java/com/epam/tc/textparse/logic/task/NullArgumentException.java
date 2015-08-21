
package com.epam.tc.textparse.logic.task;

/**
 *
 * @author Pavel
 */
public class NullArgumentException extends Exception {

    public NullArgumentException() {
    }

    public NullArgumentException(String string) {
        super(string);
    }

    public NullArgumentException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
    
    
}
