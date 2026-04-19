package com.bu.admin.repository;


import jakarta.persistence.Query;
import lombok.Data;
import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;

import java.util.Date;
import java.util.LinkedList;


@Data
public class IfQuery {
    private final LinkedList<PreparedField> stmtList = new LinkedList<>();
    private int pageSize = 0;
    private int pageNum = 0;
    private String[] sortFields;
    private boolean entityMap;


    public Query fillQueryStmt(Query query, boolean count) {
        for (PreparedField preparedField : this.stmtList) {
            preparedField.setQueryStmt(query);
        }
        if (!count && this.entityMap) {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        return query;
    }

    public void setString(String name, String value) {
        this.stmtList.add(new PreparedField(name, 3, value));
    }

    public void setString(int nIndex, String value) {
        this.stmtList.add(new PreparedField(nIndex, 3, value));
    }

    public void setInt(String name, int value) {
        this.stmtList.add(new PreparedField(name, 0, value));
    }

    public void setInt(int nIndex, String value) {
        this.stmtList.add(new PreparedField(nIndex, 0, value));
    }

    public void setShort(String name, short value) {
        this.stmtList.add(new PreparedField(name, 2, value));
    }

    public void setShort(int nIndex, String value) {
        this.stmtList.add(new PreparedField(nIndex, 2, value));
    }

    public void setLong(String name, long value) {
        this.stmtList.add(new PreparedField(name, 1, value));
    }

    public void setLong(int nIndex, String value) {
        this.stmtList.add(new PreparedField(nIndex, 1, value));
    }

    public void setDouble(String name, double value) {
        this.stmtList.add(new PreparedField(name, 4, value));
    }

    public void setDouble(int nIndex, String value) {
        this.stmtList.add(new PreparedField(nIndex, 4, value));
    }

    public void setFloat(String name, float value) {
        this.stmtList.add(new PreparedField(name, 5, value));
    }

    public void setFloat(int nIndex, String value) {
        this.stmtList.add(new PreparedField(nIndex, 5, value));
    }

    public void setDate(String name, Date value) {
        this.stmtList.add(new PreparedField(name, 6, value));
    }

    public void setDate(int nIndex, Date value) {
        this.stmtList.add(new PreparedField(nIndex, 6, value));
    }

    public void setArray(String name, Object[] value) {
        this.stmtList.add(new PreparedField(name, 7, value));
    }

    public void setObject(int nIndex, Object obj) {
        this.stmtList.add(new PreparedField(nIndex, 8, obj));
    }

    public void setObject(String name, Object obj) {
        this.stmtList.add(new PreparedField(name, 8, obj));
    }

    public String[] getSortFields() {
        return this.sortFields;
    }

    public void setSortFields(String[] sortFields) {
        this.sortFields = sortFields;
    }


    private class PreparedField {
        private String name;
        private int type;
        private int index;
        private int intValue;
        private short shortValue;
        private long longValue;
        private double doubleValue;
        private float floatValue;
        private String stringValue;
        private Date dateValue;
        private Object[] arrayValue;
        private Object objValue;


        private void setStringField(int type, String v) {
            switch (type) {
                case 0:
                    this.intValue = Integer.parseInt(v);
                    break;
                case 1:
                    this.longValue = Long.parseLong(v);
                    break;
                case 2:
                    this.shortValue = Short.parseShort(v);
                    break;
                case 4:
                    this.doubleValue = Double.parseDouble(v);
                    break;
                case 5:
                    this.floatValue = Float.parseFloat(v);
                    break;
                default:
                    break;
            }

        }

        private void setLongField(int type, long v) {
            switch (type) {
                case 0:
                    this.intValue = (int) v;
                    break;
                case 1:
                    this.longValue = v;
                    break;
                case 2:
                    this.shortValue = (short) ((int) v);
                    break;
                default:
                    break;
            }

        }

        private void setDoubleField(int type, double v) {
            switch (type) {
                case 4:
                    this.doubleValue = v;
                    break;
                case 5:
                    this.floatValue = (float) v;
                    break;
                default:
                    break;
            }

        }

        private void setDateField( Date v) {
            this.dateValue = v;
        }

        private void setArrayField(Object[] v) {
            this.arrayValue = v;
        }

        public void setObjectField(Object v) {
            this.objValue = v;
        }

        private void setQueryStmt(Query query) {
            switch (this.type) {
                case 0:
                    if (this.name == null) {
                        query.setParameter(this.index, this.intValue);
                    } else {
                        query.setParameter(this.name, this.intValue);
                    }
                    break;
                case 1:
                    if (this.name == null) {
                        query.setParameter(this.index, this.longValue);
                    } else {
                        query.setParameter(this.name, this.longValue);
                    }
                    break;
                case 2:
                    if (this.name == null) {
                        query.setParameter(this.index, this.shortValue);
                    } else {
                        query.setParameter(this.name, this.shortValue);
                    }
                    break;
                case 4:
                    if (this.name == null) {
                        query.setParameter(this.index, this.doubleValue);
                    } else {
                        query.setParameter(this.name, this.doubleValue);
                    }
                    break;
                case 5:
                    setFloatValue(query);
                    break;
                case 6:
                    setDateValue(query);
                    break;
                case 7:
                    setArrayValue(query);
                    break;
                case 8:
                    setObjectValue(query);
                    break;
                default:
                    setStringValue(query);
                    break;
            }
        }

        private void setFloatValue(Query query) {
            if (this.name == null) {
                query.setParameter(this.index, this.floatValue);
            } else {
                query.setParameter(this.name, this.floatValue);
            }
        }

        private void setDateValue(Query query) {
            if (this.name == null) {
                query.setParameter(this.index, this.dateValue);
            } else {
                query.setParameter(this.name, this.dateValue);
            }
        }

        private void setArrayValue(Query query) {
            if (this.name != null) {
                query.setParameter(this.name, this.arrayValue);
            }
        }

        private void setObjectValue(Query query) {
            if (this.name == null) {
                query.setParameter(this.index, this.objValue);
            } else {
                query.setParameter(this.name, this.objValue);
            }
        }

        private void setStringValue(Query query) {
            if (this.name == null) {
                query.setParameter(this.index, this.stringValue);
            } else {
                query.setParameter(this.name, this.stringValue);
            }
        }


        private PreparedField(String name, int type, String value) {
            this.name = name;
            this.index = -1;
            this.type = type;
            this.stringValue = value;
            this.setStringField(type, value);
        }

        private PreparedField(int index, int type, String value) {
            this.index = index;
            this.type = type;
            this.stringValue = value;
            this.setStringField(type, value);
        }


        private PreparedField(String name, int type, long value) {
            this.name = name;
            this.index = -1;
            this.type = type;
            this.stringValue = "" + value;
            this.setLongField(type, value);
        }


        private PreparedField(String name, int type, double value) {
            this.name = name;
            this.index = -1;
            this.type = type;
            this.stringValue = "" + value;
            this.setDoubleField(type, value);
        }

        private PreparedField(int index, int type, Date value) {
            this.index = index;
            this.type = type;
            this.dateValue = value;
            this.setDateField(value);
        }

        private PreparedField(String name, int type, Date value) {
            this.name = name;
            this.index = -1;
            this.type = type;
            this.dateValue = value;
            this.setDateField(value);
        }

        private PreparedField(int index, int type, Object value) {
            this.index = index;
            this.type = type;
            this.objValue = value;
            this.setObjectField(value);
        }

        private PreparedField(String name, int type, Object value) {
            this.name = name;
            this.index = -1;
            this.type = type;
            this.objValue = value;
            this.setObjectField(value);
        }

        private PreparedField(String name, int type, Object[] value) {
            this.name = name;
            this.index = -1;
            this.type = type;
            this.arrayValue = value;
            this.setArrayField(value);
        }

    }
}
