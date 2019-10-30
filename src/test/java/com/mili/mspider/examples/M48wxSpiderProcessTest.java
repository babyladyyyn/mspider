package com.mili.mspider.examples;

import com.mili.mspider.Mspider;
import org.junit.Test;

public class M48wxSpiderProcessTest {

    @Test
    public void start() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        Mspider.start(M48wxSpiderProcess.class);
    }
}
