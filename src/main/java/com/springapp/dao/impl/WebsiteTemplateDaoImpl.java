package com.springapp.dao.impl;

import com.springapp.base.Consts;
import com.springapp.base.OpResult;
import com.springapp.dao.WebsiteTemplateDao;
import com.springapp.mdl.WebsiteTemplate;
import com.springapp.mdl.assist.FieldData;
import com.yeezhao.commons.util.sql.BaseDao;
import com.yeezhao.commons.util.sql.BaseDaoFactory;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 爬虫模板服务DAO
 * 提供模板的CRUD操作
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:27 PM
 */
public class WebsiteTemplateDaoImpl implements WebsiteTemplateDao {
    private static WebsiteTemplateDao dao;
    private BaseDao baseDao = BaseDaoFactory.getDaoBaseInstance(Consts.DB_URL);
    public static WebsiteTemplateDao getInstance() {
        if (dao == null) {
            dao = new WebsiteTemplateDaoImpl();
        }
        return dao;
    }

    @Override
    public OpResult create(WebsiteTemplate websiteTemplate) {
        String sql = "INSERT INTO " + Consts.SQL_TABLE_WEBSITE + " (domain, name, type, status, author, update_time, comment) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) ";
        StringBuilder errMsgSB = new StringBuilder();
        try {
            Integer genID = baseDao.insertWithKey(sql, websiteTemplate.getDomain(), websiteTemplate.getName(), websiteTemplate.getType().getCode(),
                    websiteTemplate.getStatus().getCode(), websiteTemplate.getAuthor(),
                    new Date(System.currentTimeMillis()), websiteTemplate.getComment());
            // insert to field table
            websiteTemplate.setWebsiteID(genID);
            OpResult result1 = FieldDataDaoImpl.getInstance().create(websiteTemplate);
            if (!result1.getStatus()) errMsgSB.append(result1.getException()).append("\n");
            // insert to template table
            OpResult result2 = UrlPatternDaoImpl.getInstance().create(websiteTemplate);
            if (!result2.getStatus()) errMsgSB.append(result2.getException()).append("\n");
        } catch (SQLException e) {
            e.printStackTrace();
            return OpResult.genFailedOpResult(e.getMessage());
        }
        String errMsg = errMsgSB.toString();
        if (errMsg.isEmpty())
            return OpResult.genSuccessOpResult("TODO");
        else
            return OpResult.genFailedOpResult(errMsg);
    }

    @Override
    public OpResult read(final WebsiteTemplate websiteTemplate) {
        final Integer websiteID = websiteTemplate.getWebsiteID();
        String sql = "select domain, name, type, status, author, update_time, comment from " +
                Consts.SQL_TABLE_WEBSITE + " where ws_id = ?";
        try {
            baseDao.queryObjects(sql, new ResultSetHandler<List<WebsiteTemplate>>() {
                @Override
                public List<WebsiteTemplate> handle(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        String domain = rs.getString(Consts.SQL_COLUMN_DOMAIN);
                        String name = rs.getString(Consts.SQL_COLUMN_NAME);
                        Integer type = rs.getInt(Consts.SQL_COLUMN_TYPE);
                        Integer status = rs.getInt(Consts.SQL_COLUMN_STATUS);
                        String author = rs.getString(Consts.SQL_COLUMN_AUTHOR);
                        Date updateTime = rs.getTimestamp(Consts.SQL_COLUMN_UPDATETIME);
                        String comment = rs.getString(Consts.SQL_COLUMN_COMMENT);

                        websiteTemplate.setDomain(domain);
                        websiteTemplate.setName(name);
                        websiteTemplate.setType(Consts.WebsiteTemplateType.fromCode(type));
                        websiteTemplate.setStatus(Consts.WebsiteTemplateStatus.fromCode(status));
                        websiteTemplate.setAuthor(author);
                        websiteTemplate.setUpdateTime(updateTime);
                        websiteTemplate.setComment(comment);
                        break;
                    }
                    return null;
                }
            }, websiteID);
            // read urlPattern
            UrlPatternDaoImpl.getInstance().read(websiteTemplate);
            // read Field data
            FieldDataDaoImpl.getInstance().read(websiteTemplate);
        } catch (SQLException e) {
            e.printStackTrace();
            return OpResult.genFailedOpResult(e.getMessage());
        }
        return OpResult.genSuccessOpResult("TODO");
    }

    @Override
    public OpResult readSeveral(List<WebsiteTemplate> websiteTemplates) {
        OpResult result = OpResult.genSuccessOpResult("TODO");
        for (WebsiteTemplate websiteTemplate : websiteTemplates) {
            result = read(websiteTemplate);
        }
        return result;
    }

    @Override
    public OpResult readAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult update(WebsiteTemplate websiteTemplate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult delete(WebsiteTemplate websiteTemplate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
