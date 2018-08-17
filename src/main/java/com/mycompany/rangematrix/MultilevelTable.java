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
import java.util.HashMap;
import java.util.Map;
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
    
    
    int rc;
    int rowCounter = 0;
    float lowestPointHeader = 0;
    int columnCounter = 0;
    float sidePanelWidth = 200;
    
    ArrayList<Float> cellXList = new ArrayList<>();
    ArrayList<Float> cellYList = new ArrayList<>();
    ArrayList<Float> cellWidthList = new ArrayList<>();
    ArrayList<Float> cellHeightList = new ArrayList<>();
    
    
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
    
    ArrayList<Integer> maxes = new ArrayList<>();
    int maxLevel = 0;
    int newMaxLevel;
    
    public void getMaxLevel(Object parentColumn) {
        int columnCount = model.getColumnGroupCount(parentColumn);
        maxLevel++;
        for (int i = 0; i < columnCount; i++) {
            
            Object child = model.getColumnGroup(parentColumn, i);
            boolean isGroup = model.isColumnGroup(child);            
            if (isGroup) {
                getMaxLevel(child);
            }            
        }
        maxes.add(maxLevel);
    }
    
    public void drawColumns(Object parentColumn, FontMetrics fm, Graphics2D g2d, float parentCellX, float parentCellWidth, float cellHeight) {
        int columnCount = model.getColumnGroupCount(parentColumn);        
        
        for (int i = 0; i < columnCount; i++) {
            Object child = model.getColumnGroup(parentColumn, i);
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
//                g2d.drawLine((int)cellX, (int)lowestPointHeader, (int)cellX, getHeight());
//                cellXList.add(cellX);
//                cellWidthList.add(cellWidth);
            }
        }
    }
    
    public void getRowsAbsoluteCount(Object parentRow) {
        int rowCount = model.getRowGroupCount(parentRow);        
        
        for (int i = 0; i < rowCount; i++) {
            Object child = model.getRowGroup(parentRow, i);
            
            boolean isGroup = model.isRowGroup(child);            
            if (isGroup) {
                getRowsAbsoluteCount(child);
            } else {
                rc += rowCount;
            }
        }
    }
    
    public void drawRows(Object parentRow, FontMetrics fm, Graphics2D g2d, float parentCellY, float parentCellHeight, float cellWidth) {
        
        int rowCount = model.getRowGroupCount(parentRow);        
        
        for (int i = 0; i < rowCount; i++) {
            Object child = model.getRowGroup(parentRow, i);
            String rowName = model.getRowGroupName(child);
            
            
            float cellHeight = parentCellHeight / (rowCount);
            float cellX = columnCounter * cellWidth;
            float cellY = parentCellY + i * cellHeight;
            
            Rectangle2D rect = new Rectangle2D.Float(cellX, cellY, cellWidth, cellHeight);
            g2d.draw(rect);
            g2d.drawString(rowName, cellX + cellWidth/2 - fm.stringWidth(rowName)/2, cellY + 15);
            
            boolean isGroup = model.isRowGroup(child);            
            if (isGroup) {
                columnCounter++;
                drawRows(child, fm, g2d, cellY, cellHeight, cellWidth);
                columnCounter--;
            } else {
                g2d.drawLine((int)sidePanelWidth, (int)cellY, getWidth(), (int)cellY);
                cellYList.add(cellY);
                cellHeightList.add(cellHeight);
            }
        }
    }
    
    public void drawValues(FontMetrics fm, Graphics2D g2d){
        
        for (int i = 0; i < cellYList.size(); i++) {
            for (int j = 0; j < cellXList.size(); j++) {
                String value = (model.getValueAt(j, i)).toString();
                g2d.drawString(value, cellXList.get(j) + cellWidthList.get(j)/2 - fm.stringWidth(value)/2, cellYList.get(i) + 15);
            }            
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fm = g2d.getFontMetrics();

        Object parentColumn = ((Model)model).getColumnRoot();
        float parentCellWidth = getWidth();
        float cellHeight = fm.getHeight() + 6;
        
        drawColumns(parentColumn, fm, g2d, 0, parentCellWidth, cellHeight);
        getMaxLevel(parentColumn);
        System.out.println(maxLevel);
//        Object parentRow = ((Model)model).getRowRoot();
//        float parentCellHeight = getHeight() - lowestPointHeader;
//        float cellWidth = 100;
//        
//        
//        drawRows(parentRow, fm, g2d, lowestPointHeader, parentCellHeight, cellWidth);
//        
//        drawValues(fm, g2d);
        
    }
}
