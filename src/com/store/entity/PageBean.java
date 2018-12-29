package com.store.entity;
/**
 * 存放分页相关数据
 * @author xiaoming
 *
 */

import java.util.List;

public class PageBean<T> {

	private int currPage;// 当前页
	private int pageSize;// 每页显示的记录数
	private int totalRecords;// 总记录数
	private int totalPage;// 总页数
	private List<T> list;// 每页查询到的数据的集合

	private int prePageNum;//上一页							    *
	private int nextPageNum;//下一页
	
	// 扩展属性
	// 一共每页显示9个页码按钮
	private int startPage;// 开始页码
	private int endPage;// 结束页码

	// 完善属性
	private String url;

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPrePageNum() {
		prePageNum = currPage-1;
		if(prePageNum<1){
			prePageNum = 1;
		}
		return prePageNum;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public int getNextPageNum() {
		nextPageNum = currPage+1;
		if(nextPageNum>totalPage){
			nextPageNum = totalPage;
		}
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	
	
}
