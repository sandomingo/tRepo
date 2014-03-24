package com.springapp.mdl.assist;

import com.springapp.base.Consts;

/**
 * 模板的主要数据类，对应每个要抓取字段的xpath和简单的处理脚本
 */
public class TemplateData {
    private Integer tdID;
    private Integer fieldID;
    private String expression; // xpath表达式
    private String script; // javascript表达式
    private String regularMatch; // regex
    private String rangeStart; // 截取字符开始部分
    private String rangeEnd; // 截取字符截止部分
    private String comment; // 注释

    public TemplateData() {
        this.tdID = -1;
        this.fieldID = -1;
        this.expression = Consts.EMPTY;
        this.script = Consts.EMPTY;
        this.regularMatch = Consts.EMPTY;
        this.rangeStart = Consts.EMPTY;
        this.rangeEnd = Consts.EMPTY;
        this.comment = Consts.EMPTY;
    }

    public TemplateData(Integer tdID, Integer fieldID, String expression, String regularMatch, String script, String rangeStart, String rangeEnd) {
        this();
        if (tdID != null) {
            this.tdID = tdID;
        }
        if (fieldID != null) {
            this.fieldID = fieldID;
        }
        if (expression != null) {
            this.expression = expression;
        }
        if (regularMatch != null) {
            this.regularMatch = regularMatch;
        }
        if (script != null) {
            this.script = script;
        }
        if (rangeStart != null) {
            this.rangeStart = rangeStart;
        }
        if (rangeEnd != null) {
            this.rangeEnd = rangeEnd;
        }
    }

    public Integer getTdID() {
        return tdID;
    }

    public void setTdID(Integer tdID) {
        this.tdID = tdID;
    }

    public Integer getFieldID() {
        return fieldID;
    }

    public void setFieldID(Integer fieldID) {
        this.fieldID = fieldID;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getRegularMatch() {
        return regularMatch;
    }

    public void setRegularMatch(String regularMatch) {
        this.regularMatch = regularMatch;
    }

    public String getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(String rangeStart) {
        this.rangeStart = rangeStart;
    }

    public String getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(String rangeEnd) {
        this.rangeEnd = rangeEnd;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}