package com.springapp.serv;

import com.springapp.base.Consts;
import com.springapp.mdl.WebsiteTemplate;
import com.springapp.mdl.assist.FieldData;
import com.springapp.mdl.assist.TemplateData;
import com.springapp.utils.FileHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SanDomingo
 * Date: 3/23/14
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Xml2ObjService {
    /**
     * 从模板xml文件中得到一个或者多个WebsiteTemplate对象
     * @param xml
     * @return
     */
    public static List<WebsiteTemplate> genWebsiteTemplate(String xml, Consts.WebsiteTemplateType type) {
        List<WebsiteTemplate> result = new ArrayList<WebsiteTemplate>();
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements website = doc.getElementsByTag("website");
        int websiteNum = website.size();
        for (int i = 0; i < websiteNum; i++) {
            Element curWebsite = website.get(i);
            WebsiteTemplate websiteTemplate = new WebsiteTemplate(type);
            // name, omitted
            //domain
            websiteTemplate.setDomain(curWebsite.attr(Consts.XML_ATTR_DOMAIN));
            //type
            websiteTemplate.setType(Consts.WebsiteTemplateType.SITE);
            //url-patterns
            Elements urlPattern = curWebsite.getElementsByTag(Consts.XML_TAG_URLPATTERN);
            int urlPatternNum = urlPattern.size();
            for (int j = 0; j < urlPatternNum; j++) {
                Element curUrlPattern = urlPattern.get(j);
                websiteTemplate.addUrlPattern(curUrlPattern.ownText());
            }
            //templateData: a templateData List, consists of 2 case
            // case1: outlink
            Elements outlink = curWebsite.getElementsByTag(Consts.XML_TAG_OUTLINK);
            int outlinkNum = outlink.size();
            for (int j = 0; j < outlinkNum; j++) {
                Element curOutlink = outlink.get(j);
                List<FieldData> outlinkData = parseOutlinkElement(curOutlink);
                websiteTemplate.addData(outlinkData);
            }
            // case2: data
            Elements data = curWebsite.getElementsByTag(Consts.XML_TAG_DATA);
            int dataNum = data.size();
            for (int j = 0; j < dataNum; j++) {
                Element curData = data.get(j);
                List<FieldData> dataData = parseDataElement(curData);
                websiteTemplate.addData(dataData);
            }
            result.add(websiteTemplate);
        }
        return result;
    }

    /**
     * 解析xml中的outlink节点，获得outlink类型的TemplateData对象
     * @param curOutlink
     * @return
     */
    private static List<FieldData> parseOutlinkElement(Element curOutlink) {
        List<FieldData> result = new ArrayList<FieldData>();
        // type, name, occur, trace, multivalue, description, expression, regularMatch, script, rangeStart, rangeEnd
        Elements entry = curOutlink.getElementsByTag(Consts.XML_TAG_ENTRY);
        int entryNum = entry.size();
        for (int i = 0; i < entryNum; i++) {
            FieldData fieldData = new FieldData(Consts.TemplateDataType.Outlink);
            Element curEntry = entry.get(i);
            // occur
            fieldData.setOccur(curEntry.attr(Consts.XML_ATTR_OCCUR));
            // trace
            fieldData.setTrace(curEntry.attr(Consts.XML_ATTR_TRACE));
            // description
            fieldData.setDescription(curEntry.attr(Consts.XML_ATTR_DESCRIPTION));

            // templatedata
            List<TemplateData> templateDataList = getTemplateDatas(curEntry);
            fieldData.addTemplateData(templateDataList);
            result.add(fieldData);
        }
        return result;
    }

    /**
     * 解析xml中的data节点，获得data类型的TemplateData对象
     * @param curData
     * @return
     */
    private static List<FieldData> parseDataElement(Element curData) {
        List<FieldData> result = new ArrayList<FieldData>();
        // type, name, occur, trace, multivalue, description, expression, regularMatch, script, rangeStart, rangeEnd
        Elements field = curData.getElementsByTag(Consts.XML_TAG_FIELD);
        int fieldNum = field.size();
        for (int i = 0; i < fieldNum; i++) {
            FieldData fieldData = new FieldData(Consts.TemplateDataType.Data);
            Element curField = field.get(i);
            // name
            fieldData.setName(curField.attr(Consts.XML_ATTR_NAME));
            // multi-value
            fieldData.setMultiValue(curField.attr(Consts.XML_ATTR_MULTIVALUE));
            // occur
            fieldData.setOccur(curField.attr(Consts.XML_ATTR_OCCUR));
            // description
            fieldData.setDescription(curField.attr(Consts.XML_ATTR_DESCRIPTION));

            // templatedata
            List<TemplateData> templateDataList = getTemplateDatas(curField);
            fieldData.addTemplateData(templateDataList);
            result.add(fieldData);
        }
        return result;
    }

    /**
     * 解析xml中的entry或field节点
     * @param curField
     * @return
     */
    private static List<TemplateData> getTemplateDatas(Element curField) {
        Elements template = curField.getElementsByTag(Consts.XML_TAG_TEMPLATE);
        int templateNum = template.size();
        List<TemplateData> templateDataList = new ArrayList<TemplateData>();
        for (int j = 0; j < templateNum; j++) {
            Element curTemplate = template.get(j);
            TemplateData templateData = new TemplateData();
            // expression
            templateData.setExpression(curTemplate.getElementsByTag(Consts.XML_TAG_EXPRESSION).get(0).ownText());
            // regular-match
            Elements regularMatchElements = curTemplate.getElementsByTag(Consts.XML_TAG_REGULARMATCH);
            if (regularMatchElements.size() > 0) {
                String regularMatch = regularMatchElements.get(0).attr(Consts.XML_ATTR_REGEX);
                templateData.setRegularMatch(regularMatch);
            }
            // script
            Elements scriptElements = curTemplate.getElementsByTag(Consts.XML_TAG_SCRIPT);
            if (scriptElements.size() > 0) {
                String script = scriptElements.get(0).ownText();
                templateData.setScript(script);
            }
            // range
            Elements rangeElements = curTemplate.getElementsByTag(Consts.XML_TAG_RANGE);
            if (rangeElements.size() > 0) {
                Element curRange = rangeElements.get(0);
                String rangeStart = curRange.attr(Consts.XML_ATTR_START);
                templateData.setRangeStart(rangeStart);
                String rangeEnd = curRange.attr(Consts.XML_ATTR_END);
                templateData.setRangeEnd(rangeEnd);
            }
            templateDataList.add(templateData);
        }
        return templateDataList;
    }

    public static void main(String[] args) throws IOException {
        String file = "src/main/resources/template.xml";
        String xml = FileHandler.readFileToString(file);
        genWebsiteTemplate(xml, Consts.WebsiteTemplateType.SITE);
        System.out.println("Finished!");
    }
}
