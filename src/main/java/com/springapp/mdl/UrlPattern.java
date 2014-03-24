package com.springapp.mdl;

/**
 * Created with IntelliJ IDEA.
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class UrlPattern {
    private Integer upID;
    private Integer websiteID;
    private String urlPattern;
    private String comment;

    public UrlPattern(String urlpattern, String comment) {
        this.urlPattern = urlpattern;
        this.comment = comment;
    }

    public UrlPattern(String urlpattern) {
        this.urlPattern = urlpattern;
    }

    public UrlPattern(Integer upId, Integer websiteID, String urlPattern, String comment) {
        if (upId != null) {
            this.upID = websiteID;
        }
        if (websiteID != null) {
            this.websiteID = websiteID;
        }
        if (urlPattern != null) {
            this.urlPattern = urlPattern;
        }
        if (comment != null) {
            this.comment = comment;
        }
    }

    public Integer getUpID() {
        return upID;
    }

    public void setUpID(Integer upID) {
        this.upID = upID;
    }

    public Integer getWebsiteID() {
        return websiteID;
    }

    public void setWebsiteID(Integer websiteID) {
        this.websiteID = websiteID;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
