/*    */ package com.pitaya.codegen.ui;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CopyOfDatasourceSettingDiag
/*    */   extends AbstractDialog
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public CopyOfDatasourceSettingDiag() {
/* 22 */     setTitle("数据源设置");
/*    */     
/* 24 */     initUI();
/*    */   }
/*    */   
/*    */   private void initUI() {
/* 28 */     JLabel label1 = new JLabel("Find What:");
/* 29 */     JTextField textField1 = new JTextField();
/* 30 */     JCheckBox caseCheckBox = new JCheckBox("Match Case");
/* 31 */     JCheckBox wholeCheckBox = new JCheckBox("Whole Words");
/* 32 */     JCheckBox wrapCheckBox = new JCheckBox("Warp Around");
/* 33 */     JCheckBox backCheckBox = new JCheckBox("Search Backwards");
/* 34 */     JButton findButton = new JButton("Find");
/* 35 */     JButton cancelButton = new JButton("Cancel");
/*    */     
/* 37 */     Container c = getContentPane();
/* 38 */     GroupLayout layout = new GroupLayout(c);
/* 39 */     c.setLayout(layout);
/*    */ 
/*    */     
/* 42 */     layout.setAutoCreateGaps(true);
/* 43 */     layout.setAutoCreateContainerGaps(true);
/*    */ 
/*    */     
/* 46 */     GroupLayout.ParallelGroup hpg2a = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
/* 47 */     hpg2a.addComponent(caseCheckBox);
/* 48 */     hpg2a.addComponent(wholeCheckBox);
/*    */     
/* 50 */     GroupLayout.ParallelGroup hpg2b = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
/* 51 */     hpg2b.addComponent(wrapCheckBox);
/* 52 */     hpg2b.addComponent(backCheckBox);
/*    */     
/* 54 */     GroupLayout.SequentialGroup hpg2H = layout.createSequentialGroup();
/* 55 */     hpg2H.addGroup(hpg2a).addGroup(hpg2b);
/*    */     
/* 57 */     GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
/* 58 */     hpg2.addComponent(textField1);
/* 59 */     hpg2.addGroup(hpg2H);
/*    */     
/* 61 */     GroupLayout.ParallelGroup hpg3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
/* 62 */     hpg3.addComponent(findButton);
/* 63 */     hpg3.addComponent(cancelButton);
/*    */ 
/*    */     
/* 66 */     layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(label1).addGroup(hpg2).addGroup(hpg3));
/*    */ 
/*    */     
/* 69 */     layout.linkSize(0, new Component[] { findButton, cancelButton });
/*    */ 
/*    */ 
/*    */     
/* 73 */     GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
/* 74 */     vpg1.addComponent(label1);
/* 75 */     vpg1.addComponent(textField1);
/* 76 */     vpg1.addComponent(findButton);
/*    */     
/* 78 */     GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
/* 79 */     vpg2.addComponent(caseCheckBox);
/* 80 */     vpg2.addComponent(wrapCheckBox);
/* 81 */     vpg2.addComponent(cancelButton);
/*    */     
/* 83 */     GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
/* 84 */     vpg3.addComponent(wholeCheckBox);
/* 85 */     vpg3.addComponent(backCheckBox);
/*    */ 
/*    */     
/* 88 */     layout.setVerticalGroup(layout.createSequentialGroup().addGroup(vpg1).addGroup(vpg2).addGroup(vpg3));
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\ui\CopyOfDatasourceSettingDiag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */