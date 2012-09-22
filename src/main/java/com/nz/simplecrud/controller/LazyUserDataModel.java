package com.nz.simplecrud.controller;


import com.nz.simplecrud.entity.User;
import com.nz.simplecrud.service.DataAccessService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyUserDataModel extends LazyDataModel<User> implements Serializable{

    
    private List<User> datasource;
    private int pageSize;
    private int rowIndex;
    private int rowCount;
    private DataAccessService crudService;
    
    public LazyUserDataModel(DataAccessService crudService) {
        this.crudService = crudService;
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        datasource = crudService.findWithNamedQuery(User.ALL, first, first + pageSize);
        setRowCount(crudService.countTotalRecord(User.TOTAL));   
        return datasource;
    }
    
    @Override
    public boolean isRowAvailable() {
        if(datasource == null) 
            return false;
        int index = rowIndex % pageSize ; 
        return index >= 0 && index < datasource.size();
    }
       
    @Override
    public Object getRowKey(User user) {
        return user.getUserid().toString();
    }

    @Override
    public User getRowData() {
        if(datasource == null)
            return null;
        int index =  rowIndex % pageSize;
        if(index > datasource.size()){
            return null;
        }
        return datasource.get(index);
    }
    
    @Override
    public User getRowData(String rowKey) {
        if(datasource == null)
            return null;
       for(User user : datasource) {  
           if(user.getUserid().toString().equals(rowKey))  
           return user;  
       }  
       return null;  
    }
    
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }
    
    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    
    @Override
    public int getRowCount() {
        return this.rowCount;
    }
     
    @Override
    public void setWrappedData(Object list) {
        this.datasource = (List<User>) list;
    }
    
    @Override
    public Object getWrappedData() {
        return datasource;
    }
}
                    
