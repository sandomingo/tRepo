package com.springapp.dao;

import com.springapp.base.Consts;
import com.springapp.base.OpResult;
import com.springapp.dao.impl.TemplateDataDaoImpl;
import com.springapp.mdl.WebsiteTemplate;
import com.springapp.mdl.assist.FieldData;
import com.springapp.serv.Xml2ObjService;
import com.springapp.utils.FileHandler;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SanDomingo
 * Date: 3/24/14
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TemplateDataDaoImplTest {
    @Test
    public void testCreate() throws Exception {
        String file = "src/main/resources/template.xml";
        String xml = FileHandler.readFileToString(file);
        List<WebsiteTemplate> websiteTemplates = Xml2ObjService.genWebsiteTemplate(xml, Consts.WebsiteTemplateType.SITE);
        WebsiteTemplate websiteTemplate = websiteTemplates.get(0);
        FieldData fieldData = websiteTemplate.getData().get(0);
        OpResult result = TemplateDataDaoImpl.getInstance().create(fieldData);
        result.showResult();
    }

    @Test
    public void testRead() {
        FieldData fieldData = new FieldData(Consts.TemplateDataType.Data);
        OpResult result = TemplateDataDaoImpl.getInstance().read(fieldData);
        result.showResult();
    }
}
