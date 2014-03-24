package com.springapp.dao;

import com.springapp.base.OpResult;
import com.springapp.mdl.assist.FieldData;
import com.springapp.mdl.assist.TemplateData;

import java.util.List;

/**
 * 爬虫模板服务DAO
 * 提供模板的CRUD操作
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:27 PM
 */
public interface TemplateDataDao {
    /**
     * 新增一个templateData
     *
     * @param templateData
     * @return
     */
    public OpResult create(FieldData templateData);

    /**
     * 读取一个templateData
     * @param fieldData
     * @return
     */
    public OpResult read(FieldData fieldData);

    /**
     * 读取多个templateData
     * @param templateDatas
     * @return
     */
    public OpResult readSeveral(List <TemplateData> templateDatas);

    /**
     * 读取全部templateData
     * @return
     */
    public OpResult readAll();

    /**
     * 更新一个templateData
     * @param templateData
     * @return
     */
    public OpResult update(TemplateData templateData);

    /**
     * 删除一个templateData
     * @param templateData
     * @return
     */
    public OpResult delete(TemplateData templateData);
}
