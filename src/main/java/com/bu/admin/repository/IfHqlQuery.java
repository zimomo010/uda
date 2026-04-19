package com.bu.admin.repository;


public class IfHqlQuery extends IfQuery{
    private String hql = null;
    private String countHql = null;

    public IfHqlQuery() {
    }

    public IfHqlQuery(String hql) {
        this.hql = hql;
    }

    public IfHqlQuery(String hql, String countHql) {
        this.hql = hql;
        this.countHql = countHql;
    }

    public void setHQL(String hql) {
        this.hql = hql;
    }

    public void setCountHQL(String countHql) {
        this.countHql = countHql;
    }

    public String getHQL() {
        return this.hql;
    }

    public String getCountHQL() {
        if (this.countHql != null) {
            return this.countHql;
        } else if (this.getHQL() == null) {
            return null;
        } else {
            String hqlOri = this.getHQL().trim();
            String hqlTmp = this.getHQL().trim().toLowerCase();
            int nPos = hqlTmp.lastIndexOf(" order ");
            if (nPos >= 0) {
                hqlOri = hqlOri.substring(0, nPos);
            }

            if (hqlTmp.startsWith("from")) {
                return "select count(*) " + hqlOri;
            } else {
                nPos = hqlTmp.indexOf(" from ");
                hqlOri = hqlOri.substring(nPos);
                return "select count(*) " + hqlOri;
            }
        }
    }


}
