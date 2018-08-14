/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rangematrix;

import java.util.List;

/**
 *
 * @author daniil_pozdeev
 */
public interface RangeMatrixModel {
    
    //Column group
    
    int getColumnGroupCount();
    
    Object getColumnGroup(int index);
    
    boolean isColumnGroup(Object column);
    
    String getColumnGroupName(int index);
    
    //Column
    
    int getColumnCount(Object columnGroup);
    
    Object getColumn(int index);
    
    String getColumnName(int index);
    
    List<Object> getColumnPath(Object column);
    
    //Row Group
    
    int getRowGroupCount();
    
    Object getRowGroup(int index);
    
    boolean isRowGroup(Object row);
    
    String getRowGroupName(int index);
    
    //Row
    
    int getRowCount(Object rowGroup);    
    
    Object getRow(int index);    
    
    String getRowName(int index);
    
    List<Object> getRowPath(Object row);
    
    //Values
    
    Object getValueAt(List<Object> rowPath, List<Object> columnPath);
}
