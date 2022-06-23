package com.vdevline.service;

import com.vdevline.model.ListCondition;
import com.vdevline.model.ListResult;

import java.util.Map;

public interface VDLService {

    public Map<String, String> getData(int modelId, String id);

    public ListResult getList(int modelId, ListCondition condition);

    public Object createData(int modelId, Map<String, String> data);

    public void updateData(int modelId, Map<String, String> data);

    public void deleteData(int modelId, String id);

    public void execProcess(int processId, Map<String, String> data);

}
