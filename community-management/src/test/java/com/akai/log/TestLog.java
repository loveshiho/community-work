package com.akai.log;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// @SpringBootTest
public class TestLog {
    @Test
    public void testLog(){
        Logger logger = LoggerFactory.getLogger(TestLog.class);
        logger.info("Hello LogBack!");
    }
}
