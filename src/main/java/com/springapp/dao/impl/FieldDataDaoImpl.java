package com.springapp.dao.impl;

import com.springapp.base.Consts;
import com.springapp.base.OpResult;
import com.springapp.dao.FieldDataDao;
import com.springapp.mdl.WebsiteTemplate;
import com.springapp.mdl.assist.FieldData;
import com.springapp.mdl.assist.TemplateData;
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
public class FieldDataDaoImpl implements FieldDataDao {
    private static FieldDataDao dao;
    private BaseDao baseDao = BaseDaoFactory.getDaoBaseInstance(Consts.DB_URL);
    public static FieldDataDao getInstance() {
        if (dao == null) {
            dao = new FieldDataDaoImpl();
        }
        return dao;
    }

    @Override
    public OpResult create(WebsiteTemplate websiteTemplate) {
        String sql = "INSERT INTO " + Consts.SQL_TABLE_FIELDDATA + " (website_id, type, name, occur, trace, " +
                "multi_value, description, comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
        List<FieldData> fieldDatas = websiteTemplate.getData();
        try {
            for (FieldData fieldData : fieldDatas) {
                Integer genID = baseDao.insertWithKey(sql, websiteTemplate.getWebsiteID(), fieldData.getType().getCode(),
                        fieldData.getName(), fieldData.getOccur(), fieldData.getTrace(), fieldData.getMultiValue(),
                        fieldData.getDescription(), fieldData.getComment());
                fieldData.setFdID(genID);
                // insert to template table
                TemplateDataDaoImpl.getInstance().create(fieldData);
            }
        } catch (SQLException e) {
            return OpResult.genFailedOpResult(e.getMessage());
        }
        return OpResult.genSuccessOpResult("TODO");
    }

    @Override
    public OpResult create(List<FieldData> fieldData) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult read(WebsiteTemplate websiteTemplate) {
        final Integer websiteID = websiteTemplate.getWebsiteID();
        String sql = "select fd_id, type, name, occur, trace, multi_value, description, comment from " +
                Consts.SQL_TABLE_FIELDDATA + " where website_id = ?";
        try {
            List<FieldData> fieldDataList = baseDao.queryObjects(sql, new ResultSetHandler<List<FieldData>>() {
                @Override
                public List<FieldData> handle(ResultSet rs) throws SQLException {
                    List<FieldData> result = new ArrayList<FieldData>();
                    while (rs.next()) {
                        Integer fdID = rs.getInt(Consts.SQL_COLUMN_FDID);
                        Integer type = rs.getInt(Consts.SQL_COLUMN_TYPE);
                        String name = rs.getString(Consts.SQL_COLUMN_NAME);
                        String occur = rs.getString(Consts.SQL_COLUMN_OCCUR);
                        String trace = rs.getString(Consts.SQL_COLUMN_TRACE);
                        String multiValue = rs.getString(Consts.SQL_COLUMN_MULTIVALUE);
                        String description = rs.getString(Consts.SQL_COLUMN_DESCRIPTION);
                        String comment = rs.getString(Consts.SQL_COLUMN_COMMENT);
                        FieldData aFieldData = new FieldData(fdID, websiteID, type, name, occur, trace, multiValue,
                                description, comment);
                        result.add(aFieldData);
                    }
                    return result;
                }
            }, websiteID);

            for (FieldData fieldData : fieldDataList) {
                // read template data
                TemplateDataDaoImpl.getInstance().read(fieldData);
            }
            websiteTemplate.setData(fieldDataList);
        } catch (SQLException e) {
            e.printStackTrace();
            return OpResult.genFailedOpResult(e.getMessage());
        }
        return OpResult.genSuccessOpResult("TODO");
    }

    @Override
    public OpResult readSeveral(List<FieldData> fieldDatas) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult readAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult update(FieldData fieldData) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult delete(FieldData fieldData) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
