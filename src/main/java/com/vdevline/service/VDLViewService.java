package com.vdevline.service;

import com.vdevline.model.ListCondition;
import com.vdevline.model.ListResult;
import com.vdevline.model.Result;
import com.vdevline.model.View;

import java.util.Map;

public interface VDLViewService {

    public View getView();

    public Map<String, String> getData(int modelId, int viewId, Map<String, String> data);

    public Map<String, String> getData(int modelId, int viewId, String id);

    /**
     *
     * @param modelId  主数据实体，其他实体的列都相对于这个主实体来表示
     * @param viewId
     * @param condition
     * @return
     */
    public ListResult getList(int modelId, int viewId, ListCondition condition);

    public Object createData(int modelId, int viewId, Map<String, String> data);

    public void updateData(int modelId, int viewId, Map<String, String> data);

    public void deleteData(int modelId, int viewId, Map<String, String> data);

    public void deleteData(int modelId, int viewId, String id);

    public void execProcess(int processId, int viewId, Map<String, String> data);

}
