package com.allenwtl.util;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

public class DataPage<T> implements Serializable {


    public static final String ASC = "asc";

    public static final String DESC = "desc";

    private int pageNo =1;
    private int pageSize =20;
    private String orderBy = null ;
    private String order = null ;
    private boolean needData = true ;
    private boolean needTotalCount = true ;

    private List<T> dataList = null;
    private long totalCount = -1L;

    public DataPage() {
    }

    public DataPage(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }


    public DataPage(boolean needData, boolean needTotalCount) {
        this.needData = needData;
        this.needTotalCount = needTotalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        if(pageNo<1){
            this.pageNo =1;
        }
    }

    public DataPage<T> pageNo(int thePageNo){
        this.setPageNo(thePageNo);
        return this;
    }

    public DataPage<T> pageSize(int thePageSize){
        this.setPageSize(thePageSize);
        return this;
    }
    //得到分页数据的第一条
    public int getFirst(){
        return (this.pageNo -1)*this.pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        String lowcaseOrder = StringUtils.lowerCase(order);
        String[] orders = StringUtils.split(lowcaseOrder, ',');
        if(orders != null) {
            String[] orderArray = orders;
            int orderLength = orders.length;

            for(int index = 0; index < orderLength; ++index) {
                String orderStr = orderArray[index];
                if(!StringUtils.equals( DESC, orderStr) && !StringUtils.equals( ASC , orderStr)) {
                    throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
                }
            }

            this.order = lowcaseOrder;
        }
    }


    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public DataPage<T> orderBy(String theOrderBy) {
        this.setOrderBy(theOrderBy);
        return this;
    }

    public boolean isNeedData() {
        return needData;
    }

    public void setNeedData(boolean needData) {
        this.needData = needData;
    }

    public boolean isNeedTotalCount() {
        return needTotalCount;
    }

    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }

    public DataPage<T> needTotalCount(boolean theAutoCount){
        this.setNeedTotalCount(theAutoCount);
        return this ;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPages(){
        if(this.totalCount < 0L){
            return -1L;
        } else {
            long count =this.totalCount / this.pageSize ;
            if(this.totalCount % this.pageSize >0L){
                ++ count;
            }
            return count ;
        }
    }

    public boolean isHasNext() {
        return (long)(this.pageNo + 1) <= this.getTotalPages();
    }

    public int getNextPage() {
        return this.isHasNext()?this.pageNo + 1:this.pageNo;
    }

    public int getEndIndex() {
        return this.pageNo * this.pageSize;
    }

    public int getStartIndex() {
        return this.getFirst();
    }

    public boolean isHasPrev() {
        return this.pageNo - 1 >= 1;
    }

    public int getPrevPage() {
        return this.isHasPrev()?this.pageNo - 1:this.pageNo;
    }

}
