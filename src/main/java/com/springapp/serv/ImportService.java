package com.springapp.serv;

import com.springapp.base.Consts;
import com.springapp.base.OpResult;
import com.springapp.dao.WebsiteTemplateDao;
import com.springapp.dao.impl.WebsiteTemplateDaoImpl;
import com.springapp.mdl.WebsiteTemplate;
import com.springapp.utils.FileHandler;

import java.io.File;
import java.util.List;

/**
 * 模板导出服务类
 * 目前支持从xml导入到DB
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:27 PM
 */

public class ImportService {
    private WebsiteTemplateDao dao;

    public ImportService() {
        this.dao = new WebsiteTemplateDaoImpl();
    }

    /**
     * 从单个xml文件导入到DB
     * @param xmlFilePath
     * @return
     */
    public OpResult importFromXML(String xmlFilePath, Consts.WebsiteTemplateType type) {
        OpResult result = new OpResult();
        try {
            String xml = FileHandler.readFileToString(xmlFilePath);
            importFromXMLString(xml, type);
        } catch (Exception e) {
            e.printStackTrace();
            result.setException(e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 从一个包含多个xml的目录导入DB
     * @param xmlFileDir
     * @return
     */
    public OpResult importFromDir(String xmlFileDir, Consts.WebsiteTemplateType type) {
        OpResult result = new OpResult();
        try {
            File dir = new File(xmlFileDir);
            File[] files = dir.listFiles();
            for (File file : files) {
                String xmlFile = file.getAbsolutePath();
                importFromXML(xmlFile, type);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setException(e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 用一个xml的字符串导入DB
     * @param xmlString
     * @return
     */
    public OpResult importFromXMLString(String xmlString, Consts.WebsiteTemplateType type) {
        OpResult result = new OpResult();
        try {
            List<WebsiteTemplate> websiteTemplates = Xml2ObjService.genWebsiteTemplate(xmlString, type);
            for (WebsiteTemplate websiteTemplate : websiteTemplates) {
                dao.create(websiteTemplate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setException(e.getMessage());
            return result;
        }
        return result;
    }
}
