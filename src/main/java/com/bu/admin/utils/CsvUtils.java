package com.bu.admin.utils;

import com.bu.admin.business.uda.enums.AreaCodes;
import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCodes;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CsvUtils {

    private CsvUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);
    private static final String DECIMAL_TRANS_ERROR = "BigDecimal trans error";


    /*
       @read the data of csv file into a list of object with propNames
    */
    public static <T> List<T> readCsvDeal(String excelName, Class<T> entityClass, String[] propNames, Integer firstRowNum, Boolean checkParamsNum) {
        String fileType = excelName.substring(excelName.lastIndexOf(".") + 1);
        if (!"csv".equals(fileType)) {
            throw new BasicException(ErrorCodes.DataDeal.INPUT_FILE_ERROR);
        }
        List<T> list = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(excelName), StandardCharsets.UTF_8))) {
            if (null == firstRowNum) firstRowNum = 0;
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            int cells;
            int dealNum = 0;
            for (CSVRecord csvRecord : csvParser) {
                if (Boolean.TRUE.equals(checkParamsNum) && csvRecord.size() < propNames.length) {
                    throw new BasicException(ErrorCodes.DataDeal.EXCEL_DATA_MIS_PARAM);
                }
                cells = Math.min(csvRecord.size(), propNames.length);
                if (dealNum >= firstRowNum) {
                    setCsvCellIntoEntity(csvRecord, cells, propNames, entityClass, list);
                }
                dealNum += 1;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new BasicException(ErrorCodes.DataDeal.INPUT_FILE_ERROR);
        }
        return list;
    }


    /*
    @write the data into a csv file with head params
 */
    public static void writeCsvFile(List<String[]> objectData, String[] headParams, String targetFileUrl) {
        String fileType = targetFileUrl.substring(targetFileUrl.lastIndexOf(".") + 1);
        if (!"csv".equals(fileType)) {
            throw new BasicException(ErrorCodes.DataDeal.INPUT_FILE_ERROR);
        }
        try (FileWriter fileWriter = new FileWriter(targetFileUrl)) {
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
            for (int rowNum = 0; rowNum < objectData.size() + 1; rowNum++) {
                if (rowNum == 0) {
                    List<String> headList = Arrays.asList(headParams);
                    csvPrinter.printRecord(headList);
                } else {
                    List<String> params = Arrays.asList(objectData.get(rowNum - 1));
                    csvPrinter.printRecord(params);
                }
            }
            csvPrinter.flush();
        } catch (BasicException e1) {
            throw e1;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    private static <T> void setCsvCellIntoEntity(CSVRecord csvRecord, int cells, String[] propNames, Class<T> entityClass, List<T> list) {
        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            for (int cellNum = 0; cellNum < cells; cellNum++) {
                String propName = propNames[cellNum];
                String value = csvRecord.get(cellNum).trim();
                logger.debug("set propNam is [{}],value is [{}]", propName, value);
                if (StringUtils.isNotEmpty(value)) {
                    setDataIntoEntity(entity, value, propName);
                }
            }
            list.add(entity);
        } catch (Exception e) {
            logger.error("get entity method error,error message is:", e);
            throw new BasicException(ErrorCodes.DataDeal.EXCEL_NOT_DATA_ERROR);
        }
    }


    private static <T> void setDataIntoEntity(T entity, String value, String propName) throws InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(entity.getClass(), propName);
        if (null != propertyDescriptor) {
            if (propertyDescriptor.getPropertyType() == BigDecimal.class) {
                if (isNumericWithDecimal(value)) {
                    invokeWriteData(propertyDescriptor, entity, new BigDecimal(value));
                } else {
                    logger.info("set propNam is [{}],value is [{}],trans error", propName, value);
                    propertyDescriptor = BeanUtils.getPropertyDescriptor(entity.getClass(), "transErrorParam");
                    invokeWriteData(propertyDescriptor, entity, propName);
                    propertyDescriptor = BeanUtils.getPropertyDescriptor(entity.getClass(), "transErrorMessage");
                    invokeWriteData(propertyDescriptor, entity, DECIMAL_TRANS_ERROR);
                }
            } else if (propertyDescriptor.getPropertyType() == LocalDate.class) {
                propertyDescriptor.getWriteMethod().invoke(entity, DateConverterUtils.getStringDateAllFormat(value));
            } else if (propertyDescriptor.getPropertyType() == Integer.class) {
                propertyDescriptor.getWriteMethod().invoke(entity, Double.valueOf(value).intValue());
            } else {
                propertyDescriptor.getWriteMethod().invoke(entity, value.trim());
            }
        }
    }


    private static boolean isNumericWithDecimal(String str) {
        if (Boolean.TRUE.equals(str.matches("-?\\d+(\\.\\d+)?")))
            return true;
        return str.matches("[-+]?\\d+(\\.\\d+)([eE][-+]?\\d+)?");
    }


    private static void invokeWriteData(PropertyDescriptor propertyDescriptor, Object entity, Object value) {
        if (null == propertyDescriptor) {
            logger.info("no property get ...............");
            return;
        }
        try {
            propertyDescriptor.getWriteMethod().invoke(entity, value);
        } catch (Exception e) {
            logger.info("property write error  ...............");
        }
    }

    public static void main(String[] args) {
        // Do nothing because of X and Y.
        List<String> areaList = Arrays.stream(AreaCodes.values())
                .map(Enum::name)
                .toList();
        logger.info("areaList is [{}]", areaList);
        areaList.forEach(a->{
            logger.info("area is [{}]", a);
            AreaCodes areaCode = AreaCodes.valueOf(a);
            logger.info("area is [{}]", areaCode);
        });
    }

}
