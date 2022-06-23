package com.vdevline.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListResult {
    private Map<String, Integer> nameIndex = new HashMap<>();
    private List<List<String>> retult = new ArrayList<>();

    public Map<String, Integer> getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(Map<String, Integer> nameIndex) {
        this.nameIndex = nameIndex;
    }

    public List<List<String>> getRetult() {
        return retult;
    }

    public void setRetult(List<List<String>> retult) {
        this.retult = retult;
    }
}
