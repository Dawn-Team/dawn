package com.arvinsichuan.dawn.pmh.datacleaning;

import com.arvinsichuan.dawn.pmh.datasource.DataSourceCube;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
    Author:Administrator
    Time:2017/11/1 16:40
*/
public interface DataPreProcessor {
    public static Map<String,String> preProcessBeanMap = new TreeMap<>();

    public DataSourceCube cleanDataSource();
}
