package com.springapp.mdl.assist;

import com.springapp.base.Consts;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板的主要数据类，对应每个要抓取字段的信息
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:02 PM
 */
public class FieldData {
    private Integer fdID;
    private Integer websiteID;
    private Integer fieldID;
    private Consts.TemplateDataType type; // 模板数据类型：data或outlink
    private String name; // 字段名称，data类型独有
    private String occur; // 字段出现需求，mandatory, should, optional, 默认为optional
    private String trace; // 是否追踪，outlink独有字段
    private String multiValue; // 是否有多个值，data类型独有
    private String description; // 描述
    private List<TemplateData> templateDataList; // template对象
    private String comment; // 注释

    private FieldData() {
        this.fdID = -1;
        this.websiteID = -1;
        this.type = Consts.TemplateDataType.Data;
        this.name = Consts.EMPTY;
        this.occur = Consts.EMPTY;
        this.trace = Consts.EMPTY;
        this.multiValue = Consts.EMPTY;
        this.description = Consts.EMPTY;
        this.templateDataList = new ArrayList<TemplateData>();
        this.comment = Consts.EMPTY;
    }
    public FieldData(Consts.TemplateDataType type) {
        this();
        this.type = type;
    }

    public FieldData(Integer fdID, Integer websiteID, Integer type, String name, String occur, String trace, String multiValue, String description, String comment) {
        this();
        if (fdID != null) {
            this.fdID = fdID;
        }
        if (websiteID != null) {
            this.websiteID = websiteID;
        }
        if (type != null) {
            this.type = Consts.TemplateDataType.fromCode(type);
        }
        if (name != null) {
            this.name = name;
        }
        if (occur != null) {
            this.occur = occur;
        }
        if (trace != null) {
            this.trace = trace;
        }
        if (multiValue != null) {
            this.multiValue = multiValue;
        }
        if (description != null) {
            this.description = description;
        }
        if (comment != null) {
            this.comment = comment;
        }
    }

    public Integer getFdID() {
        return fdID;
    }

    public void setFdID(Integer fdID) {
        this.fdID = fdID;
    }

    public Integer getWebsiteID() {
        return websiteID;
    }

    public void setWebsiteID(Integer websiteID) {
        this.websiteID = websiteID;
    }

    public Integer getFieldID() {
        return fieldID;
    }

    public void setFieldID(Integer fieldID) {
        this.fieldID = fieldID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Consts.TemplateDataType getType() {
        return type;
    }

    public void setType(Consts.TemplateDataType type) {
        this.type = type;
    }

    public String getOccur() {
        return occur;
    }

    public void setOccur(String occur) {
        this.occur = occur;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getMultiValue() {
        return multiValue;
    }

    public void setMultiValue(String multiValue) {
        this.multiValue = multiValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TemplateData> getTemplateDataList() {
        return templateDataList;
    }

    public void setTemplateDataList(List<TemplateData> templateDataList) {
        this.templateDataList = templateDataList;
    }

    public void addTemplateData(TemplateData templateData) {
        this.templateDataList.add(templateData);
    }

    public void addTemplateData(List<TemplateData> templateDataList) {
        this.templateDataList.addAll(templateDataList);
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
