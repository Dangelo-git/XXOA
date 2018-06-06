package com.dangelo.xxoa.model;

public class UserMonSchedule {
	// 查询个人日程(按月)
	// id :个人招考日程id; userNo :用户id; articlesNo :招考日程(文章)id}
	public String id; // 1,
	public String no; // "",
	public String userNo; // "8",
	public String articlesNo; // "180"

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getArticlesNo() {
		return articlesNo;
	}

	public void setArticlesNo(String articlesNo) {
		this.articlesNo = articlesNo;
	}

}
