package com.mili.mspider;

import com.mili.mspider.examples.M48wxSpiderProcess;
import org.junit.Test;

public class MspiderTest {

    @Test
    public void start() {
        Mspider.start(M48wxSpiderProcess.class,1);
    }
}
