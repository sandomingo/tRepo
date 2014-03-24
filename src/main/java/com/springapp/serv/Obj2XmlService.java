package com.springapp.serv;

import com.springapp.base.Consts;
import com.springapp.base.SimplePair;
import com.springapp.mdl.UrlPattern;
import com.springapp.mdl.WebsiteTemplate;
import com.springapp.mdl.assist.FieldData;
import com.springapp.mdl.assist.TemplateData;
import com.springapp.utils.FileHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: SanDomingo
 * Date: 3/23/14
 * Time: 4:34 PM
 */
public class Obj2XmlService {
    public static String getWebsiteTemplateXMLString(List<WebsiteTemplate> websiteTemplates) throws TransformerException, ParserConfigurationException {
        Document doc = genWebsiteTemplateXML(websiteTemplates);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new StringWriter());
        transformer.transform(source, result);
        String xmlString = result.getWriter().toString();
        return xmlString;
    }

    /**
     * 将一个WebsiteTemplate对象转换成XML Document对象
     * @param websiteTemplate
     * @return
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public static String getWebsiteTemplateXMLString(WebsiteTemplate websiteTemplate) throws TransformerException, ParserConfigurationException {
        List<WebsiteTemplate> websiteTemplates = new ArrayList<WebsiteTemplate>();
        websiteTemplates.add(websiteTemplate);
        return getWebsiteTemplateXMLString(websiteTemplates);
    }
    /**
     * 将多个WebsiteTemplate对象转换成一个XML Document对象
     * @param websiteTemplates
     * @return
     * @throws ParserConfigurationException
     */
    public static Document genWebsiteTemplateXML(List<WebsiteTemplate> websiteTemplates) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        // root
        Element websites = doc.createElement(Consts.XML_TAG_WEBSITES);
        doc.appendChild(websites);

        for (WebsiteTemplate websiteTemplate : websiteTemplates) {
            // website: 1
            Element website = genWebsiteElement(doc, websiteTemplate);
            websites.appendChild(website);

            // url-pattern: 1-n
            List<Element> urlPatterns = genUrlPatternElements(doc, websiteTemplate);
            for (Element urlPattern : urlPatterns) {
                website.appendChild(urlPattern);
            }
            // out-link: 1
            List<Element> outlinks = genOutlinkElement(doc, websiteTemplate.getData());
            for (Element outlink : outlinks) {
                website.appendChild(outlink);
            }
            // data: 1
            List<Element> data = genDataElement(doc, websiteTemplate.getData());
            for (Element datum : data) {
                website.appendChild(datum);
            }
        }
        return doc;
    }

    /**
     * 创建data元素
     * @param doc
     * @param data
     * @return 返回0或1个
     */
    private static List<Element> genDataElement(Document doc, List<FieldData> data) {
        List<Element> result = new ArrayList<Element>();
        boolean hasData = false;
        Element element = doc.createElement(Consts.XML_TAG_DATA);
        for (FieldData fieldData : data) {
            if (fieldData.getType() != Consts.TemplateDataType.Data) continue;
            hasData = true;
            Element field = genFieldElements(doc, fieldData);
            element.appendChild(field);
        }
        if (hasData) {
            result.add(element);
        }
        return result;
    }

    /**
     * 创建field元素
     * @param doc
     * @param fieldData
     * @return 返回0或1个
     */
    private static Element genFieldElements(Document doc, FieldData fieldData) {
        Element element = doc.createElement(Consts.XML_TAG_FIELD);
        element.setAttribute(Consts.XML_ATTR_NAME, fieldData.getName());
        element.setAttribute(Consts.XML_ATTR_MULTIVALUE, fieldData.getMultiValue());
        element.setAttribute(Consts.XML_ATTR_OCCUR, fieldData.getOccur());
        element.setAttribute(Consts.XML_ATTR_DESCRIPTION, fieldData.getDescription());
        List<Element> templates = genTemplateElement(doc, fieldData.getTemplateDataList());
        for (Element template : templates) {
            element.appendChild(template);
        }
        return element;
    }

    /**
     * 创建outlink元素
     * @param doc
     * @param data
     * @return 返回0或1个
     */
    private static List<Element> genOutlinkElement(Document doc, List<FieldData> data) {
        List<Element> result = new ArrayList<Element>();
        boolean hasOutlink = false;
        Element element = doc.createElement(Consts.XML_TAG_OUTLINK);
        for (FieldData fieldData : data) {
            if (fieldData.getType() != Consts.TemplateDataType.Outlink) continue;
            hasOutlink = true;
            Element entry = genEntryElement(doc, fieldData);
            element.appendChild(entry);
        }
        if (hasOutlink) {
            result.add(element);
        }
        return result;
    }


    /**
     * 创建entry元素
     * @param doc
     * @param fieldData
     * @return
     */
    private static Element genEntryElement(Document doc, FieldData fieldData) {
        Element element = doc.createElement(Consts.XML_TAG_ENTRY);
        element.setAttribute(Consts.XML_ATTR_OCCUR, fieldData.getOccur());
        element.setAttribute(Consts.XML_ATTR_TRACE, fieldData.getTrace());
        element.setAttribute(Consts.XML_ATTR_DESCRIPTION, fieldData.getDescription());
        List<Element> templates = genTemplateElement(doc, fieldData.getTemplateDataList());
        for (Element template : templates) {
            element.appendChild(template);
        }
        return element;
    }

    /**
     * 创建template元素
     * @param doc
     * @param templateDataList
     * @return
     */
    private static List<Element> genTemplateElement(Document doc, List<TemplateData> templateDataList) {
        List<Element> result = new ArrayList<Element>();
        for (TemplateData templateData : templateDataList) {
            Element element = doc.createElement(Consts.XML_TAG_TEMPLATE);
            Element expression = doc.createElement(Consts.XML_TAG_EXPRESSION);
            expression.setTextContent(templateData.getExpression());
            element.appendChild(expression);
            if (!templateData.getScript().isEmpty()) {
                Element script = doc.createElement(Consts.XML_TAG_SCRIPT);
                script.setAttribute(Consts.XML_ATTR_METHOD, templateData.getScript());
                element.appendChild(script);
            }
            if (!templateData.getRegularMatch().isEmpty()) {
                Element regularMatch = doc.createElement(Consts.XML_TAG_REGULARMATCH);
                regularMatch.setAttribute(Consts.XML_ATTR_REGEX, templateData.getRegularMatch());
                element.appendChild(regularMatch);
            }
            if (!templateData.getRangeStart().isEmpty() || !templateData.getRangeEnd().isEmpty()) {
                Element range = doc.createElement(Consts.XML_TAG_RANGE);
                if (!templateData.getRangeStart().isEmpty()) {
                    range.setAttribute(Consts.XML_ATTR_START, templateData.getRangeStart());
                }
                if (!templateData.getRangeEnd().isEmpty()) {
                    range.setAttribute(Consts.XML_ATTR_END, templateData.getRangeEnd());
                }
                element.appendChild(range);
            }
            result.add(element);
        }
        return result;
    }

    /**
     * 创建url－pattern元素
     * @param doc
     * @param websiteTemplate
     * @return
     */
    private static List<Element> genUrlPatternElements(Document doc, WebsiteTemplate websiteTemplate) {
        List<Element> result = new ArrayList<Element>();
        for (UrlPattern urlPattern : websiteTemplate.getUrlPatterns()) {
            String urlPatternStr = urlPattern.getUrlPattern();
            Element element = doc.createElement(Consts.XML_TAG_URLPATTERN);
            element.setTextContent(urlPatternStr);
            result.add(element);
        }
        return result;
    }

    /**
     * 创建website元素
     * @param doc
     * @param websiteTemplate
     * @return
     */
    private static Element genWebsiteElement(Document doc, WebsiteTemplate websiteTemplate) {
        Element element = doc.createElement(Consts.XML_TAG_WEBSITE);
        element.setAttribute(Consts.XML_ATTR_DOMAIN, websiteTemplate.getDomain());
        return element;
    }

    /**
     * For debugging using
     * @param args
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws IOException
     */
    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException {
        String file = "src/main/resources/template.xml";
        String xml = FileHandler.readFileToString(file);
        List<WebsiteTemplate> websiteTemplates = Xml2ObjService.genWebsiteTemplate(xml, Consts.WebsiteTemplateType.SITE);
        for (WebsiteTemplate websiteTemplate : websiteTemplates) {
            String xmlstr = Obj2XmlService.getWebsiteTemplateXMLString(websiteTemplates);
            System.out.println(xmlstr);
        }
    }
}
