package com.luxoft.jpt.course.reallife;

import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Xml {

    public static void main(String[] args) throws IOException {
        Element root = new Element("trades");
        Document document = new Document(root);
        for (int i = 0; i < 500_000; i++) {
            root.addContent(new Element("trade" + i));
        }
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(document, System.out);
    }
}
