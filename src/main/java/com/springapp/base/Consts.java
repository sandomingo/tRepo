package com.springapp.base;

/**
 * Created with IntelliJ IDEA.
 * User: SanDomingo
 * Date: 3/13/14
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Consts {
    public static final String DB_URL = "jdbc:mysql://localhost/db_crawler_template?useUnicode=true&characterEncoding=utf-8|developer|developer";
    public static final String SQL_COLUMN_URLPATTERN = "url_pattern";
    public static final String SQL_COLUMN_DOMAIN = "domain";
    public static final String SQL_COLUMN_NAME = "name";
    public static final String SQL_COLUMN_TEMPLATE = "template";
    public static final String SQL_COLUMN_STATUS = "status";
    public static final String SQL_COLUMN_UPDATETIME = "update_time";
    public static final String SQL_COLUMN_AUTHOR = "author";
    public static final String SQL_COLUMN_COMMENT = "comment";
    public static final String SQL_COLUMN_TYPE = "type";
    public static final String SQL_COLUMN_ID = "id";
    public static final String SQL_COLUMN_TSID = "ts_id";
    public static final String SQL_COLUMN_SSID = "ss_id";
    public static final String SQL_COLUMN_WEBSITEID = "website_id";
    public static final String SQL_COLUMN_URL = "url";
    public static final String SQL_COLUMN_FETCHINTERVAL = "fetch_interval";
    public static final String SQL_COLUMN_UPID = "up_id";
    public static final String SQL_COLUMN_FDID = "fd_id";
    public static final String SQL_COLUMN_TDID = "td_id";
    public static final String SQL_COLUMN_OCCUR = "occur";
    public static final String SQL_COLUMN_TRACE = "trace";
    public static final String SQL_COLUMN_MULTIVALUE = "multi_value";
    public static final String SQL_COLUMN_DESCRIPTION = "description";
    public static final String SQL_COLUMN_EXPRESSION = "expression";
    public static final String SQL_COLUMN_REGULARMATCH = "regular_match";
    public static final String SQL_COLUMN_SCRIPT = "script";
    public static final String SQL_COLUMN_RANGESTART = "range_start";
    public static final String SQL_COLUMN_RANGEEND = "range_end";
    public static final String SQL_TABLE_SITE_TEMPLATE = "t_site_template";
    public static final String SQL_TABLE_SEARCH_TEMPLATE = "t_search_template";
    public static final String SQL_DB_TEMPLATE = "db_crawler_template";
    public static final String SQL_TABLE_WEBSITE = "t_website";
    public static final String SQL_TABLE_SEARCHSEED = "t_search_seed";
    public static final String SQL_TABLE_SITESEED = "t_site_seed";
    public static final String SQL_TABLE_URLPATTERN = "t_url_pattern";
    public static final String SQL_TABLE_FIELDDATA = "t_field_data";
    public static final String SQL_TABLE_TEMPLATEDATA = "t_template_data";
    public static final String SQL_TABLE_TESTURLS = "u_test_urls";

    public static final int STATUS_ONLINE = 100;
    public static final int STATUS_ERROR = -100;
    public static final int STATUS_TESTED = 1;
    public static final int STATUS_NOTTESTED = 0;

    public static final int TEMPLATE_TYPE_SITE = 1;
    public static final int TEMPLATE_TYPE_SEARCH = 2;

    public static final String XML_TAG_WEBSITES = "websites";
    public static final String XML_TAG_WEBSITE = "website";
    public static final String XML_TAG_URLPATTERN = "url-pattern";
    public static final String XML_TAG_OUTLINK = "outlink";
    public static final String XML_TAG_DATA = "data";
    public static final String XML_TAG_ENTRY = "entry";
    public static final String XML_TAG_TEMPLATE = "template";
    public static final String XML_TAG_EXPRESSION = "expression";
    public static final String XML_TAG_REGULARMATCH = "regular-match";
    public static final String XML_TAG_RANGE = "range";
    public static final String XML_TAG_FIELD = "field";
    public static final String XML_TAG_SCRIPT = "script";

    public static final String XML_ATTR_DOMAIN = "domain";
    public static final String XML_ATTR_OCCUR = "occur";
    public static final String XML_ATTR_TRACE = "trace";
    public static final String XML_ATTR_DESCRIPTION = "description";
    public static final String XML_ATTR_REGEX = "regex";
    public static final String XML_ATTR_METHOD = "method";
    public static final String XML_ATTR_NAME = "name";
    public static final String XML_ATTR_MULTIVALUE = "multi-value";
    public static final String XML_ATTR_START = "start";
    public static final String XML_ATTR_END = "end";

    public static final String EMPTY = "";
    public static final String NEWLINE = "\n";
    /**
     * 网站模板类型
     * 1 － 搜索模板
     * 2 － 网页模板
     */
    public enum WebsiteTemplateType {
        SEARCH(1), SITE(2);
        private int code;
        private WebsiteTemplateType(int i) {
            this.code = i;
        }
        public int getCode() {
            return code;
        }

        public static WebsiteTemplateType fromCode(int code) {
            switch (code) {
                case 1:
                    return SEARCH;
                default:
                    return SITE;
            }
        }
    }

    /**
     * 模板抓取内容类型
     * 1 － 数据
     * 2 － 外链
     */
    public enum TemplateDataType {
        Data(1), Outlink(2);
        private int code;
        private TemplateDataType(int i) {
            this.code = i;
        }
        public int getCode() {
            return code;
        }

        public static TemplateDataType fromCode(int code) {
            switch (code) {
                case 1:
                    return Data;
                default:
                    return Outlink;
            }
        }
    }

    /**
     * 网站模板状态
     * 1 － 正常
     * 2 － 编辑中
     * 3 － 出错
     */
    public enum WebsiteTemplateStatus {
        OK(1), EDITING(2), ERROR(3);
        private int code;
        private WebsiteTemplateStatus(int i) {
            this.code = i;
        }
        public int getCode() {
            return code;
        }

        public static WebsiteTemplateStatus fromCode(int code) {
            switch (code) {
                case 1:
                    return OK;
                case 2:
                    return EDITING;
                default:
                    return ERROR;
            }
        }
    }
}
