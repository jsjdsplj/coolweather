package com.example.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by jack on 2017/8/15.
 */

public class Province extends DataSupport{
    private int id;
    private String provinceName;
    private int provinveCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinveCode() {
        return provinveCode;
    }

    public void setProvinveCode(int provinveCode) {
        this.provinveCode = provinveCode;
    }
}
