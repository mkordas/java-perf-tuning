/**
 * Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 * Author: Ionut Balosin
 * E-mail: ibalosin@luxoft.com
 */

package com.luxoft.jpt.course.bean.mxbean;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;


public class AdvancedMeasurementsMXBean {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        System.out.printf("Running application with Java version [%s] \n", System.getProperty("java.version"));

        printOperatingSystemMXBean();
        printMemoryMXBean();
        printClassLoadingMXBean();
        printThreadMXBean();
        printCompilationMXBean();
        printMemoryManagerMXBean();
        printMemoryPoolMXBean();
    }

    private static void printOperatingSystemMXBean() {
        System.out.printf("\n\t\t ******** [%s] ******** \n", OperatingSystemMXBean.class.getSimpleName());
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        System.out.printf("Amount of virtual memory that is guaranteed to be available to the running process in bytes = [%,d] \n", osBean.getCommittedVirtualMemorySize());
        System.out.printf("Amount of free physical memory in bytes = [%,d] \n", osBean.getFreePhysicalMemorySize());
        System.out.printf("Amount of free swap space in bytes = [%,d] \n", osBean.getFreeSwapSpaceSize());
        System.out.printf("'Recent cpu usage' for the Java Virtual Machine process or -1 if this operation is not supported = [%,f] \n", osBean.getProcessCpuLoad());
        System.out.printf("CPU time used by the process on which the Java virtual machine is running in nanoseconds = [%,d] \n", osBean.getProcessCpuTime());
        System.out.printf("'Recent cpu usage' for the whole system or -1 if this operation is not supported = [%,f] \n", osBean.getSystemCpuLoad());
        System.out.printf("Total amount of physical memory in bytes = [%,d] \n", osBean.getTotalPhysicalMemorySize());
        System.out.printf("Total amount of swap space in bytes = [%,d] \n", osBean.getTotalSwapSpaceSize());
        System.out.printf("Operating system architecture = [%s] \n", osBean.getArch());
        System.out.printf("Number of processors available to the Java virtual machine = [%,d] \n", osBean.getAvailableProcessors());
        System.out.printf("System load average for the last minute or -1 if this operation is not supported = [%,f] \n", osBean.getSystemLoadAverage());
        System.out.printf("Operating system name = [%s] \n", osBean.getName());
        System.out.printf("Operating system version = [%s] \n", osBean.getVersion());
    }

    private static void printMemoryMXBean() {
        System.out.printf("\n\t\t ******** [%s] ******** \n", MemoryMXBean.class.getSimpleName());
        MemoryMXBean memMXBean = ManagementFactory.getPlatformMXBean(MemoryMXBean.class);
        //J-
        System.out.printf(
            "Current memory usage of the heap that is used for object allocation in bytes: init = [%,d], used = [%,d], committed = [%,d], max = [%,d] \n",
            memMXBean.getHeapMemoryUsage().getInit(),
            memMXBean.getHeapMemoryUsage().getUsed(),
            memMXBean.getHeapMemoryUsage().getCommitted(),
            memMXBean.getHeapMemoryUsage().getMax()
        );
        //J+

        //J-
        System.out.printf(
            "Current memory usage of non-heap memory that is used by the Java virtual machine in bytes: init = [%,d], used = [%,d], committed = [%,d], max = [%,d] \n",
            memMXBean.getNonHeapMemoryUsage().getInit(),
            memMXBean.getNonHeapMemoryUsage().getUsed(),
            memMXBean.getNonHeapMemoryUsage().getCommitted(),
            memMXBean.getNonHeapMemoryUsage().getMax()
        );
        //J+
        System.out.printf("approximate number of objects for which finalization is pending = [%,d] \n", memMXBean.getObjectPendingFinalizationCount());
    }

    private static void printClassLoadingMXBean() {
        System.out.printf("\n\n\t\t ******** [%s] ******** \n", ClassLoadingMXBean.class.getSimpleName());
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getPlatformMXBean(ClassLoadingMXBean.class);
        System.out.printf("Number of classes that are currently loaded in the Java virtual machine = [%,d] \n", classLoadingMXBean.getLoadedClassCount());
        System.out.printf("Total number of classes that have been loaded since the Java virtual machine has started execution = [%,d] \n", classLoadingMXBean.getTotalLoadedClassCount());
        System.out.printf("Total number of classes unloaded since the Java virtual machine has started execution = [%,d] \n", classLoadingMXBean.getUnloadedClassCount());
    }

    private static void printThreadMXBean() {
        System.out.printf("\n\t\t ******** [%s] ******** \n", ThreadMXBean.class.getSimpleName());
        ThreadMXBean threadMXBean = ManagementFactory.getPlatformMXBean(ThreadMXBean.class);
        System.out.printf("Total CPU time for the current thread in nanoseconds = [%,d] \n", threadMXBean.getCurrentThreadCpuTime());
        System.out.printf("Current number of live daemon threads = [%,d] \n", threadMXBean.getDaemonThreadCount());
        System.out.printf("Current number of live threads including both daemon and non-daemon threads = [%,d] \n", threadMXBean.getThreadCount());
    }

    private static void printCompilationMXBean() {
        System.out.printf("\n\t\t ******** [%s] ******** \n", CompilationMXBean.class.getSimpleName());
        CompilationMXBean compilationMXBean = ManagementFactory.getPlatformMXBean(CompilationMXBean.class);
        System.out.printf("Name of the Just-in-time (JIT) compiler = [%s] \n", compilationMXBean.getName());
        System.out.printf("Approximate accumlated elapsed time (in milliseconds) spent in compilation = [%,d] \n", compilationMXBean.getTotalCompilationTime());
    }

    private static void printMemoryManagerMXBean() {
        System.out.printf("\n\t\t[%s] \n", MemoryManagerMXBean.class.getSimpleName());
        List<MemoryManagerMXBean> memManagerMXBeans = ManagementFactory.getMemoryManagerMXBeans();

        for (MemoryManagerMXBean memManagerMXBean : memManagerMXBeans) {
            System.out.printf("Name representing this memory manager = [%s] \n", memManagerMXBean.getName());
            String[] memoryPoolNames = memManagerMXBean.getMemoryPoolNames();
            for (String memoryPoolName : memoryPoolNames) {
                System.out.printf("Name of memory pools that this memory manager manages = [%s] \n", memoryPoolName);
            }
        }
    }

    private static void printMemoryPoolMXBean() {
        System.out.printf("\n\t\t ******** [%s] ******** \n", MemoryPoolMXBean.class.getSimpleName());
        List<MemoryPoolMXBean> memPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();

        for (MemoryPoolMXBean memPoolMXBean : memPoolMXBeans) {
            System.out.printf("Name representing this memory manager = [%s] \n", memPoolMXBean.getName());
            //J-
            System.out.printf(
                "Estimate of the memory usage of this memory pool: init = [%,d], used = [%,d], committed = [%,d], max = [%,d] \n",
                memPoolMXBean.getUsage().getInit(),
                memPoolMXBean.getUsage().getUsed(),
                memPoolMXBean.getUsage().getCommitted(),
                memPoolMXBean.getUsage().getMax()
            );
            //J+
        }
    }

}
