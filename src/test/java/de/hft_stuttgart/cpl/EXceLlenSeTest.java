package de.hft_stuttgart.cpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class EXceLlenSeTest {
    @Test
    void testParser() throws ParseException {
        testParserSingle("(2+3)*4\n");
        testParserSingle("(2+3)*42+3*4\n5+6*7\n");
    }

    void testParserSingle(String input) throws ParseException {
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        EXceLlenSe parser = new EXceLlenSe(bis);
        while (true)
        {
            try {
                parser.parseOneLine();
            }
            catch (ParseException ex) {
                if ( ex.getMessage().equals("End Of Input") ) {
                    break;
                }
                else {
                    throw ex;
                }
            }
        }
    }
}