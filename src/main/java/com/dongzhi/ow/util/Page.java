package com.dongzhi.ow.util;

public class Page {

	private int start;
	private int count;
	private int total;
	private String param;
	
	private static final int defaultCount = 10;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public Page () {
		count = defaultCount;
	}
	
	public Page (int start, int count) {
		this();
		this.start = start;
		this.count = count;
	}
	
	public boolean isHasPreviouse() {
		if(start==0)
			return false;
		return true;
	}
	
	public boolean isHasNext(){
        if(start==getLast())
            return false;
        return true;
    }
	
	 public int getTotalPage(){
	        int totalPage;
	        if (0 == total % count)
	            totalPage = total /count;
	        else
	            totalPage = total / count + 1;
	 
	        if(0==totalPage)
	            totalPage = 1;
	        return totalPage;
	 
	    }
	
	public int getLast(){
        int last;
        if (0 == total % count)
            last = total - count;
        else
            last = total - total % count;
        last = last<0?0:last;
        return last;
    }
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String gid) {
		this.param = gid;
	}
	
}
