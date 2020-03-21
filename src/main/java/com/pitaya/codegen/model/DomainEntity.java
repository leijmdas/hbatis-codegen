/*    */ package com.pitaya.codegen.model;
/*    */ 
/*    */

import java.util.ArrayList;
import java.util.List;

/*    */

/*    */
/*    */ 
/*    */ public class DomainEntity
/*    */   extends BaseEntity
/*    */ {
/*    */   private String tableRemark;
/*    */   private String tableName;
/*    */   private String simpleTableName;
/* 13 */   private List<EntityField> fields = new ArrayList<>();
/*    */ 
/*    */   
/* 16 */   private List<EntityField> primaryKeys = new ArrayList<>();
/*    */ 
/*    */   
/*    */   private String pkClassName;
/*    */ 
/*    */ 
/*    */   
/* 23 */   public String getPkClassName() { return this.pkClassName; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public void setPkClassName(String pkClassName) { this.pkClassName = pkClassName; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String getTableName() { return this.tableName; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setTableName(String tableName) { this.tableName = tableName; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public List<EntityField> getFields() { return this.fields; }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public void setFields(List<EntityField> fields) { this.fields = fields; }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public List<EntityField> getPrimaryKeys() { return this.primaryKeys; }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void setPrimaryKeys(List<EntityField> primaryKeys) { this.primaryKeys = primaryKeys; }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public String getSimpleTableName() { return this.simpleTableName; }
/*    */ 
/*    */ 
/*    */   
/* 59 */   public void setSimpleTableName(String simpleTableName) { this.simpleTableName = simpleTableName; }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public String getTableRemark() { return this.tableRemark; }
/*    */ 
/*    */ 
/*    */   
/* 67 */   public void setTableRemark(String tableRemark) { this.tableRemark = tableRemark; }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\model\DomainEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */