package com.dangelo.xxoa.model;

public class contents {
	// { preview :预览图; relatedAreaNo :相关地区编码no; schoolName :学校名称;
	// schoolIntroduction :学校详细招生简章; schoolWebsite :学校网站;
	// schoolAddress :学校地址; schoolType :学校类型id; SchoolTypes:学校类型对象; schoolRule
	// :招生规则;
	// schoolTel :电话; bmTime :报名时间; ksTime :考试时间; ksAdress :考试地址;
	// kskm :考试科目; lnkt :历年考题; tag :招生人数;}
	public String preview; // "http://182.92.101.59/kindeditor/attached/image/20140812/20140812174455_0937.jpg",

	public String id; // 175,
	public String tag; // 30,招生人数
	public String relatedAreaNo; // "41",
	public String schoolName; // "山东艺术学院",
	public String schoolWebsite; // "http://www.sdca.edu.cn",
	public String schoolAddress; // "济南市长清区大学科技园紫薇路6000号 ",
	public String schoolType; // 4,
	public String schoolRule; // "艺术设计学本科专业，录取时按文化成绩占70%，专业成绩占30%结构后的成绩排序录取。\r\n戏剧影视美术设计(化妆美容设计)本科专业在录取时以专业成绩占70%，文化课成绩占30%的比例按结构分排序由高到低录取。"
	public String bmIntro; // "",报名时间说明
	public String ksIntro; // "",考试时间说明;
	public String schoolTel; // "0373-8888888",
	public String bmTime; // "2014-08-12 21:53:00",
	public String ksTime; // "2014-08-12 21:53:00",
	public String ksAdress; // "考试地点考试地点考试地点考试地点考试地点考试地点",
	public String kskm; // "考试科目考试科目考试科目考试科目考试科目考试科目考试科目考试科目考试科目考试科目考试科目",
	public String lnkt; // "历年考题历年考题历年考题历年考题历年考题历年考题历年考题历年考题",
	public String addTime; // "2014-08-12 21:55:01",
	public String sort; // 0,
	public String type; // 1002,
	public String status; // 1,
	public String state; // 1
	public String lngX;
	public String latY;
	public String sc;//新增用户收藏状态字段sc (1:收藏,0:没收藏)
	// public Object SchoolTypes;
	// public String[] SchoolTypes;

	// {
	// "4": "独立艺术院校",
	// "3": "美术学院",
	// "2": "二本",
	// "1": "一本"
	// },

	// public String[] getSchoolTypes() {
	// return SchoolTypes;
	// }
	//
	// public void setSchoolTypes(String[] schoolTypes) {
	// SchoolTypes = schoolTypes;
	// }

	public String getSc() {
		return sc;
	}

	public void setSc(String sc) {
		this.sc = sc;
	}

	public String getLngX() {
		return lngX;
	}

	public void setLngX(String lngX) {
		this.lngX = lngX;
	}

	public String getLatY() {
		return latY;
	}

	public void setLatY(String latY) {
		this.latY = latY;
	}

	public String getBmIntro() {
		return bmIntro;
	}

	// public Object getSchoolTypes() {
	// return SchoolTypes;
	// }
	//
	// public void setSchoolTypes(Object schoolTypes) {
	// SchoolTypes = schoolTypes;
	// }

	public String getSchoolTel() {
		return schoolTel;
	}

	public void setSchoolTel(String schoolTel) {
		this.schoolTel = schoolTel;
	}

	public String getBmTime() {
		return bmTime;
	}

	public void setBmTime(String bmTime) {
		this.bmTime = bmTime;
	}

	public String getKsTime() {
		return ksTime;
	}

	public void setKsTime(String ksTime) {
		this.ksTime = ksTime;
	}

	public String getKsAdress() {
		return ksAdress;
	}

	public void setKsAdress(String ksAdress) {
		this.ksAdress = ksAdress;
	}

	public String getKskm() {
		return kskm;
	}

	public void setKskm(String kskm) {
		this.kskm = kskm;
	}

	public String getLnkt() {
		return lnkt;
	}

	public void setLnkt(String lnkt) {
		this.lnkt = lnkt;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setBmIntro(String bmIntro) {
		this.bmIntro = bmIntro;
	}

	public String getKsIntro() {
		return ksIntro;
	}

	public void setKsIntro(String ksIntro) {
		this.ksIntro = ksIntro;
	}

	// public String getSchoolTypes() {
	// return SchoolTypes;
	// }
	//
	// public void setSchoolTypes(String schoolTypes) {
	// SchoolTypes = schoolTypes;
	// }

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRelatedAreaNo() {
		return relatedAreaNo;
	}

	public void setRelatedAreaNo(String relatedAreaNo) {
		this.relatedAreaNo = relatedAreaNo;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolWebsite() {
		return schoolWebsite;
	}

	public void setSchoolWebsite(String schoolWebsite) {
		this.schoolWebsite = schoolWebsite;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getSchoolRule() {
		return schoolRule;
	}

	public void setSchoolRule(String schoolRule) {
		this.schoolRule = schoolRule;
	}

}
