/*    */ package com.pitaya.codegen.util;
/*    */ 
/*    */

import com.pitaya.codegen.ui.AbstractDialog;

import java.util.HashMap;
import java.util.Map;

/*    */
/*    */

/*    */
/*    */ 
/*    */ public class UIFactory
/*    */ {
/* 10 */   public static Map<Class<?>, AbstractDialog> diagCache = new HashMap<>();
/*    */ 
/*    */   
/*    */   public static <T extends AbstractDialog> T instanceDiag(Class<T> clazz) {
/*    */     try {
/* 15 */       if (diagCache.containsKey(clazz)) {
/* 16 */         return (T)diagCache.get(clazz);
/*    */       }
/* 18 */       AbstractDialog abstractDialog = (AbstractDialog)clazz.newInstance();
/* 19 */       diagCache.put(clazz, abstractDialog);
/* 20 */       return (T)abstractDialog;
/* 21 */     } catch (Exception e) {
/* 22 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\util\UIFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */