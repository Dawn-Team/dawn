/*
 *     This project is part of project Dawn, A Data Process Solution.
 *     Copyright (C) 2017, Dawn team<https://github.com/Dawn-Team>.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.arvinsichuan.dawn.pmh.datasource;

import com.arvinsichuan.dawn.pmh.datasource.exceptions.ParametersNotFoundException;
import com.arvinsichuan.general.exceltool.ExcelTool;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 31-Oct-17
 * <p>
 * Package: com.arvinsichuan.dawn.pmh.datasource
 *
 * @author ArvinSiChuan
 */
@Service("excelDPPDataSource")
@Scope("prototype")
public class ExcelDPPDataSourceImpl implements DPPDatasource {
    public static final String EXCEL_FILE_PATH_PARA_NAME = "excelPath";
    private static final int TITLE_ROW_INDEX = 0;

    private Workbook workbook;

    public ExcelDPPDataSourceImpl() {

    }

    public ExcelDPPDataSourceImpl(Map parameters) throws ParametersNotFoundException {
        testParameters(parameters);
    }

    private void testParameters(Map parameters) throws ParametersNotFoundException {
        boolean flag = true;
        if (parameters == null) {
            flag = false;
            throw new ParametersNotFoundException(EXCEL_FILE_PATH_PARA_NAME);
        } else if (parameters.get(EXCEL_FILE_PATH_PARA_NAME) == null) {
            flag = false;
            throw new ParametersNotFoundException(EXCEL_FILE_PATH_PARA_NAME);
        } else if (!(parameters.get(EXCEL_FILE_PATH_PARA_NAME) instanceof String)) {
            flag = false;
            throw new ParametersNotFoundException(EXCEL_FILE_PATH_PARA_NAME);
        }
    }

    @Override
    public void setDatasourceParameters(Map parameters) throws ParametersNotFoundException, IOException,
            InvalidFormatException {
        testParameters(parameters);
        String filePath = (String) parameters.get(EXCEL_FILE_PATH_PARA_NAME);
        workbook = ExcelTool.readExcelFile(new File(filePath));
    }

    @Override
    public DataSourceCube getDatasourceCubeWithParameters(Map parameters) throws ParametersNotFoundException,
            IOException, InvalidFormatException {
        setDatasourceParameters(parameters);
        return getDatasourceCube();
    }


    @Override
    public DataSourceCube getDatasourceCube() {
        DataSourceCube dsCube = new DataSourceCube();
        List<String> sheetsNames = getSheetsNames();
        dsCube.setCubeLevelNames(sheetsNames);
        Map<String, List> squareLevelNames = new TreeMap<>();
        sheetsNames.forEach(sheetsName -> {
            squareLevelNames.put(sheetsName, getTableTitles(sheetsName));
        });
        dsCube.setSquareLevelNames(squareLevelNames);
        dsCube.setCube(getCube());
        return dsCube;
    }


    private List<String> getSheetsNames() {
        List<String> sheets = new ArrayList<>();
        int sheetNum = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetNum; i++) {
            sheets.add(workbook.getSheetName(i));
        }
        return sheets;
    }


    private List<Map<String, List>> getCube() {
        List<Map<String, List>> cube = new ArrayList<>();
        List<String> sheetNames = getSheetsNames();
        sheetNames.forEach(sheetName -> {
            Map<String, List> sheetContent = new TreeMap<>();
            cube.add(sheetContent);
            Sheet sheet = workbook.getSheet(sheetName);
            //default 0 as the title row
            int row = sheet.getPhysicalNumberOfRows(), col = sheet.getRow(0).getPhysicalNumberOfCells();
            for (int i = 0; i < col; i++) {
                List<String> colElements = new ArrayList<>();
                Cell titleCell = sheet.getRow(TITLE_ROW_INDEX).getCell(i);
                String title = smartTypeDecisionToString(titleCell);
                sheetContent.put(title, colElements);
                for (int j = 1; j < row; j++) {
                    Cell contentCell = sheet.getRow(j).getCell(i);
                    colElements.add(smartTypeDecisionToString(contentCell));
                }
            }
        });
        return cube;
    }

    private List<String> getTableTitles(String sheetName) {
        List<String> titles = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);
        //default 0 as the title row
        int row = sheet.getPhysicalNumberOfRows(), col = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 0; i < col; i++) {
            Cell titleCell = sheet.getRow(TITLE_ROW_INDEX).getCell(i);
            String title = smartTypeDecisionToString(titleCell);
            titles.add(title);
        }
        return titles;
    }


    private String smartTypeDecisionToString(Cell cell) {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        String parsedValue = "";
        if (cell == null) {
            parsedValue = "NULL";
        } else {
            switch (cell.getCellTypeEnum()) {
                case _NONE:
                    parsedValue = "";
                    break;
                case ERROR:
                    parsedValue = "";
                    break;
                case BOOLEAN:
                    parsedValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case STRING:
                    parsedValue = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    parsedValue = String.valueOf(cell.getNumericCellValue());
                    break;
                case BLANK:
                    parsedValue = "";
                    break;
                case FORMULA:
                    CellValue cellValue = evaluator.evaluate(cell);
                    parsedValue = smartTypeDecisionToString(cell);
                    break;
                default:
                    break;
            }
        }
        return parsedValue;
    }

}
