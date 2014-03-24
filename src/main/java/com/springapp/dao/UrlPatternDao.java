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
public interface UrlPatternDao {
    /**
     * 新增一个urlpattern
     *
     * @param websiteTemplate
     * @return
     */
    public OpResult create(WebsiteTemplate websiteTemplate);

    /**
     * 检查给定url是否与已存url-pattern匹配
     * @param url
     * @return
     */
    public OpResult check(String url);


    /**
     * 读取一个websiteTemplate的url pattern
     * @param websiteTemplate
     * @return
     */
    public OpResult read(WebsiteTemplate websiteTemplate);

}
