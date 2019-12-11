/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Core.BaseClass;

import static java.lang.System.err;
import static java.lang.System.out;
import java.util.List;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.DocumentBuilder;
//import org.w3c.dom.NodeList;
//import org.w3c.dom.Element;
//import java.io.File;
//import java.io.StringReader;

import static org.apache.poi.hssf.usermodel.HeaderFooter.file;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
//import org.dom4j.io.SAXReader;
//import org.w3c.css.sac.InputSource;

/**
 *
 * @author om26642
 */
public class XMLUtility extends BaseClass {

    public Document xmlDoc;
    public Node currentNode;

    public XMLUtility() {

    }

    public void parseSOAPXML(String inputString) {
        try {
            xmlDoc = DocumentHelper.parseText(inputString);
        } catch (Exception ex) {
            err.println("[ERROR] Failed to parse string to XML - " + ex.getMessage());
        }
        
    }

    public void setNodeText(String nodeXpath, String nodeValue) {
        try {

            out.println("[INFO] XML Nodes - " + xmlDoc.selectNodes("*").size());

            Node node = xmlDoc.selectSingleNode(nodeXpath);

            if (node != null) {

                node.setText(nodeValue);
                
                out.println("[INFO] XML Node value - " + nodeXpath + " - set to : " + nodeValue);
            } else {
                err.println("[ERROR] Failed to edit node value  - node not found ");
            }

        } catch (Exception ex) {
            err.println("[ERROR] Failed to edit node value  - " + ex.getMessage());
        }
    }

    public int countNodeOccurencesWithinCurrentNode(String nodeXpath) {
        try {
            List<Node> nodes;

            if (this.currentNode != null) {
                nodes = this.currentNode.selectNodes(nodeXpath);

                if (nodes != null) {
                    out.println("[INFO] XML Nodes found - " + nodeXpath + " - count: " + nodes.size());

                    return nodes.size();
                } else {
                    err.println("[ERROR] Failed to extract node value  - node not found ");
                    return 0;
                }
            } else {
                err.println("[ERROR] Failed to extract node value  - parent node was null ");
                return 0;
            }

        } catch (Exception ex) {
            err.println("[ERROR] Failed to find xml node  - " + ex.getMessage());
            return 0;
        }

    }

    public String setCurrentNode(String nodeXpath) {
        try {
            this.currentNode = xmlDoc.selectSingleNode(nodeXpath);

            if (this.currentNode != null) {

//                out.println("[INFO] XML Node found - " + nodeXpath + " - value:  " + this.currentNode.asXML());

                return this.currentNode.asXML();

            } else {
                err.println("[ERROR] Failed to define current node " + nodeXpath + " - node not found , XML - " + this.xmlDoc.asXML());
                return null;
            }

        } catch (Exception ex) {
            err.println("[ERROR] Failed to set current node  - " + ex.getMessage());
            return null;
        }
    }

    public String retrieveAllChildNodes(String nodeXpath) {
        try {
            List<Node> nodes = xmlDoc.selectNodes(nodeXpath);
            
            if (nodes != null) {

                for (Node node : nodes) {
                    out.println("[INFO] XML Node found - " + node + " - name:  " + node.getPath());
                }

                return nodes.toString();

            } else {
                err.println("[ERROR] Failed to define current node  - node not found , XML - " + this.xmlDoc.asXML());
                return null;
            }

        } catch (Exception ex) {
            err.println("[ERROR] Failed to set current node  - " + ex.getMessage());
            return null;
        }
    }

    public String getNodeValueFromCurrentNode(String nodeXpath) {
        if (!nodeXpath.equals("")) {
            try {
                Node node;                
//                System.out.println("[Info]Current node : " + this.currentNode.getStringValue());

                if (this.currentNode != null) {

                    node = this.currentNode.selectSingleNode(nodeXpath);

                    if (node != null) {
//                        out.println("[INFO] XML Node found - " + nodeXpath + " - value:  " + node.asXML());

                        return node.getStringValue();

                    } else {
                        err.println("[ERROR] Failed to extract node value  - node not found ");
                        return null;
                    }

                } else {
                    err.println("[ERROR] Failed to extract node value  - parent node was null ");
                    return null;
                }

            } catch (Exception ex) {
                err.println("[ERROR] Failed to find xml node  - " + nodeXpath + ", error - " + ex.getMessage());
                return null;
            }
        } else {
            return "";
        }
    }

    public boolean setNodeValueFromCurrentNode(String nodeXpath, String nodeValue) {
        try {
            Node node;

            if (this.currentNode != null) {
                
                node = this.currentNode.selectSingleNode(nodeXpath);

                if (node != null) {
                    out.println("[INFO] XML Node found - " + nodeXpath + " - current value:  " + node.asXML());

                    node.setText(nodeValue);

                    out.println("[INFO] XML Node value set successfully - " + nodeXpath + " - new value:  " + node.asXML());

                    return true;

                } else {
                    err.println("[ERROR] Failed to set node value for xpath: " + nodeXpath + " - node not found ");
                    return false;
                }

            } else {
                err.println("[ERROR] Failed to set node value  - parent node was null ");
                return false;
            }

        } catch (Exception ex) {
            err.println("[ERROR] Failed to find xmk node  - " + ex.getMessage());
            return false;
        }
    }

    public String getNodeValueFromDocument(String nodeXpath) {
        try {

            out.println("[INFO] XML Nodes found - " + xmlDoc.selectNodes("*").size());

            Node node = xmlDoc.selectSingleNode(nodeXpath);

            out.println("[INFO] XML Node - " + nodeXpath + " - value:  " + node.getStringValue());

            if (node != null) {

                return node.getStringValue();

            } else {
                err.println("[ERROR] Failed to extract node value  - node not found ");
                return null;
            }

        } catch (Exception ex) {
            err.println("[ERROR] Failed to extract node value  - " + ex.getMessage());

            return null;
        }
    }

    public String returnDocumentXMLAsString() {
        
        return xmlDoc.asXML();
    }

    public void GetNodeFromXmlFileByNodeName(String xmlfileResponse, String currentNode) {
        try 
        {
            if (xmlfileResponse != null) 
            {
//                DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//                Document doc = docBuilder.parse(new InputSource(new StringReader(xmlfileResponse)));
           
           // out.println("[INFO] XML Node value - " + nodeXpath + " - set to : " + nodeValue );
               
            } 
            else 
            {
                err.println("[ERROR] Failed node not found or XmlfileResponse was null");
            }            
        } 
        
        catch (Exception ex) 
        {
            err.println("[ERROR] Failed to retrieve text from node " + currentNode + " - " + ex.getMessage());
        }
    }
    
    public String getNodeNameFromCurrentNode(String nodeXpath) 
    {
        try 
        {
            Node node;

            node = this.currentNode.selectSingleNode(nodeXpath);

            if (node != null) 
            {
//                        out.println("[INFO] XML Node found - " + nodeXpath + " - value:  " + node.asXML());
                return node.getName();
            } 
            else 
            {
                err.println("[ERROR] Failed to extract the node name  - node not found ");
                return null;
            }

        } 
        catch (Exception ex) 
        {
            err.println("[ERROR] Failed to find xml node  - " + nodeXpath + ", error - " + ex.getMessage());
            return null;
        }
 
    }

}
