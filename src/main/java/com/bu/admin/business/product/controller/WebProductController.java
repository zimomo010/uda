package com.bu.admin.business.product.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.fileupload.service.FileUploadService;
import com.bu.admin.feign.bo.batchdata.BatchData;
import com.bu.admin.feign.bo.marketdata.MarketData;
import com.bu.admin.feign.bo.product.*;
import com.bu.admin.feign.bo.user.Company;
import com.bu.admin.feign.enumtype.common.BatchDealBusTypes;
import com.bu.admin.feign.enumtype.common.FileType;
import com.bu.admin.feign.enumtype.company.CompanyType;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api
@RestController
@RequestMapping("/web/product")
@WsOperation("产品管理")
public class WebProductController {

    @Resource
    private ProductCenterClient productCenterClient;
    @Resource
    private FileUploadService fileUploadService;


    @ApiOperation(value = "创建产品", notes = "创建产品")
    @PostMapping(value = "/newProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "创建产品", ignoreLogFields = {"Authorization"}, requiredParams = {"productBasic"})
    public String newProduct(@RequestBody Product product,
                             @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        product.getProductBasic().setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(
                productCenterClient.newProduct(product, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取发行商列表", notes = "获取发行商列表")
    @GetMapping(value = "/getIssuerList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取发行商列表", ignoreLogFields = "Authorization")
    public String getIssuerList(@RequestHeader("Authorization") String token) {
        Company company = new Company();
        company.setCompanyType(CompanyType.ISSUER);
        company.setIdentification(true);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getCompanyInfoList(company,
                        ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取产品列表", notes = "获取产品列表")
    @GetMapping(value = "/getProductList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取产品列表", ignoreLogFields = {"Authorization"})
    public String getProductList(@ModelAttribute ProductBasic productBasic,
                                 @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getProductList(productBasic,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "获取产品详细", notes = "获取产品详细")
    @GetMapping(value = "/getProductDetailById", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取产品详细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getProductDetailById(@ModelAttribute ProductBasic productBasic,
                                       @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getProductDetailById(productBasic,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }



    @ApiOperation(value = "获取市场数据列表", notes = "获取市场数据列表")
    @GetMapping(value = "/getSecurityBasicList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取市场数据列表", ignoreLogFields = {"Authorization"})
    public String getSecurityBasicList(@ModelAttribute SecurityBasic securityBasic,
                                        @RequestHeader("Authorization") String token) {

        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getSecurityBasicList(securityBasic,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "获取市场数据价格列表", notes = "获取市场数据价格列表")
    @GetMapping(value = "/getSecurityPriceList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取市场数据价格列表", ignoreLogFields = {"Authorization"}, requiredParams = {"fileType"})
    public String getSecurityPriceList(@ModelAttribute MarketData marketData,
                                       @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getSecurityPriceList(marketData,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除市场数据", notes = "删除市场数据")
    @PostMapping(value = "/delSecurityPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除市场数据", requiredParams = {"id","securityCode"}, ignoreLogFields = {"Authorization"})
    public String delSecurityPrice(@RequestBody SecurityPrice securityPrice, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        securityPrice.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.delSecurityPrice(securityPrice,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    /**
     * 新增文件上传记录
     */
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=multipart/form-data")
    @WsOperation(value = "文件上传", ignoreLogFields = "Authorization")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file,
                             @RequestParam(value = "fileType") FileType fileType,
                             @RequestParam(value = "dealBusType") BatchDealBusTypes dealBusType,
                             @RequestHeader("Authorization") String token) {
        String fileUrl = fileUploadService.newNasFile(file, fileType, WsContext.getContext().getUserId());
        BatchData batchData = new BatchData();
        batchData.setFileUrl(fileUrl);
        batchData.setDealBusType(dealBusType);
        batchData.setUpdateUser(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.checkFile(batchData,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改票据ISIN", notes = "修改票据ISIN")
    @PostMapping(value = "/modifyProductBasicIsin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改票据ISIN", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "isinId"})
    public String modifyProductBasicIsin(@RequestBody ProductBasic productBasic, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        productBasic.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyProductBasicIsin(productBasic,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除产品事件", notes = "删除产品事件")
    @PostMapping(value = "/delProductEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除产品事件", requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String delProductEvent(@RequestBody ProductEvent productEvent, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        productEvent.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.delProductEvent(productEvent,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "产品信息修改收益率", notes = "产品信息修改收益率")
    @PostMapping(value = "/modifyProductImpliedYield", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "产品信息修改收益率", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "impliedYield"})
    public String modifyProductImpliedYield(@RequestBody ProductBasic productBasic, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        productBasic.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyProductImpliedYield(productBasic,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }
}
