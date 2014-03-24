package com.springapp.dao;

import com.springapp.base.OpResult;
import com.springapp.mdl.TestUrl;

/**
 * 测试模板的服务DAO
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:27 PM
 */
public interface TestUrlDao {
    /**
     * 更新测试用的urls
     * @param url
     * @return
     */
    public OpResult updateTestUrl(TestUrl url);

    /**
     * 查看测试用的urls
     * @param url
     * @return
     */
    public OpResult getTestUrl(TestUrl url);
}
