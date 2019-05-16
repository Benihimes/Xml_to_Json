import java.util.ArrayList;
import java.util.List;

public class XmlModel {
    private String name;
    private List<XmlModel> children;

    public XmlModel(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public void addChild(XmlModel xmlElement) {
        children.add(xmlElement);
    }

    public List<XmlModel> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }
}
