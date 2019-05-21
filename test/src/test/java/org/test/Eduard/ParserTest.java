package org.test.Eduard;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserTest {
    String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Test
    public void testOneElementFile() throws Exception {
        Parser parser = new Parser();
        parser.getResult("Case1.xml");
        String json;
        json = readFile("src/main/json/output1.json", StandardCharsets.UTF_8);
        boolean isCorrect = false;
        String []split1 = json.split("\\[");
        String []split2 = json.split("\\]");

        if (split1.length+split2.length-2 == 2) {
            isCorrect = true;
            Assert.assertTrue(isCorrect);
        }
    }
    @Test
    public void testMultipleElementsFile() throws Exception {
        Parser parser = new Parser();
        parser.getResult("Case2.xml");
        String json = readFile("src/main/json/output2.json", StandardCharsets.UTF_8);
        boolean isCorrect = false;
        int a = 0;
        int count = 0;
        for (int i = json.indexOf('[')+1; i < json.length(); i++) {
            count = 0;
            for (int j = i; j < json.length(); j++) {
                if (json.charAt(j) == '[')
                    count++;
                if (json.charAt(j) == ']') {
                    i = j;
                    break;
                }
            }
            if (count > a)
                a = count;
        }
        if (a+1 == 5) {
            isCorrect = true;
            Assert.assertTrue(isCorrect);
        }
    }
    @Test
    public void testInvalidInputFile() throws Exception{
        Parser parser = new Parser();
        try {
            parser.parse("Case3.xml");
            Assert.assertTrue(false);
        }
        catch (SAXParseException e) {
            Assert.assertTrue(true);
        }
    }
}
