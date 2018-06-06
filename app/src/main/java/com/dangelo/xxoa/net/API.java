package com.dangelo.xxoa.net;

/**
 * Created by dangelo on 16/10/25.
 */
public class API {
    public final static String Method_LOGIN = "login";
    public final static String Method_LOGOUT = "logOut";
    public final static String Method_INFOLIST = "infoList";
    public final static String Method_DOWNLOAD = "download";
    public final static String Method_SCHEDULALL = "scheduleAll";
    public final static String Method_SCHEDULUPDATE = "scheduleUpdate";
    public final static String Method_SCHEDULADD = "scheduleAdd";
    public final static String Method_SCHEDULDELETE = "scheduleDelete";
    public final static String Method_MEETINGBASIS = "meetingBasis";
    public final static String Method_MEETALLLIST = "meetAllList";
    public final static String Method_LISTALLTOPIC = "listAllTopic";
    public final static String Method_MEETINGAPPLY = "meetingApply";
    public final static String Method_BROWSENEWS = "getBrowseNews";
    //发文
    public final static String Method_UADISPATCHDOC_SELECTINFO = "uaDispatchDoc/selectInfo.htm";
    public final static String Method_UADISPATCHDOC_GETUSERACTIVITI = "uaDispatchDoc/getUserActiviti.htm";
    public final static String Method_UADISPATCHDOC_GETNOTFINISH = "uaDispatchDoc/getNotFinish.htm";
    public final static String Method_UADISPATCHDOC_GETFINISH = "uaDispatchDoc/getFinish.htm";
    public final static String Method_UADISPATCHDOC_GETUSERDISPATCH = "uaDispatchDoc/getUserDispatch.htm";
    public final static String Method_UADISPATCHDOC_GETUSERSTOP = "uaDispatchDoc/getUserStop.htm";
    public final static String Method_UAAPPROVALOPINION_SELECTUSERACTIVITI = "UaApprovalOpinion/selectUserActiviti.htm";
    public final static String Method_UAAPPROVALOPINION_GETNOTUSERFINISH = "UaApprovalOpinion/getNotUserFinish.htm";
    public final static String Method_UAAPPROVALOPINION_GETUSERFINISH = "UaApprovalOpinion/getUserFinish.htm";
    public final static String Method_UAINFILETABLE_VAGUEUAINFILETABLE = "UaInFileTable/vagueUaInFileTable.htm";
    public final static String Method_UAAPPROVALOPINION_SELECTALLSTOP = "UaApprovalOpinion/selectAllStop.htm";
    public final static String Method_UAENROLMENT_VAGUENOTCONFERENCEINFOALL = "UaEnrolment/vagueNotConferenceInfoAll.htm";
    public final static String Method_EXTERNALMEETING_VAGUEPARTICIPANTSALL = "ExternalMeeting/vagueParticipantsAll.htm";
    public final static String Method_UAAPPROVALOPINION_FINDNEXTACTIVITI = "UaApprovalOpinion/findNextActiviti.htm";//1.8 下一步流程节点
    public final static String Method_UAAPPROVALOPINION_GETALLUSERACTIVITI = "UaApprovalOpinion/getAllUserActiviti.htm";//1.9 查询岗位下的人
    public final static String Method_UAAPPROVALOPINION_NEXTUSERACTIVITI = "UaApprovalOpinion/nextUserActiviti.htm";//1.10提交下一步给下一个审批人
    public final static String Method_UAINFILETABLE_SAVEUAINFILETABLE = "UaInFileTable/saveUaInFileTable.htm";//)1.1  添加收文信息（添加草稿箱）
    //知识库
    public final static String Method_KNOWLEDGECATEGORY_FINDALL = "KnowledgeCategory/findAll.htm";//)2.5查询知识库所有分类
    public final static String Method_KNOWLEDGEUPLOADFILE_FINDUPLOADFILE = "KnowledgeUploadFile_findUploadFile.htm";//)1.6查询文件
    public final static String Method_KNOWLEDGEUPLOADFILE_PERSONALADD = "KnowledgeUploadFile/personalAdd.htm";//)1.3保存到个人库
    //用户登录
    public final static String Method_LOGIN_USERLOGIN = "Login/userLogin.htm";//)1.1  用户登录
    public final static String Method_UAMESSAGENUM_REFRESHMESSAGENUM = "UaMessageNum/refreshMessageNum.htm";//)1.1  消息数量通知
    //通知
    public final static String Method_NOTICEPUBLIC_NOTICEPUBLICLIST = "noticePublic/noticePublicList.htm";//)1.3通知公告列表







    public final static String JSON_LOGIN = "{'uuEName':'xtgly','uuPassword':'kk'}";
    public final static String JSON_LOGOUT = "{'uuEName':'xtgly','uuPassword':'kk','uuKeyID':'503342854837482'}";
    public final static String JSON_INFOLIST = "{'uuEName':'xtgly','showRange':'2','docStatus':'2'}";
    public final static String JSON_DOWNLOAD = "{'docId':'4830','uuEName':'xtgly'}";
    public final static String JSON_SCHEDULALL = "{'constitutor':'xtgly'}";
    public final static String JSON_SCHEDULUPDATE = "{'id':'488','topic':'测试','address':'北京','startDate':'2016-09-06 " +
            "10:48:21','endDate':'2016-09-06 10:48:21','remark':'备注','isallDay':'true','constitutor':'xtgly'}";
    public final static String JSON_SCHEDULADD = "{'topic':'测试2','address':'北京','startDate':'2016-09-06 10:48:21'," +
            "'endDate':'2016-09-06 10:48:21','remark':'备注','isallDay':'true','constitutor':'xtgly'}";
    public final static String JSON_SCHEDULDELETE = "{'id':'487'}";
    public final static String JSON_MEETINGBASIS = "{'uuEName':'xtgly'}";
    public final static String JSON_MEETALLLIST = "{'pageNo':'1','isInform':'true','uuEName':'xtgly'}";
    public final static String JSON_LISTALLTOPIC = "{'uuEName':'xtgly','meetID':'6720'}";
    public final static String JSON_MEETINGAPPLY = "{{'pageNo':'1','isInform':'true','uuEName':'xtgly'}}";
    //通知18、新闻17、公告338
    public final static String JSON_BROWSENEWS = "{\"channel\":\"17\"}";
    public final static String JSON_BROWSEMSG = "{\"channel\":\"18\"}";
    public final static String JSON_BROWSENOTES = "{\"channel\":\"338\"}";


