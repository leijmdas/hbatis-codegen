/*    */ package com.pitaya.codegen.util;
/*    */ 
/*    */

import javax.swing.*;
import java.awt.*;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class UIUtil
/*    */ {
/*    */   public static JComponent createField(String lableStr, Component cmp) {
/* 14 */     SpringLayout layout = new SpringLayout();
/* 15 */     JPanel panel = new JPanel();
/* 16 */     panel.setLayout(layout);
/* 17 */     panel.setMaximumSize(new Dimension(700, 30));
/* 18 */     panel.setMinimumSize(new Dimension(300, 30));
/*    */ 
/*    */     
/* 21 */     JLabel label = new JLabel(lableStr);
/* 22 */     label.setPreferredSize(new Dimension(70, 30));
/* 23 */     panel.add(label);
/*    */ 
/*    */     
/* 26 */     panel.add(cmp);
/*    */     
/* 28 */     Spring st = Spring.constant(10);
/*    */ 
/*    */     
/* 31 */     layout.putConstraint("West", label, st, "West", panel);
/*    */     
/* 33 */     layout.putConstraint("East", cmp, st, "East", panel);
/*    */     
/* 35 */     layout.putConstraint("West", cmp, st, "East", label);
/*    */ 
/*    */     
/* 38 */     return panel;
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\util\UIUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */