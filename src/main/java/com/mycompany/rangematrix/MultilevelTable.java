/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rangematrix;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
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

    

    
    

    

    
//    int cellX;
//    int cellY;
    
    
    int rowCounter = 0;

    public void drawColumn(Object parent, FontMetrics fm, Graphics2D g2d, float parentCellWidth, int parentCellNumber, float cellHeight) {
        int columnCount = model.getColumnGroupCount(parent);        
        for (int i = 0; i < columnCount; i++) {
            Object child = model.getColumnGroup(parent, i);
            String columnName = model.getColumnGroupName(child);
            float cellWidth = parentCellWidth / columnCount;
            float cellX = parentCellNumber * parentCellWidth + i * cellWidth;
            float cellY = rowCounter * cellHeight;
            Rectangle2D rect = new Rectangle2D.Float(cellX, cellY, cellWidth, cellHeight);
            g2d.draw(rect);
            g2d.drawString(columnName, cellX + cellWidth / 2 - fm.stringWidth(columnName) / 2, cellY + 15);
            boolean isGroup = model.isColumnGroup(child);
            if (isGroup) {
                rowCounter++;
                drawColumn(child, fm, g2d, cellWidth, i, cellHeight);
                rowCounter--;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fm = g2d.getFontMetrics();

        Object parent = model.getRoot();
        float parentCellWidth = getWidth();
        int cellHeight = fm.getHeight() + 6;
        
        drawColumn(parent, fm, g2d, parentCellWidth, 0, cellHeight);
    }
}
