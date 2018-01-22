
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.toolsandprofiling.conpool;

import java.util.ArrayList;
import java.util.List;

import static com.luxoft.jpt.course.util.TestUtil.MB;


public class ConnectionPool {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static final int NUMBER_OF_CONNECTIONS = 99999999;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private List<Connection> connectionPool;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public ConnectionPool() {
        connectionPool = new ArrayList<Connection>();
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    public void addConnection(Connection connection) {
        connectionPool.add(connection);
    }

    public void allocateConnections() {
        System.out.printf("Allocating [%,d] objects on Heap ... \n", NUMBER_OF_CONNECTIONS);
        for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
            System.out.printf("Left free memory [%,d] (MB) \n", (java.lang.Runtime.getRuntime().freeMemory() / MB));
            Connection connection = new Connection(new String("userName"), new String("password"), new String("host"), new String("port"), new Listener());
            addConnection(connection);
        }
    }

}
