package com.mili.mspider;

import jodd.util.ThreadUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class MspiderTest {

    private final static Logger logger = LoggerFactory.getLogger(MspiderTest.class);

    @Test
    public void test1() {
//        IntStream.range(1,100)
//        Stream.of(100,10)
//        IntStream.range(1,100).flatMap((i,b) -> {
//            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<Integer>() {
//
//                private AtomicInteger atomicLong = new AtomicInteger(0);
//
//                @Override
//                public boolean hasNext() {
//                    return true;
//                }
//
//                @Override
//                public Integer next() {
//                    return atomicLong.getAndIncrement();
//                }
//            }, 0), true);
//        }).parallel().forEach(i -> {
//            ThreadUtil.sleep(200);
//            logger.info(i + "");
//        });
    }

    @Test
    public void test2() {
        StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<Integer>() {

            private AtomicInteger atomicLong = new AtomicInteger(0);

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return atomicLong.getAndIncrement();
            }
        }, 0), true).forEach(i -> {
            ThreadUtil.sleep(200);
            logger.info(i + "");
        });
    }

    @Test
    public void test3() {
        StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<Integer>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return index++;
            }
        }, 0), true).limit(100000).sorted().collect(Collectors.toList()).parallelStream()
                .forEach(i -> {
                    logger.info(i + "");
                    ThreadUtil.sleep(200);
                });
    }

    @Test
    public void test6() {
        List<Integer> list = IntStream.range(1, 100).boxed().filter(i -> {
            System.out.println(i);
            return true;
        }).collect(Collectors.toList());
        System.out.println(list.stream().count());
    }
}
