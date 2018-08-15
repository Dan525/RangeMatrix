/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rangematrix;

import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.UIManager;

/**
 *
 * @author daniil_pozdeev
 */
public class MultilevelTable extends JComponent {

    private RangeMatrixModel model;
    private int columnGroupCount;
    
    public MultilevelTable(RangeMatrixModel model) {
        init(model);
    }

    MultilevelTable() {
        
    }
    
    private void init(RangeMatrixModel model) {
        this.model = model;
        //updateUI();
    }
    
    @Override
    public void updateUI() {
        setUI(UIManager.getUI(this));
        invalidate();
    }
    
    public RangeMatrixModel getModel() {
        return model;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        Object parent = model.getRoot();
        int groupCount = model.getColumnGroupCount(parent);
        int columnCount;
        
        String groupName;
        String columnName;
        
        FontMetrics fm = g.getFontMetrics();
        int cellX = 0;
        int cellY = 0;
        int cellWidth;
//        g.drawRect(cellX, cellY, getWidth(), fm.getHeight() + 6);
//        g.drawString(s, cellX + 3, cellY + 15);
        
        //Draw column groups
        for (int i = 0; i < groupCount; i++) {
            Object columnGroup = model.getColumnGroup(parent, i);
            groupName = model.getColumnGroupName(columnGroup);
            cellWidth = getWidth() / groupCount;
            cellX = i * getWidth() / groupCount;
            cellY = 0;
            g.drawRect(cellX, cellY, cellWidth, fm.getHeight() + 6);
            g.drawString(groupName, cellX + 3, cellY + 15);
            columnCount = model.getColumnCount(columnGroup);
            for (int j = 0; j < columnCount; j++) {
                Object column = model.getColumn(parent, j);
                columnName = model.getColumnName(column, j);
                cellWidth = cellWidth / columnCount;
                cellX = i * cellWidth;
                cellY = (fm.getHeight() + 6);
                g.drawRect(cellX, cellY, cellWidth, fm.getHeight() + 6);
                g.drawString(groupName, cellX + 3, cellY + 15);
            }
            
        }
    }
}
