package com.bu.admin.feign.service.impl;

import com.bu.admin.business.fileupload.bo.LocalFile;
import com.bu.admin.business.operationLog.bo.SyncSendMessageLogBO;
import com.bu.admin.feign.bo.batchdata.BatchData;
import com.bu.admin.feign.bo.content.BankCDData;
import com.bu.admin.feign.bo.file.BusFile;
import com.bu.admin.feign.bo.marketdata.MarketData;
import com.bu.admin.feign.bo.pnl.PnlQueryBo;
import com.bu.admin.feign.bo.product.*;
import com.bu.admin.feign.bo.report.BusTemplateConfig;
import com.bu.admin.feign.bo.report.Report;
import com.bu.admin.feign.bo.system.Dictionary;
import com.bu.admin.feign.bo.transaction.*;
import com.bu.admin.feign.bo.user.*;
import com.bu.admin.feign.service.ProductCenterClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;


@Component
public class ProductCenterClientHystrix implements ProductCenterClient {
    @Override
    public String saasUserLogin(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getRegVerifyCode(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }
    @Override
    public String getImageVerifyCode(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String saasUserReg(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }


    @Override
    public String newIssuerUser(IssuerUser issuerUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newPlatOpUser(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newSaasUser(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findPlatOpUsers(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findSassUserPage(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String saasUserModifyInfo(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String updateSaasUserStatus(UpdateSaasUserStatus updateSaasUserStatus, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String companyCreate(Company company, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String companyIdentifySub(Company company, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String companyInfoModify(Company company, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getCompanyInfoBySaasUser(Company company, String serverName, String serverToken) {
        return hystrixResponse();
    }
    @Override
    public String getCompanyInfoList(Company company, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getCompanyInfoById(Company company, String serverName, String serverToken) {
        return hystrixResponse();
    }


    @Override
    public String newProduct(Product product, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newProductSpecific(Product product, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getProductList(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getProductDetailById(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getUserFavoriteProductList(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getProductBarrierList(ProductBarrier productBarrier, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newProductBarrier(ProductBarrier productBarrier, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyProductBarrier(ProductBarrier productBarrier, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delProductBarrier(ProductBarrier productBarrier, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getProductEventList(ProductEvent productEvent, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newProductEvent(ProductEvent productEvent, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyProductEvent(ProductEvent productEvent, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delProductEvent(ProductEvent productEvent, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getProductPrice(ProductPrice productUnderlying, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newProductPrice(ProductPrice productUnderlying, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delProductPrice(ProductPrice productUnderlying, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newUserEvent(UserEvent userEvent, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getUserFavoriteList(UserFavorite userFavorite, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyProduct(Product product, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String productOnSale(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String productOffShelf(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyProductBasicIsin(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String dealProductData(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getDictionariesByTypeCode(Dictionary dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTypeCodes(Dictionary dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findDictionaries(Dictionary dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getDictionaryById(Dictionary dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newDictionary(Dictionary dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyDictionary(Dictionary dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delDictionary(Dictionary dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String restoreDictionary(Dictionary dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newBusFile(BusFile busFile, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findBusFileList(BusFile busFile, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String checkBusFilePurview(BusFile busFile, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delBusFile(BusFile busFile, String serverName, String serverToken) {
        return hystrixResponse();
    }


    @Override
    public String getProductSpecTempData(ProductSpecificTemplate productSpecificTemplate, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newProductSpecTempData(ProductSpecificTemplate dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delProductSpecTempData(ProductSpecificTemplate dictionary, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newUserComment(UserComment userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delUserComment(UserComment userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delSelfComment(UserComment userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getUserCommentList(UserComment userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getUserLimit(UserComment userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String disableUserComment(UserComment userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String restoreUserComment(UserComment userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getUserStatistics(UserComment userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getUserMessages(UserMessage userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String userMessageRead(UserMessage userComment, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findUserContentEvent(UserEvent userEvent, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getUserTaskList(UserTask userTask, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getBankCDData(BankCDData bankCDData, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String dealBankCDDataExcel(LocalFile localFile, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String userMQMessage(UserMessage userMessage, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newEAMCustomer(Customer customer, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyEAMCustomer(Customer customer, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String subEAMCustomer(Customer customer, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String dealEAMCustomer(Customer customer, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getEamCustomers(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getCustomers(Customer customer, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getCustomerDetail(Customer customer, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransactionList(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransactionDetail(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransactionSecurity(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getBondTransaction(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String batchTransSecLogExtendModify(BatchTransSecurityLog batchTransSecurityLog, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delSecurityTransaction(TransSecurity transSecurity, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransactionFxPosition(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getFxTransaction(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransactionCashFlow(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String batchTransCashFlowModify(BatchCashFlow batchCashFlow, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delCashFlow(CashFlow cashFlow, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delFxTransaction(TransRelative transRelative, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newTransaction(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String cancelTransaction(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String closeTransaction(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String confirmTransaction(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delTransaction(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String fundsDeliveryConfirm(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String securityDeliveryConfirm(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getCusAccountList(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }


    @Override
    public String getTransStatistic(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getUserTransList(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String checkFile(BatchData batchData, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String dealBatchData(BatchData batchData, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newBatchDataWithWarning(BatchData batchData, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String rollBackBatchData(BatchData batchData, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findTransactionStatisticList(Company company, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findUserSecurityList(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String transactionChangeSecurity(UserSecurity userSecurity, String serverName, String serverToken) {
        return hystrixResponse();
    }


    @Override
    public String getNoteSummary(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String generateFileFromTrans(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String batchReportGenerate(Report report, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getBatchDealList(BatchData batchData, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findReportDataList(Report report, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String generateReport(Report report, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTemplateConfigModel(BusTemplateConfig busTemplateConfig, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTemplateConfigList(BusTemplateConfig busTemplateConfig, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getBusTemplateConfigById(BusTemplateConfig busTemplateConfig, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newBusTemplateConfig(BusTemplateConfig busTemplateConfig, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String updateBusTemplateConfig(BusTemplateConfig busTemplateConfig, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransAccCcyList(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransFeeList(TransFee transFee, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newTransFee(TransFee transFee, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String updateTransFee(TransFee transFee, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String deleteFeeById(TransFee transFee, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String updateProductBasicNotional(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyProductBasicNotional(ProductNotional productNotional, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyProductBasicIssueDate(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyProductBasicMaturityDate(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyProductBasicNoteId(ProductBasic productBasic, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyTransNoteHolder(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyTransEndClient(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyCrossBoarderType(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String transMigrateFinish(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getSecurityBasicList(SecurityBasic securityBasic, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getSecurityPriceList(MarketData marketData, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String dealSyncDate(ProductSync productSync, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delSecurityPrice(SecurityPrice securityPrice, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String unificationLogin(UnificationLogin login, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransValuationAdjustList(TransValuationAdjust adjust, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newTransValuationAdjust(TransValuationAdjust adjust, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delTransValuationAdjust(TransValuationAdjust adjust, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String confirmRedemption(Transaction transaction, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getNewCompanyCode(String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String addCompanyPermission(AddSaasUserCompanyPermission addSaasUserCompanyPermission, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String addSpecialAccountPermission(AddSaasUserSpecialAccountPermission addSaasUserSpecialAccountPermission, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getCompanyPermission(String userId, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getSpecialAccountPermission(String userId, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransactionPositionSummary(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String updateTransPayment(TransPayment transPayment, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransPaymentList(TransPayment transPayment, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findTransSecurityPositionList(TransSecurityPosition transSecurityPosition, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String saveTransSecurityPosition(TransSecurityPosition transSecurityPosition, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTSCouponList(TSCouponPay tsCouponPay, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String associateMainUser(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String disassociateMainUser(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String resetClientPassword(User user, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findEndClientUserPage(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String fuzzyQueryEndClientList(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getRepoTransactionDetail(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getFxTransactionFroPage(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransactionCashFlowFroPage(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getCashFlowStatsDetailList(CashFlowStats cashFlowStats, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getCashFlowStatsSummaryList(CashFlowStats cashFlowStats, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyCashFlowOpeningBalance(CashFlowStats cashFlowStats, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getLatestNotional(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String addLatestNotionalTradeDate(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getTransactionValuation(Transaction transaction, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String sendTransactionValuation(TransValuationParams transValuationParams, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String updateFinancingFloatingRate(TransFinancing transFinancing, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String generateFileFromPnl(PnlQueryBo pnlQueryBo, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String openWebSocket(String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String closeWebSocket(String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findSyncMessageLogPage(SyncSendMessageLogBO syncSendMessageLogBO, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String modifyProductImpliedYield(ProductBasic productBasic, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String updateSalesOwnership(Transaction transaction, String applicationName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String newClientUser(SaasUser saasUser, String serverName, String serverToken) {
        return hystrixResponse();
    }

    private String hystrixResponse() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", 500);
        jsonObject.addProperty("msg", "网络错误,请稍后再试");
        return jsonObject.toString();
    }
}
