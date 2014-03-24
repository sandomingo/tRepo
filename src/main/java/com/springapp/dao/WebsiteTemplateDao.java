package com.springapp.dao;

import com.springapp.base.OpResult;
import com.springapp.mdl.WebsiteTemplate;

import java.util.List;

/**
 * 爬虫模板服务DAO
 * 提供模板的CRUD操作
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:27 PM
 */
public interface WebsiteTemplateDao {
    /**
     * 新增一个websiteTemplate
     * @param websiteTemplate
     * @return
     */
    public OpResult create(WebsiteTemplate websiteTemplate);

    /**
     * 读取一个websiteTemplate
     * @param websiteTemplate
     * @return
     */
    public OpResult read(WebsiteTemplate websiteTemplate);

    /**
     * 读取多个websiteTemplate
     * @param websiteTemplates
     * @return
     */
    public OpResult readSeveral(List<WebsiteTemplate> websiteTemplates);

    /**
     * 读取全部websiteTemplate
     * @return
     */
    public OpResult readAll();

    /**
     * 更新一个websiteTemplate
     * @param websiteTemplate
     * @return
     */
    public OpResult update(WebsiteTemplate websiteTemplate);

    /**
     * 删除一个websiteTemplate
     * @param websiteTemplate
     * @return
     */
    public OpResult delete(WebsiteTemplate websiteTemplate);
}
