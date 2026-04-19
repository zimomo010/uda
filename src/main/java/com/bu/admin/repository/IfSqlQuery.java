package com.bu.admin.repository;

import org.hibernate.query.NativeQuery;

import java.util.HashMap;


public class IfSqlQuery extends IfQuery{

    private String sql = null;
    private String countSql = null;
    private HashMap<String,Object> entityMap = null;

    public IfSqlQuery() {
    }

    public IfSqlQuery(String sql) {
        this.sql = sql;
    }

    public IfSqlQuery(String sql, String countSql) {
        this.sql = sql;
        this.countSql = countSql;
    }

    public String getSQL() {
        return this.sql;
    }

    public void setSQL(String sql) {
        this.sql = sql;
    }

    public void setCountSQL(String countSql) {
        this.countSql = countSql;
    }

    public <T> void addEntity(String objName, Class<T> className) {
        if (this.entityMap == null) {
            this.entityMap = new HashMap<>();
        }

        this.entityMap.put(objName, className);
    }

    public String getCountSQL() {
        if (this.countSql != null) {
            return this.countSql;
        } else if (this.getSQL() == null) {
            return null;
        } else {
            String sqlOri = this.getSQL().trim();
            String sqlTmp = this.getSQL().trim().toLowerCase();
            int nPos = sqlTmp.lastIndexOf(" order ");
            if (nPos >= 0) {
                sqlOri = sqlOri.substring(0, nPos);
            }

            nPos = sqlTmp.indexOf(" from ");
            sqlOri = sqlOri.substring(nPos);
            return "select count(*) AS COUNT " + sqlOri;
        }
    }

    public <T> NativeQuery<T> fillEntity(NativeQuery<T> query) {
        if (this.entityMap == null) {
            return query;
        } else {
            this.entityMap.keySet().forEach(objName -> {
                Class<?> className = (Class<?>) this.entityMap.get(objName);
                query.addEntity(objName, className);
            });

            return query;
        }
    }
}
