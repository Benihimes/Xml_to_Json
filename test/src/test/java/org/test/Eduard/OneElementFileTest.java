package org.test.Eduard;

import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Attachment;
import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OneElementFileTest {
    String file_name;
    String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    Parser parser = new Parser();
    @Пусть("на вход программы подан xml-файл с одним элементом {word}")
    public void на_вход_программы_подан_xml_файл_с_одним_элементом(String file) throws IOException, SAXException, ParserConfigurationException {
        file_name = file;
        parser.getResult(file_name);

        Path content = Paths.get("src/main/xml/Case1.xml");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Case1.xml", is);
        }
    }

    @Тогда("на выходе получаем json с одним элементом")
    public void на_выходе_получаем_json_с_элементом() throws IOException {
        String json = readFile("src/main/json/output1.json", StandardCharsets.UTF_8);
        boolean correct = false;
        String []split1 = json.split("\\[");
        String []split2 = json.split("\\]");
        if (split1.length+split2.length-2 == 2) {
            correct = true;
            Assert.assertTrue(correct);
        }
        Path content = Paths.get("src/main/json/output1.json");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("output1.json", is);
        }

    }
}
