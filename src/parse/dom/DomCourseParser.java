package parse.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.soap.Text;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DomCourseParser {
	private static int sum;
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
			NodeList list=
					document.getElementsByTagName("duration");
			for(int i=0;i<list.getLength();i++){
				Element duration=(Element)list.item(i);
//				Node day= duration.getFirstChild();
				Text day=(Text) duration.getChildNodes().item(0);
				sum+=Integer.parseInt(day.getTextContent());
			}
			System.out.println(sum);

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

}
