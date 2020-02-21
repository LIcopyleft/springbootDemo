package com.spring.springbootdemo.model;

import java.util.ArrayList;
import java.util.List;

public class DataContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataContentExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUrlIdIsNull() {
            addCriterion("url_id is null");
            return (Criteria) this;
        }

        public Criteria andUrlIdIsNotNull() {
            addCriterion("url_id is not null");
            return (Criteria) this;
        }

        public Criteria andUrlIdEqualTo(Integer value) {
            addCriterion("url_id =", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdNotEqualTo(Integer value) {
            addCriterion("url_id <>", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdGreaterThan(Integer value) {
            addCriterion("url_id >", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("url_id >=", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdLessThan(Integer value) {
            addCriterion("url_id <", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdLessThanOrEqualTo(Integer value) {
            addCriterion("url_id <=", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdIn(List<Integer> values) {
            addCriterion("url_id in", values, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdNotIn(List<Integer> values) {
            addCriterion("url_id not in", values, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdBetween(Integer value1, Integer value2) {
            addCriterion("url_id between", value1, value2, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdNotBetween(Integer value1, Integer value2) {
            addCriterion("url_id not between", value1, value2, "urlId");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNull() {
            addCriterion("classify is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNotNull() {
            addCriterion("classify is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyEqualTo(String value) {
            addCriterion("classify =", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotEqualTo(String value) {
            addCriterion("classify <>", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThan(String value) {
            addCriterion("classify >", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThanOrEqualTo(String value) {
            addCriterion("classify >=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThan(String value) {
            addCriterion("classify <", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThanOrEqualTo(String value) {
            addCriterion("classify <=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLike(String value) {
            addCriterion("classify like", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotLike(String value) {
            addCriterion("classify not like", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyIn(List<String> values) {
            addCriterion("classify in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotIn(List<String> values) {
            addCriterion("classify not in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyBetween(String value1, String value2) {
            addCriterion("classify between", value1, value2, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotBetween(String value1, String value2) {
            addCriterion("classify not between", value1, value2, "classify");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andStagenameIsNull() {
            addCriterion("stageName is null");
            return (Criteria) this;
        }

        public Criteria andStagenameIsNotNull() {
            addCriterion("stageName is not null");
            return (Criteria) this;
        }

        public Criteria andStagenameEqualTo(String value) {
            addCriterion("stageName =", value, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameNotEqualTo(String value) {
            addCriterion("stageName <>", value, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameGreaterThan(String value) {
            addCriterion("stageName >", value, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameGreaterThanOrEqualTo(String value) {
            addCriterion("stageName >=", value, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameLessThan(String value) {
            addCriterion("stageName <", value, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameLessThanOrEqualTo(String value) {
            addCriterion("stageName <=", value, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameLike(String value) {
            addCriterion("stageName like", value, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameNotLike(String value) {
            addCriterion("stageName not like", value, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameIn(List<String> values) {
            addCriterion("stageName in", values, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameNotIn(List<String> values) {
            addCriterion("stageName not in", values, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameBetween(String value1, String value2) {
            addCriterion("stageName between", value1, value2, "stagename");
            return (Criteria) this;
        }

        public Criteria andStagenameNotBetween(String value1, String value2) {
            addCriterion("stageName not between", value1, value2, "stagename");
            return (Criteria) this;
        }

        public Criteria andPlatformnameIsNull() {
            addCriterion("platformName is null");
            return (Criteria) this;
        }

        public Criteria andPlatformnameIsNotNull() {
            addCriterion("platformName is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformnameEqualTo(String value) {
            addCriterion("platformName =", value, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameNotEqualTo(String value) {
            addCriterion("platformName <>", value, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameGreaterThan(String value) {
            addCriterion("platformName >", value, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameGreaterThanOrEqualTo(String value) {
            addCriterion("platformName >=", value, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameLessThan(String value) {
            addCriterion("platformName <", value, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameLessThanOrEqualTo(String value) {
            addCriterion("platformName <=", value, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameLike(String value) {
            addCriterion("platformName like", value, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameNotLike(String value) {
            addCriterion("platformName not like", value, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameIn(List<String> values) {
            addCriterion("platformName in", values, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameNotIn(List<String> values) {
            addCriterion("platformName not in", values, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameBetween(String value1, String value2) {
            addCriterion("platformName between", value1, value2, "platformname");
            return (Criteria) this;
        }

        public Criteria andPlatformnameNotBetween(String value1, String value2) {
            addCriterion("platformName not between", value1, value2, "platformname");
            return (Criteria) this;
        }

        public Criteria andClassifyshowIsNull() {
            addCriterion("classifyShow is null");
            return (Criteria) this;
        }

        public Criteria andClassifyshowIsNotNull() {
            addCriterion("classifyShow is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyshowEqualTo(String value) {
            addCriterion("classifyShow =", value, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowNotEqualTo(String value) {
            addCriterion("classifyShow <>", value, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowGreaterThan(String value) {
            addCriterion("classifyShow >", value, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowGreaterThanOrEqualTo(String value) {
            addCriterion("classifyShow >=", value, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowLessThan(String value) {
            addCriterion("classifyShow <", value, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowLessThanOrEqualTo(String value) {
            addCriterion("classifyShow <=", value, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowLike(String value) {
            addCriterion("classifyShow like", value, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowNotLike(String value) {
            addCriterion("classifyShow not like", value, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowIn(List<String> values) {
            addCriterion("classifyShow in", values, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowNotIn(List<String> values) {
            addCriterion("classifyShow not in", values, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowBetween(String value1, String value2) {
            addCriterion("classifyShow between", value1, value2, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andClassifyshowNotBetween(String value1, String value2) {
            addCriterion("classifyShow not between", value1, value2, "classifyshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowIsNull() {
            addCriterion("tradeShow is null");
            return (Criteria) this;
        }

        public Criteria andTradeshowIsNotNull() {
            addCriterion("tradeShow is not null");
            return (Criteria) this;
        }

        public Criteria andTradeshowEqualTo(String value) {
            addCriterion("tradeShow =", value, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowNotEqualTo(String value) {
            addCriterion("tradeShow <>", value, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowGreaterThan(String value) {
            addCriterion("tradeShow >", value, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowGreaterThanOrEqualTo(String value) {
            addCriterion("tradeShow >=", value, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowLessThan(String value) {
            addCriterion("tradeShow <", value, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowLessThanOrEqualTo(String value) {
            addCriterion("tradeShow <=", value, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowLike(String value) {
            addCriterion("tradeShow like", value, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowNotLike(String value) {
            addCriterion("tradeShow not like", value, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowIn(List<String> values) {
            addCriterion("tradeShow in", values, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowNotIn(List<String> values) {
            addCriterion("tradeShow not in", values, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowBetween(String value1, String value2) {
            addCriterion("tradeShow between", value1, value2, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andTradeshowNotBetween(String value1, String value2) {
            addCriterion("tradeShow not between", value1, value2, "tradeshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowIsNull() {
            addCriterion("districtShow is null");
            return (Criteria) this;
        }

        public Criteria andDistrictshowIsNotNull() {
            addCriterion("districtShow is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictshowEqualTo(String value) {
            addCriterion("districtShow =", value, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowNotEqualTo(String value) {
            addCriterion("districtShow <>", value, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowGreaterThan(String value) {
            addCriterion("districtShow >", value, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowGreaterThanOrEqualTo(String value) {
            addCriterion("districtShow >=", value, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowLessThan(String value) {
            addCriterion("districtShow <", value, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowLessThanOrEqualTo(String value) {
            addCriterion("districtShow <=", value, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowLike(String value) {
            addCriterion("districtShow like", value, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowNotLike(String value) {
            addCriterion("districtShow not like", value, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowIn(List<String> values) {
            addCriterion("districtShow in", values, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowNotIn(List<String> values) {
            addCriterion("districtShow not in", values, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowBetween(String value1, String value2) {
            addCriterion("districtShow between", value1, value2, "districtshow");
            return (Criteria) this;
        }

        public Criteria andDistrictshowNotBetween(String value1, String value2) {
            addCriterion("districtShow not between", value1, value2, "districtshow");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andStageshowIsNull() {
            addCriterion("stageShow is null");
            return (Criteria) this;
        }

        public Criteria andStageshowIsNotNull() {
            addCriterion("stageShow is not null");
            return (Criteria) this;
        }

        public Criteria andStageshowEqualTo(String value) {
            addCriterion("stageShow =", value, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowNotEqualTo(String value) {
            addCriterion("stageShow <>", value, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowGreaterThan(String value) {
            addCriterion("stageShow >", value, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowGreaterThanOrEqualTo(String value) {
            addCriterion("stageShow >=", value, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowLessThan(String value) {
            addCriterion("stageShow <", value, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowLessThanOrEqualTo(String value) {
            addCriterion("stageShow <=", value, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowLike(String value) {
            addCriterion("stageShow like", value, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowNotLike(String value) {
            addCriterion("stageShow not like", value, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowIn(List<String> values) {
            addCriterion("stageShow in", values, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowNotIn(List<String> values) {
            addCriterion("stageShow not in", values, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowBetween(String value1, String value2) {
            addCriterion("stageShow between", value1, value2, "stageshow");
            return (Criteria) this;
        }

        public Criteria andStageshowNotBetween(String value1, String value2) {
            addCriterion("stageShow not between", value1, value2, "stageshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowIsNull() {
            addCriterion("titleShow is null");
            return (Criteria) this;
        }

        public Criteria andTitleshowIsNotNull() {
            addCriterion("titleShow is not null");
            return (Criteria) this;
        }

        public Criteria andTitleshowEqualTo(String value) {
            addCriterion("titleShow =", value, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowNotEqualTo(String value) {
            addCriterion("titleShow <>", value, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowGreaterThan(String value) {
            addCriterion("titleShow >", value, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowGreaterThanOrEqualTo(String value) {
            addCriterion("titleShow >=", value, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowLessThan(String value) {
            addCriterion("titleShow <", value, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowLessThanOrEqualTo(String value) {
            addCriterion("titleShow <=", value, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowLike(String value) {
            addCriterion("titleShow like", value, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowNotLike(String value) {
            addCriterion("titleShow not like", value, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowIn(List<String> values) {
            addCriterion("titleShow in", values, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowNotIn(List<String> values) {
            addCriterion("titleShow not in", values, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowBetween(String value1, String value2) {
            addCriterion("titleShow between", value1, value2, "titleshow");
            return (Criteria) this;
        }

        public Criteria andTitleshowNotBetween(String value1, String value2) {
            addCriterion("titleShow not between", value1, value2, "titleshow");
            return (Criteria) this;
        }

        public Criteria andProcontactIsNull() {
            addCriterion("proContact is null");
            return (Criteria) this;
        }

        public Criteria andProcontactIsNotNull() {
            addCriterion("proContact is not null");
            return (Criteria) this;
        }

        public Criteria andProcontactEqualTo(String value) {
            addCriterion("proContact =", value, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactNotEqualTo(String value) {
            addCriterion("proContact <>", value, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactGreaterThan(String value) {
            addCriterion("proContact >", value, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactGreaterThanOrEqualTo(String value) {
            addCriterion("proContact >=", value, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactLessThan(String value) {
            addCriterion("proContact <", value, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactLessThanOrEqualTo(String value) {
            addCriterion("proContact <=", value, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactLike(String value) {
            addCriterion("proContact like", value, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactNotLike(String value) {
            addCriterion("proContact not like", value, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactIn(List<String> values) {
            addCriterion("proContact in", values, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactNotIn(List<String> values) {
            addCriterion("proContact not in", values, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactBetween(String value1, String value2) {
            addCriterion("proContact between", value1, value2, "procontact");
            return (Criteria) this;
        }

        public Criteria andProcontactNotBetween(String value1, String value2) {
            addCriterion("proContact not between", value1, value2, "procontact");
            return (Criteria) this;
        }

        public Criteria andProphoneIsNull() {
            addCriterion("proPhone is null");
            return (Criteria) this;
        }

        public Criteria andProphoneIsNotNull() {
            addCriterion("proPhone is not null");
            return (Criteria) this;
        }

        public Criteria andProphoneEqualTo(String value) {
            addCriterion("proPhone =", value, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneNotEqualTo(String value) {
            addCriterion("proPhone <>", value, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneGreaterThan(String value) {
            addCriterion("proPhone >", value, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneGreaterThanOrEqualTo(String value) {
            addCriterion("proPhone >=", value, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneLessThan(String value) {
            addCriterion("proPhone <", value, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneLessThanOrEqualTo(String value) {
            addCriterion("proPhone <=", value, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneLike(String value) {
            addCriterion("proPhone like", value, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneNotLike(String value) {
            addCriterion("proPhone not like", value, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneIn(List<String> values) {
            addCriterion("proPhone in", values, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneNotIn(List<String> values) {
            addCriterion("proPhone not in", values, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneBetween(String value1, String value2) {
            addCriterion("proPhone between", value1, value2, "prophone");
            return (Criteria) this;
        }

        public Criteria andProphoneNotBetween(String value1, String value2) {
            addCriterion("proPhone not between", value1, value2, "prophone");
            return (Criteria) this;
        }

        public Criteria andBuyingunitIsNull() {
            addCriterion("buyingUnit is null");
            return (Criteria) this;
        }

        public Criteria andBuyingunitIsNotNull() {
            addCriterion("buyingUnit is not null");
            return (Criteria) this;
        }

        public Criteria andBuyingunitEqualTo(String value) {
            addCriterion("buyingUnit =", value, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitNotEqualTo(String value) {
            addCriterion("buyingUnit <>", value, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitGreaterThan(String value) {
            addCriterion("buyingUnit >", value, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitGreaterThanOrEqualTo(String value) {
            addCriterion("buyingUnit >=", value, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitLessThan(String value) {
            addCriterion("buyingUnit <", value, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitLessThanOrEqualTo(String value) {
            addCriterion("buyingUnit <=", value, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitLike(String value) {
            addCriterion("buyingUnit like", value, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitNotLike(String value) {
            addCriterion("buyingUnit not like", value, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitIn(List<String> values) {
            addCriterion("buyingUnit in", values, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitNotIn(List<String> values) {
            addCriterion("buyingUnit not in", values, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitBetween(String value1, String value2) {
            addCriterion("buyingUnit between", value1, value2, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingunitNotBetween(String value1, String value2) {
            addCriterion("buyingUnit not between", value1, value2, "buyingunit");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrIsNull() {
            addCriterion("buyingAddr is null");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrIsNotNull() {
            addCriterion("buyingAddr is not null");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrEqualTo(String value) {
            addCriterion("buyingAddr =", value, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrNotEqualTo(String value) {
            addCriterion("buyingAddr <>", value, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrGreaterThan(String value) {
            addCriterion("buyingAddr >", value, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrGreaterThanOrEqualTo(String value) {
            addCriterion("buyingAddr >=", value, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrLessThan(String value) {
            addCriterion("buyingAddr <", value, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrLessThanOrEqualTo(String value) {
            addCriterion("buyingAddr <=", value, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrLike(String value) {
            addCriterion("buyingAddr like", value, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrNotLike(String value) {
            addCriterion("buyingAddr not like", value, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrIn(List<String> values) {
            addCriterion("buyingAddr in", values, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrNotIn(List<String> values) {
            addCriterion("buyingAddr not in", values, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrBetween(String value1, String value2) {
            addCriterion("buyingAddr between", value1, value2, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingaddrNotBetween(String value1, String value2) {
            addCriterion("buyingAddr not between", value1, value2, "buyingaddr");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneIsNull() {
            addCriterion("buyingPhone is null");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneIsNotNull() {
            addCriterion("buyingPhone is not null");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneEqualTo(String value) {
            addCriterion("buyingPhone =", value, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneNotEqualTo(String value) {
            addCriterion("buyingPhone <>", value, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneGreaterThan(String value) {
            addCriterion("buyingPhone >", value, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneGreaterThanOrEqualTo(String value) {
            addCriterion("buyingPhone >=", value, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneLessThan(String value) {
            addCriterion("buyingPhone <", value, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneLessThanOrEqualTo(String value) {
            addCriterion("buyingPhone <=", value, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneLike(String value) {
            addCriterion("buyingPhone like", value, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneNotLike(String value) {
            addCriterion("buyingPhone not like", value, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneIn(List<String> values) {
            addCriterion("buyingPhone in", values, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneNotIn(List<String> values) {
            addCriterion("buyingPhone not in", values, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneBetween(String value1, String value2) {
            addCriterion("buyingPhone between", value1, value2, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andBuyingphoneNotBetween(String value1, String value2) {
            addCriterion("buyingPhone not between", value1, value2, "buyingphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrIsNull() {
            addCriterion("proxyOrgAddr is null");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrIsNotNull() {
            addCriterion("proxyOrgAddr is not null");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrEqualTo(String value) {
            addCriterion("proxyOrgAddr =", value, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrNotEqualTo(String value) {
            addCriterion("proxyOrgAddr <>", value, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrGreaterThan(String value) {
            addCriterion("proxyOrgAddr >", value, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrGreaterThanOrEqualTo(String value) {
            addCriterion("proxyOrgAddr >=", value, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrLessThan(String value) {
            addCriterion("proxyOrgAddr <", value, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrLessThanOrEqualTo(String value) {
            addCriterion("proxyOrgAddr <=", value, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrLike(String value) {
            addCriterion("proxyOrgAddr like", value, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrNotLike(String value) {
            addCriterion("proxyOrgAddr not like", value, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrIn(List<String> values) {
            addCriterion("proxyOrgAddr in", values, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrNotIn(List<String> values) {
            addCriterion("proxyOrgAddr not in", values, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrBetween(String value1, String value2) {
            addCriterion("proxyOrgAddr between", value1, value2, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgaddrNotBetween(String value1, String value2) {
            addCriterion("proxyOrgAddr not between", value1, value2, "proxyorgaddr");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneIsNull() {
            addCriterion("proxyOrgPhone is null");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneIsNotNull() {
            addCriterion("proxyOrgPhone is not null");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneEqualTo(String value) {
            addCriterion("proxyOrgPhone =", value, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneNotEqualTo(String value) {
            addCriterion("proxyOrgPhone <>", value, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneGreaterThan(String value) {
            addCriterion("proxyOrgPhone >", value, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneGreaterThanOrEqualTo(String value) {
            addCriterion("proxyOrgPhone >=", value, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneLessThan(String value) {
            addCriterion("proxyOrgPhone <", value, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneLessThanOrEqualTo(String value) {
            addCriterion("proxyOrgPhone <=", value, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneLike(String value) {
            addCriterion("proxyOrgPhone like", value, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneNotLike(String value) {
            addCriterion("proxyOrgPhone not like", value, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneIn(List<String> values) {
            addCriterion("proxyOrgPhone in", values, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneNotIn(List<String> values) {
            addCriterion("proxyOrgPhone not in", values, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneBetween(String value1, String value2) {
            addCriterion("proxyOrgPhone between", value1, value2, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andProxyorgphoneNotBetween(String value1, String value2) {
            addCriterion("proxyOrgPhone not between", value1, value2, "proxyorgphone");
            return (Criteria) this;
        }

        public Criteria andPronameIsNull() {
            addCriterion("proName is null");
            return (Criteria) this;
        }

        public Criteria andPronameIsNotNull() {
            addCriterion("proName is not null");
            return (Criteria) this;
        }

        public Criteria andPronameEqualTo(String value) {
            addCriterion("proName =", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameNotEqualTo(String value) {
            addCriterion("proName <>", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameGreaterThan(String value) {
            addCriterion("proName >", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameGreaterThanOrEqualTo(String value) {
            addCriterion("proName >=", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameLessThan(String value) {
            addCriterion("proName <", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameLessThanOrEqualTo(String value) {
            addCriterion("proName <=", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameLike(String value) {
            addCriterion("proName like", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameNotLike(String value) {
            addCriterion("proName not like", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameIn(List<String> values) {
            addCriterion("proName in", values, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameNotIn(List<String> values) {
            addCriterion("proName not in", values, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameBetween(String value1, String value2) {
            addCriterion("proName between", value1, value2, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameNotBetween(String value1, String value2) {
            addCriterion("proName not between", value1, value2, "proname");
            return (Criteria) this;
        }

        public Criteria andClassesIsNull() {
            addCriterion("classes is null");
            return (Criteria) this;
        }

        public Criteria andClassesIsNotNull() {
            addCriterion("classes is not null");
            return (Criteria) this;
        }

        public Criteria andClassesEqualTo(String value) {
            addCriterion("classes =", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesNotEqualTo(String value) {
            addCriterion("classes <>", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesGreaterThan(String value) {
            addCriterion("classes >", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesGreaterThanOrEqualTo(String value) {
            addCriterion("classes >=", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesLessThan(String value) {
            addCriterion("classes <", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesLessThanOrEqualTo(String value) {
            addCriterion("classes <=", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesLike(String value) {
            addCriterion("classes like", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesNotLike(String value) {
            addCriterion("classes not like", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesIn(List<String> values) {
            addCriterion("classes in", values, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesNotIn(List<String> values) {
            addCriterion("classes not in", values, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesBetween(String value1, String value2) {
            addCriterion("classes between", value1, value2, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesNotBetween(String value1, String value2) {
            addCriterion("classes not between", value1, value2, "classes");
            return (Criteria) this;
        }

        public Criteria andNoticetimeIsNull() {
            addCriterion("noticeTime is null");
            return (Criteria) this;
        }

        public Criteria andNoticetimeIsNotNull() {
            addCriterion("noticeTime is not null");
            return (Criteria) this;
        }

        public Criteria andNoticetimeEqualTo(String value) {
            addCriterion("noticeTime =", value, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeNotEqualTo(String value) {
            addCriterion("noticeTime <>", value, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeGreaterThan(String value) {
            addCriterion("noticeTime >", value, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeGreaterThanOrEqualTo(String value) {
            addCriterion("noticeTime >=", value, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeLessThan(String value) {
            addCriterion("noticeTime <", value, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeLessThanOrEqualTo(String value) {
            addCriterion("noticeTime <=", value, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeLike(String value) {
            addCriterion("noticeTime like", value, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeNotLike(String value) {
            addCriterion("noticeTime not like", value, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeIn(List<String> values) {
            addCriterion("noticeTime in", values, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeNotIn(List<String> values) {
            addCriterion("noticeTime not in", values, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeBetween(String value1, String value2) {
            addCriterion("noticeTime between", value1, value2, "noticetime");
            return (Criteria) this;
        }

        public Criteria andNoticetimeNotBetween(String value1, String value2) {
            addCriterion("noticeTime not between", value1, value2, "noticetime");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeIsNull() {
            addCriterion("submitFileDeadTime is null");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeIsNotNull() {
            addCriterion("submitFileDeadTime is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeEqualTo(String value) {
            addCriterion("submitFileDeadTime =", value, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeNotEqualTo(String value) {
            addCriterion("submitFileDeadTime <>", value, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeGreaterThan(String value) {
            addCriterion("submitFileDeadTime >", value, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeGreaterThanOrEqualTo(String value) {
            addCriterion("submitFileDeadTime >=", value, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeLessThan(String value) {
            addCriterion("submitFileDeadTime <", value, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeLessThanOrEqualTo(String value) {
            addCriterion("submitFileDeadTime <=", value, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeLike(String value) {
            addCriterion("submitFileDeadTime like", value, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeNotLike(String value) {
            addCriterion("submitFileDeadTime not like", value, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeIn(List<String> values) {
            addCriterion("submitFileDeadTime in", values, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeNotIn(List<String> values) {
            addCriterion("submitFileDeadTime not in", values, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeBetween(String value1, String value2) {
            addCriterion("submitFileDeadTime between", value1, value2, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andSubmitfiledeadtimeNotBetween(String value1, String value2) {
            addCriterion("submitFileDeadTime not between", value1, value2, "submitfiledeadtime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeIsNull() {
            addCriterion("entitledCheckTime is null");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeIsNotNull() {
            addCriterion("entitledCheckTime is not null");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeEqualTo(String value) {
            addCriterion("entitledCheckTime =", value, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeNotEqualTo(String value) {
            addCriterion("entitledCheckTime <>", value, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeGreaterThan(String value) {
            addCriterion("entitledCheckTime >", value, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeGreaterThanOrEqualTo(String value) {
            addCriterion("entitledCheckTime >=", value, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeLessThan(String value) {
            addCriterion("entitledCheckTime <", value, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeLessThanOrEqualTo(String value) {
            addCriterion("entitledCheckTime <=", value, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeLike(String value) {
            addCriterion("entitledCheckTime like", value, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeNotLike(String value) {
            addCriterion("entitledCheckTime not like", value, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeIn(List<String> values) {
            addCriterion("entitledCheckTime in", values, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeNotIn(List<String> values) {
            addCriterion("entitledCheckTime not in", values, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeBetween(String value1, String value2) {
            addCriterion("entitledCheckTime between", value1, value2, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andEntitledchecktimeNotBetween(String value1, String value2) {
            addCriterion("entitledCheckTime not between", value1, value2, "entitledchecktime");
            return (Criteria) this;
        }

        public Criteria andAppendixIsNull() {
            addCriterion("appendix is null");
            return (Criteria) this;
        }

        public Criteria andAppendixIsNotNull() {
            addCriterion("appendix is not null");
            return (Criteria) this;
        }

        public Criteria andAppendixEqualTo(String value) {
            addCriterion("appendix =", value, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixNotEqualTo(String value) {
            addCriterion("appendix <>", value, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixGreaterThan(String value) {
            addCriterion("appendix >", value, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixGreaterThanOrEqualTo(String value) {
            addCriterion("appendix >=", value, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixLessThan(String value) {
            addCriterion("appendix <", value, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixLessThanOrEqualTo(String value) {
            addCriterion("appendix <=", value, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixLike(String value) {
            addCriterion("appendix like", value, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixNotLike(String value) {
            addCriterion("appendix not like", value, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixIn(List<String> values) {
            addCriterion("appendix in", values, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixNotIn(List<String> values) {
            addCriterion("appendix not in", values, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixBetween(String value1, String value2) {
            addCriterion("appendix between", value1, value2, "appendix");
            return (Criteria) this;
        }

        public Criteria andAppendixNotBetween(String value1, String value2) {
            addCriterion("appendix not between", value1, value2, "appendix");
            return (Criteria) this;
        }

        public Criteria andPronoIsNull() {
            addCriterion("proNo is null");
            return (Criteria) this;
        }

        public Criteria andPronoIsNotNull() {
            addCriterion("proNo is not null");
            return (Criteria) this;
        }

        public Criteria andPronoEqualTo(String value) {
            addCriterion("proNo =", value, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoNotEqualTo(String value) {
            addCriterion("proNo <>", value, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoGreaterThan(String value) {
            addCriterion("proNo >", value, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoGreaterThanOrEqualTo(String value) {
            addCriterion("proNo >=", value, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoLessThan(String value) {
            addCriterion("proNo <", value, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoLessThanOrEqualTo(String value) {
            addCriterion("proNo <=", value, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoLike(String value) {
            addCriterion("proNo like", value, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoNotLike(String value) {
            addCriterion("proNo not like", value, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoIn(List<String> values) {
            addCriterion("proNo in", values, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoNotIn(List<String> values) {
            addCriterion("proNo not in", values, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoBetween(String value1, String value2) {
            addCriterion("proNo between", value1, value2, "prono");
            return (Criteria) this;
        }

        public Criteria andPronoNotBetween(String value1, String value2) {
            addCriterion("proNo not between", value1, value2, "prono");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeIsNull() {
            addCriterion("obtainFileTime is null");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeIsNotNull() {
            addCriterion("obtainFileTime is not null");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeEqualTo(String value) {
            addCriterion("obtainFileTime =", value, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeNotEqualTo(String value) {
            addCriterion("obtainFileTime <>", value, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeGreaterThan(String value) {
            addCriterion("obtainFileTime >", value, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeGreaterThanOrEqualTo(String value) {
            addCriterion("obtainFileTime >=", value, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeLessThan(String value) {
            addCriterion("obtainFileTime <", value, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeLessThanOrEqualTo(String value) {
            addCriterion("obtainFileTime <=", value, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeLike(String value) {
            addCriterion("obtainFileTime like", value, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeNotLike(String value) {
            addCriterion("obtainFileTime not like", value, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeIn(List<String> values) {
            addCriterion("obtainFileTime in", values, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeNotIn(List<String> values) {
            addCriterion("obtainFileTime not in", values, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeBetween(String value1, String value2) {
            addCriterion("obtainFileTime between", value1, value2, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andObtainfiletimeNotBetween(String value1, String value2) {
            addCriterion("obtainFileTime not between", value1, value2, "obtainfiletime");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceIsNull() {
            addCriterion("tenderingFilePrice is null");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceIsNotNull() {
            addCriterion("tenderingFilePrice is not null");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceEqualTo(String value) {
            addCriterion("tenderingFilePrice =", value, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceNotEqualTo(String value) {
            addCriterion("tenderingFilePrice <>", value, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceGreaterThan(String value) {
            addCriterion("tenderingFilePrice >", value, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceGreaterThanOrEqualTo(String value) {
            addCriterion("tenderingFilePrice >=", value, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceLessThan(String value) {
            addCriterion("tenderingFilePrice <", value, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceLessThanOrEqualTo(String value) {
            addCriterion("tenderingFilePrice <=", value, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceLike(String value) {
            addCriterion("tenderingFilePrice like", value, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceNotLike(String value) {
            addCriterion("tenderingFilePrice not like", value, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceIn(List<String> values) {
            addCriterion("tenderingFilePrice in", values, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceNotIn(List<String> values) {
            addCriterion("tenderingFilePrice not in", values, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceBetween(String value1, String value2) {
            addCriterion("tenderingFilePrice between", value1, value2, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfilepriceNotBetween(String value1, String value2) {
            addCriterion("tenderingFilePrice not between", value1, value2, "tenderingfileprice");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrIsNull() {
            addCriterion("tenderingFileAddr is null");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrIsNotNull() {
            addCriterion("tenderingFileAddr is not null");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrEqualTo(String value) {
            addCriterion("tenderingFileAddr =", value, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrNotEqualTo(String value) {
            addCriterion("tenderingFileAddr <>", value, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrGreaterThan(String value) {
            addCriterion("tenderingFileAddr >", value, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrGreaterThanOrEqualTo(String value) {
            addCriterion("tenderingFileAddr >=", value, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrLessThan(String value) {
            addCriterion("tenderingFileAddr <", value, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrLessThanOrEqualTo(String value) {
            addCriterion("tenderingFileAddr <=", value, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrLike(String value) {
            addCriterion("tenderingFileAddr like", value, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrNotLike(String value) {
            addCriterion("tenderingFileAddr not like", value, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrIn(List<String> values) {
            addCriterion("tenderingFileAddr in", values, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrNotIn(List<String> values) {
            addCriterion("tenderingFileAddr not in", values, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrBetween(String value1, String value2) {
            addCriterion("tenderingFileAddr between", value1, value2, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andTenderingfileaddrNotBetween(String value1, String value2) {
            addCriterion("tenderingFileAddr not between", value1, value2, "tenderingfileaddr");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeIsNull() {
            addCriterion("openTenderTime is null");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeIsNotNull() {
            addCriterion("openTenderTime is not null");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeEqualTo(String value) {
            addCriterion("openTenderTime =", value, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeNotEqualTo(String value) {
            addCriterion("openTenderTime <>", value, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeGreaterThan(String value) {
            addCriterion("openTenderTime >", value, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeGreaterThanOrEqualTo(String value) {
            addCriterion("openTenderTime >=", value, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeLessThan(String value) {
            addCriterion("openTenderTime <", value, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeLessThanOrEqualTo(String value) {
            addCriterion("openTenderTime <=", value, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeLike(String value) {
            addCriterion("openTenderTime like", value, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeNotLike(String value) {
            addCriterion("openTenderTime not like", value, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeIn(List<String> values) {
            addCriterion("openTenderTime in", values, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeNotIn(List<String> values) {
            addCriterion("openTenderTime not in", values, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeBetween(String value1, String value2) {
            addCriterion("openTenderTime between", value1, value2, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentendertimeNotBetween(String value1, String value2) {
            addCriterion("openTenderTime not between", value1, value2, "opentendertime");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrIsNull() {
            addCriterion("openTenderAddr is null");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrIsNotNull() {
            addCriterion("openTenderAddr is not null");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrEqualTo(String value) {
            addCriterion("openTenderAddr =", value, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrNotEqualTo(String value) {
            addCriterion("openTenderAddr <>", value, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrGreaterThan(String value) {
            addCriterion("openTenderAddr >", value, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrGreaterThanOrEqualTo(String value) {
            addCriterion("openTenderAddr >=", value, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrLessThan(String value) {
            addCriterion("openTenderAddr <", value, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrLessThanOrEqualTo(String value) {
            addCriterion("openTenderAddr <=", value, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrLike(String value) {
            addCriterion("openTenderAddr like", value, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrNotLike(String value) {
            addCriterion("openTenderAddr not like", value, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrIn(List<String> values) {
            addCriterion("openTenderAddr in", values, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrNotIn(List<String> values) {
            addCriterion("openTenderAddr not in", values, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrBetween(String value1, String value2) {
            addCriterion("openTenderAddr between", value1, value2, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andOpentenderaddrNotBetween(String value1, String value2) {
            addCriterion("openTenderAddr not between", value1, value2, "opentenderaddr");
            return (Criteria) this;
        }

        public Criteria andBudgetamountIsNull() {
            addCriterion("budgetAmount is null");
            return (Criteria) this;
        }

        public Criteria andBudgetamountIsNotNull() {
            addCriterion("budgetAmount is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetamountEqualTo(String value) {
            addCriterion("budgetAmount =", value, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountNotEqualTo(String value) {
            addCriterion("budgetAmount <>", value, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountGreaterThan(String value) {
            addCriterion("budgetAmount >", value, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountGreaterThanOrEqualTo(String value) {
            addCriterion("budgetAmount >=", value, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountLessThan(String value) {
            addCriterion("budgetAmount <", value, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountLessThanOrEqualTo(String value) {
            addCriterion("budgetAmount <=", value, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountLike(String value) {
            addCriterion("budgetAmount like", value, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountNotLike(String value) {
            addCriterion("budgetAmount not like", value, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountIn(List<String> values) {
            addCriterion("budgetAmount in", values, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountNotIn(List<String> values) {
            addCriterion("budgetAmount not in", values, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountBetween(String value1, String value2) {
            addCriterion("budgetAmount between", value1, value2, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andBudgetamountNotBetween(String value1, String value2) {
            addCriterion("budgetAmount not between", value1, value2, "budgetamount");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeIsNull() {
            addCriterion("resFileSubTime is null");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeIsNotNull() {
            addCriterion("resFileSubTime is not null");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeEqualTo(String value) {
            addCriterion("resFileSubTime =", value, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeNotEqualTo(String value) {
            addCriterion("resFileSubTime <>", value, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeGreaterThan(String value) {
            addCriterion("resFileSubTime >", value, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeGreaterThanOrEqualTo(String value) {
            addCriterion("resFileSubTime >=", value, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeLessThan(String value) {
            addCriterion("resFileSubTime <", value, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeLessThanOrEqualTo(String value) {
            addCriterion("resFileSubTime <=", value, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeLike(String value) {
            addCriterion("resFileSubTime like", value, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeNotLike(String value) {
            addCriterion("resFileSubTime not like", value, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeIn(List<String> values) {
            addCriterion("resFileSubTime in", values, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeNotIn(List<String> values) {
            addCriterion("resFileSubTime not in", values, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeBetween(String value1, String value2) {
            addCriterion("resFileSubTime between", value1, value2, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubtimeNotBetween(String value1, String value2) {
            addCriterion("resFileSubTime not between", value1, value2, "resfilesubtime");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrIsNull() {
            addCriterion("resFileSubAddr is null");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrIsNotNull() {
            addCriterion("resFileSubAddr is not null");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrEqualTo(String value) {
            addCriterion("resFileSubAddr =", value, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrNotEqualTo(String value) {
            addCriterion("resFileSubAddr <>", value, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrGreaterThan(String value) {
            addCriterion("resFileSubAddr >", value, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrGreaterThanOrEqualTo(String value) {
            addCriterion("resFileSubAddr >=", value, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrLessThan(String value) {
            addCriterion("resFileSubAddr <", value, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrLessThanOrEqualTo(String value) {
            addCriterion("resFileSubAddr <=", value, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrLike(String value) {
            addCriterion("resFileSubAddr like", value, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrNotLike(String value) {
            addCriterion("resFileSubAddr not like", value, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrIn(List<String> values) {
            addCriterion("resFileSubAddr in", values, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrNotIn(List<String> values) {
            addCriterion("resFileSubAddr not in", values, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrBetween(String value1, String value2) {
            addCriterion("resFileSubAddr between", value1, value2, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfilesubaddrNotBetween(String value1, String value2) {
            addCriterion("resFileSubAddr not between", value1, value2, "resfilesubaddr");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeIsNull() {
            addCriterion("resFileOpenTime is null");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeIsNotNull() {
            addCriterion("resFileOpenTime is not null");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeEqualTo(String value) {
            addCriterion("resFileOpenTime =", value, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeNotEqualTo(String value) {
            addCriterion("resFileOpenTime <>", value, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeGreaterThan(String value) {
            addCriterion("resFileOpenTime >", value, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeGreaterThanOrEqualTo(String value) {
            addCriterion("resFileOpenTime >=", value, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeLessThan(String value) {
            addCriterion("resFileOpenTime <", value, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeLessThanOrEqualTo(String value) {
            addCriterion("resFileOpenTime <=", value, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeLike(String value) {
            addCriterion("resFileOpenTime like", value, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeNotLike(String value) {
            addCriterion("resFileOpenTime not like", value, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeIn(List<String> values) {
            addCriterion("resFileOpenTime in", values, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeNotIn(List<String> values) {
            addCriterion("resFileOpenTime not in", values, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeBetween(String value1, String value2) {
            addCriterion("resFileOpenTime between", value1, value2, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andResfileopentimeNotBetween(String value1, String value2) {
            addCriterion("resFileOpenTime not between", value1, value2, "resfileopentime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeIsNull() {
            addCriterion("winBidTime is null");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeIsNotNull() {
            addCriterion("winBidTime is not null");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeEqualTo(String value) {
            addCriterion("winBidTime =", value, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeNotEqualTo(String value) {
            addCriterion("winBidTime <>", value, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeGreaterThan(String value) {
            addCriterion("winBidTime >", value, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeGreaterThanOrEqualTo(String value) {
            addCriterion("winBidTime >=", value, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeLessThan(String value) {
            addCriterion("winBidTime <", value, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeLessThanOrEqualTo(String value) {
            addCriterion("winBidTime <=", value, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeLike(String value) {
            addCriterion("winBidTime like", value, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeNotLike(String value) {
            addCriterion("winBidTime not like", value, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeIn(List<String> values) {
            addCriterion("winBidTime in", values, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeNotIn(List<String> values) {
            addCriterion("winBidTime not in", values, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeBetween(String value1, String value2) {
            addCriterion("winBidTime between", value1, value2, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andWinbidtimeNotBetween(String value1, String value2) {
            addCriterion("winBidTime not between", value1, value2, "winbidtime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeIsNull() {
            addCriterion("tenderingNoticeTime is null");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeIsNotNull() {
            addCriterion("tenderingNoticeTime is not null");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeEqualTo(String value) {
            addCriterion("tenderingNoticeTime =", value, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeNotEqualTo(String value) {
            addCriterion("tenderingNoticeTime <>", value, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeGreaterThan(String value) {
            addCriterion("tenderingNoticeTime >", value, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeGreaterThanOrEqualTo(String value) {
            addCriterion("tenderingNoticeTime >=", value, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeLessThan(String value) {
            addCriterion("tenderingNoticeTime <", value, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeLessThanOrEqualTo(String value) {
            addCriterion("tenderingNoticeTime <=", value, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeLike(String value) {
            addCriterion("tenderingNoticeTime like", value, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeNotLike(String value) {
            addCriterion("tenderingNoticeTime not like", value, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeIn(List<String> values) {
            addCriterion("tenderingNoticeTime in", values, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeNotIn(List<String> values) {
            addCriterion("tenderingNoticeTime not in", values, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeBetween(String value1, String value2) {
            addCriterion("tenderingNoticeTime between", value1, value2, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andTenderingnoticetimeNotBetween(String value1, String value2) {
            addCriterion("tenderingNoticeTime not between", value1, value2, "tenderingnoticetime");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountIsNull() {
            addCriterion("winBidTotalAmount is null");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountIsNotNull() {
            addCriterion("winBidTotalAmount is not null");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountEqualTo(String value) {
            addCriterion("winBidTotalAmount =", value, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountNotEqualTo(String value) {
            addCriterion("winBidTotalAmount <>", value, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountGreaterThan(String value) {
            addCriterion("winBidTotalAmount >", value, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountGreaterThanOrEqualTo(String value) {
            addCriterion("winBidTotalAmount >=", value, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountLessThan(String value) {
            addCriterion("winBidTotalAmount <", value, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountLessThanOrEqualTo(String value) {
            addCriterion("winBidTotalAmount <=", value, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountLike(String value) {
            addCriterion("winBidTotalAmount like", value, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountNotLike(String value) {
            addCriterion("winBidTotalAmount not like", value, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountIn(List<String> values) {
            addCriterion("winBidTotalAmount in", values, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountNotIn(List<String> values) {
            addCriterion("winBidTotalAmount not in", values, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountBetween(String value1, String value2) {
            addCriterion("winBidTotalAmount between", value1, value2, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidtotalamountNotBetween(String value1, String value2) {
            addCriterion("winBidTotalAmount not between", value1, value2, "winbidtotalamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameIsNull() {
            addCriterion("winBidBisName is null");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameIsNotNull() {
            addCriterion("winBidBisName is not null");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameEqualTo(String value) {
            addCriterion("winBidBisName =", value, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameNotEqualTo(String value) {
            addCriterion("winBidBisName <>", value, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameGreaterThan(String value) {
            addCriterion("winBidBisName >", value, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameGreaterThanOrEqualTo(String value) {
            addCriterion("winBidBisName >=", value, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameLessThan(String value) {
            addCriterion("winBidBisName <", value, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameLessThanOrEqualTo(String value) {
            addCriterion("winBidBisName <=", value, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameLike(String value) {
            addCriterion("winBidBisName like", value, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameNotLike(String value) {
            addCriterion("winBidBisName not like", value, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameIn(List<String> values) {
            addCriterion("winBidBisName in", values, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameNotIn(List<String> values) {
            addCriterion("winBidBisName not in", values, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameBetween(String value1, String value2) {
            addCriterion("winBidBisName between", value1, value2, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisnameNotBetween(String value1, String value2) {
            addCriterion("winBidBisName not between", value1, value2, "winbidbisname");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrIsNull() {
            addCriterion("winBidBisAddr is null");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrIsNotNull() {
            addCriterion("winBidBisAddr is not null");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrEqualTo(String value) {
            addCriterion("winBidBisAddr =", value, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrNotEqualTo(String value) {
            addCriterion("winBidBisAddr <>", value, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrGreaterThan(String value) {
            addCriterion("winBidBisAddr >", value, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrGreaterThanOrEqualTo(String value) {
            addCriterion("winBidBisAddr >=", value, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrLessThan(String value) {
            addCriterion("winBidBisAddr <", value, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrLessThanOrEqualTo(String value) {
            addCriterion("winBidBisAddr <=", value, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrLike(String value) {
            addCriterion("winBidBisAddr like", value, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrNotLike(String value) {
            addCriterion("winBidBisAddr not like", value, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrIn(List<String> values) {
            addCriterion("winBidBisAddr in", values, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrNotIn(List<String> values) {
            addCriterion("winBidBisAddr not in", values, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrBetween(String value1, String value2) {
            addCriterion("winBidBisAddr between", value1, value2, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisaddrNotBetween(String value1, String value2) {
            addCriterion("winBidBisAddr not between", value1, value2, "winbidbisaddr");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountIsNull() {
            addCriterion("winBidBisAmount is null");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountIsNotNull() {
            addCriterion("winBidBisAmount is not null");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountEqualTo(String value) {
            addCriterion("winBidBisAmount =", value, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountNotEqualTo(String value) {
            addCriterion("winBidBisAmount <>", value, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountGreaterThan(String value) {
            addCriterion("winBidBisAmount >", value, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountGreaterThanOrEqualTo(String value) {
            addCriterion("winBidBisAmount >=", value, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountLessThan(String value) {
            addCriterion("winBidBisAmount <", value, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountLessThanOrEqualTo(String value) {
            addCriterion("winBidBisAmount <=", value, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountLike(String value) {
            addCriterion("winBidBisAmount like", value, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountNotLike(String value) {
            addCriterion("winBidBisAmount not like", value, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountIn(List<String> values) {
            addCriterion("winBidBisAmount in", values, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountNotIn(List<String> values) {
            addCriterion("winBidBisAmount not in", values, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountBetween(String value1, String value2) {
            addCriterion("winBidBisAmount between", value1, value2, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andWinbidbisamountNotBetween(String value1, String value2) {
            addCriterion("winBidBisAmount not between", value1, value2, "winbidbisamount");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeIsNull() {
            addCriterion("firstNoticeTime is null");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeIsNotNull() {
            addCriterion("firstNoticeTime is not null");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeEqualTo(String value) {
            addCriterion("firstNoticeTime =", value, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeNotEqualTo(String value) {
            addCriterion("firstNoticeTime <>", value, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeGreaterThan(String value) {
            addCriterion("firstNoticeTime >", value, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeGreaterThanOrEqualTo(String value) {
            addCriterion("firstNoticeTime >=", value, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeLessThan(String value) {
            addCriterion("firstNoticeTime <", value, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeLessThanOrEqualTo(String value) {
            addCriterion("firstNoticeTime <=", value, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeLike(String value) {
            addCriterion("firstNoticeTime like", value, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeNotLike(String value) {
            addCriterion("firstNoticeTime not like", value, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeIn(List<String> values) {
            addCriterion("firstNoticeTime in", values, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeNotIn(List<String> values) {
            addCriterion("firstNoticeTime not in", values, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeBetween(String value1, String value2) {
            addCriterion("firstNoticeTime between", value1, value2, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andFirstnoticetimeNotBetween(String value1, String value2) {
            addCriterion("firstNoticeTime not between", value1, value2, "firstnoticetime");
            return (Criteria) this;
        }

        public Criteria andOtherIsNull() {
            addCriterion("other is null");
            return (Criteria) this;
        }

        public Criteria andOtherIsNotNull() {
            addCriterion("other is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEqualTo(String value) {
            addCriterion("other =", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotEqualTo(String value) {
            addCriterion("other <>", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThan(String value) {
            addCriterion("other >", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThanOrEqualTo(String value) {
            addCriterion("other >=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThan(String value) {
            addCriterion("other <", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThanOrEqualTo(String value) {
            addCriterion("other <=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLike(String value) {
            addCriterion("other like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotLike(String value) {
            addCriterion("other not like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherIn(List<String> values) {
            addCriterion("other in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotIn(List<String> values) {
            addCriterion("other not in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherBetween(String value1, String value2) {
            addCriterion("other between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotBetween(String value1, String value2) {
            addCriterion("other not between", value1, value2, "other");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}