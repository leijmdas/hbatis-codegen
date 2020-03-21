/*    */ package com.pitaya.codegen.util;
/*    */ 
/*    */

/*    */
/*    */

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public final class DefaultJdbcTypeRegistry {
    private static final Map<JdbcType, Class<?>> jdbcTypeMap = new HashMap<JdbcType, Class<?>>() {
        private static final long serialVersionUID = -5302727745166102178L;

        {
            this.put(JdbcType.INTEGER, Integer.class);
            this.put(JdbcType.BIGINT, Long.class);
            this.put(JdbcType.SMALLINT, Short.class);
            this.put(JdbcType.FLOAT, Float.class);
            this.put(JdbcType.DOUBLE, Double.class);
            this.put(JdbcType.DECIMAL, BigDecimal.class);
            this.put(JdbcType.VARCHAR, String.class);
            this.put(JdbcType.TINYINT, Byte.class);
            this.put(JdbcType.BOOLEAN, Boolean.class);
            this.put(JdbcType.TIMESTAMP, Date.class);
            this.put(JdbcType.DATE, Date.class);
            this.put(JdbcType.BLOB, byte[].class);
            this.put(JdbcType.CHAR, String.class);
            this.put(JdbcType.LONGVARCHAR, String.class);
            this.put(JdbcType.BIT, Boolean.class);
            this.put(JdbcType.LONGVARBINARY, byte[].class);
        }
    };

    public DefaultJdbcTypeRegistry() {
    }

    public static Class<?> getDefaultJavaType(JdbcType jdbcType) {
        return (Class)jdbcTypeMap.get(jdbcType);
    }
}
