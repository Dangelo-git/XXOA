package com.dangelo.xxoa.bean;

import java.util.List;

/**
 * Created by dangelo on 16/11/21.
 */
public class MeetBasis {
//    "wsMeetBasis":{
//        "uuEName":"xtgly",
//                "meetName":"",
//                "comperes":[
//        {
//            "attributeName":"测试一",
//                "attributeValue":"测试一",
//                "id":"407"
//        }
//        ],
//        "sites":[
//        {
//            "attributeName":"会议室一",
//                "attributeValue":"会议室一",
//                "id":"409"
//        }
//        ],
//        "types":[
//        {
//            "attributeName":"专题会议",
//                "attributeValue":"专题会议",
//                "id":"14"
//        },
//        {
//            "attributeName":"报告会",
//                "attributeValue":"报告会",
//                "id":"15"
//        }
//        ],
//        "typeCounts":[
//        {
//            "attributeName":"专题会议",
//                "count":10,
//                "id":"14"
//        },
//        {
//            "attributeName":"报告会",
//                "count":1,
//                "id":"15"
//        }
//        ],
//        "deptList":[
//        {
//            "CName":"厅领导",
//                "orderCode":1,
//                "orgId":955
//        },
//        {
//            "CName":"办公室",
//                "orderCode":2,
//                "orgId":940
//        },
//        {
//            "CName":"政策法规处",
//                "orderCode":2,
//                "orgId":941
//        },
//        {
//            "CName":"综合规划处",
//                "orderCode":3,
//                "orgId":942
//        },
//        {
//            "CName":"建设管理处",
//                "orderCode":4,
//                "orgId":943
//        },
//        {
//            "CName":"农村公路处",
//                "orderCode":5,
//                "orgId":944
//        },
//        {
//            "CName":"交通运输处",
//                "orderCode":6,
//                "orgId":939
//        },
//        {
//            "CName":"安全监督处",
//                "orderCode":7,
//                "orgId":938
//        },
//        {
//            "CName":"审计监督处",
//                "orderCode":8,
//                "orgId":946
//        },
//        {
//            "CName":"交通产业工会办公室",
//                "orderCode":9,
//                "orgId":947
//        },
//        {
//            "CName":"财务处",
//                "orderCode":10,
//                "orgId":948
//        },
//        {
//            "CName":"政工人事处",
//                "orderCode":11,
//                "orgId":837
//        },
//        {
//            "CName":"自治区交通战备办公室",
//                "orderCode":12,
//                "orgId":949
//        },
//        {
//            "CName":"边防公路管理处",
//                "orderCode":13,
//                "orgId":950
//        },
//        {
//            "CName":"驻厅纪检组",
//                "orderCode":14,
//                "orgId":836
//        },
//        {
//            "CName":"通信信息中心",
//                "orderCode":15,
//                "orgId":10
//        },
//        {
//            "CName":"机关后勤服务中心",
//                "orderCode":16,
//                "orgId":951
//        },
//        {
//            "CName":"路网中心",
//                "orderCode":30,
//                "orgId":5590
//        }
//        ]
//    }
    private String uuEName;
    private String meetName;//会议名称
    private List<Meetattribute>  comperes;//主持人信息
    private List<Meetattribute> sites;//会议室信息
    private List<Meetattribute> types;//会议类型信息
    private List<typeCounts> typeCounts;//会议类型次数信息
    private List<DeptInfo> deptList;//部门信息

    public List<DeptInfo> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptInfo> deptList) {
        this.deptList = deptList;
    }

    public String getUuEName() {
        return uuEName;
    }

    public void setUuEName(String uuEName) {
        this.uuEName = uuEName;
    }

    public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }

    public List<Meetattribute> getComperes() {
        return comperes;
    }

    public void setComperes(List<Meetattribute> comperes) {
        this.comperes = comperes;
    }

    public List<Meetattribute> getSites() {
        return sites;
    }

    public void setSites(List<Meetattribute> sites) {
        this.sites = sites;
    }

    public List<Meetattribute> getTypes() {
        return types;
    }

    public void setTypes(List<Meetattribute> types) {
        this.types = types;
    }

    public List<typeCounts> getTypeCounts() {
        return typeCounts;
    }

    public void setTypeCounts(List<typeCounts> typeCounts) {
        this.typeCounts = typeCounts;
    }
}