    public final static String RSPONSE_LOGIN = "{'uuEName':'xtgly','uuPassword':'kk'}";
    public final static String RSPONSE_UADISPATCHDOC_SELECTINFO = "{'uuEName':'xtgly','uuPassword':'kk'}";
    public final static String RSPONSE_UADISPATCHDOC_GETUSERACTIVITI = " {\n" +
            "\t\t\"dispatchDocList\": {\n" +
            "\t\t\t\"total\": 1,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 306,\n" +
            "\t\t\t\t\"docTitle\": \"审计科\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": null,\n" +
            "\t\t\t\t\"docLabel\": \"keyuan\",\n" +
            "\t\t\t\t\"docDrafterName\": \"审计科科员1\",\n" +
            "\t\t\t\t\"docDrafterId\": null,\n" +
            "\t\t\t\t\"zhouJiaoNum\": null,\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": null,\n" +
            "\t\t\t\t\"docMainSendUnit\": null,\n" +
            "\t\t\t\t\"docMainSendUnitId\": null,\n" +
            "\t\t\t\t\"docSendOffice\": null,\n" +
            "\t\t\t\t\"docSendOfficeId\": null,\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": null,\n" +
            "\t\t\t\t\"recordName\": null,\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": null,\n" +
            "\t\t\t\t\"updateTime\": null,\n" +
            "\t\t\t\t\"docPath\": null,\n" +
            "\t\t\t\t\"docMainSendShow\": null,\n" +
            "\t\t\t\t\"docSendShow\": null,\n" +
            "\t\t\t\t\"docFileName\": null,\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": null,\n" +
            "\t\t\t\t\"processId\": null,\n" +
            "\t\t\t\t\"dept\": \"审计科\",\n" +
            "\t\t\t\t\"deptId\": null,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}\n";
    public final static String RSPONSE_UADISPATCHDOC_GETNOTFINISH=" {\n" +
            "\t\t\"dispatchDocList\": {\n" +
            "\t\t\t\"total\": 1,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 305,\n" +
            "\t\t\t\t\"docTitle\": \"gg\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": null,\n" +
            "\t\t\t\t\"docLabel\": \"gg\",\n" +
            "\t\t\t\t\"docDrafterName\": \"审计科科员1\",\n" +
            "\t\t\t\t\"docDrafterId\": null,\n" +
            "\t\t\t\t\"zhouJiaoNum\": null,\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": null,\n" +
            "\t\t\t\t\"docMainSendUnit\": null,\n" +
            "\t\t\t\t\"docMainSendUnitId\": null,\n" +
            "\t\t\t\t\"docSendOffice\": null,\n" +
            "\t\t\t\t\"docSendOfficeId\": null,\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": null,\n" +
            "\t\t\t\t\"recordName\": null,\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": null,\n" +
            "\t\t\t\t\"updateTime\": null,\n" +
            "\t\t\t\t\"docPath\": null,\n" +
            "\t\t\t\t\"docMainSendShow\": null,\n" +
            "\t\t\t\t\"docSendShow\": null,\n" +
            "\t\t\t\t\"docFileName\": null,\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": null,\n" +
            "\t\t\t\t\"processId\": null,\n" +
            "\t\t\t\t\"dept\": \"审计科\",\n" +
            "\t\t\t\t\"deptId\": null,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t} \n";
    public final static String RSPONSE_UADISPATCHDOC_GETFINISH=" {\n" +
            "\t\t\"dispatchDocList\": {\n" +
            "\t\t\t\"total\": 4,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 318,\n" +
            "\t\t\t\t\"docTitle\": \"99999\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": null,\n" +
            "\t\t\t\t\"docLabel\": \"99999\",\n" +
            "\t\t\t\t\"docDrafterName\": \"审计科科员1\",\n" +
            "\t\t\t\t\"docDrafterId\": null,\n" +
            "\t\t\t\t\"zhouJiaoNum\": \"州交审计[2018]2号\",\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": null,\n" +
            "\t\t\t\t\"docMainSendUnit\": null,\n" +
            "\t\t\t\t\"docMainSendUnitId\": null,\n" +
            "\t\t\t\t\"docSendOffice\": null,\n" +
            "\t\t\t\t\"docSendOfficeId\": null,\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": null,\n" +
            "\t\t\t\t\"recordName\": null,\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": null,\n" +
            "\t\t\t\t\"updateTime\": null,\n" +
            "\t\t\t\t\"docPath\": null,\n" +
            "\t\t\t\t\"docMainSendShow\": null,\n" +
            "\t\t\t\t\"docSendShow\": null,\n" +
            "\t\t\t\t\"docFileName\": null,\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": null,\n" +
            "\t\t\t\t\"processId\": null,\n" +
            "\t\t\t\t\"dept\": \"审计科\",\n" +
            "\t\t\t\t\"deptId\": null,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"id\": 311,\n" +
            "\t\t\t\t\"docTitle\": \"222\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": null,\n" +
            "\t\t\t\t\"docLabel\": \"222\",\n" +
            "\t\t\t\t\"docDrafterName\": \"审计科科员1\",\n" +
            "\t\t\t\t\"docDrafterId\": null,\n" +
            "\t\t\t\t\"zhouJiaoNum\": \"州交审计[2018]1号\",\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": null,\n" +
            "\t\t\t\t\"docMainSendUnit\": null,\n" +
            "\t\t\t\t\"docMainSendUnitId\": null,\n" +
            "\t\t\t\t\"docSendOffice\": null,\n" +
            "\t\t\t\t\"docSendOfficeId\": null,\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": null,\n" +
            "\t\t\t\t\"recordName\": null,\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": null,\n" +
            "\t\t\t\t\"updateTime\": null,\n" +
            "\t\t\t\t\"docPath\": null,\n" +
            "\t\t\t\t\"docMainSendShow\": null,\n" +
            "\t\t\t\t\"docSendShow\": null,\n" +
            "\t\t\t\t\"docFileName\": null,\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": null,\n" +
            "\t\t\t\t\"processId\": null,\n" +
            "\t\t\t\t\"dept\": \"审计科\",\n" +
            "\t\t\t\t\"deptId\": null,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"id\": 310,\n" +
            "\t\t\t\t\"docTitle\": \"111\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": null,\n" +
            "\t\t\t\t\"docLabel\": \"111\",\n" +
            "\t\t\t\t\"docDrafterName\": \"审计科科员1\",\n" +
            "\t\t\t\t\"docDrafterId\": null,\n" +
            "\t\t\t\t\"zhouJiaoNum\": \"州交审计[2018]0号\",\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": null,\n" +
            "\t\t\t\t\"docMainSendUnit\": null,\n" +
            "\t\t\t\t\"docMainSendUnitId\": null,\n" +
            "\t\t\t\t\"docSendOffice\": null,\n" +
            "\t\t\t\t\"docSendOfficeId\": null,\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": null,\n" +
            "\t\t\t\t\"recordName\": null,\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": null,\n" +
            "\t\t\t\t\"updateTime\": null,\n" +
            "\t\t\t\t\"docPath\": null,\n" +
            "\t\t\t\t\"docMainSendShow\": null,\n" +
            "\t\t\t\t\"docSendShow\": null,\n" +
            "\t\t\t\t\"docFileName\": null,\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": null,\n" +
            "\t\t\t\t\"processId\": null,\n" +
            "\t\t\t\t\"dept\": \"审计科\",\n" +
            "\t\t\t\t\"deptId\": null,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"id\": 306,\n" +
            "\t\t\t\t\"docTitle\": \"审计科\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": null,\n" +
            "\t\t\t\t\"docLabel\": \"keyuan\",\n" +
            "\t\t\t\t\"docDrafterName\": \"审计科科员1\",\n" +
            "\t\t\t\t\"docDrafterId\": null,\n" +
            "\t\t\t\t\"zhouJiaoNum\": \"州交审计[2018]1号\",\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": null,\n" +
            "\t\t\t\t\"docMainSendUnit\": null,\n" +
            "\t\t\t\t\"docMainSendUnitId\": null,\n" +
            "\t\t\t\t\"docSendOffice\": null,\n" +
            "\t\t\t\t\"docSendOfficeId\": null,\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": null,\n" +
            "\t\t\t\t\"recordName\": null,\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": null,\n" +
            "\t\t\t\t\"updateTime\": null,\n" +
            "\t\t\t\t\"docPath\": null,\n" +
            "\t\t\t\t\"docMainSendShow\": null,\n" +
            "\t\t\t\t\"docSendShow\": null,\n" +
            "\t\t\t\t\"docFileName\": null,\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": null,\n" +
            "\t\t\t\t\"processId\": null,\n" +
            "\t\t\t\t\"dept\": \"审计科\",\n" +
            "\t\t\t\t\"deptId\": null,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}\n";

