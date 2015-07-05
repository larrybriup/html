package parse.sax;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class SaxParser {
	// 利用SAX技术解析XML文件
	public static void main(String[] args) {
		try {
			// 1、创建SAX解析器对象
			XMLReader parser = XMLReaderFactory.createXMLReader();
			// 2,创建事件处理器
			MyHandler handler = new MyHandler();
			// 3,为XML解析器添加事件处理器
			parser.setContentHandler(handler);
			// 4,进行解析
//			parser.parse(args[0]);
			parser.parse("src/xml/dtd/course.xml");
			//设置验证型解析器
			parser.setFeature("http://xml", true);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

// 自定义的事件处理器
class MyHandler extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		// 文档开始解析要用的回调函数
		System.out.println("Parse Start!");
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		// 解析元素的开始标记触发的回调函数
		System.out.print("<" + qName);
		for (int i = 0; i < atts.getLength(); i++) {
			String attName = atts.getQName(i);
			String attValue = atts.getValue(i);
			System.out.print(" " + attName + "=\"" + attValue + "\"");
		}
		System.out.print(">");

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// 解析元素的结束标记触发的回调函数
		System.out.print("</" + qName + ">");

	}

	@Override
	public void endDocument() throws SAXException {
		// 文档解析完成调用的回调函数
		System.out.println();
		System.out.println("Parse End!");
	}

//	@Override
//	public void characters(char[] ch, int start, int length)
//			throws SAXException {
//		// 解析字符数据触发的回调函数
//		String content = new String(ch, start, length);
//		System.out.print(content);
//
//	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		String content = new String(ch, start, length);
		System.out.print(content);
	}

	//显示内容
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String text = new String(ch, start, length);
		System.out.print(text);
	}
	

}