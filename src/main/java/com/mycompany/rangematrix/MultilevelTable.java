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
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author daniil_pozdeev
 */
public class MultilevelTable extends JComponent {

    private RangeMatrixModel model;
    private int columnGroupCount;
    private ArrayList<Cell> cells = new ArrayList<>();

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
    float lowestPointHeader = 0;
    int columnCounter = 0;
    
    public float setLowestPoint(float y, float cellHeight) {
        if (y > lowestPointHeader) {
            lowestPointHeader = y + cellHeight;
            return y;
        } else {
            return lowestPointHeader + cellHeight;
        }
    }

    public Cell getCell(int col, int row) {
        for (Cell cell : cells) {
            if (cell.getCol() == col && cell.getRow() == row) {
                return cell;
            }
        }
        return null;
    }
    
    public void drawColumns(Object parent, FontMetrics fm, Graphics2D g2d, float parentCellX, float parentCellWidth, float cellHeight) {
        int columnCount = model.getColumnGroupCount(parent);        
        
        for (int i = 0; i < columnCount; i++) {
            Object child = model.getColumnGroup(parent, i);
            String columnName = model.getColumnGroupName(child);
            
            float cellWidth = parentCellWidth / columnCount;
            float cellX = parentCellX + i * cellWidth;
            float cellY = rowCounter * cellHeight;
            setLowestPoint(cellY, cellHeight);
            
            Rectangle2D rect = new Rectangle2D.Float(cellX, cellY, cellWidth, cellHeight);
            Cell cell = new Cell(rect, i, rowCounter);
            cells.add(cell);
            g2d.draw(cell.getRect());
            g2d.drawString(columnName, cellX + cellWidth/2 - fm.stringWidth(columnName)/2, cellY + 15);
            
            boolean isGroup = model.isColumnGroup(child);            
            if (isGroup) {
                rowCounter++;
                drawColumns(child, fm, g2d, cellX, cellWidth, cellHeight);
                rowCounter--;
            } else {
                for (int k = 0; k < model.getRowCount(); k++) {
                    Rectangle2D rectRow = new Rectangle2D.Float(cellX, lowestPointHeader + k*cellHeight, cellWidth, cellHeight);
                    g2d.draw(rectRow);
                }
            }
        }
    }
    
    public void drawCell(FontMetrics fm, Graphics2D g2d) {
        int rowCount = model.getRowGroupCount();
        
        
        
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fm = g2d.getFontMetrics();

        Object parent = ((Model)model).getColumnRoot();
        float parentCellWidth = getWidth() - 200;
        float cellHeight = fm.getHeight() + 6;
        
        drawColumns(parent, fm, g2d, 200, parentCellWidth, cellHeight);
        System.out.println(getCell(3,2).getX());
        
    }
}
