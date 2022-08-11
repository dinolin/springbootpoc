package com.example.parameter;

public class ProductQueryParameter {
    private String keyword;
    private String orderBy;
    private String sortRule;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSortRule() {
        return sortRule;
    }

    public void setSortRule(String sortRule) {
        this.sortRule = sortRule;
    }

	public int getPriceFrom() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPriceTo() {
		// TODO Auto-generated method stub
		return 0;
	}
}
