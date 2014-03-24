package com.springapp.dao.impl;

import com.springapp.base.Consts;
import com.springapp.base.OpResult;
import com.springapp.dao.TemplateDataDao;
import com.springapp.mdl.UrlPattern;
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
public class TemplateDataDaoImpl implements TemplateDataDao {
    private static TemplateDataDao dao;

    private BaseDao baseDao = BaseDaoFactory.getDaoBaseInstance(Consts.DB_URL);
    public static TemplateDataDao getInstance() {
        if (dao == null) {
            dao = new TemplateDataDaoImpl();
        }
        return dao;
    }

    @Override
    public OpResult create(FieldData fieldData) {
        String sql = "INSERT INTO " + Consts.SQL_TABLE_TEMPLATEDATA + " (field_id, expression, regular_match, script, " +
                "range_start, range_end) VALUES (?, ?, ?, ?, ?, ?)";
        List<TemplateData> templateDataList = fieldData.getTemplateDataList();
        try {
            for (TemplateData templateData : templateDataList) {
                Integer genID = baseDao.insertWithKey(sql, fieldData.getFdID(), templateData.getExpression(),
                        templateData.getRegularMatch(), templateData.getScript(), templateData.getRangeStart(),
                        templateData.getRangeEnd());
                templateData.setTdID(genID);
            }
        } catch (SQLException e) {
            return OpResult.genFailedOpResult(e.getMessage());
        }
        return OpResult.genSuccessOpResult("TODO");
    }

    @Override
    public OpResult read(FieldData fieldData) {
        final Integer fieldID = fieldData.getFdID();
        String sql = "select td_id, expression, regular_match, script, range_start, range_end from " +
                Consts.SQL_TABLE_TEMPLATEDATA + " where field_id = ?";
        try {
            List<TemplateData> templateDataList = baseDao.queryObjects(sql, new ResultSetHandler<List<TemplateData>>() {
                @Override
                public List<TemplateData> handle(ResultSet rs) throws SQLException {
                    List<TemplateData> result = new ArrayList<TemplateData>();
                    while (rs.next()) {
                        Integer tdID = rs.getInt(Consts.SQL_COLUMN_TDID);
                        String expression = rs.getString(Consts.SQL_COLUMN_EXPRESSION);
                        String regularMatch = rs.getString(Consts.SQL_COLUMN_REGULARMATCH);
                        String script = rs.getString(Consts.SQL_COLUMN_SCRIPT);
                        String rangeStart = rs.getString(Consts.SQL_COLUMN_RANGESTART);
                        String rangeEnd = rs.getString(Consts.SQL_COLUMN_RANGEEND);
                        TemplateData aTemplateData = new TemplateData(tdID, fieldID, expression, regularMatch,
                                script, rangeStart, rangeEnd);
                        result.add(aTemplateData);
                    }
                    return result;
                }
            }, fieldID);

            fieldData.setTemplateDataList(templateDataList);
        } catch (SQLException e) {
            e.printStackTrace();
            return OpResult.genFailedOpResult(e.getMessage());
        }
        return OpResult.genSuccessOpResult("TODO");
    }

    @Override
    public OpResult readSeveral(List<TemplateData> templateDatas) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult readAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult update(TemplateData templateData) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OpResult delete(TemplateData templateData) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