    public final static String RSPONSE_UADISPATCHDOC_GETUSERDISPATCH="{\n" +
            "\t\t\"dispatchDocList\": {\n" +
            "\t\t\t\"total\": 2,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 316,\n" +
            "\t\t\t\t\"docTitle\": \"11111\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\"docLabel\": \"1111\",\n" +
            "\t\t\t\t\"docDrafterName\": \"审计科科员1\",\n" +
            "\t\t\t\t\"docDrafterId\": 157,\n" +
            "\t\t\t\t\"zhouJiaoNum\": null,\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": 1524723864000,\n" +
            "\t\t\t\t\"docMainSendUnit\": \"\",\n" +
            "\t\t\t\t\"docMainSendUnitId\": \"\",\n" +
            "\t\t\t\t\"docSendOffice\": \"\",\n" +
            "\t\t\t\t\"docSendOfficeId\": \"\",\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": 35,\n" +
            "\t\t\t\t\"recordName\": \"会议纪要\",\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": 0,\n" +
            "\t\t\t\t\"updateTime\": null,\n" +
            "\t\t\t\t\"docPath\": \"/root/filewebapp/transportOA/download/Docfile/\",\n" +
            "\t\t\t\t\"docMainSendShow\": \"\",\n" +
            "\t\t\t\t\"docSendShow\": \"\",\n" +
            "\t\t\t\t\"docFileName\": \"shenjike1_1524723864274.doc\",\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": null,\n" +
            "\t\t\t\t\"processId\": null,\n" +
            "\t\t\t\t\"dept\": \"审计科\",\n" +
            "\t\t\t\t\"deptId\": 4621,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t},{\n" +
            "\t\t\t\t\"id\": 312,\n" +
            "\t\t\t\t\"docTitle\": \"发文发文\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\"docLabel\": \"000\",\n" +
            "\t\t\t\t\"docDrafterName\": \"审计科科员1\",\n" +
            "\t\t\t\t\"docDrafterId\": 157,\n" +
            "\t\t\t\t\"zhouJiaoNum\": null,\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": 1524723457000,\n" +
            "\t\t\t\t\"docMainSendUnit\": \"\",\n" +
            "\t\t\t\t\"docMainSendUnitId\": \"\",\n" +
            "\t\t\t\t\"docSendOffice\": \"\",\n" +
            "\t\t\t\t\"docSendOfficeId\": \"\",\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": 39,\n" +
            "\t\t\t\t\"recordName\": \"工会联合会文件\",\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": 0,\n" +
            "\t\t\t\t\"updateTime\": null,\n" +
            "\t\t\t\t\"docPath\": \"/root/filewebapp/transportOA/download/Docfile/\",\n" +
            "\t\t\t\t\"docMainSendShow\": \"\",\n" +
            "\t\t\t\t\"docSendShow\": \"\",\n" +
            "\t\t\t\t\"docFileName\": \"shenjike1_1524723457152.doc\",\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": null,\n" +
            "\t\t\t\t\"processId\": null,\n" +
            "\t\t\t\t\"dept\": \"审计科\",\n" +
            "\t\t\t\t\"deptId\": 4621,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}\n";

