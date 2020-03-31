package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils;

import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.sp.SpUser;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.body.ProgressResponseCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/12
 * desc   :  隐患排查获取数据工具类
 */
public class HiddenCheckHttpUtils {

    /**
     * 获取检查列表
     * @param keyWord
     * @param start
     * @param length
     * @param callback
     */
    public static void getCheckList(String keyWord, int start, int length, String inspectionType, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/projectInspection/list")
                .params("keyWord", keyWord)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .params("inspection_type", inspectionType)
                .execute(callback);
    }

    /**
     * 获取领导带班检查详情
     * @param id
     * @param callback
     */
    public static void getCheckDetail(String id, String inspectionType, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/projectInspection/getOne")
                .params("id", id)
                .params("token", SpUser.INSTANCE.getToken())
                .params("inspection_type", inspectionType)
                .execute(callback);
    }

    /**
     * 获取检查问题列表
     * @param start
     * @param length
     * @param inspectionId
     * @param projectId
     * @param inspectionTypeDetail
     * @param callback
     */
    public static void getCheckQuestionList(int start, int length, String inspectionId, String projectId, String inspectionTypeDetail, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/projectInspection/specialsafetyinspectionListApp")
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("inspection_id", inspectionId)
                .params("project_id", projectId)
                .params("inspection_type_detail", inspectionTypeDetail)
                .execute(callback);
    }

    /**
     * 获取添加检查id
     * @param callback
     */
    public static void getCheckAddId(PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/projectInspection/getId")
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }

    public static void addLeaderCheck(String projectId, String projectName, String projectManager, String createTime, String createId, String imageProgress, String manageReq,
                                      String inspectionDate, String classLeader, String rectificatDate, String chargePerson, String addId,  ProgressResponseCallBack progressCallBack,PretreatmentCallback callback) {
        File file1 = new File("/storage/emulated/legacy/DCIM/Camera/IMG_20200320_154442.jpg");
        File file2 = new File("/storage/emulated/legacy/DCIM/Camera/IMG_20200320_154436.jpg");
        List<File> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        EasyHttp.post("apia/v1/projectInspection/addOrUpdate")
                .params("project_id", projectId)
                .params("project_name", projectName)
                .params("project_manager", projectManager)
                .params("create_time", createTime)
                .params("create_id", createId)
                .params("image_progress", imageProgress)
                .params("manage_req", manageReq)
                .params("inspection_date", inspectionDate)
                .params("class_leader", classLeader)
                .params("rectificat_date", rectificatDate)
                .params("charge_person", chargePerson)
                .params("inspection_type", "1")
                .params("add_detail_id", addId)
                .params("insertOrUpdate", "1")
                .params("file_path_update","")
                .params("token", SpUser.INSTANCE.getToken())
                .addFileParams("file", fileList, progressCallBack)//多文件
                .execute(callback);

    }

    /**
     * 添加项目问题
     * @param projectId
     * @param projectName
     * @param problemDesc
     * @param rectificationReq
     * @param rectificationPerson
     * @param completeDate
     * @param remark
     * @param inspectionType
     * @param addId
     * @param fileList
     * @param progressCallBack
     * @param callback
     */
    public static void addCheckQuestion(String projectId, String projectName, String problemDesc, String rectificationReq, String rectificationPerson,
                                        String completeDate, String remark, String inspectionType, String addId, List<File> fileList, ProgressResponseCallBack progressCallBack, PretreatmentCallback callback) {

//        File file1 = new File("/storage/emulated/legacy/DCIM/Camera/IMG_20200320_154442.jpg");
//        File file2 = new File("/storage/emulated/legacy/DCIM/Camera/IMG_20200320_154436.jpg");
//        List<File> fileList = new ArrayList<>();
//        fileList.add(file1);
//        fileList.add(file2);
        EasyHttp.post("apia/v1/projectInspection/addOrUpdateDetail")
                .params("project_id", projectId)
                .params("project_name", projectName)
                .params("problem_description", problemDesc)
                .params("rectification_requirements", rectificationReq)
                .params("rectification_person", rectificationPerson)
                .params("complete_date", completeDate)
                .params("remark", remark)
                .params("inspection_type", inspectionType)
                .params("add_detail_id", addId)
                .params("insertOrUpdate", "1")
                .params("file_path_update", "")
                .params("token", SpUser.INSTANCE.getToken())
                //.params("file", file1, progressCallBack)//单文件
                .addFileParams("file", fileList, progressCallBack)//多文件
                .execute(callback);
    }
}
