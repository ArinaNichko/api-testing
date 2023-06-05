package utils;

import exceptions.TestExecutionException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.io.File;

public class UpdateXmlHelper {

  public String updateFieldByPath(
          String pathToFile, String fullFieldPath, String valueToUpdate) {
    try {
      Document document = new SAXReader().read(new File(pathToFile));
      XPath xpath = document.createXPath(fullFieldPath);
      Node node = xpath.selectSingleNode(document);

      node.setText(valueToUpdate);
      return document.asXML();

    } catch (DocumentException e) {
      throw new TestExecutionException("Problem with parsing xml string");
    }
  }
}
