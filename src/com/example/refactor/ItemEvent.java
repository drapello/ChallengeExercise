package com.example.refactor;

public class ItemEvent {
    private String id;
    private String ql;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    private String site;
    private String workbench;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQl() {
        return ql;
    }

    public void setQl(String ql) {
        this.ql = ql;
    }

    public String getWorkbench() {
        return workbench;
    }

    public void setWorkbench(String workbench) {
        this.workbench = workbench;
    }

}
