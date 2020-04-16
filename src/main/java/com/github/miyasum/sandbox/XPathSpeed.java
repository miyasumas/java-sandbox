package com.github.miyasum.sandbox;

import com.google.common.base.Stopwatch;
import com.google.common.io.Resources;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

/**
 * XPath の処理速度
 *
 * @author MIYASAKA Yasumasa
 */
public class XPathSpeed {

  public static void main(String[] args) throws Throwable {
    File file = new File(Resources.getResource("sample.xml").toURI());

    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = builderFactory.newDocumentBuilder();
    Document doc = builder.parse(file);

    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();
    XPathExpression expr = xpath.compile("/root/items/item");

    int trial = 1000000;

    Stopwatch watch1 = Stopwatch.createStarted();
    for (int i = 0; i < trial; i++) {
      expr.evaluate(doc);
    }
    System.out.println("Compile: " + watch1.stop().elapsed(TimeUnit.MILLISECONDS) + "[msec]");

    Stopwatch watch2 = Stopwatch.createStarted();
    for (int i = 0; i < trial; i++) {
      xpath.evaluate("/root/items/item", doc);
    }
    System.out.println("Compile: " + watch2.stop().elapsed(TimeUnit.MILLISECONDS) + "[msec]");
  }
}
