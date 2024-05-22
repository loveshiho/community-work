package com.akai.system.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*构建树形结构实体类*/
public class TreeSelect implements Serializable {
    private Long id;
    private String label;
    private List<TreeSelect> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeSelect> getChildren() {
        return children;
    }

    public void setChildren(List<TreeSelect> children) {
        this.children = children;
    }

    public TreeSelect() {
    }

    public TreeSelect(SysDept dept) {
        if (dept != null) {
            this.id = dept.getDeptId();
            this.label = dept.getDeptName();
            List<TreeSelect> list = new ArrayList<>();
            dept.getChildren().forEach(item -> {
                TreeSelect treeSelect = new TreeSelect(item);
                list.add(treeSelect);
            });
            this.children = list;
        }
    }

}
