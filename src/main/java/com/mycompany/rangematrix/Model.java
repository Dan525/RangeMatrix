/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rangematrix;

import java.io.File;
import java.util.List;

/**
 *
 * @author daniil_pozdeev
 */
public class Model implements RangeMatrixModel {

    File columnRoot;
    File rowRoot;
    
    Model() {
        this.columnRoot = new File("C:\\Источники сигнала");
        this.rowRoot = new File("C:\\Источники сигнала\\Плата 1");
    }
    
    public Object getColumnRoot() {
        return columnRoot;
    }
    
    public Object getRowRoot() {
        return rowRoot;
    }
    
    @Override
    public int getColumnGroupCount(Object column) {
        if (column == null) {
            return 0;
        }
        return ((File)column).list().length;
    }

    @Override
    public Object getColumnGroup(Object column, int index) {
        return ((File)column).listFiles()[index];
    }

    @Override
    public int getColumnIndex(Object parent, Object child) {
        File[] childrenFiles = ((File)parent).listFiles();
            if (childrenFiles == null) {
                return -1;
            }
        String childName = ((File)child).getPath();
        for (int i = 0; i < childrenFiles.length; i++) {
            if (childName.equals(childrenFiles[i].getPath())) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public boolean isColumnGroup(Object column) {
        if (((File)column).list().length == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getColumnGroupName(Object column) {
        return ((File)column).getName();
    }

    @Override
    public int getColumnCount(Object columnGroup) {
        if (columnGroup == null) {
            return 0;
        }
        return ((File)columnGroup).list().length;
    }

    @Override
    public Object getColumn(Object columnGroup,int index) {
        return ((File)columnGroup).listFiles()[index];
    }

    @Override
    public String getColumnName(Object columnGroup,int index) {
        return ((File)columnGroup).getName();
    }

    @Override
    public List<Object> getColumnPath(Object column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowGroupCount(Object row) {
        if (row == null) {
            return 0;
        }
        return ((File)row).list().length;
    }

    @Override
    public Object getRowGroup(Object row, int index) {
        return ((File)row).listFiles()[index];
    }

    @Override
    public boolean isRowGroup(Object row) {
        if (((File)row).list().length == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getRowGroupName(Object row) {
        return ((File)row).getName();
    }

    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public Object getRow(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRowName(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getRowPath(Object row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int row, int col) {
        return 1;
    }

    
}
