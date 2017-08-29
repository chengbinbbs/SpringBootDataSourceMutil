package com.chengbinbbs.config.mybatis;

/**
 * @author zhangcb
 * @created on 2017-07-10 17:30.
 */
public enum DataSourceType {
    read("read", "从库"), write("write", "主库");

    private String type;

    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
