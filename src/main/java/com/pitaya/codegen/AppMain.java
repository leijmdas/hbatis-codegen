/*    */ package com.pitaya.codegen;
/*    */ 
/*    */

import com.pitaya.codegen.ui.MainFrame;

import javax.swing.*;

/*    */

/*    */
/*    */ 
/*    */ public class AppMain
/*    */ {
/*    */   public static void main(String[] args) {
/*    */     try {
/* 11 */       AppContext.init();
/*    */     }
/* 13 */     catch (Exception e) {
/* 14 */       e.printStackTrace();
/* 15 */       JOptionPane.showMessageDialog(null, "初始化程序异常:" + e.getMessage());
/*    */     } 
/* 17 */     System.out.println("config properties:" + AppContext.getProps());
/* 18 */     MainFrame main = new MainFrame(1024, 768, "Hbatis代码生成工具V2.0");
/* 19 */     AppContext.setApp(main);
/*    */     
/* 21 */     main.setVisible(true);
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\AppMain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */