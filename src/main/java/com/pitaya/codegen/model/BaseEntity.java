/*    */ package com.pitaya.codegen.model;
/*    */ 
/*    */

import com.pitaya.codegen.util.NameRuleUtil;

import java.util.ArrayList;
import java.util.List;

/*    */
/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaseEntity
/*    */ {
/*    */   private String pkgName;
/*    */   private String className;
/* 14 */   private List<String> imports = new ArrayList<>();
/*    */ 
/*    */   
/* 17 */   public String getPkgName() { return this.pkgName; }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public void setPkgName(String pkgName) { this.pkgName = pkgName; }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public String getClassName() { return this.className; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void setClassName(String className) { this.className = className; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public List<String> getImports() { return this.imports; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public void setImports(List<String> imports) { this.imports = imports; }
/*    */ 
/*    */   
/*    */   public boolean hasImport(String im) {
/* 41 */     for (String tmp : this.imports) {
/* 42 */       if (tmp.contains(im)) {
/* 43 */         return true;
/*    */       }
/*    */     } 
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public void addImport(String im) {
/* 50 */     if (!hasImport(im)) {
/* 51 */       this.imports.add(im);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 56 */   public String getFieldMethodName(String fieldName) { return NameRuleUtil.getClassFieldMethodName(fieldName); }
/*    */ 
/*    */   
/*    */   public String getFullname() {
/* 60 */     if (this.pkgName == null || this.pkgName.equals("")) {
/* 61 */       return this.className;
/*    */     }
/* 63 */     return String.valueOf(this.pkgName) + "." + this.className;
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\model\BaseEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */