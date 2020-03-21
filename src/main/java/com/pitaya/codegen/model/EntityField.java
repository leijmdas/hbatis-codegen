/*    */ package com.pitaya.codegen.model;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityField
/*    */ {
/*    */   private String name;
/*    */   private String propType;
/*    */   private String colTypeName;
/*    */   private String colName;
/*    */   private Boolean nullable;
/*    */   private Integer length;
/*    */   private String comment;
/*    */   private Boolean autoIncrement;
/*    */   private Boolean primaryKey;
/*    */   
/* 27 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void setName(String name) { this.name = name; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public String getPropType() { return this.propType; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public void setPropType(String propType) { this.propType = propType; }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public String getColName() { return this.colName; }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public void setColName(String colName) { this.colName = colName; }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public Boolean getNullable() { return this.nullable; }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public void setNullable(Boolean nullable) { this.nullable = nullable; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public Integer getLength() { return this.length; }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public void setLength(Integer length) { this.length = length; }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public String getComment() { return this.comment; }
/*    */ 
/*    */ 
/*    */   
/* 72 */   public void setComment(String comment) { this.comment = comment; }
/*    */ 
/*    */ 
/*    */   
/* 76 */   public Boolean getAutoIncrement() { return this.autoIncrement; }
/*    */ 
/*    */ 
/*    */   
/* 80 */   public void setAutoIncrement(Boolean autoIncrement) { this.autoIncrement = autoIncrement; }
/*    */ 
/*    */ 
/*    */   
/* 84 */   public Boolean getPrimaryKey() { return this.primaryKey; }
/*    */ 
/*    */ 
/*    */   
/* 88 */   public void setPrimaryKey(Boolean primaryKey) { this.primaryKey = primaryKey; }
/*    */ 
/*    */ 
/*    */   
/* 92 */   public String getColTypeName() { return this.colTypeName; }
/*    */ 
/*    */ 
/*    */   
/* 96 */   public void setColTypeName(String colTypeName) { this.colTypeName = colTypeName; }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\model\EntityField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */