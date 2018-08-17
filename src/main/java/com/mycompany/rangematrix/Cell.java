/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rangematrix;

import java.awt.geom.Rectangle2D;

/**
 *
 * @author daniil_pozdeev
 */
public class Cell {
    
    Rectangle2D rect;
    double x;
    double y;
    int row;
    int col;
    double width;
    double height;
    int heightMultiplier = 1;
    
    public Cell(Rectangle2D rect, int col, int row) {
        this.rect = rect;
        this.col = col;
        this.row = row;
        this.x = rect.getX();
        this.y = rect.getY();
        this.width = rect.getWidth();
        this.height = rect.getHeight();
    }

    public Rectangle2D getRect() {
        return rect;
    }

    public void setRect(Rectangle2D rect) {
        this.rect = rect;
    }
    
    public void multiplyHigh(int heightMultiplier) {
        double newHeight = this.getHeight() * heightMultiplier;
        this.setHeight(newHeight);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getHeightMultiplier() {
        return heightMultiplier;
    }

    public void setHeightMultiplier(int heightMultiplier) {
        this.heightMultiplier = heightMultiplier;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        return true;
    }

    
    

    
    
}
