package com.arvinsichuan.dawn.pmh.datacleaning.impl;

import com.arvinsichuan.dawn.pmh.datacleaning.DataPreProcessor;
import com.arvinsichuan.dawn.pmh.datasource.DataSourceCube;
import org.springframework.stereotype.Service;

import java.util.*;

/*
    Author:Administrator
    Time:2017/11/1 16:43
*/
@Service("averageDataCleanMethod")
public class AverageDataPreProcessor implements DataPreProcessor {
    public AverageDataPreProcessor() {
        preProcessBeanMap.put("average","averageDataCleanMethod");
    }

    @Override
    public DataSourceCube cleanDataSource(DataSourceCube dataSourceCube, Map param) {
        for (Map<String, List> squareLevelNames:dataSourceCube.getCube()) {
            squareLevelNames = cleanSquareLevelNames(squareLevelNames);
        }
        return dataSourceCube;
    }

    private Map<String, List> cleanSquareLevelNames(Map<String, List> squareLevelNames) {
        Map<String ,List> newsquareLevelNames = new HashMap<String ,List>();
        for (String key : squareLevelNames.keySet()) {
            List<String> value = squareLevelNames.get(key);
            System.out.println("Pre:"+key + "  " + value.toString());
            value = cleanCubeLevelNames(value);
            System.out.println("\nBel:"+key + "  " + value.toString());
            newsquareLevelNames.put(key,value);
        }
        return newsquareLevelNames;
    }

    private List<String> cleanCubeLevelNames(List<String> cubeLevelNames){
        Double sum = 0.0;
        for (String value : cubeLevelNames) {
            if (!value.isEmpty()) {
                Double num = 0.0;
                try {
                    num = Double.parseDouble(value);
                }catch (Exception e){
//                    throw new UnComputationException("This kind of data can't computation");
                    e.printStackTrace();
                    return cubeLevelNames;
                }
                sum = sum + num;
            }
        }
        Double average = sum/cubeLevelNames.size();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++average = "+average);
        for (int i =0 ; i<cubeLevelNames.size();i++) {
            String value = cubeLevelNames.get(i);
            if (value.isEmpty()) {
                cubeLevelNames.set(i,average.toString());
                System.out.print("String = |"+value+"|");
            }
        }
        return cubeLevelNames;
    }
}
