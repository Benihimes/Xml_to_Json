package org.test.Eduard;

import java.util.LinkedList;
import java.util.List;

public class XmlModel {
    private String name;
    private String text;

    private List<XmlModel> children;

    public XmlModel(String name, String text) {
        this.name = name;
        this.text = text;
        this.children = new LinkedList(); ;
    }

    public String getName() {
        return name;
    }
    public String getText() {
        return text;
    }

    public  List<XmlModel> getChildren() {
        return children;
    }

    public XmlModel addChild(String name, String text) {
        XmlModel childNode = new XmlModel(name, text);
        this.children.add(childNode);
        return childNode;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString(){
        return name+" "+text+" "+children;
    }
}

