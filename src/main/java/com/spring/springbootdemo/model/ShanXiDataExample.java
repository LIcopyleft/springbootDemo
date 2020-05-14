package com.spring.springbootdemo.model;

import java.util.ArrayList;
import java.util.List;

public class ShanXiDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShanXiDataExample() {
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

        public Criteria andCategoryFirstIsNull() {
            addCriterion("category_first is null");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstIsNotNull() {
            addCriterion("category_first is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstEqualTo(String value) {
            addCriterion("category_first =", value, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstNotEqualTo(String value) {
            addCriterion("category_first <>", value, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstGreaterThan(String value) {
            addCriterion("category_first >", value, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstGreaterThanOrEqualTo(String value) {
            addCriterion("category_first >=", value, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstLessThan(String value) {
            addCriterion("category_first <", value, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstLessThanOrEqualTo(String value) {
            addCriterion("category_first <=", value, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstLike(String value) {
            addCriterion("category_first like", value, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstNotLike(String value) {
            addCriterion("category_first not like", value, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstIn(List<String> values) {
            addCriterion("category_first in", values, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstNotIn(List<String> values) {
            addCriterion("category_first not in", values, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstBetween(String value1, String value2) {
            addCriterion("category_first between", value1, value2, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategoryFirstNotBetween(String value1, String value2) {
            addCriterion("category_first not between", value1, value2, "categoryFirst");
            return (Criteria) this;
        }

        public Criteria andCategorySecondIsNull() {
            addCriterion("category_second is null");
            return (Criteria) this;
        }

        public Criteria andCategorySecondIsNotNull() {
            addCriterion("category_second is not null");
            return (Criteria) this;
        }

        public Criteria andCategorySecondEqualTo(String value) {
            addCriterion("category_second =", value, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondNotEqualTo(String value) {
            addCriterion("category_second <>", value, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondGreaterThan(String value) {
            addCriterion("category_second >", value, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondGreaterThanOrEqualTo(String value) {
            addCriterion("category_second >=", value, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondLessThan(String value) {
            addCriterion("category_second <", value, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondLessThanOrEqualTo(String value) {
            addCriterion("category_second <=", value, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondLike(String value) {
            addCriterion("category_second like", value, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondNotLike(String value) {
            addCriterion("category_second not like", value, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondIn(List<String> values) {
            addCriterion("category_second in", values, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondNotIn(List<String> values) {
            addCriterion("category_second not in", values, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondBetween(String value1, String value2) {
            addCriterion("category_second between", value1, value2, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andCategorySecondNotBetween(String value1, String value2) {
            addCriterion("category_second not between", value1, value2, "categorySecond");
            return (Criteria) this;
        }

        public Criteria andHashcodeIsNull() {
            addCriterion("hashcode is null");
            return (Criteria) this;
        }

        public Criteria andHashcodeIsNotNull() {
            addCriterion("hashcode is not null");
            return (Criteria) this;
        }

        public Criteria andHashcodeEqualTo(String value) {
            addCriterion("hashcode =", value, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeNotEqualTo(String value) {
            addCriterion("hashcode <>", value, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeGreaterThan(String value) {
            addCriterion("hashcode >", value, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeGreaterThanOrEqualTo(String value) {
            addCriterion("hashcode >=", value, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeLessThan(String value) {
            addCriterion("hashcode <", value, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeLessThanOrEqualTo(String value) {
            addCriterion("hashcode <=", value, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeLike(String value) {
            addCriterion("hashcode like", value, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeNotLike(String value) {
            addCriterion("hashcode not like", value, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeIn(List<String> values) {
            addCriterion("hashcode in", values, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeNotIn(List<String> values) {
            addCriterion("hashcode not in", values, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeBetween(String value1, String value2) {
            addCriterion("hashcode between", value1, value2, "hashcode");
            return (Criteria) this;
        }

        public Criteria andHashcodeNotBetween(String value1, String value2) {
            addCriterion("hashcode not between", value1, value2, "hashcode");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNull() {
            addCriterion("TABLENAME is null");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNotNull() {
            addCriterion("TABLENAME is not null");
            return (Criteria) this;
        }

        public Criteria andTablenameEqualTo(String value) {
            addCriterion("TABLENAME =", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotEqualTo(String value) {
            addCriterion("TABLENAME <>", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThan(String value) {
            addCriterion("TABLENAME >", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThanOrEqualTo(String value) {
            addCriterion("TABLENAME >=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThan(String value) {
            addCriterion("TABLENAME <", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThanOrEqualTo(String value) {
            addCriterion("TABLENAME <=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLike(String value) {
            addCriterion("TABLENAME like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotLike(String value) {
            addCriterion("TABLENAME not like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameIn(List<String> values) {
            addCriterion("TABLENAME in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotIn(List<String> values) {
            addCriterion("TABLENAME not in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameBetween(String value1, String value2) {
            addCriterion("TABLENAME between", value1, value2, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotBetween(String value1, String value2) {
            addCriterion("TABLENAME not between", value1, value2, "tablename");
            return (Criteria) this;
        }

        public Criteria andRegioncodeIsNull() {
            addCriterion("REGIONCODE is null");
            return (Criteria) this;
        }

        public Criteria andRegioncodeIsNotNull() {
            addCriterion("REGIONCODE is not null");
            return (Criteria) this;
        }

        public Criteria andRegioncodeEqualTo(String value) {
            addCriterion("REGIONCODE =", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeNotEqualTo(String value) {
            addCriterion("REGIONCODE <>", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeGreaterThan(String value) {
            addCriterion("REGIONCODE >", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeGreaterThanOrEqualTo(String value) {
            addCriterion("REGIONCODE >=", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeLessThan(String value) {
            addCriterion("REGIONCODE <", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeLessThanOrEqualTo(String value) {
            addCriterion("REGIONCODE <=", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeLike(String value) {
            addCriterion("REGIONCODE like", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeNotLike(String value) {
            addCriterion("REGIONCODE not like", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeIn(List<String> values) {
            addCriterion("REGIONCODE in", values, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeNotIn(List<String> values) {
            addCriterion("REGIONCODE not in", values, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeBetween(String value1, String value2) {
            addCriterion("REGIONCODE between", value1, value2, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeNotBetween(String value1, String value2) {
            addCriterion("REGIONCODE not between", value1, value2, "regioncode");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andHttpurlIsNull() {
            addCriterion("HTTPURL is null");
            return (Criteria) this;
        }

        public Criteria andHttpurlIsNotNull() {
            addCriterion("HTTPURL is not null");
            return (Criteria) this;
        }

        public Criteria andHttpurlEqualTo(String value) {
            addCriterion("HTTPURL =", value, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlNotEqualTo(String value) {
            addCriterion("HTTPURL <>", value, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlGreaterThan(String value) {
            addCriterion("HTTPURL >", value, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlGreaterThanOrEqualTo(String value) {
            addCriterion("HTTPURL >=", value, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlLessThan(String value) {
            addCriterion("HTTPURL <", value, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlLessThanOrEqualTo(String value) {
            addCriterion("HTTPURL <=", value, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlLike(String value) {
            addCriterion("HTTPURL like", value, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlNotLike(String value) {
            addCriterion("HTTPURL not like", value, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlIn(List<String> values) {
            addCriterion("HTTPURL in", values, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlNotIn(List<String> values) {
            addCriterion("HTTPURL not in", values, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlBetween(String value1, String value2) {
            addCriterion("HTTPURL between", value1, value2, "httpurl");
            return (Criteria) this;
        }

        public Criteria andHttpurlNotBetween(String value1, String value2) {
            addCriterion("HTTPURL not between", value1, value2, "httpurl");
            return (Criteria) this;
        }

        public Criteria andProjectcodeIsNull() {
            addCriterion("PROJECTCODE is null");
            return (Criteria) this;
        }

        public Criteria andProjectcodeIsNotNull() {
            addCriterion("PROJECTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andProjectcodeEqualTo(String value) {
            addCriterion("PROJECTCODE =", value, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeNotEqualTo(String value) {
            addCriterion("PROJECTCODE <>", value, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeGreaterThan(String value) {
            addCriterion("PROJECTCODE >", value, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECTCODE >=", value, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeLessThan(String value) {
            addCriterion("PROJECTCODE <", value, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeLessThanOrEqualTo(String value) {
            addCriterion("PROJECTCODE <=", value, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeLike(String value) {
            addCriterion("PROJECTCODE like", value, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeNotLike(String value) {
            addCriterion("PROJECTCODE not like", value, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeIn(List<String> values) {
            addCriterion("PROJECTCODE in", values, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeNotIn(List<String> values) {
            addCriterion("PROJECTCODE not in", values, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeBetween(String value1, String value2) {
            addCriterion("PROJECTCODE between", value1, value2, "projectcode");
            return (Criteria) this;
        }

        public Criteria andProjectcodeNotBetween(String value1, String value2) {
            addCriterion("PROJECTCODE not between", value1, value2, "projectcode");
            return (Criteria) this;
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectnameIsNull() {
            addCriterion("PROJECTNAME is null");
            return (Criteria) this;
        }

        public Criteria andProjectnameIsNotNull() {
            addCriterion("PROJECTNAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjectnameEqualTo(String value) {
            addCriterion("PROJECTNAME =", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotEqualTo(String value) {
            addCriterion("PROJECTNAME <>", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameGreaterThan(String value) {
            addCriterion("PROJECTNAME >", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECTNAME >=", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLessThan(String value) {
            addCriterion("PROJECTNAME <", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLessThanOrEqualTo(String value) {
            addCriterion("PROJECTNAME <=", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLike(String value) {
            addCriterion("PROJECTNAME like", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotLike(String value) {
            addCriterion("PROJECTNAME not like", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameIn(List<String> values) {
            addCriterion("PROJECTNAME in", values, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotIn(List<String> values) {
            addCriterion("PROJECTNAME not in", values, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameBetween(String value1, String value2) {
            addCriterion("PROJECTNAME between", value1, value2, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotBetween(String value1, String value2) {
            addCriterion("PROJECTNAME not between", value1, value2, "projectname");
            return (Criteria) this;
        }

        public Criteria andReceivetimeIsNull() {
            addCriterion("RECEIVETIME is null");
            return (Criteria) this;
        }

        public Criteria andReceivetimeIsNotNull() {
            addCriterion("RECEIVETIME is not null");
            return (Criteria) this;
        }

        public Criteria andReceivetimeEqualTo(String value) {
            addCriterion("RECEIVETIME =", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotEqualTo(String value) {
            addCriterion("RECEIVETIME <>", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeGreaterThan(String value) {
            addCriterion("RECEIVETIME >", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVETIME >=", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeLessThan(String value) {
            addCriterion("RECEIVETIME <", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeLessThanOrEqualTo(String value) {
            addCriterion("RECEIVETIME <=", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeLike(String value) {
            addCriterion("RECEIVETIME like", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotLike(String value) {
            addCriterion("RECEIVETIME not like", value, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeIn(List<String> values) {
            addCriterion("RECEIVETIME in", values, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotIn(List<String> values) {
            addCriterion("RECEIVETIME not in", values, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeBetween(String value1, String value2) {
            addCriterion("RECEIVETIME between", value1, value2, "receivetime");
            return (Criteria) this;
        }

        public Criteria andReceivetimeNotBetween(String value1, String value2) {
            addCriterion("RECEIVETIME not between", value1, value2, "receivetime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeIsNull() {
            addCriterion("FABUPX_TIME is null");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeIsNotNull() {
            addCriterion("FABUPX_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeEqualTo(String value) {
            addCriterion("FABUPX_TIME =", value, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeNotEqualTo(String value) {
            addCriterion("FABUPX_TIME <>", value, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeGreaterThan(String value) {
            addCriterion("FABUPX_TIME >", value, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeGreaterThanOrEqualTo(String value) {
            addCriterion("FABUPX_TIME >=", value, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeLessThan(String value) {
            addCriterion("FABUPX_TIME <", value, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeLessThanOrEqualTo(String value) {
            addCriterion("FABUPX_TIME <=", value, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeLike(String value) {
            addCriterion("FABUPX_TIME like", value, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeNotLike(String value) {
            addCriterion("FABUPX_TIME not like", value, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeIn(List<String> values) {
            addCriterion("FABUPX_TIME in", values, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeNotIn(List<String> values) {
            addCriterion("FABUPX_TIME not in", values, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeBetween(String value1, String value2) {
            addCriterion("FABUPX_TIME between", value1, value2, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andFabupxTimeNotBetween(String value1, String value2) {
            addCriterion("FABUPX_TIME not between", value1, value2, "fabupxTime");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlIsNull() {
            addCriterion("artcle_url is null");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlIsNotNull() {
            addCriterion("artcle_url is not null");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlEqualTo(String value) {
            addCriterion("artcle_url =", value, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlNotEqualTo(String value) {
            addCriterion("artcle_url <>", value, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlGreaterThan(String value) {
            addCriterion("artcle_url >", value, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlGreaterThanOrEqualTo(String value) {
            addCriterion("artcle_url >=", value, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlLessThan(String value) {
            addCriterion("artcle_url <", value, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlLessThanOrEqualTo(String value) {
            addCriterion("artcle_url <=", value, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlLike(String value) {
            addCriterion("artcle_url like", value, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlNotLike(String value) {
            addCriterion("artcle_url not like", value, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlIn(List<String> values) {
            addCriterion("artcle_url in", values, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlNotIn(List<String> values) {
            addCriterion("artcle_url not in", values, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlBetween(String value1, String value2) {
            addCriterion("artcle_url between", value1, value2, "artcleUrl");
            return (Criteria) this;
        }

        public Criteria andArtcleUrlNotBetween(String value1, String value2) {
            addCriterion("artcle_url not between", value1, value2, "artcleUrl");
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