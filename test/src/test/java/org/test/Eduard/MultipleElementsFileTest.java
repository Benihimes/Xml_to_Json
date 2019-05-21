package org.test.Eduard;

import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultipleElementsFileTest {
    private Parser parser = new Parser();
    private String file_name;
    String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    @Пусть("на вход программы подан xml-файл с 5 уровнями вложенности {word}")
    public void test5lev(String file) throws IOException, SAXException, ParserConfigurationException {

        file_name = file;
        parser.getResult(file_name);
    }

    @Тогда("на выходе получаем json с 5 уровнями вложенности")
    public void на_выходе_получаем_json_с_уровнями_вложенности() throws Exception {
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
}
