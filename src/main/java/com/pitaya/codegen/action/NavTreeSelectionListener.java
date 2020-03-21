/*    */ package com.pitaya.codegen.action;
/*    */ 
/*    */

import com.pitaya.codegen.AppContext;
import com.pitaya.codegen.ui.NavTreePanel;
import com.pitaya.codegen.ui.TableGridPanel;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class NavTreeSelectionListener
/*    */   implements TreeSelectionListener
/*    */ {
/*    */   private NavTreePanel treePanel;
/*    */   
/* 14 */   public NavTreeSelectionListener(NavTreePanel navTreePanel) { this.treePanel = navTreePanel; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void valueChanged(TreeSelectionEvent e) {
/* 19 */     Object treeNode = e.getNewLeadSelectionPath().getLastPathComponent();
/* 20 */     if (treeNode instanceof NavTreePanel.DBTableTreeNode) {
/* 21 */       NavTreePanel.DBTableTreeNode dbTreeNode = (NavTreePanel.DBTableTreeNode)treeNode;
/*    */ 
/*    */ 
/*    */       
/* 25 */       TableGridPanel gridPanel = (TableGridPanel)AppContext.activePanel(TableGridPanel.class);
/*    */       
/* 27 */       gridPanel.loadTableMetas(dbTreeNode.getTable());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codegen\action\NavTreeSelectionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */