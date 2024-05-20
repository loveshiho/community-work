package com.akai.log;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// @SpringBootTest
public class testLog {
    @Test
    public void testLog(){
        Logger logger = LoggerFactory.getLogger(testLog.class);
        logger.info("Hello LogBack!");
    }
}
