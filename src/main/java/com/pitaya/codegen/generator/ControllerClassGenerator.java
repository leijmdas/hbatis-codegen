/*    */ package com.pitaya.codegen.generator;
/*    */ 
/*    */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.model.DaoEntity;
import com.pitaya.codegen.model.DomainEntity;
import com.pitaya.codegen.model.ServiceEntity;
import com.pitaya.codegen.model.ServiceImplEntity;
import com.pitaya.codegen.util.TemplateHelper;
import freemarker.template.Template;

import javax.swing.*;
import java.io.File;

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
/*    */ public class ControllerClassGenerator
/*    */ {
/*    */   public void generate(String tableName, String entityName, String entityPkg, String daoPkg, String servicePkg, String controllerPkg) {
/* 19 */     Template tpl = TemplateHelper.getInstance().getTemplate("controller.java.tpl");
/*    */     
/* 21 */     DomainEntity domainEntity = EntityClassGenerator.getDomainEntity(tableName, entityName, entityPkg);
/*    */     
/* 23 */     DaoEntity daoEntity = MapperClassGenerator.getDaoEntity(domainEntity, daoPkg);
/*    */     
/* 25 */     ServiceEntity serviceEntity = ServiceClassGenerator.getServiceEntity(daoEntity, servicePkg);
/*    */     
/* 27 */     serviceEntity.addImport(serviceEntity.getFullname());
/*    */ 
/*    */ 
/*    */     
/* 31 */     File dstFile = AppContext.getGenerateTargetFile(serviceEntity.getPkgName(), String.valueOf(serviceEntity.getClassName()) + ".java");
/*    */     
/*    */     try {
/* 34 */       TemplateHelper.write2File(tpl, serviceEntity, dstFile);
/* 35 */     } catch (Exception e) {
/* 36 */       e.printStackTrace();
/* 37 */       JOptionPane.showMessageDialog(null, "生成Service异常[tableName:" + tableName + "]:" + e.getMessage());
/*    */     } 
/*    */     
/* 40 */     ServiceImplEntity entity = new ServiceImplEntity();
/* 41 */     entity.setClassName(String.valueOf(serviceEntity.getClassName()) + "Impl");
/* 42 */     entity.setPkgName(String.valueOf(serviceEntity.getPkgName()) + ".impl");
/* 43 */     entity.setServiceEntity(serviceEntity);
/* 44 */     entity.setDomainEntity(domainEntity);
/* 45 */     entity.setDaoEntity(daoEntity);
/* 46 */     entity.addImport("org.springframework.beans.factory.annotation.Autowired");
/* 47 */     entity.addImport("org.springframework.stereotype.Service");
/* 48 */     entity.addImport(String.valueOf(entityPkg) + "." + domainEntity.getClassName());
/* 49 */     entity.addImport(String.valueOf(daoEntity.getPkgName()) + "." + daoEntity.getClassName());
/* 50 */     entity.addImport(String.valueOf(serviceEntity.getPkgName()) + "." + serviceEntity.getClassName());
/*    */     
/* 52 */     tpl = TemplateHelper.getInstance().getTemplate("service.impl.java.tpl");
/*    */     
/* 54 */     dstFile = AppContext.getGenerateTargetFile(entity.getPkgName(), String.valueOf(entity.getClassName()) + ".java");
/*    */     
/*    */     try {
/* 57 */       TemplateHelper.write2File(tpl, entity, dstFile);
/* 58 */     } catch (Exception e) {
/* 59 */       e.printStackTrace();
/* 60 */       JOptionPane.showMessageDialog(null, "生成ServiceImpl异常[tableName:" + tableName + "]:" + e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\generator\ControllerClassGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */