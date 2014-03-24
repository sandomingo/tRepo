package com.springapp.dao;

import com.springapp.base.OpResult;
import com.springapp.mdl.SearchSeed;

import java.util.List;

/**
 * 对应搜索模板的初始种子DAO
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 3:19 PM
 */
public interface SearchSeedDao {
    /**
     * 新增一个searchSeed
     * @param searchSeed
     * @return
     */
    public OpResult create(SearchSeed searchSeed);

/**
     * 读取一个searchSeed
     * @param searchSeed
     * @return
     */
    public OpResult read(SearchSeed searchSeed);

    /**
     * 读取多个searchSeed
     * @param searchSeed
     * @return
     */
    public OpResult readSeveral(List<SearchSeed> searchSeed);

    /**
     * 读取全部searchSeed
     * @return
     */
    public OpResult readAll();

    /**
     * 更新一个searchSeed
     * @param searchSeed
     * @return
     */
    public OpResult update(SearchSeed searchSeed);

    /**
     * 删除一个searchSeed
     * @param searchSeed
     * @return
     */
    public OpResult delete(SearchSeed searchSeed);
}
