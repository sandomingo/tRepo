package com.springapp.mdl;

import com.springapp.base.Consts;
import com.springapp.base.SimplePair;
import com.springapp.mdl.assist.FieldData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 爬虫用的网站模板类
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 1:49 PM
 */
public class WebsiteTemplate {
    private Integer websiteID;
    private String name;
    private String domain;
    private Consts.WebsiteTemplateType type;
    private Consts.WebsiteTemplateStatus status;
    private String author;
    private Date updateTime;
    private String comment;
    private List<UrlPattern> urlPatterns; // List of url - comment
    private List<FieldData> data;

    private WebsiteTemplate() {
        this.websiteID = -1;
        this.type = Consts.WebsiteTemplateType.SITE;
        this.name = Consts.EMPTY;
        this.domain = Consts.EMPTY;
        this.status = Consts.WebsiteTemplateStatus.EDITING;
        this.author = Consts.EMPTY;
        this.updateTime = new Date(System.currentTimeMillis());
        this.comment = Consts.EMPTY;
        this.urlPatterns = new ArrayList<UrlPattern>();
        this.data = new ArrayList<FieldData>();
    }


    public WebsiteTemplate(Integer websiteID) {
        this();
        this.websiteID = websiteID;
    }

    public WebsiteTemplate(Consts.WebsiteTemplateType type) {
        this();
        this.type = type;
    }

    public WebsiteTemplate(Integer websiteID, String name, Integer type, Integer status, String author, Date updateTime, String comment) {
        this();
        if (websiteID != null) {
            this.websiteID = websiteID;
        }
        if (name != null) {
            this.name = name;
        }
        if (type != null) {
            this.type = Consts.WebsiteTemplateType.fromCode(type);
        }
        if (status != null) {
            this.status = Consts.WebsiteTemplateStatus.fromCode(status);
        }
        if (author != null) {
            this.author = author;
        }
        if (updateTime != null) {
            this.updateTime = updateTime;
        }
        if (comment != null) {
            this.comment = comment;
        }
    }

    public Integer getWebsiteID() {
        return websiteID;
    }

    public void setWebsiteID(Integer websiteID) {
        this.websiteID = websiteID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Consts.WebsiteTemplateType getType() {
        return type;
    }

    public void setType(Consts.WebsiteTemplateType type) {
        this.type = type;
    }

    public Consts.WebsiteTemplateStatus getStatus() {
        return status;
    }

    public void setStatus(Consts.WebsiteTemplateStatus status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<UrlPattern> getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(List<UrlPattern> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    public void addUrlPattern(String urlpattern, String comment) {
        this.urlPatterns.add(new UrlPattern(urlpattern, comment));
    }

    public void addUrlPattern(String urlpattern) {
        this.urlPatterns.add(new UrlPattern(urlpattern));
    }

    public List<FieldData> getData() {
        return data;
    }

    public void setData(List<FieldData> data) {
        this.data = data;
    }

    public void addData(FieldData fieldData) {
        this.data.add(fieldData);
    }

    public void addData(List<FieldData> fieldData) {
        this.data.addAll(fieldData);
    }
}
