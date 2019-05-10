package com.zkch.bugly.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

//BRVAH 框架多条目使用方式
//给每一个item数据设置itemType
public class NewsBean implements Serializable , MultiItemEntity {

    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOTER = 2;

    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    /**
     * docid
     */
    private String docid;
    /**
     * 标题
     */
    private String title;
    /**
     * 小内容
     */
    private String digest;
    /**
     * 图片地址
     */
    private String imgsrc;
    /**
     * 来源
     */
    private String source;
    /**
     * 时间
     */
    private String ptime;
    /**
     * TAG
     */
    private String tag;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "docid='" + docid + '\'' +
                ", title='" + title + '\'' +
                ", digest='" + digest + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", source='" + source + '\'' +
                ", ptime='" + ptime + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
