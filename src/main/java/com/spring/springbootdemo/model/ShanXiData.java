package com.spring.springbootdemo.model;

public class ShanXiData {
    private Integer urlId;

    private String categoryFirst;

    private String categorySecond;

    private String hashcode;

    private String tablename;

    private String regioncode;

    private String url;

    private String httpurl;

    private String projectcode;

    private String id;

    private String projectname;

    private String receivetime;

    private String fabupxTime;

    private String artcleUrl;

    private String content;

    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public String getCategoryFirst() {
        return categoryFirst;
    }

    public void setCategoryFirst(String categoryFirst) {
        this.categoryFirst = categoryFirst == null ? null : categoryFirst.trim();
    }

    public String getCategorySecond() {
        return categorySecond;
    }

    public void setCategorySecond(String categorySecond) {
        this.categorySecond = categorySecond == null ? null : categorySecond.trim();
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode == null ? null : hashcode.trim();
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public String getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode == null ? null : regioncode.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getHttpurl() {
        return httpurl;
    }

    public void setHttpurl(String httpurl) {
        this.httpurl = httpurl == null ? null : httpurl.trim();
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode == null ? null : projectcode.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(String receivetime) {
        this.receivetime = receivetime == null ? null : receivetime.trim();
    }

    public String getFabupxTime() {
        return fabupxTime;
    }

    public void setFabupxTime(String fabupxTime) {
        this.fabupxTime = fabupxTime == null ? null : fabupxTime.trim();
    }

    public String getArtcleUrl() {
        return artcleUrl;
    }

    public void setArtcleUrl(String artcleUrl) {
        this.artcleUrl = artcleUrl == null ? null : artcleUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}