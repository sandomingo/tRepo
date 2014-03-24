package com.springapp.dao;

import com.springapp.base.Consts;
import com.springapp.base.OpResult;
import com.springapp.dao.impl.WebsiteTemplateDaoImpl;
import com.springapp.mdl.WebsiteTemplate;
import com.springapp.serv.Obj2XmlService;
import com.springapp.serv.Xml2ObjService;
import com.springapp.utils.FileHandler;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SanDomingo
 * Date: 3/24/14
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebsiteTemplateDaoImplTest {
    public static void main(String[] args) throws IOException {
        String file = "src/main/resources/template.xml";
        String xml = FileHandler.readFileToString(file);
        List<WebsiteTemplate> websiteTemplates = Xml2ObjService.genWebsiteTemplate(xml, Consts.WebsiteTemplateType.SITE);

        WebsiteTemplate websiteTemplate = websiteTemplates.get(0);
        OpResult result = WebsiteTemplateDaoImpl.getInstance().create(websiteTemplate);
        result.showResult();
    }

    @Test
    public void testRead() {
        WebsiteTemplate websiteTemplate = new WebsiteTemplate(7);
        OpResult result = WebsiteTemplateDaoImpl.getInstance().read(websiteTemplate);
        result.showResult();
        try {
        String xml = Obj2XmlService.getWebsiteTemplateXMLString(websiteTemplate);
        System.out.println(xml);
    } catch (TransformerException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } catch (ParserConfigurationException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
}

}
