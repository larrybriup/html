package parse.ex;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

//import javax.xml.parsers.DocumentBuilderFactory;

public class NumberInfoSearch {
	/*
	 * <list> 
	 * <id>11</id>
	 * <num>1368315</num>
	 * <code>010</code>
	 * <city>北京市</city>
	 * <cardtype>北京移动神州行卡</cardtype>
	 *  </list>
	 */
	public static void main(String[] args) {
		if (args.length <= 0) {
			System.out.println("请输入要查询的号码!");
//			System.exit(1);
			return;
		}

		try {
			XMLReader parser = XMLReaderFactory.createXMLReader();
			MyHandler handler = new MyHandler();
			String key = args[0];
			handler.setKey(key);
			parser.setContentHandler(handler);
			parser.parse("src/parse/ex/mobilelist.xml");
			if (handler.str.equals("")) {
				System.out.println("您查询的号码不存在!请重新输入!");
			}

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class MyHandler extends DefaultHandler {

	private static boolean flag1, flag2;
	public String str = "";
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equals("num")) {
			flag1 = true;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals("cardtype") && flag1 && flag2) {

			System.out.print(str);
			flag1 = false;
			flag2 = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String content = new String(ch, start, length);
		if (flag1) {

			if (content.equals(key)) {
				flag2 = true;
			}
			if (flag2) {
				str += content;

			}
		}
	}

}
