package com.springapp.dao;

import com.springapp.base.OpResult;
import com.springapp.mdl.WebsiteTemplate;
import com.springapp.mdl.assist.FieldData;

import java.util.List;

/**
 * 爬虫模板服务DAO
 * 提供模板的CRUD操作
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:27 PM
 */
public interface FieldDataDao {
    /**
     * 新增一个fieldData
     *
     * @param fieldData
     * @return
     */
    public OpResult create(WebsiteTemplate fieldData);

    /**
     * 新增多个fieldData
     * @param fieldData
     * @return
     */
    public OpResult create(List<FieldData> fieldData);

    /**
     * 读取一个fieldData
     * @param websiteTemplate
     * @return
     */
    public OpResult read(WebsiteTemplate websiteTemplate);

    /**
     * 读取多个fieldData
     * @param fieldDatas
     * @return
     */
    public OpResult readSeveral(List<FieldData> fieldDatas);

    /**
     * 读取全部fieldData
     * @return
     */
    public OpResult readAll();

    /**
     * 更新一个fieldData
     * @param fieldData
     * @return
     */
    public OpResult update(FieldData fieldData);

    /**
     * 删除一个fieldData
     * @param fieldData
     * @return
     */
    public OpResult delete(FieldData fieldData);
}
