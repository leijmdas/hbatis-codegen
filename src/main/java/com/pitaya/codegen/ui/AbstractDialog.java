/*    */ package com.pitaya.codegen.ui;
/*    */ 
/*    */

import javax.swing.*;

/*    */
/*    */ public abstract class AbstractDialog
/*    */   extends JDialog {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public AbstractDialog() {
/* 10 */     setSize(450, 300);
/* 11 */     setLocationRelativeTo(null);
/* 12 */     setModal(true);
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\ui\AbstractDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */