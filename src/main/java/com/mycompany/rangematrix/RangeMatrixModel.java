/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rangematrix;

import java.util.List;
import javax.swing.event.TreeModelListener;

/**
 *
 * @author daniil_pozdeev
 */
public interface RangeMatrixModel {
    
    public Object getRoot();
    
    //Column group
    
    int getColumnGroupCount(Object parent);
    
    Object getColumnGroup(Object parent, int index);
    
    int getColumnIndex(Object parent, Object child);
    
    boolean isColumnGroup(Object column);
    
    String getColumnGroupName(Object column);
    
    //Column
    
    int getColumnCount(Object columnGroup);
    
    Object getColumn(Object columnGroup,int index);
    
    String getColumnName(Object columnGroup,int index);
    
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
