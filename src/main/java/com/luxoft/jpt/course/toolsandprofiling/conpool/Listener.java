
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.toolsandprofiling.conpool;

public class Listener {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private Callback callback;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Listener() {
        super();
        this.callback = new Callback("callback");
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

}
