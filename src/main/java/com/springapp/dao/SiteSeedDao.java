package com.springapp.dao;

import com.springapp.base.OpResult;
import com.springapp.mdl.SiteSeed;

import java.util.List;

/**
 * 对应网页模板的初始种子DAO
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 3:19 PM
 */
public interface SiteSeedDao {
    /**
     * 新增一个siteSeed
     * @param siteSeed
     * @return
     */
    public OpResult create(SiteSeed siteSeed);

    /**
     * 读取一个siteSeed
     * @param siteSeed
     * @return
     */
    public OpResult read(SiteSeed siteSeed);

    /**
     * 读取多个siteSeed
     * @param siteSeed
     * @return
     */
    public OpResult readSeveral(List<SiteSeed> siteSeed);

    /**
     * 读取全部siteSeed
     * @return
     */
    public OpResult readAll();

    /**
     * 更新一个siteSeed
     * @param siteSeed
     * @return
     */
    public OpResult update(SiteSeed siteSeed);

    /**
     * 删除一个siteSeed
     * @param siteSeed
     * @return
     */
    public OpResult delete(SiteSeed siteSeed);
}