    public final static String RSPONSE_UADISPATCHDOC_GETUSERSTOP=" {\n" +
            "\t\t\"dispatchDocList\": {\n" +
            "\t\t\t\"total\": 2,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 308,\n" +
            "\t\t\t\t\"docTitle\": \"停办测试\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\"docLabel\": \"停办测试\",\n" +
            "\t\t\t\t\"docDrafterName\": \"超人\",\n" +
            "\t\t\t\t\"docDrafterId\": 5,\n" +
            "\t\t\t\t\"zhouJiaoNum\": null,\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": 1524628248000,\n" +
            "\t\t\t\t\"docMainSendUnit\": \"\",\n" +
            "\t\t\t\t\"docMainSendUnitId\": \"\",\n" +
            "\t\t\t\t\"docSendOffice\": \"\",\n" +
            "\t\t\t\t\"docSendOfficeId\": \"\",\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": 13,\n" +
            "\t\t\t\t\"recordName\": \"下行文\",\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": 2,\n" +
            "\t\t\t\t\"updateTime\": 1524628394000,\n" +
            "\t\t\t\t\"docPath\": \"D:/transportOA/download/Docfile/\",\n" +
            "\t\t\t\t\"docMainSendShow\": \"\",\n" +
            "\t\t\t\t\"docSendShow\": \"\",\n" +
            "\t\t\t\t\"docFileName\": \"admin_1524628248812.doc\",\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": 0,\n" +
            "\t\t\t\t\"processId\": 1005040,\n" +
            "\t\t\t\t\"dept\": \"测试\",\n" +
            "\t\t\t\t\"deptId\": 4548,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"id\": 304,\n" +
            "\t\t\t\t\"docTitle\": \"3333\",\n" +
            "\t\t\t\t\"docEmergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\"docLabel\": \"312456\",\n" +
            "\t\t\t\t\"docDrafterName\": \"超人\",\n" +
            "\t\t\t\t\"docDrafterId\": 5,\n" +
            "\t\t\t\t\"zhouJiaoNum\": null,\n" +
            "\t\t\t\t\"docYear\": null,\n" +
            "\t\t\t\t\"docMonth\": null,\n" +
            "\t\t\t\t\"docDay\": null,\n" +
            "\t\t\t\t\"createTime\": 1524563333000,\n" +
            "\t\t\t\t\"docMainSendUnit\": \"\",\n" +
            "\t\t\t\t\"docMainSendUnitId\": \"\",\n" +
            "\t\t\t\t\"docSendOffice\": \"\",\n" +
            "\t\t\t\t\"docSendOfficeId\": \"\",\n" +
            "\t\t\t\t\"docTyping\": null,\n" +
            "\t\t\t\t\"docProofreading\": null,\n" +
            "\t\t\t\t\"docCopyNumber\": null,\n" +
            "\t\t\t\t\"docDrafterNumber\": null,\n" +
            "\t\t\t\t\"recordId\": 13,\n" +
            "\t\t\t\t\"recordName\": \"下行文\",\n" +
            "\t\t\t\t\"activityChart\": null,\n" +
            "\t\t\t\t\"docStatus\": 2,\n" +
            "\t\t\t\t\"updateTime\": 1524627887000,\n" +
            "\t\t\t\t\"docPath\": \"D:/transportOA/download/Docfile/\",\n" +
            "\t\t\t\t\"docMainSendShow\": \"\",\n" +
            "\t\t\t\t\"docSendShow\": \"\",\n" +
            "\t\t\t\t\"docFileName\": \"admin_1524563333205.doc\",\n" +
            "\t\t\t\t\"docHomePagePath\": null,\n" +
            "\t\t\t\t\"docHomePageName\": null,\n" +
            "\t\t\t\t\"docHandleStatus\": 0,\n" +
            "\t\t\t\t\"processId\": 1000011,\n" +
            "\t\t\t\t\"dept\": \"测试\",\n" +
            "\t\t\t\t\"deptId\": 4548,\n" +
            "\t\t\t\t\"pageNum\": null\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}\n ";
    public final static String RSPONSE_UAAPPROVALOPINION_SELECTUSERACTIVITI="{\n" +
            "\t\t\"UaInFileTable\": {\n" +
            "\t\t\t\"total\": 1,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 94,\n" +
            "\t\t\t\t\"processInstanceId\": 975001,\n" +
            "\t\t\t\t\"inTime\": 1524453224000,\n" +
            "\t\t\t\t\"theReceivingId\": \"4548\",\n" +
            "\t\t\t\t\"theReceiving\": \"测试\",\n" +
            "\t\t\t\t\"userId\": 5,\n" +
            "\t\t\t\t\"userName\": \"超人\",\n" +
            "\t\t\t\t\"civilService\": \"gsg\",\n" +
            "\t\t\t\t\"civilNumber\": \"sgs\",\n" +
            "\t\t\t\t\"emergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\"inNumber\": \"sgsg\",\n" +
            "\t\t\t\t\"fileType\": \"sgsg\",\n" +
            "\t\t\t\t\"fileTitle\": \"32443\",\n" +
            "\t\t\t\t\"fileAbstract\": \"sgsgsg\",\n" +
            "\t\t\t\t\"fileName\": null,\n" +
            "\t\t\t\t\"filePath\": null,\n" +
            "\t\t\t\t\"fileAllName\": null,\n" +
            "\t\t\t\t\"currentOpinion\": \"sdgsg\",\n" +
            "\t\t\t\t\"status\": 1,\n" +
            "\t\t\t\t\"numberOf\": 5,\n" +
            "\t\t\t\t\"remarks\": \"sgsg\",\n" +
            "\t\t\t\t\"label\": \"sds\",\n" +
            "\t\t\t\t\"operatorUserId\": \"超人\",\n" +
            "\t\t\t\t\"pages\": 2,\n" +
            "\t\t\t\t\"handleStatus\": 0,\n" +
            "\t\t\t\t\"cfOne\": null,\n" +
            "\t\t\t\t\"cfTwo\": null,\n" +
            "\t\t\t\t\"cfThree\": null,\n" +
            "\t\t\t\t\"deptName\": null,\n" +
            "\t\t\t\t\"rcreatetime\": null,\n" +
            "\t\t\t\t\"hstarttime\": null,\n" +
            "\t\t\t\t\"uuname\": null,\n" +
            "\t\t\t\t\"create_TIME_\": null,\n" +
            "\t\t\t\t\"name_\": \"登记人\",\n" +
            "\t\t\t\t\"end_TIME_\": null\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}\n";
    public final static String RSPONSE_UAAPPROVALOPINION_GETNOTUSERFINISH="{\n" +
            "\t\t\"UaInFileTable\": {\n" +
            "\t\t\t\"total\": 1,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 94,\n" +
            "\t\t\t\t\"processInstanceId\": 975001,\n" +
            "\t\t\t\t\"inTime\": 1524453224000,\n" +
            "\t\t\t\t\"theReceivingId\": \"4548\",\n" +
            "\t\t\t\t\"theReceiving\": \"测试\",\n" +
            "\t\t\t\t\"userId\": 5,\n" +
            "\t\t\t\t\"userName\": \"超人\",\n" +
            "\t\t\t\t\"civilService\": \"gsg\",\n" +
            "\t\t\t\t\"civilNumber\": \"sgs\",\n" +
            "\t\t\t\t\"emergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\"inNumber\": \"sgsg\",\n" +
            "\t\t\t\t\"fileType\": \"sgsg\",\n" +
            "\t\t\t\t\"fileTitle\": \"32443\",\n" +
            "\t\t\t\t\"fileAbstract\": \"sgsgsg\",\n" +
            "\t\t\t\t\"fileName\": null,\n" +
            "\t\t\t\t\"filePath\": null,\n" +
            "\t\t\t\t\"fileAllName\": null,\n" +
            "\t\t\t\t\"currentOpinion\": \"sdgsg\",\n" +
            "\t\t\t\t\"status\": 1,\n" +
            "\t\t\t\t\"numberOf\": 5,\n" +
            "\t\t\t\t\"remarks\": \"sgsg\",\n" +
            "\t\t\t\t\"label\": \"sds\",\n" +
            "\t\t\t\t\"operatorUserId\": \"超人\",\n" +
            "\t\t\t\t\"pages\": 2,\n" +
            "\t\t\t\t\"handleStatus\": 0,\n" +
            "\t\t\t\t\"cfOne\": null,\n" +
            "\t\t\t\t\"cfTwo\": null,\n" +
            "\t\t\t\t\"cfThree\": null,\n" +
            "\t\t\t\t\"deptName\": null,\n" +
            "\t\t\t\t\"rcreatetime\": null,\n" +
            "\t\t\t\t\"hstarttime\": null,\n" +
            "\t\t\t\t\"uuname\": null,\n" +
            "\t\t\t\t\"create_TIME_\": null,\n" +
            "\t\t\t\t\"name_\": \"登记人\",\n" +
            "\t\t\t\t\"end_TIME_\": null\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}\n";
    public final static String RSPONSE_UAAPPROVALOPINION_GETUSERFINISH="{\n" +
            "\t\t\"UaInFileTable\": {\n" +
            "\t\t\t\"total\": 1,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 94,\n" +
            "\t\t\t\t\"processInstanceId\": 975001,\n" +
            "\t\t\t\t\"inTime\": 1524453224000,\n" +
            "\t\t\t\t\"theReceivingId\": \"4548\",\n" +
            "\t\t\t\t\"theReceiving\": \"测试\",\n" +
            "\t\t\t\t\"userId\": 5,\n" +
            "\t\t\t\t\"userName\": \"超人\",\n" +
            "\t\t\t\t\"civilService\": \"gsg\",\n" +
            "\t\t\t\t\"civilNumber\": \"sgs\",\n" +
            "\t\t\t\t\"emergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\"inNumber\": \"sgsg\",\n" +
            "\t\t\t\t\"fileType\": \"sgsg\",\n" +
            "\t\t\t\t\"fileTitle\": \"32443\",\n" +
            "\t\t\t\t\"fileAbstract\": \"sgsgsg\",\n" +
            "\t\t\t\t\"fileName\": null,\n" +
            "\t\t\t\t\"filePath\": null,\n" +
            "\t\t\t\t\"fileAllName\": null,\n" +
            "\t\t\t\t\"currentOpinion\": \"sdgsg\",\n" +
            "\t\t\t\t\"status\": 1,\n" +
            "\t\t\t\t\"numberOf\": 5,\n" +
            "\t\t\t\t\"remarks\": \"sgsg\",\n" +
            "\t\t\t\t\"label\": \"sds\",\n" +
            "\t\t\t\t\"operatorUserId\": \"超人\",\n" +
            "\t\t\t\t\"pages\": 2,\n" +
            "\t\t\t\t\"handleStatus\": 0,\n" +
            "\t\t\t\t\"cfOne\": null,\n" +
            "\t\t\t\t\"cfTwo\": null,\n" +
            "\t\t\t\t\"cfThree\": null,\n" +
            "\t\t\t\t\"deptName\": null,\n" +
            "\t\t\t\t\"rcreatetime\": null,\n" +
            "\t\t\t\t\"hstarttime\": null,\n" +
            "\t\t\t\t\"uuname\": null,\n" +
            "\t\t\t\t\"create_TIME_\": null,\n" +
            "\t\t\t\t\"name_\": \"登记人\",\n" +
            "\t\t\t\t\"end_TIME_\": null\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}\n";
    public final static String RSPONSE_UAAPPROVALOPINION_VAGUEUAINFILETABLE="{\n" +
            "\t\t\"UaInFileTable\": {\n" +
            "\t\t\t\"total\": 11,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\t\"id\": 92,\n" +
            "\t\t\t\t\t\"processInstanceId\": null,\n" +
            "\t\t\t\t\t\"inTime\": 1524452034000,\n" +
            "\t\t\t\t\t\"theReceivingId\": 4548,\n" +
            "\t\t\t\t\t\"theReceiving\": \"测试\",\n" +
            "\t\t\t\t\t\"userId\": 5,\n" +
            "\t\t\t\t\t\"userName\": \"超人\",\n" +
            "\t\t\t\t\t\"civilService\": \"\",\n" +
            "\t\t\t\t\t\"civilNumber\": \"\",\n" +
            "\t\t\t\t\t\"emergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\t\"inNumber\": \"\",\n" +
            "\t\t\t\t\t\"fileType\": \"\",\n" +
            "\t\t\t\t\t\"fileTitle\": \"fhfhfhfhfh\",\n" +
            "\t\t\t\t\t\"fileAbstract\": \"\",\n" +
            "\t\t\t\t\t\"fileName\": null,\n" +
            "\t\t\t\t\t\"filePath\": null,\n" +
            "\t\t\t\t\t\"fileAllName\": null,\n" +
            "\t\t\t\t\t\"currentOpinion\": \"\",\n" +
            "\t\t\t\t\t\"status\": 0,\n" +
            "\t\t\t\t\t\"numberOf\": null,\n" +
            "\t\t\t\t\t\"remarks\": \"\",\n" +
            "\t\t\t\t\t\"label\": \"\",\n" +
            "\t\t\t\t\t\"operatorUserId\": \"超人\",\n" +
            "\t\t\t\t\t\"pages\": 6,\n" +
            "\t\t\t\t\t\"handleStatus\": 0,\n" +
            "\t\t\t\t\t\"cfOne\": null,\n" +
            "\t\t\t\t\t\"cfTwo\": null,\n" +
            "\t\t\t\t\t\"cfThree\": null\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"id\": 97,\n" +
            "\t\t\t\t\t\"processInstanceId\": null,\n" +
            "\t\t\t\t\t\"inTime\": null,\n" +
            "\t\t\t\t\t\"theReceivingId\": 4548,\n" +
            "\t\t\t\t\t\"theReceiving\": \"测试\",\n" +
            "\t\t\t\t\t\"userId\": 5,\n" +
            "\t\t\t\t\t\"userName\": \"超人\",\n" +
            "\t\t\t\t\t\"civilService\": \"\",\n" +
            "\t\t\t\t\t\"civilNumber\": \"\",\n" +
            "\t\t\t\t\t\"emergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\t\"inNumber\": \"\",\n" +
            "\t\t\t\t\t\"fileType\": \"\",\n" +
            "\t\t\t\t\t\"fileTitle\": \"66666666666\",\n" +
            "\t\t\t\t\t\"fileAbstract\": \"\",\n" +
            "\t\t\t\t\t\"fileName\": null,\n" +
            "\t\t\t\t\t\"filePath\": null,\n" +
            "\t\t\t\t\t\"fileAllName\": null,\n" +
            "\t\t\t\t\t\"currentOpinion\": \"\",\n" +
            "\t\t\t\t\t\"status\": 0,\n" +
            "\t\t\t\t\t\"numberOf\": null,\n" +
            "\t\t\t\t\t\"remarks\": \"\",\n" +
            "\t\t\t\t\t\"label\": \"\",\n" +
            "\t\t\t\t\t\"operatorUserId\": \"超人\",\n" +
            "\t\t\t\t\t\"pages\": 2,\n" +
            "\t\t\t\t\t\"handleStatus\": 0,\n" +
            "\t\t\t\t\t\"cfOne\": null,\n" +
            "\t\t\t\t\t\"cfTwo\": null,\n" +
            "\t\t\t\t\t\"cfThree\": null\n" +
            "\t\t\t\t}\n" +
            "\t\t\t],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 2\n" +
            "\t\t}\n" +
            "\t}\n";
    public final static String RSPONSE_UAAPPROVALOPINION_SELECTALLSTOP="{\n" +
            "\t\t\"UaInFileTable\": {\n" +
            "\t\t\t\"total\": 1,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 94,\n" +
            "\t\t\t\t\"processInstanceId\": 975001,\n" +
            "\t\t\t\t\"inTime\": 1524453224000,\n" +
            "\t\t\t\t\"theReceivingId\": \"4548\",\n" +
            "\t\t\t\t\"theReceiving\": \"测试\",\n" +
            "\t\t\t\t\"userId\": 5,\n" +
            "\t\t\t\t\"userName\": \"超人\",\n" +
            "\t\t\t\t\"civilService\": \"gsg\",\n" +
            "\t\t\t\t\"civilNumber\": \"sgs\",\n" +
            "\t\t\t\t\"emergencyDegree\": \"常规\",\n" +
            "\t\t\t\t\"inNumber\": \"sgsg\",\n" +
            "\t\t\t\t\"fileType\": \"sgsg\",\n" +
            "\t\t\t\t\"fileTitle\": \"32443\",\n" +
            "\t\t\t\t\"fileAbstract\": \"sgsgsg\",\n" +
            "\t\t\t\t\"fileName\": null,\n" +
            "\t\t\t\t\"filePath\": null,\n" +
            "\t\t\t\t\"fileAllName\": null,\n" +
            "\t\t\t\t\"currentOpinion\": \"sdgsg\",\n" +
            "\t\t\t\t\"status\": 1,\n" +
            "\t\t\t\t\"numberOf\": 5,\n" +
            "\t\t\t\t\"remarks\": \"sgsg\",\n" +
            "\t\t\t\t\"label\": \"sds\",\n" +
            "\t\t\t\t\"operatorUserId\": \"超人\",\n" +
            "\t\t\t\t\"pages\": 2,\n" +
            "\t\t\t\t\"handleStatus\": 0,\n" +
            "\t\t\t\t\"cfOne\": null,\n" +
            "\t\t\t\t\"cfTwo\": null,\n" +
            "\t\t\t\t\"cfThree\": null,\n" +
            "\t\t\t\t\"deptName\": null,\n" +
            "\t\t\t\t\"rcreatetime\": null,\n" +
            "\t\t\t\t\"hstarttime\": null,\n" +
            "\t\t\t\t\"uuname\": null,\n" +
            "\t\t\t\t\"create_TIME_\": null,\n" +
            "\t\t\t\t\"name_\": \"登记人\",\n" +
            "\t\t\t\t\"end_TIME_\": null\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}";
    public final static String RSPONSE_UAENROLMENT_VAGUENOTCONFERENCEINFOALL="{\n" +
            "\t\t\"UaEnrolment\": {\n" +
            "\t\t\t\"total\": 2,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 280,\n" +
            "\t\t\t\t\"conferenceInfoId\": 135,\n" +
            "\t\t\t\t\"userId\": 5,\n" +
            "\t\t\t\t\"userName\": \"测试:超人\",\n" +
            "\t\t\t\t\"reportingIssues\": null,\n" +
            "\t\t\t\t\"topicMaterialPath\": null,\n" +
            "\t\t\t\t\"status\": 3,\n" +
            "\t\t\t\t\"disc\": null,\n" +
            "\t\t\t\t\"stopTime\": 1523696009000,\n" +
            "\t\t\t\t\"conferenceName\": \"测试\",\n" +
            "\t\t\t\t\"conferenceType\": \"会议类型二\",\n" +
            "\t\t\t\t\"conferenceTime\": 1523696009000,\n" +
            "\t\t\t\t\"meetingName\": \"会议室三\",\n" +
            "\t\t\t\t\"conferenceInfoStatus\": 1,\n" +
            "\t\t\t\t\"signUp\": 1,\n" +
            "\t\t\t\t\"operatorUserId\": 5,\n" +
            "\t\t\t\t\"newInformation\": null\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"id\": 278,\n" +
            "\t\t\t\t\"conferenceInfoId\": 134,\n" +
            "\t\t\t\t\"userId\": 5,\n" +
            "\t\t\t\t\"userName\": \"测试:超人\",\n" +
            "\t\t\t\t\"reportingIssues\": \"99979\",\n" +
            "\t\t\t\t\"topicMaterialPath\": \"D:/webapp/download/\",\n" +
            "\t\t\t\t\"status\": 1,\n" +
            "\t\t\t\t\"disc\": \"\",\n" +
            "\t\t\t\t\"stopTime\": 1523689330000,\n" +
            "\t\t\t\t\"conferenceName\": \"23232\",\n" +
            "\t\t\t\t\"conferenceType\": \"会议类型二\",\n" +
            "\t\t\t\t\"conferenceTime\": 1523689330000,\n" +
            "\t\t\t\t\"meetingName\": \"会议室三\",\n" +
            "\t\t\t\t\"conferenceInfoStatus\": 1,\n" +
            "\t\t\t\t\"signUp\": 1,\n" +
            "\t\t\t\t\"operatorUserId\": 5,\n" +
            "\t\t\t\t\"newInformation\": 2\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}\n";
    public final static String RSPONSE_EXTERNALMEETING_VAGUEPARTICIPANTSALL="{\n" +
            "\t\t\"ExternalMeeting\": {\n" +
            "\t\t\t\"total\": 1,\n" +
            "\t\t\t\"rows\": [{\n" +
            "\t\t\t\t\"id\": 38,\n" +
            "\t\t\t\t\"externalMeetingId\": 37,\n" +
            "\t\t\t\t\"meetingName\": \"官方\",\n" +
            "\t\t\t\t\"userId\": 5,\n" +
            "\t\t\t\t\"userName\": \"测试:超人\",\n" +
            "\t\t\t\t\"signUp\": 3,\n" +
            "\t\t\t\t\"leaveReason\": null,\n" +
            "\t\t\t\t\"meetingType\": \"会议类型一\",\n" +
            "\t\t\t\t\"meetingTime\": 1523845591000,\n" +
            "\t\t\t\t\"meetingPlace\": \"开会1\",\n" +
            "\t\t\t\t\"externalMeetingStatus\": 1,\n" +
            "\t\t\t\t\"meetingRequirement\": \"是的根深蒂固\",\n" +
            "\t\t\t\t\"leaderShip\": \"sffs\",\n" +
            "\t\t\t\t\"approverName\": \"超人\",\n" +
            "\t\t\t\t\"newInformation\": 1\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"currentpage\": 1,\n" +
            "\t\t\t\"pageTotal\": 1\n" +
            "\t\t}\n" +
            "\t}";
    public final static String RSPONSE_UAAPPROVALOPINION_FINDNEXTACTIVITI="{\n" +
            "\t\t\"PackInfo\": [{\n" +
            "\t\t\t\"name\": \"登记人\",\n" +
            "\t\t\t\"receivetypeid\": \"1\",\n" +
            "\t\t\t\"presonsationid\": \"71,组员\",\n" +
            "\t\t\t\"signbatchtypeid\": \"cleanDoc\",\n" +
            "\t\t\t\"receivedeptid\": \"station\",\n" +
            "\t\t\t\"conditionText\": \"${sequence == 'ubreak'}\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"name\": \"办公室主任\",\n" +
            "\t\t\t\"receivetypeid\": \"1\",\n" +
            "\t\t\t\"presonsationid\": \"54,主任\",\n" +
            "\t\t\t\"signbatchtypeid\": \"deptLeaderCheck\",\n" +
            "\t\t\t\"receivedeptid\": \"station\",\n" +
            "\t\t\t\"conditionText\": \"${sequence == 'dwcheck'}\"\n" +
            "\t\t}],\n" +
            "\t\t\"presonsationid\": \"56,科室负责人\"\n" +
            "\t}\n";
    public final static String RSPONSE_UAAPPROVALOPINION_GETALLUSERACTIVITI="{\n" +
            "\t\t\"currentStation\": [{\n" +
            "\t\t\t\"id\": 3,\n" +
            "\t\t\t\"userName\": \"xx\",\n" +
            "\t\t\t\"passWord\": \"e10adc3949ba59abbe56e057f20f883e\",\n" +
            "\t\t\t\"userSex\": 1,\n" +
            "\t\t\t\"status\": 1,\n" +
            "\t\t\t\"userPhone\": \"15888888888\",\n" +
            "\t\t\t\"userAddress\": \"23\",\n" +
            "\t\t\t\"userCreateTime\": 1517217525000,\n" +
            "\t\t\t\"userUpdateTime\": 1523600782000,\n" +
            "\t\t\t\"deptId\": 4548,\n" +
            "\t\t\t\"deptName\": \"测试\",\n" +
            "\t\t\t\"operatorUserId\": 123456,\n" +
            "\t\t\t\"userRoomNumber\": \"sd\",\n" +
            "\t\t\t\"auditStatus\": 1,\n" +
            "\t\t\t\" userEnglishName \": \"zhuH\",\n" +
            "\t\t\t\"userKey\": \"1\",\n" +
            "\t\t\t\"userIdNumber\": \"123\",\n" +
            "\t\t\t\"userDateBirth\": 1517217573000,\n" +
            "\t\t\t\"personStatus\": 1\n" +
            "\t\t}]\n" +
            "\t}";
    public final static String RSPONSE_UAAPPROVALOPINION_NEXTUSERACTIVITI="";
    public final static String RSPONSE_UAINFILETABLE_SAVEUAINFILETABLE="";
    public final static String RSPONSE_KNOWLEDGEUPLOADFILE_PERSONALADD="";

