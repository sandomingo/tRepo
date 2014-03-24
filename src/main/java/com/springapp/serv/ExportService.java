package com.springapp.serv;

import com.springapp.base.OpResult;
import com.springapp.dao.WebsiteTemplateDao;
import com.springapp.dao.impl.WebsiteTemplateDaoImpl;
import com.springapp.mdl.WebsiteTemplate;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板导出服务类
 * 目前支持从DB中导出模板到xml格式
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:27 PM
 */
public class ExportService {
    private WebsiteTemplateDao dao;

    public ExportService() {
        this.dao = new WebsiteTemplateDaoImpl();
    }
    /**
     * 将一个websiteTemplate导出到一个xml文件
     * @param websiteTemplateID
     * @return
     */
    public  OpResult export2XMLString(Integer websiteTemplateID) {
        WebsiteTemplate websiteTemplate = new WebsiteTemplate(websiteTemplateID);
        OpResult result = dao.read(websiteTemplate);
        try {
            String xmlString = Obj2XmlService.getWebsiteTemplateXMLString(websiteTemplate);
            result.setOutput(xmlString);
        } catch (TransformerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }

    /**
     * 将多个websiteTemplate导出到一个xml文件
     * @param websiteTemplateIDs
     * @return
     */
    public  OpResult export2XMLString(List<Integer> websiteTemplateIDs) {
        List<WebsiteTemplate> websiteTemplates = new ArrayList<WebsiteTemplate>();
        for (Integer id : websiteTemplateIDs) {
            WebsiteTemplate websiteTemplate = new WebsiteTemplate(id);
            websiteTemplates.add(websiteTemplate);
        }
        OpResult result = dao.readSeveral(websiteTemplates);
        try {
            String xmlString = Obj2XmlService.getWebsiteTemplateXMLString(websiteTemplates);
            result.setOutput(xmlString);
        } catch (TransformerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }

    /**
     * 将一个websiteTemplate导出到一个xml文件
     * @param websiteTemplateID
     * @return
     */
    private OpResult export2XML(Integer websiteTemplateID) {
        WebsiteTemplate websiteTemplate = new WebsiteTemplate(websiteTemplateID);
        OpResult result = dao.read(websiteTemplate);
        return result;
    }

    /**
     * 将多个websiteTemplate导出到一个xml文件
     * @param websiteTemplateIDs
     * @return
     */
    public OpResult export2XML(List<Integer> websiteTemplateIDs) {
        List<WebsiteTemplate> websiteTemplates = new ArrayList<WebsiteTemplate>();
        for (Integer id : websiteTemplateIDs) {
            WebsiteTemplate websiteTemplate = new WebsiteTemplate(id);
            websiteTemplates.add(websiteTemplate);
        }
        OpResult result = dao.readSeveral(websiteTemplates);
        return result;
    }

    /**
     * 将多个websiteTemplate导出到多个xml文件
     * @param websiteTemplateIDs
     * @return
     */
    public OpResult export2XMLs(List<Integer> websiteTemplateIDs) {
        for (Integer id : websiteTemplateIDs) {
            export2XML(id);
        }
        return OpResult.genSuccessOpResult("TODO");
    }

}
