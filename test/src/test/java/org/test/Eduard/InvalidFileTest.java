package org.test.Eduard;

import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class InvalidFileTest {
    private Parser parser = new Parser();
    private String file_name;
    @Пусть("на вход программы подан некорректный {word}")
    public void на_вход_программы_подан_некорректный_xml(String file)  {

        this.file_name = file;
    }

    @Тогда("должна возникнуть ошибка конвертации XML")
    public void должна_возникнуть_ошибка_конвертации_XML() throws IOException, SAXException, ParserConfigurationException {
        try {
            parser.parse(file_name);
            Assert.assertTrue(false);
        }
        catch (SAXParseException e) {
            Assert.assertTrue(true);
        }
    }
}