    public final static String RSPONSE_KNOWLEDGEUPLOADFILE_FINDUPLOADFILE="{\"knowledgeUploadFiles\":\n" +
            "{\"total\": 4,\n" +
            "\"rows\": [\n" +
            "{ \"id\":252,\n" +
            "\"uploadFileName\":\"10.jpg\",\n" +
            "\"uploadFilePath\":\"/root/filewebapp/filedownload/knowledge/\",\n" +
            "\"upFileUserId\":5,\"upFileCategoryName\":\"个人一\",\n" +
            "\"knowledgeCategoryId\":null,\n" +
            "\"uploadFileCreateTime\":1524460491000,\n" +
            "\"uploadFileDownloadSum\":0,\n" +
            "\"uploadFileDelete\":1,\n" +
            "\"uploadFileUserName\":\"超人\",\n" +
            "\"uploadFileDeptName\":\"测试\",\n" +
            "\"uploadFileSaveName\":\"admin_10_1524460491524.jpg\",\n" +
            "\"saveOperateUserid\":5\n" +
            "}\n" +
            "],\n" +
            "\"currentpage\":1,\n" +
            "\"pageTotal\":1\n" +
            "}\n" +
            "}\n";

    public final static String RSPONSE_KNOWLEDGECATEGORY_FINDALL="{ \"aList\" : [{ \"id\":12,\n" +
            "\"knowledgeType\":2,\n" +
            "\"knowledgeCategory\":\"个人一\",\n" +
            "\"knowledgeDept\":\"测试\",\n" +
            "\"knowledgeSum\":4,\n" +
            "\"operatorUserId\":5,\n" +
            "\"knowledgeCreateTime\":1524274377000,\n" +
            "\"knowledgeUpdateTime\":1524460269000,\n" +
            "\"knowledgeSort\":\"1\"\n" +
            "},\n" +
            "{ \"id\":12,\n" +
            "\"knowledgeType\":0,\n" +
            "\"knowledgeCategory\":\"个人\",\n" +
            "\"knowledgeDept\":\"测\",\n" +
            "\"knowledgeSum\":4,\n" +
            "\"operatorUserId\":5,\n" +
            "\"knowledgeCreateTime\":1524274377000,\n" +
            "\"knowledgeUpdateTime\":1524460269000,\n" +
            "\"knowledgeSort\":\"1\"\n" +
            "},\n" +
            "{ \"id\":12,\n" +
            "\"knowledgeType\":1,\n" +
            "\"knowledgeCategory\":\"个人1\",\n" +
            "\"knowledgeDept\":\"测1\",\n" +
            "\"knowledgeSum\":4,\n" +
            "\"operatorUserId\":5,\n" +
            "\"knowledgeCreateTime\":1524274377000,\n" +
            "\"knowledgeUpdateTime\":1524460269000,\n" +
            "\"knowledgeSort\":\"1\"\n" +
            "}\n" +
            "],\n" +
            "\"station\":\"keshizhishiku\"\n" +
            "}";
    public final static String RSPONSE_LOGIN_USERLOGIN="{\n" +
            "\t\t\"userKey\": " +
            "\"3DAEA4D5D8B9FF23E522F33DC6E37077EF5118BB5F38F64554CBC68482AA64F2DCB9596F2BEA5AD352375FA391F7D724C225F5B2775291BF2895CF6F2F0E11045192F93C1C2890CD\"\n" +
            "\t}";
    public final static String RSPONSE_NOTICEPUBLICCONTROLLER_NOTICEPUBLICLIST="{\"noticeList\":{\"total\":4," +
            "\"rows\":[{\"id\":14,\"notice\":\"测试通知公告04\",\"noticeContent\":\"测试通知公告测试通知公告测试通知公告\"," +
            "\"issuer\":\"公告管理员\",\"issuerId\":171,\"issueDate\":1523419503000,\"noticeState\":null," +
            "\"noticePubFileList\":null},{\"id\":13,\"notice\":\"测试通知公告02\",\"noticeContent\":\"测试通知公告测试通知公告测试通知公告\"," +
            "\"issuer\":\"公告管理员\",\"issuerId\":171,\"issueDate\":1523419492000,\"noticeState\":null," +
            "\"noticePubFileList\":null},{\"id\":12,\"notice\":\"测试通知公告01\",\"noticeContent\":\"测试通知公告测试通知公告测试通知公告\"," +
            "\"issuer\":\"公告管理员\",\"issuerId\":171,\"issueDate\":1523419484000,\"noticeState\":null," +
            "\"noticePubFileList\":null}],\"currentpage\":1,\"pageTotal\":2}}";
}
