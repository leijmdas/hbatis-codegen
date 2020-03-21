/*    */ package com.pitaya.codegen.ui;
/*    */ 
/*    */

import com.pitaya.codegen.action.NavTreeSelectionListener;
import com.pitaya.codegen.model.TableDTO;
import com.pitaya.codegen.util.DBHelper;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.List;

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

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NavTreePanel
/*    */   extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 25 */   private NavTreeSelectionListener treeSelectionListener = new NavTreeSelectionListener(this);
/* 26 */   private JTree tree = new JTree(new DefaultMutableTreeNode("默认项目"));
/*    */   
/*    */   public NavTreePanel() {
/* 29 */     setLayout(new BorderLayout());
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
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     JScrollPane scrollPanel = new JScrollPane();
/* 47 */     scrollPanel.setAutoscrolls(true);
/* 48 */     scrollPanel.getViewport().add(this.tree);
/* 49 */     scrollPanel.setMinimumSize(new Dimension(200, 300));
/* 50 */     scrollPanel.setMaximumSize(new Dimension(400, 300));
/*    */     
/* 52 */     this.tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("默认项目")));
/*    */     
/* 54 */     add(scrollPanel, "Center");
/*    */     
/* 56 */     this.tree.addTreeSelectionListener((TreeSelectionListener)this.treeSelectionListener);
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadTables() {
/* 61 */     List<TableDTO> tables = DBHelper.getTables();
/* 62 */     DefaultTreeModel treeModel = (DefaultTreeModel)this.tree.getModel();
/* 63 */     DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)treeModel.getRoot();
/*    */     
/* 65 */     DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode("数据表(" + tables.size() + "个)");
/* 66 */     for (TableDTO table : tables) {
/* 67 */       tableNode.add(new DBTableTreeNode(table));
/*    */     }
/*    */     
/* 70 */     rootNode.add(tableNode);
/* 71 */     rootNode.add(new DefaultMutableTreeNode("实体类"));
/*    */     
/* 73 */     this.tree.expandPath(new TreePath(rootNode));
/*    */   }
/*    */   
/*    */   public static class DBTableTreeNode extends DefaultMutableTreeNode {
/*    */     private TableDTO table;
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public DBTableTreeNode(TableDTO table) {
/* 81 */       super(String.valueOf(table.getName()) + "【" + table.getRemark() + "】");
/* 82 */       this.table = table;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 87 */     public TableDTO getTable() { return this.table; }
/*    */   }
/*    */ }


/* Location:              L:\dongxw\Git工具dmp\hbatis-codegen.jar!\com\pitaya\codege\\ui\NavTreePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */