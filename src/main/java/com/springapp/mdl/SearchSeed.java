package com.springapp.mdl;

/**
 * 搜索模板种子
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:04 PM
 */
public class SearchSeed {
    private Integer sID;
    private String url; // 搜索种子url
    private String encoding; // 搜索关键词编码
    private int retry; // 爬虫抓取失败后重试次数(default = 3)
    private String comment; // 注释

    public Integer getsID() {
        return sID;
    }

    public void setsID(Integer sID) {
        this.sID = sID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
