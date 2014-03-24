package com.springapp.serv;

import com.springapp.base.Consts;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SanDomingo
 * Date: 3/24/14
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyApp {
    /**
     * 从目录将xml模板导入数据库
     */
    public static void ImportSiteTemplates2DB() {
        String dir = "data/template/evermotion/site";
        ImportService importService = new ImportService();
        importService.importFromDir(dir, Consts.WebsiteTemplateType.SITE);
    }

    /**
     * 从目录将xml模板导入数据库
     */
    public static void ImportSiteTemplate2DB() {
        String file = "data/template/evermotion/site/auto-163-template.xml";
        ImportService importService = new ImportService();
        importService.importFromXML(file, Consts.WebsiteTemplateType.SITE);
    }

    public static void getTemplateXMLfromDB() {
        ExportService exportService = new ExportService();
        List<Integer> ids = Arrays.asList(32,33,34,35,36,37);
        System.out.println((String) exportService.export2XMLString(ids).getOutput());
    }
    public static void main(String[] args) {
//        MyApp.ImportSiteTemplate2DB();
        MyApp.getTemplateXMLfromDB();
    }
}
