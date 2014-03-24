package com.springapp.dao;

import com.springapp.base.Consts;
import com.springapp.base.OpResult;
import com.springapp.dao.impl.FieldDataDaoImpl;
import com.springapp.mdl.WebsiteTemplate;
import com.springapp.serv.Xml2ObjService;
import com.springapp.utils.FileHandler;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SanDomingo
 * Date: 3/24/14
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldDataDaoImplTest {
    public static void main(String[] args) throws IOException {
        String file = "src/main/resources/template.xml";
        String xml = FileHandler.readFileToString(file);
        List<WebsiteTemplate> websiteTemplates = Xml2ObjService.genWebsiteTemplate(xml, Consts.WebsiteTemplateType.SITE);
        WebsiteTemplate websiteTemplate = websiteTemplates.get(0);
        OpResult result = FieldDataDaoImpl.getInstance().create(websiteTemplate);
        result.showResult();
    }
}
