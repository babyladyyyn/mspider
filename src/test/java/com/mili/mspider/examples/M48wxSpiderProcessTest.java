package com.mili.mspider.examples;

import com.mili.mspider.Mspider;
import org.junit.Test;

public class M48wxSpiderProcessTest {

    @Test
    public void start() {

        Mspider.create(M48wxSpiderProcess.class).thread(10).start();
    }
}
