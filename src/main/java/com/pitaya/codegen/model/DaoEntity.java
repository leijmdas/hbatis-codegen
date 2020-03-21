/*    */ package com.pitaya.codegen.model;
/*    */ 
/*    */ public class DaoEntity
/*    */   extends BaseEntity
/*    */ {
/*    */   private DomainEntity domainEntity;
/*  7 */   private String domainPKClassName = null;
/*    */ 
/*    */ 
/*    */   
/* 11 */   public DomainEntity getDomainEntity() { return this.domainEntity; }
/*    */ 
/*    */ 
/*    */   
/* 15 */   public void setDomainEntity(DomainEntity domainEntity) { this.domainEntity = domainEntity; }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public String getDomainPKClassName() { return this.domainPKClassName; }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public void setDomainPKClassName(String domainPKClassName) { this.domainPKClassName = domainPKClassName; }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\model\DaoEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */