/*    */ package com.pitaya.codegen.model;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServiceImplEntity
/*    */   extends BaseEntity
/*    */ {
/*    */   private DomainEntity domainEntity;
/*    */   private DaoEntity daoEntity;
/*    */   private ServiceEntity serviceEntity;
/*    */   
/* 13 */   public DomainEntity getDomainEntity() { return this.domainEntity; }
/*    */ 
/*    */ 
/*    */   
/* 17 */   public void setDomainEntity(DomainEntity domainEntity) { this.domainEntity = domainEntity; }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public DaoEntity getDaoEntity() { return this.daoEntity; }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public void setDaoEntity(DaoEntity daoEntity) { this.daoEntity = daoEntity; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public ServiceEntity getServiceEntity() { return this.serviceEntity; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void setServiceEntity(ServiceEntity serviceEntity) { this.serviceEntity = serviceEntity; }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\model\ServiceImplEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */