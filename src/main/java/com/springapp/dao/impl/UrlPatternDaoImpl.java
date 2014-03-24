package com.springapp.dao.impl;

import com.springapp.base.Consts;
import com.springapp.base.OpResult;
import com.springapp.dao.UrlPatternDao;
import com.springapp.mdl.UrlPattern;
import com.springapp.mdl.WebsiteTemplate;
import com.yeezhao.commons.util.sql.BaseDao;
import com.yeezhao.commons.util.sql.BaseDaoFactory;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫模板服务DAO
 * 提供模板的CRUD操作
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:27 PM
 */
public class UrlPatternDaoImpl implements UrlPatternDao {
    private static UrlPatternDao dao;
    private BaseDao baseDao = BaseDaoFactory.getDaoBaseInstance(Consts.DB_URL);
    public static UrlPatternDao getInstance() {
        if (dao == null) {
            dao = new UrlPatternDaoImpl();
        }
        return dao;
    }

    @Override
    public OpResult create(WebsiteTemplate websiteTemplate) {
        String sql = "INSERT INTO " + Consts.SQL_TABLE_URLPATTERN + " (website_id, url_pattern, comment) " +
                "VALUES (?, ?, ?)";
        List<UrlPattern> urlPatterns = websiteTemplate.getUrlPatterns();
        try {
            for (UrlPattern urlPattern : urlPatterns) {
                Integer genID = baseDao.insertWithKey(sql, websiteTemplate.getWebsiteID(), urlPattern.getUrlPattern(), urlPattern.getComment());
                urlPattern.setUpID(genID);
            }
        } catch (SQLException e) {
            return OpResult.genFailedOpResult(e.getMessage());
        }
        return OpResult.genSuccessOpResult("TODO");
    }

    @Override
    public OpResult check(String url) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult read(WebsiteTemplate websiteTemplate) {
        final Integer websiteID = websiteTemplate.getWebsiteID();
        String sql = "select up_id, url_pattern, comment from " + Consts.SQL_TABLE_URLPATTERN + " where website_id = ?";
        try {
            List<UrlPattern> urlPatterns = baseDao.queryObjects(sql, new ResultSetHandler<List<UrlPattern>>() {
                @Override
                public List<UrlPattern> handle(ResultSet rs) throws SQLException {
                    List<UrlPattern> result = new ArrayList<UrlPattern>();
                    while (rs.next()) {
                        Integer upId = rs.getInt(Consts.SQL_COLUMN_UPID);
                        String urlPattern = rs.getString(Consts.SQL_COLUMN_URLPATTERN);
                        String comment = rs.getString(Consts.SQL_COLUMN_COMMENT);
                        UrlPattern anUrlPattern = new UrlPattern(upId, websiteID, urlPattern, comment);
                        result.add(anUrlPattern);
                    }
                    return result;
                }
            }, websiteID);

            websiteTemplate.setUrlPatterns(urlPatterns);
        } catch (SQLException e) {
            return OpResult.genFailedOpResult(e.getMessage());
        }
        return OpResult.genSuccessOpResult("TODO");
    }
}
