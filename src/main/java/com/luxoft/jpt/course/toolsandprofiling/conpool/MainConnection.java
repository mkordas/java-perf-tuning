
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.toolsandprofiling.conpool;

public class MainConnection {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private ConnectionPool connectionPool;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        // -Xms4m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:\ibalosin\heap_Dump2.hprof -Xrunhprof:file=c:\ibalosin\heap_Dump2.hprof.txt
        // -Xms4m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:\ibalosin\heap_Dump2.hprof -Xrunhprof:heap=sites,cpu=samples,depth=10,monitor=y,thread=y,doe=y

        MainConnection mainHeapDump = new MainConnection();
        mainHeapDump.setConnectionPool(new ConnectionPool());
        mainHeapDump.getConnectionPool().allocateConnections();
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

}
