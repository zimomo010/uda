package com.bu.admin.business.scheduletask.service.impl;

import com.bu.admin.business.scheduletask.request.WebExportScheduleTaskExecuteLogRequest;
import com.bu.admin.business.scheduletask.service.ScheduleTaskService;
import com.bu.admin.feign.bo.scheduletask.PageQueryScheduleTaskExecuteLogBO;
import com.bu.admin.feign.bo.scheduletask.ScheduleTaskExecuteLogResponseBO;
import com.bu.admin.feign.bo.scheduletask.ScheduleTaskLogHTMValuationSummaryBO;
import com.bu.admin.feign.bo.scheduletask.ScheduleTaskLogMTMValuationSummaryBO;
import com.bu.admin.feign.service.ScheduleTaskRpcClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.BeanCopyUtils;
import com.bu.admin.utils.JSONUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 计划任务 service impl
 *
 * @author liujiegang
 * @date 2024/8/13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    private final ScheduleTaskRpcClient scheduleTaskRpcClient;

    @Override
    public SXSSFWorkbook generateExcel(WebExportScheduleTaskExecuteLogRequest exportRequest) {
        PageQueryScheduleTaskExecuteLogBO pageQueryBO = BeanCopyUtils.convertBusinessValue(exportRequest, PageQueryScheduleTaskExecuteLogBO.class);
        pageQueryBO.setPageNum(1);
        pageQueryBO.setPageSize(Integer.MAX_VALUE);

        JsonObject rpcPageResult = ResponseCheckUtils.checkResponse(scheduleTaskRpcClient.pageForExecuteLog(pageQueryBO,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).getAsJsonObject();

        JsonArray rpcUserData = rpcPageResult.getAsJsonArray("dataList");
        List<ScheduleTaskExecuteLogResponseBO> executeLogs = JSONUtils.getJson(rpcUserData.toString(), new TypeToken<List<ScheduleTaskExecuteLogResponseBO>>() {
        }.getType());

        if ("HTM_VALUATION_SUMMARY".equals(exportRequest.getBusType())) {
            return generateHTMExcel(executeLogs);
        } else if ("MTM_VALUATION_SUMMARY".equals(exportRequest.getBusType())) {
            return generateMTMExcel(executeLogs);
        }
        return null;
    }

    /**
     * 生成HTM类型任务的执行日志excel
     *
     * @param executeLogs
     * @return
     */
    private SXSSFWorkbook generateHTMExcel(List<ScheduleTaskExecuteLogResponseBO> executeLogs) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rows = 0;
        int cols = 0;

        // 表头
        String[] columns = new String[]{"Status", "Note ID", "NAV", "Price %", "Value Date", "Pull to Par Adjustment (1)", "Pull to Par Adjustment (2)",
                "Accrued Interest (1)", "Accrued Interest (2)", "FX PNL (1)", "FX PNL (2)", "Cashflow Post-issuance in Issue Currency (1)",
                "Cashflow Post-issuance in Issue Currency (2)", "Fx Rate", "Accumulated Accrual (1)", "Accumulated Accrual (2)", "Accumulated Accrual Daily Diffenece (1)",
                "Accumulated Accrual Daily Diffenece (2)", "Accumulated Accrual Daily Diffenece Check", "Cause Of Failure", "Execute Date"};

        Row head = sheet.createRow(rows++);
        CellStyle headStyle = getHeadCellStyle(workbook);
        for (int i = 0; i < columns.length; ++i) {
            addCellWithStyle(head, cols++, headStyle).setCellValue(columns[i]);
        }

        // 表内容
        CellStyle bodyStyle = getBodyCellStyle(workbook);
        for (ScheduleTaskExecuteLogResponseBO executeLog : executeLogs) {
            ScheduleTaskLogHTMValuationSummaryBO htmValuationSummary = null;
            if (StringUtils.hasLength(executeLog.getValuationSummaryData())) {
                htmValuationSummary = JSONUtils.getJson(executeLog.getValuationSummaryData(), ScheduleTaskLogHTMValuationSummaryBO.class);
            }
            ScheduleTaskLogHTMValuationSummaryBO.TransactionValuationSummary valuationSummary = htmValuationSummary != null ?
                    htmValuationSummary.getTransactionValuationSummary() : null;

            if (null == valuationSummary) {
                valuationSummary = setHtmValuationBlankData();
            }
            cols = 0;
            Row row = sheet.createRow(rows++);
            addCellWithStyle(row, cols++, bodyStyle).setCellValue("SUCCESS".equals(executeLog.getStatus()) ? "执行成功" : "执行失败");
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(executeLog.getBillNoteId());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getValuationNav());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getValuationPrice());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getValuationDate());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getPollToParAdjCcy1());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getPollToParAdjCcy2());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getBondAccrualDiff1());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getBondAccrualDiff2());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getFxPnlDiff1());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getFxPnlDiff2());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getPostCashAssetCcyDiff1());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getPostCashAssetCcyDiff2());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getFxRate());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getAccumulatedAccrualCcy1());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getAccumulatedAccrualCcy2());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getAccumulatedAccrualDiff1());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getAccumulatedAccrualDiff2());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(valuationSummary.getAccumulatedDailyCheckMark());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(executeLog.getFailReason());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(executeLog.getExecuteTime());
            log.debug("cols is  {}", cols);
        }
        return workbook;
    }

    private ScheduleTaskLogHTMValuationSummaryBO.TransactionValuationSummary setHtmValuationBlankData() {
        ScheduleTaskLogHTMValuationSummaryBO.TransactionValuationSummary valuationSummary = new ScheduleTaskLogHTMValuationSummaryBO.TransactionValuationSummary();
        valuationSummary.setValuationNav("");
        valuationSummary.setValuationPrice("");
        valuationSummary.setValuationDate("");
        valuationSummary.setPollToParAdjCcy1("");
        valuationSummary.setPollToParAdjCcy2("");
        valuationSummary.setBondAccrualDiff1("");
        valuationSummary.setBondAccrualDiff2("");
        valuationSummary.setFxPnlDiff1("");
        valuationSummary.setFxPnlDiff2("");
        valuationSummary.setPostCashAssetCcyDiff1("");
        valuationSummary.setPostCashAssetCcyDiff2("");
        valuationSummary.setFxRate("");
        valuationSummary.setAccumulatedAccrualCcy1("");
        valuationSummary.setAccumulatedAccrualCcy2("");
        valuationSummary.setAccumulatedAccrualDiff1("");
        valuationSummary.setAccumulatedAccrualDiff2("");
        valuationSummary.setAccumulatedDailyCheckMark("");
        return valuationSummary;
    }

    /**
     * 生成MTM类型任务的执行日志excel
     *
     * @param executeLogs
     * @return
     */
    private SXSSFWorkbook generateMTMExcel(List<ScheduleTaskExecuteLogResponseBO> executeLogs) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        Integer rows = 0;
        int cols = 0;

        // 表头
        String[] columns = new String[]{"Status", "End Client", "End Client Long Name", "Note Holder", "Note Holder Long Name",
                "Note ID", "Note ISIN", "Notional", "Issue Ccy", "NAV in Issue ccy", "NAV%", "CCY", "Fee Accrual", "Current int. Accrual",
                "Coupon Payable to Client", "Cash Asset", "Funding Amount", "Outstanding FX Notional", "Outstanding Bond Notional",
                "Fee Accrual weekly Difference", "Current int. Accrual weekly Difference", "Coupon Payable to Client weekly Difference",
                "Cash Asset weekly Difference", "Funding Amount weekly Difference", "Outstanding FX Notional weekly Difference",
                "Outstanding Bond Notional weekly Difference", "Bond Notional+Fee Accrual+Int.Accrual+Coupon+Financing Amount weekly Diff",
                "Bond Notional+Fee Accrual+Int.Accrual+Coupon+Financing Amount weekly check", "Cause Of Failure", "Execute Date"};

        Row head = sheet.createRow(rows++);
        CellStyle headStyle = getHeadCellStyle(workbook);
        for (String column : columns) {
            addCellWithStyle(head, cols++, headStyle).setCellValue(column);
        }

        // 表内容
        CellStyle bodyStyle = getBodyCellStyle(workbook);
        for (ScheduleTaskExecuteLogResponseBO executeLog : executeLogs) {
            List<ScheduleTaskLogMTMValuationSummaryBO> mtmValuationSummaries = null;
            if (StringUtils.hasLength(executeLog.getValuationSummaryData())) {
                mtmValuationSummaries = JSONUtils.getJson(executeLog.getValuationSummaryData(), new TypeToken<List<ScheduleTaskLogMTMValuationSummaryBO>>() {
                }.getType());
            } else {
                ScheduleTaskLogMTMValuationSummaryBO mtmValuationSummaryBO = new ScheduleTaskLogMTMValuationSummaryBO();
                mtmValuationSummaryBO.setNoteId(executeLog.getBillNoteId());
                mtmValuationSummaries = new ArrayList<>();
                mtmValuationSummaries.add(mtmValuationSummaryBO);
            }
            addCellValue(mtmValuationSummaries, sheet, rows, executeLog, bodyStyle);
        }
        return workbook;
    }

    private void addCellValue(List<ScheduleTaskLogMTMValuationSummaryBO> mtmValuationSummaries, Sheet sheet,
                              Integer rows, ScheduleTaskExecuteLogResponseBO executeLog, CellStyle bodyStyle) {
        for (ScheduleTaskLogMTMValuationSummaryBO mtmValuationSummary : Optional.ofNullable(mtmValuationSummaries).orElse(Collections.emptyList())) {
            if (null == mtmValuationSummary) {
                mtmValuationSummary = setMtmValuationBlankData();
            }
            int cols = 0;
            Row row = sheet.createRow(rows++);
            addCellWithStyle(row, cols++, bodyStyle).setCellValue("SUCCESS".equals(executeLog.getStatus()) ? "执行成功" : "执行失败");
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getClientShortName());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getClientLongName());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getNoteHolderShortName());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getNoteHolderLongName());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getNoteId());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getNoteIsin());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getNotional());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getIssueCcy());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getNav());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getNavRate());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getCurrencyType());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getTotalFeeAmt());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getFinCostAmount());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getCouponReceived());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getAccountBalance());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getFinancingAmt());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getOutStandingFxNotional());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getOutStandingBondNotional());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getTotalFeeAmtWeeklyDiff());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getFinCostAmtWeeklyDiff());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getCouponReceivedWeeklyDiff());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getAccountBalanceWeeklyDiff());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getFinancingAmtWeeklyDiff());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getOsFxNotionalWeeklyDiff());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getOsBondNotionalWeeklyDiff());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(mtmValuationSummary.getTotalWeeklyDiff());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(null != mtmValuationSummary.getTotalWeeklyDiffMark() ? String.valueOf(mtmValuationSummary.getTotalWeeklyDiffMark()) : "");
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(executeLog.getFailReason());
            addCellWithStyle(row, cols++, bodyStyle).setCellValue(executeLog.getExecuteTime());
            log.debug("get clos is {}", cols);
        }
    }

    private ScheduleTaskLogMTMValuationSummaryBO setMtmValuationBlankData() {
        ScheduleTaskLogMTMValuationSummaryBO mtmValuationSummary = new ScheduleTaskLogMTMValuationSummaryBO();
        mtmValuationSummary.setClientShortName("");
        mtmValuationSummary.setClientLongName("");
        mtmValuationSummary.setNoteHolderShortName("");
        mtmValuationSummary.setNoteHolderLongName("");
        mtmValuationSummary.setNoteId("");
        mtmValuationSummary.setNoteIsin("");
        mtmValuationSummary.setNotional("");
        mtmValuationSummary.setIssueCcy("");
        mtmValuationSummary.setNav("");
        mtmValuationSummary.setNavRate("");
        mtmValuationSummary.setCurrencyType("");
        mtmValuationSummary.setTotalFeeAmt("");
        mtmValuationSummary.setFinCostAmount("");
        mtmValuationSummary.setCouponReceived("");
        mtmValuationSummary.setAccountBalance("");
        mtmValuationSummary.setFinancingAmt("");
        mtmValuationSummary.setOutStandingFxNotional("");
        mtmValuationSummary.setOutStandingBondNotional("");
        mtmValuationSummary.setTotalFeeAmtWeeklyDiff("");
        mtmValuationSummary.setFinCostAmtWeeklyDiff("");
        mtmValuationSummary.setCouponReceivedWeeklyDiff("");
        mtmValuationSummary.setAccountBalanceWeeklyDiff("");
        mtmValuationSummary.setFinancingAmtWeeklyDiff("");
        mtmValuationSummary.setOsFxNotionalWeeklyDiff("");
        mtmValuationSummary.setOsBondNotionalWeeklyDiff("");
        mtmValuationSummary.setTotalWeeklyDiff("");
        mtmValuationSummary.setTotalWeeklyDiffMark(null);
        return mtmValuationSummary;
    }

    /**
     * 添加单元格及样式
     *
     * @param row
     * @param colPosition
     * @param cellStyle
     * @return
     */
    private Cell addCellWithStyle(Row row, int colPosition, CellStyle cellStyle) {
        Cell cell = row.createCell(colPosition);
        cell.setCellStyle(cellStyle);
        return cell;
    }

    /**
     * 表头单元格样式
     *
     * @param workbook
     * @return
     */
    private CellStyle getHeadCellStyle(Workbook workbook) {
        CellStyle style = getBaseCellStyle(workbook);

        // fill
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    /**
     * 内容单元格样式
     *
     * @param workbook
     * @return
     */
    private CellStyle getBodyCellStyle(Workbook workbook) {
        return getBaseCellStyle(workbook);
    }

    /**
     * 单元格样式
     *
     * @param workbook
     * @return
     */
    private CellStyle getBaseCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        // font
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        // align
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);

        // border
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());

        return style;
    }
}
