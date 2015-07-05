package parse.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser {
	// 利用DOM技术解析XML文件
	public static void main(String[] args) {
		try {
			// 1,获取DOM解析器对象
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder parser = factory.newDocumentBuilder();
			// 2,使用解析器去解析XML文件
			// 获取文档节点
			Document document = parser.parse("src/xml/basics/course.xml");

			parse(document);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void parse(Node node) {
		switch (node.getNodeType()) {
		case Node.DOCUMENT_NODE:
			System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			Document document =(Document)node;
			//获取元素的根节点
		Element root=document.getDocumentElement();
		parse(root);
			break;
			
		case Node.ELEMENT_NODE:
			Element element=(Element)node;
			System.out.print("<"+element.getNodeName());
			
			//获取元素节点上的所有属性
			NamedNodeMap atts=element.getAttributes();
			for (int i = 0; i < atts.getLength(); i++) {
				//根据序列获取具体的某个属性
				Node attr=atts.item(i);
				String attrName= attr.getNodeName();
				String attrValue=attr.getNodeValue();
				System.out.print(" "+attrName+"=\""+attrValue+"\"");
				
			}
			System.out.print(">");
			//获取元素节点的所有字节点
			NodeList childs=element.getChildNodes();
			for (int i = 0; i < childs.getLength(); i++) {
				Node child=childs.item(i);
//				child.getChildNodes();
				parse(child);
				
			}
			System.out.print("</"+element.getNodeName()+">");
			break;
			
		case Node.TEXT_NODE:
//			System.out.print(node.getNodeValue());
			System.out.print(node.getTextContent());
			break;

		}

	}
}
