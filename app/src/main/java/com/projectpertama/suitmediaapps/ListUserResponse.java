package com.projectpertama.suitmediaapps;

import java.util.List;

public class ListUserResponse{
	private int perPage;
	private int total;
	private List<DataItem> data;
	private int page;
	private int totalPages;

	public int getPerPage(){
		return perPage;
	}

	public int getTotal(){
		return total;
	}

	public List<DataItem> getData(){
		return data;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}
}