package com.bu.admin.feign.service;

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
import feign.Headers;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FeignClient("gj-product-center")
public interface ProductCenterClient {

    @ApiOperation(value = "后台登陆", notes = "后台登陆")
    @PostMapping(value = "/server/saasUser/saasUserLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String saasUserLogin(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "统一登陆", notes = "统一登陆")
    @PostMapping(value = "/server/saasUser/unificationLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String unificationLogin(@RequestBody UnificationLogin login, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取注册验证码", notes = "获取注册验证码")
    @PostMapping(value = "/server/saasUser/getRegVerifyCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getRegVerifyCode(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取图片验证码", notes = "获取图片验证码")
    @PostMapping(value = "/server/saasUser/getImageVerifyCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getImageVerifyCode(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping(value = "/server/saasUser/saasUserReg", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String saasUserReg(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增发行商用户", notes = "新增发行商用户")
    @PostMapping(value = "/server/saasUser/newIssuerUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newIssuerUser(@RequestBody IssuerUser issuerUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增平台运营用户", notes = "新增平台运营用户")
    @PostMapping(value = "/server/saasUser/newPlatOpUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newPlatOpUser(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping(value = "/server/saasUser/newSaasUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newSaasUser(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "分页查询平台用户", notes = "分页查询平台用户")
    @PostMapping(value = "/server/saasUser/findPlatOpUsers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findPlatOpUsers(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName,
                                  @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @PostMapping(value = "/server/saasUser/findSassUserPage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findSassUserPage(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName,
                           @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "用户信息修改", notes = "用户信息修改")
    @PostMapping(value = "/server/saasUser/saasUserModifyInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String saasUserModifyInfo(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改用户状态", notes = "修改用户状态")
    @PostMapping(value = "/server/saasUser/updateSaasUserStatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateSaasUserStatus(@RequestBody @Validated UpdateSaasUserStatus updateSaasUserStatus, @RequestHeader("MiServerName") String serverName,
                                       @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "用户主体创建", notes = "用户主体创建")
    @PostMapping(value = "/server/company/companyCreate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String companyCreate(@RequestBody Company company, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "公司主体提交", notes = "公司主体提交")
    @PostMapping(value = "/server/company/companyIdentifySub", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String companyIdentifySub(@RequestBody Company company, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "公司主体修改", notes = "公司主体修改")
    @PostMapping(value = "/server/company/companyInfoModify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String companyInfoModify(@RequestBody Company company, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "通过创建用户获取公司", notes = "通过创建用户获取公司")
    @PostMapping(value = "/server/company/getCompanyInfoBySaasUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCompanyInfoBySaasUser(@RequestBody Company company, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取公司列表", notes = "获取公司列表")
    @PostMapping(value = "/server/company/getCompanyInfoList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCompanyInfoList(@RequestBody Company company, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "通过ID获取公司", notes = "通过ID获取公司")
    @PostMapping(value = "/server/company/getCompanyInfoById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCompanyInfoById(@RequestBody Company company, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "创建产品", notes = "创建产品")
    @PostMapping(value = "/server/product/newProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newProduct(@RequestBody Product product, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "创建产品个性信息", notes = "创建产品个性信息")
    @PostMapping(value = "/server/product/newProductSpecific", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newProductSpecific(@RequestBody Product product, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取产品列表", notes = "获取产品列表")
    @PostMapping(value = "/server/product/getProductList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getProductList(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取产品详细", notes = "获取产品详细")
    @PostMapping(value = "/server/product/getProductDetailById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getProductDetailById(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户收藏产品", notes = "获取用户收藏产品")
    @PostMapping(value = "/server/product/getUserFavoriteProductList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUserFavoriteProductList(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "产品信息修改", notes = "产品信息修改")
    @PostMapping(value = "/server/product/modifyProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyProduct(@RequestBody Product product, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "产品上架", notes = "产品上架")
    @PostMapping(value = "/server/product/productOnSale", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String productOnSale(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "产品下架", notes = "产品下架")
    @PostMapping(value = "/server/product/productOffShelf", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String productOffShelf(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改票据ISIN", notes = "修改票据ISIN")
    @PostMapping(value = "/server/product/modifyProductBasicIsin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyProductBasicIsin(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取产品障碍列表", notes = "获取产品障碍列表")
    @PostMapping(value = "/server/product/getProductBarrierList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getProductBarrierList(@RequestBody ProductBarrier productBarrier, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增产品障碍", notes = "新增产品障碍")
    @PostMapping(value = "/server/product/newProductBarrier", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newProductBarrier(@RequestBody ProductBarrier productBarrier, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改产品障碍", notes = "修改产品障碍")
    @PostMapping(value = "/server/product/modifyProductBarrier", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyProductBarrier(@RequestBody ProductBarrier productBarrier, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除产品障碍", notes = "删除产品障碍")
    @PostMapping(value = "/server/product/delProductBarrier", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delProductBarrier(@RequestBody ProductBarrier productBarrier, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取产品事件的列表", notes = "获取产品事件的列表")
    @PostMapping(value = "/server/product/getProductEventList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getProductEventList(@RequestBody ProductEvent productEvent, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增产品事件", notes = "新增产品事件")
    @PostMapping(value = "/server/product/newProductEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newProductEvent(@RequestBody ProductEvent productEvent, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改产品事件", notes = "修改产品事件")
    @PostMapping(value = "/server/product/modifyProductEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyProductEvent(@RequestBody ProductEvent productEvent, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除产品事件", notes = "删除产品事件")
    @PostMapping(value = "/server/product/delProductEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delProductEvent(@RequestBody ProductEvent productEvent, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取产品价格", notes = "获取产品价格")
    @PostMapping(value = "/server/product/getProductPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getProductPrice(@RequestBody ProductPrice productPrice, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增产品价格", notes = "新增产品价格")
    @PostMapping(value = "/server/product/newProductPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newProductPrice(@RequestBody ProductPrice productPrice, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除产品价格", notes = "删除产品价格")
    @PostMapping(value = "/server/product/delProductPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delProductPrice(@RequestBody ProductPrice productPrice, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "处理产品引擎逻辑", notes = "处理产品引擎逻辑")
    @PostMapping(value = "/server/product/dealProductData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String dealProductData(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增收藏", notes = "新增收藏")
    @PostMapping(value = "/server/userEvent/newUserEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newUserEvent(@RequestBody UserEvent userEvent, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户收藏列表", notes = "获取用户收藏列表")
    @PostMapping(value = "/server/userEvent/getUserFavoriteList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUserFavoriteList(@RequestBody UserFavorite userFavorite, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增评论", notes = "新增评论")
    @PostMapping(value = "/server/userEvent/newUserComment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newUserComment(@RequestBody UserComment userComment, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除评论", notes = "删除评论")
    @PostMapping(value = "/server/userEvent/delUserComment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delUserComment(@RequestBody UserComment userComment, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除自有评论", notes = "删除自有评论")
    @PostMapping(value = "/server/userEvent/delSelfComment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delSelfComment(@RequestBody UserComment userComment, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户限制", notes = "获取用户限制")
    @PostMapping(value = "/server/userEvent/getUserCommentList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUserCommentList(@RequestBody UserComment userComment, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除评论", notes = "删除评论")
    @PostMapping(value = "/server/userEvent/getUserLimit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUserLimit(@RequestBody UserComment userComment, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "用户禁言", notes = "用户禁言")
    @PostMapping(value = "/server/userEvent/disableUserComment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String disableUserComment(@RequestBody UserComment userComment, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "用户解禁", notes = "用户解禁")
    @PostMapping(value = "/server/userEvent/restoreUserComment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String restoreUserComment(@RequestBody UserComment userComment, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户统计", notes = "获取用户统计")
    @PostMapping(value = "/server/userEvent/getUserStatistics", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUserStatistics(@RequestBody UserComment userComment, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户通知", notes = "获取用户通知")
    @PostMapping(value = "/server/userEvent/getUserMessages", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUserMessages(@RequestBody UserMessage userMessage, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "用户阅读通知", notes = "用户阅读通知")
    @PostMapping(value = "/server/userEvent/userMessageRead", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String userMessageRead(@RequestBody UserMessage userMessage, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取指定字典表值", notes = "获取指定字典表值")
    @PostMapping(value = "/server/dictionary/getDictionariesByTypeCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getDictionariesByTypeCode(@RequestBody Dictionary dictionary, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取字典类型集合", notes = "获取字典类型集合")
    @PostMapping(value = "/server/dictionary/getTypeCodes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTypeCodes(@RequestBody Dictionary dictionary, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "查询字典列表", notes = "查询字典列表")
    @PostMapping(value = "/server/dictionary/findDictionaries", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findDictionaries(@RequestBody Dictionary dictionary, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取字典详细", notes = "获取字典详细")
    @PostMapping(value = "/server/dictionary/getDictionaryById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getDictionaryById(@RequestBody Dictionary dictionary, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增字典", notes = "新增字典")
    @PostMapping(value = "/server/dictionary/newDictionary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newDictionary(@RequestBody Dictionary dictionary, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改字典", notes = "修改字典")
    @PostMapping(value = "/server/dictionary/modifyDictionary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyDictionary(@RequestBody Dictionary dictionary, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除字典", notes = "删除字典")
    @PostMapping(value = "/server/dictionary/delDictionary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delDictionary(@RequestBody Dictionary dictionary, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "恢复字典", notes = "恢复字典")
    @PostMapping(value = "/server/dictionary/restoreDictionary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String restoreDictionary(@RequestBody Dictionary dictionary, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping(value = "/server/busFile/newBusFile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newBusFile(@RequestBody BusFile busFile, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取上传文件列表", notes = "获取上传文件列表")
    @PostMapping(value = "/server/busFile/findBusFileList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findBusFileList(@RequestBody BusFile busFile, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "检查文件权限", notes = "检查文件权限")
    @PostMapping(value = "/server/busFile/checkBusFilePurview", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String checkBusFilePurview(@RequestBody BusFile busFile, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除业务文件", notes = "删除业务文件")
    @PostMapping(value = "/server/busFile/delBusFile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delBusFile(@RequestBody BusFile busFile, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除字典", notes = "删除字典")
    @PostMapping(value = "/server/product/getProductSpecTempData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getProductSpecTempData(@RequestBody ProductSpecificTemplate productSpecificTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping(value = "/server/product/newProductSpecTempData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newProductSpecTempData(@RequestBody ProductSpecificTemplate productSpecificTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取上传文件列表", notes = "获取上传文件列表")
    @PostMapping(value = "/server/product/delProductSpecTempData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delProductSpecTempData(@RequestBody ProductSpecificTemplate productSpecificTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户内容操作", notes = "获取用户内容操作")
    @PostMapping(value = "/server/content/findUserContentEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findUserContentEvent(@RequestBody UserEvent userEvent, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户任务列表", notes = "获取用户任务列表")
    @PostMapping(value = "/server/userEvent/getUserTaskList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUserTaskList(@RequestBody UserTask userTask, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取大额存单数据", notes = "获取大额存单数据")
    @PostMapping(value = "/server/financialData/getBankCDData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getBankCDData(@RequestBody BankCDData bankCDData, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "处理大额存单文件", notes = "处理大额存单文件")
    @PostMapping(value = "/server/financialData/dealBankCDDataExcel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String dealBankCDDataExcel(@RequestBody LocalFile localFile, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "处理大额存单文件", notes = "处理大额存单文件")
    @PostMapping(value = "/server/userEvent/userMQMessage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String userMQMessage(@RequestBody UserMessage userMessage, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增客户", notes = "新增客户")
    @PostMapping(value = "/server/saasUser/newEAMCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newEAMCustomer(@RequestBody Customer customer, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改客户信息", notes = "修改客户信息")
    @PostMapping(value = "/server/saasUser/modifyEAMCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyEAMCustomer(@RequestBody Customer customer, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "提交客户信息", notes = "提交客户信息")
    @PostMapping(value = "/server/saasUser/subEAMCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String subEAMCustomer(@RequestBody Customer customer, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "客户审核", notes = "客户审核")
    @PostMapping(value = "/server/saasUser/dealEAMCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String dealEAMCustomer(@RequestBody Customer customer, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取EAM用户列表", notes = "获取EAM用户列表")
    @PostMapping(value = "/server/saasUser/getEamCustomers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getEamCustomers(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @PostMapping(value = "/server/saasUser/getCustomers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCustomers(@RequestBody Customer customer, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取EAM用户详细", notes = "获取EAM用户详细")
    @PostMapping(value = "/server/saasUser/getCustomerDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCustomerDetail(@RequestBody Customer customer, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单列表", notes = "获取订单列表")
    @PostMapping(value = "/server/transaction/getTransactionList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransactionList(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单详细", notes = "获取订单详细")
    @PostMapping(value = "/server/transaction/getTransactionDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransactionDetail(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单证券仓位", notes = "获取订单证券仓位")
    @PostMapping(value = "/server/transaction/getTransactionSecurity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransactionSecurity(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单证券详细", notes = "获取订单证券详细")
    @PostMapping(value = "/server/transaction/getBondTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getBondTransaction(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "订单证券交易扩展信息批量修改", notes = "订单证券交易扩展信息批量修改")
    @PostMapping(value = "/server/transaction/batchTransSecLogExtendModify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String batchTransSecLogExtendModify(@RequestBody BatchTransSecurityLog batchTransSecurityLog, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除证券交易", notes = "删除证券交易")
    @PostMapping(value = "/server/transaction/delSecurityTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delSecurityTransaction(@RequestBody TransSecurity transSecurity, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单汇率仓位", notes = "获取订单汇率仓位")
    @PostMapping(value = "/server/transaction/getTransactionFxPosition", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransactionFxPosition(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单汇率明细", notes = "获取订单汇率明细")
    @PostMapping(value = "/server/transaction/getFxTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getFxTransaction(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单现金流明细", notes = "获取订单现金流明细")
    @PostMapping(value = "/server/transaction/getTransactionCashFlow", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransactionCashFlow(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "订单现金流信息批量修改", notes = "订单现金流信息批量修改")
    @PostMapping(value = "/server/transRelative/batchTransCashFlowModify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String batchTransCashFlowModify(@RequestBody BatchCashFlow batchCashFlow, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "现金流删除", notes = "现金流删除")
    @PostMapping(value = "/server/transRelative/delCashFlow", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delCashFlow(@RequestBody CashFlow cashFlow, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除外汇交易", notes = "删除外汇交易")
    @PostMapping(value = "/server/transRelative/delFxTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delFxTransaction(@RequestBody TransRelative transRelative, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新建订单", notes = "新建订单")
    @PostMapping(value = "/server/transaction/newTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newTransaction(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "取消订单", notes = "取消订单")
    @PostMapping(value = "/server/transaction/cancelTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String cancelTransaction(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "关闭订单", notes = "关闭订单")
    @PostMapping(value = "/server/transaction/closeTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String closeTransaction(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "确认订单", notes = "确认订单")
    @PostMapping(value = "/server/transaction/confirmTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String confirmTransaction(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除订单", notes = "删除订单")
    @PostMapping(value = "/server/transaction/delTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delTransaction(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "资金交割确认", notes = "资金交割确认")
    @PostMapping(value = "/server/transaction/fundsDeliveryConfirm", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String fundsDeliveryConfirm(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "证券交割确认", notes = "证券交割确认")
    @PostMapping(value = "/server/transaction/securityDeliveryConfirm", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String securityDeliveryConfirm(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取EAM客户账户列表", notes = "获取EAM客户账户列表")
    @PostMapping(value = "/server/saasUser/getCusAccountList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCusAccountList(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单统计", notes = "获取订单统计")
    @PostMapping(value = "/server/transaction/getTransStatistic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransStatistic(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户订单统计", notes = "获取用户订单统计")
    @PostMapping(value = "/server/transaction/getUserTransList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUserTransList(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "检查文件", notes = "检查文件")
    @PostMapping(value = "/server/batchData/checkFile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers("connectTimeout: 200000")
    String checkFile(@RequestBody BatchData batchData, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "处理文件", notes = "处理文件")
    @PostMapping(value = "/server/batchData/dealBatchData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String dealBatchData(@RequestBody BatchData batchData, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "提交警告文件", notes = "提交警告文件")
    @PostMapping(value = "/server/batchData/newBatchDataWithWarning", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newBatchDataWithWarning(@RequestBody BatchData batchData, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "撤销导入数据", notes = "撤销导入数据")
    @PostMapping(value = "/server/batchData/rollBackBatchData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String rollBackBatchData(@RequestBody BatchData batchData, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取客户统计列表", notes = "获取客户统计列表")
    @PostMapping(value = "/server/transaction/findTransactionStatisticList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findTransactionStatisticList(@RequestBody Company company, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取客户订单证券", notes = "获取客户订单证券")
    @PostMapping(value = "/server/transaction/findUserSecurityList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findUserSecurityList(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "订单调仓", notes = "订单调仓")
    @PostMapping(value = "/server/transaction/transactionChangeSecurity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String transactionChangeSecurity(@RequestBody UserSecurity userSecurity, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);


    @ApiOperation(value = "获取票据统计信息", notes = "获取票据统计信息")
    @PostMapping(value = "/server/transaction/getNoteSummary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getNoteSummary(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);


    @ApiOperation(value = "生成报告", notes = "生成报告")
    @PostMapping(value = "/server/transaction/generateFileFromTrans", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String generateFileFromTrans(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "批量生成报告", notes = "批量生成报告")
    @PostMapping(value = "/server/transaction/batchReportGenerate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String batchReportGenerate(@RequestBody Report report, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取批量数据上传列表", notes = "获取批量数据上传列表")
    @PostMapping(value = "/server/batchData/getBatchDealList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getBatchDealList(@RequestBody BatchData batchData, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取数据集合", notes = "获取数据集合")
    @PostMapping(value = "/server/report/findReportDataList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findReportDataList(@RequestBody Report report, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "生成报告", notes = "生成报告")
    @PostMapping(value = "/server/report/generateReport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String generateReport(@RequestBody Report report, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取模版配置模块信息", notes = "获取模版配置模块信息")
    @PostMapping(value = "/server/report/getTemplateConfigModel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTemplateConfigModel(@RequestBody BusTemplateConfig busTemplateConfig, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取模版配置列表", notes = "获取模版配置列表")
    @PostMapping(value = "/server/report/findBusTemplateConfigList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTemplateConfigList(@RequestBody BusTemplateConfig busTemplateConfig, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取模版配置详细", notes = "获取模版配置详细")
    @PostMapping(value = "/server/report/getBusTemplateConfigById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getBusTemplateConfigById(@RequestBody BusTemplateConfig busTemplateConfig, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增业务模版配置", notes = "新增业务模版配置")
    @PostMapping(value = "/server/report/newBusTemplateConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newBusTemplateConfig(@RequestBody BusTemplateConfig busTemplateConfig, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改业务模版配置", notes = "修改业务模版配置")
    @PostMapping(value = "/server/report/updateBusTemplateConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateBusTemplateConfig(@RequestBody BusTemplateConfig busTemplateConfig, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单账户币种列表", notes = "获取订单账户币种列表")
    @PostMapping(value = "/server/transaction/getTransAccCcyList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransAccCcyList(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取组合费用列表", notes = "获取组合费用列表")
    @PostMapping(value = "/server/transRelative/getTransFeeList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransFeeList(@RequestBody TransFee transFee, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新建费用", notes = "新建费用")
    @PostMapping(value = "/server/transRelative/newTransFee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newTransFee(@RequestBody TransFee transFee, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更新费用", notes = "更新费用")
    @PostMapping(value = "/server/transRelative/updateTransFee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateTransFee(@RequestBody TransFee transFee, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除费用", notes = "删除费用")
    @PostMapping(value = "/server/transRelative/deleteFeeById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteFeeById(@RequestBody TransFee transFee, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "计算票据面值", notes = "计算票据面值")
    @PostMapping(value = "/server/transaction/updateProductBasicNotional", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateProductBasicNotional(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改票据面值", notes = "修改票据面值")
    @PostMapping(value = "/server/transaction/modifyProductBasicNotional", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyProductBasicNotional(@RequestBody ProductNotional productNotional, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改票据发行日", notes = "修改票据发行日")
    @PostMapping(value = "/server/transaction/modifyProductBasicIssueDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyProductBasicIssueDate(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken) ;

    @ApiOperation(value = "修改票据到期日", notes = "修改票据到期日")
    @PostMapping(value = "/server/transaction/modifyProductBasicMaturityDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyProductBasicMaturityDate(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改票据NoteId", notes = "修改票据NoteId")
    @PostMapping(value = "/server/transaction/modifyProductBasicNoteId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyProductBasicNoteId(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken) ;

    @ApiOperation(value = "修改票据持有人", notes = "修改票据持有人")
    @PostMapping(value = "/server/transaction/modifyTransNoteHolder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyTransNoteHolder(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken) ;

    @ApiOperation(value = "修改票据出资人", notes = "修改票据出资人")
    @PostMapping(value = "/server/transaction/modifyTransEndClient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyTransEndClient(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken) ;

    @ApiOperation(value = "修改跨境交易类型", notes = "修改跨境交易类型")
    @PostMapping(value = "/server/transaction/modifyCrossBoarderType", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyCrossBoarderType(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken) ;

    @ApiOperation(value = "迁移完成确认", notes = "迁移完成确认")
    @PostMapping(value = "/server/transaction/transMigrateFinish", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String transMigrateFinish(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取市场数据列表", notes = "获取证券信息")
    @PostMapping(value = "/server/marketData/getSecurityBasicList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getSecurityBasicList(@RequestBody SecurityBasic securityBasic, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取市场数据价格列表", notes = "获取证券信息")
    @PostMapping(value = "/server/marketData/getSecurityPriceList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getSecurityPriceList(@RequestBody MarketData marketData, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "同步已上传数据", notes = "同步已上传数据")
    @PostMapping(value = "/server/marketData/syncData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String dealSyncDate(@RequestBody ProductSync productSync, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除市场数据", notes = "删除市场数据")
    @PostMapping(value = "/server/product/delSecurityPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delSecurityPrice(@RequestBody SecurityPrice securityPrice, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取调整列表", notes = "获取调整列表")
    @PostMapping(value = "/server/transaction/getTransValuationAdjustList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransValuationAdjustList(@RequestBody TransValuationAdjust adjust, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新建调整", notes = "新建调整")
    @PostMapping(value = "/server/transaction/newTransValuationAdjust", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newTransValuationAdjust(@RequestBody TransValuationAdjust adjust,@RequestHeader("MiServerName") String applicationName,@RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除调整", notes = "删除调整")
    @PostMapping(value = "/server/transaction/delTransValuationAdjust", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delTransValuationAdjust(@RequestBody TransValuationAdjust adjust,@RequestHeader("MiServerName") String applicationName,@RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "确认票据赎回", notes = "确认票据赎回")
    @PostMapping(value = "/server/transaction/confirmRedemption", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String confirmRedemption(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取最新的公司编号", notes = "获取最新的公司编号")
    @GetMapping(value = "/server/company/getNewCompanyCode", produces = MediaType.APPLICATION_JSON_VALUE)
    String getNewCompanyCode(@RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "保存用户的公司机构数据权限", notes = "保存用户的公司机构数据权限")
    @PostMapping(value = "/server/saasUser/addCompanyPermission", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String addCompanyPermission(@RequestBody AddSaasUserCompanyPermission addSaasUserCompanyPermission, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "保存用户的专户数据权限", notes = "保存用户的专户数据权限")
    @PostMapping(value = "/server/saasUser/addSpecialAccountPermission", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String addSpecialAccountPermission(@RequestBody AddSaasUserSpecialAccountPermission addSaasUserSpecialAccountPermission, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "查询用户的公司机构数据权限", notes = "查询用户的公司机构数据权限")
    @GetMapping(value = "/server/saasUser/getCompanyPermission", produces = MediaType.APPLICATION_JSON_VALUE)
    String getCompanyPermission(@RequestParam String userId, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "查询用户的专户数据权限", notes = "查询用户的专户数据权限")
    @GetMapping(value = "/server/saasUser/getSpecialAccountPermission", produces = MediaType.APPLICATION_JSON_VALUE)
    String getSpecialAccountPermission(@RequestParam String userId, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单position", notes = "获取订单position")
    @PostMapping(value = "/server/transaction/getTransactionPositionSummary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransactionPositionSummary(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更新票据支付", notes = "更新票据支付")
    @PostMapping(value = "/server/transaction/updateTransPayment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateTransPayment(@RequestBody TransPayment transPayment, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取支付列表", notes = "获取支付列表")
    @GetMapping(value = "/server/transaction/getTransPaymentList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransPaymentList(@RequestBody TransPayment transPayment, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取债券付息列表", notes = "获取债券付息列表")
    @GetMapping(value = "/server/transaction/getTSCouponList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTSCouponList(@RequestBody TSCouponPay tsCouponPay, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取用户总仓位列表", notes = "获取用户总仓位列表")
    @GetMapping(value = "/server/transPosition/findTransSecurityPositionList", produces = MediaType.APPLICATION_JSON_VALUE)
    String findTransSecurityPositionList(@RequestBody TransSecurityPosition transSecurityPosition, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更新用户债券风险设置", notes = "更新用户债券风险设置")
    @GetMapping(value = "/server/transPosition/saveTransSecurityPosition", produces = MediaType.APPLICATION_JSON_VALUE)
    String saveTransSecurityPosition(@RequestBody TransSecurityPosition transSecurityPosition, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增终端客户", notes = "新增终端客户")
    @PostMapping(value = "/server/clientUser/newClientUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newClientUser(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "关联客户", notes = "关联客户")
    @PostMapping(value = "/server/clientUser/associateMainUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String associateMainUser(@RequestBody SaasUser saasUser,  @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "解除关联客户", notes = "解除关联客户")
    @PostMapping(value = "/server/clientUser/disassociateMainUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String disassociateMainUser(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "后台重置密码", notes = "后台重置密码")
    @PostMapping(value = "/server/clientUser/resetPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String resetClientPassword(@RequestBody User user,  @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "分页查询客户", notes = "分页查询客户")
    @PostMapping(value = "/server/clientUser/findEndClientUserPage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findEndClientUserPage(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "模糊查询客户", notes = "模糊查询客户")
    @PostMapping(value = "/server/clientUser/fuzzyQueryEndClientList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String fuzzyQueryEndClientList(@RequestBody SaasUser saasUser, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取Repo订单详细", notes = "获取Repo订单详细")
    @PostMapping(value = "/server/transaction/getRepoTransactionDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getRepoTransactionDetail(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);


    @ApiOperation(value = "获取订单汇率明细列表", notes = "获取订单汇率明细列表")
    @PostMapping(value = "/server/transaction/getFxTransactionFroPage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getFxTransactionFroPage(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单现金流明细列表", notes = "获取订单现金流明细列表")
    @PostMapping(value = "/server/transaction/getTransactionCashFlowFroPage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransactionCashFlowFroPage(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取现金流统计明细", notes = "获取现金流统计明细")
    @PostMapping(value = "/server/transRelative/getCashFlowStatsDetailList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCashFlowStatsDetailList(@RequestBody CashFlowStats cashFlowStats, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取现金流统计概要", notes = "获取现金流统计概要")
    @PostMapping(value = "/server/transRelative/getCashFlowStatsSummaryList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCashFlowStatsSummaryList(@RequestBody CashFlowStats cashFlowStats, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改现金流期初余额", notes = "修改现金流期初余额")
    @PostMapping(value = "/server/transRelative/modifyCashFlowOpeningBalance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyCashFlowOpeningBalance(@RequestBody CashFlowStats cashFlowStats, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取票据最后扩资记录", notes = "获取票据最后扩资记录")
    @PostMapping(value = "/server/transaction/getLatestNotional", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getLatestNotional(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更新票据最后扩资信息", notes = "更新票据最后扩资信息")
    @PostMapping(value = "/server/transaction/addLatestNotionalTradeDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String addLatestNotionalTradeDate(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取估值信息", notes = "获取估值信息")
    @PostMapping(value = "/server/transaction/getTransactionValuation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransactionValuation(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "发送估值数据", notes = "发送估值数据")
    @PostMapping(value = "/server/transaction/sendTransactionValuation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String sendTransactionValuation(@RequestBody TransValuationParams transValuationParams, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改融资浮动费率", notes = "修改融资浮动费率")
    @PostMapping(value = "/server/transFinancing/updateFinancingFloatingRate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateFinancingFloatingRate(@RequestBody TransFinancing transFinancing, @RequestHeader("MiServerName")  String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "Pnl生成报告", notes = "Pnl生成报告")
    @PostMapping(value = "/server/transPnl/generateFileFromPnl", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String generateFileFromPnl(@RequestBody PnlQueryBo pnlQueryBo, @RequestHeader("MiServerName")  String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "open websocket链接", notes = "open websocket链接")
    @PostMapping(value = "/server/websocket/openWebSocket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String openWebSocket( @RequestHeader("MiServerName")  String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "close websocket链接", notes = "close websocket链接")
    @PostMapping(value = "/server/websocket/closeWebSocket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String closeWebSocket( @RequestHeader("MiServerName")  String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "查询同步消息日志", notes = "查询同步消息日志")
    @PostMapping(value = "/server/messageLog/getSendMessageList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findSyncMessageLogPage(@RequestBody SyncSendMessageLogBO syncSendMessageLogBO, @RequestHeader("MiServerName")  String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "产品信息修改收益率", notes = "产品信息修改收益率")
    @PostMapping(value = "/server/product/modifyProductImpliedYield", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyProductImpliedYield(@RequestBody ProductBasic productBasic, @RequestHeader("MiServerName")  String applicationName,@RequestHeader("Authorization") String serverToken);
    @ApiOperation(value = "修改销售所有权", notes = "修改销售所有权")
    @PostMapping(value = "/server/transaction/updateSalesOwnership", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateSalesOwnership(@RequestBody Transaction transaction, @RequestHeader("MiServerName")  String applicationName,@RequestHeader("Authorization") String serverToken);
}
