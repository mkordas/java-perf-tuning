
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.objectreference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

import static com.luxoft.jpt.course.util.TestUtil.invokeGC;


public class PhantomReferenceSample {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Creating Phantom References");

        // The reference itself will be appended to the dead queue for clean up.
        ReferenceQueue<MyResource> referenceQueue = new ReferenceQueue<MyResource>();

        // Below reference is now became a phantom reference.
        // The object will be collected only if no strong references are available.
        MyResource myResource = new PhantomReferenceSample().new MyResource();

        //J-
        PhantomResourceReference<MyResource> phantomReference =
            new PhantomReferenceSample()
                .new PhantomResourceReference<MyResource>(myResource, referenceQueue);
        //J+

        // Make resource eligible for GC
        myResource = null;

        // The reference should not be enqueued
        System.out.printf("Is resource enqueued ? [%b] \n", phantomReference.isEnqueued());

        // The object may now be garbage collected
        invokeGC();

        System.out.println("\n*** After GC invocation ***\n");

        // The reference should be enqueued
        System.out.printf("Is resource enqueued ? [%b] \n", phantomReference.isEnqueued());

        Reference<? extends MyResource> reference = referenceQueue.poll();
        if (reference != null) {
            ((PhantomResourceReference<?>) reference).cleanUp();
        }

    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Inner Classes 
    //~ ----------------------------------------------------------------------------------------------------------------

    public class MyResource {
        // NB: If there is a finalize() method then PhantomReference's doesn't get appended to a ReferenceQueue
        private MyResource() {
            System.out.printf("[%s] has been created \n", MyResource.class.getSimpleName());
        }

    }

    class PhantomResourceReference<Resource> extends PhantomReference<Resource> {

        public PhantomResourceReference(Resource referent, ReferenceQueue<? super Resource> q) {
            super(referent, q);
        }

        void cleanUp() {
            System.out.println("Clean up routine for resource");
        }
    }

}
