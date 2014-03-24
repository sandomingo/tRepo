package com.springapp.mdl;

/**
 * 网页模板种子
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:08 PM
 */
public class SiteSeed {
    private Integer tID;
    private String url; // 网页模板种子url
    private int nutchFetchInterval; // nutch爬虫爬取数据的时间间隔
    private String type; // 种子类型，一般为seed
    private String comment; // 注释

    public Integer gettID() {
        return tID;
    }

    public void settID(Integer tID) {
        this.tID = tID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNutchFetchInterval() {
        return nutchFetchInterval;
    }

    public void setNutchFetchInterval(int nutchFetchInterval) {
        this.nutchFetchInterval = nutchFetchInterval;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
