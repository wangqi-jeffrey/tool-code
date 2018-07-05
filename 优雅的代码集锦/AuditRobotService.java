package com.longxin.ssls.robot.service;

import com.google.common.collect.Lists;
import com.longxin.common.utils.JSONUtils;
import com.longxin.disburse.bussiness.enums.LoanStatus;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuditRobotService {
    private static final int LOAN_AMOUNT_MAX = 1_000_000;
    private static final String SSLS_API_SERVICE_ID = "ssls-api";
    @Autowired
    private BackendInvokeService backendInvokeSvc;

    public void loginAndAudit(String user, String passwd, Integer merchantAppId, int queryAuditStatus, int limit,
                              int auditResult, String auditRemark) throws ClientProtocolException, IOException
    {
        backendInvokeSvc.invokeWithContext(SSLS_API_SERVICE_ID, ctx -> {
            String loginFull = String.format("/ssls/web/user/login?userName=%s&pwd=%s", user, passwd);
            try {
                HttpResponse loginRes = ctx.execute(new HttpGet(loginFull));
                int loginHttpCode = loginRes.getStatusLine().getStatusCode();
                if (200 != loginHttpCode) {
                    log.warn("bad http code for login " + loginHttpCode + " for " + user);
                    return null;
                }

                Map<String, Object> loginBody = JSONUtils.parseObject(EntityUtils.toString(loginRes.getEntity()), Map.class);
                Integer loginCode = (Integer) loginBody.get("code");
                if (200 != loginCode.intValue()) {
                    log.warn("bad business code for login " + loginCode + " for " + user);
                    return null;
                }

                String listReqLine = String.format(
                        "/ssls/web/loanApplication?rows=%d&page=1&auditStatus=%d",
                        limit, queryAuditStatus);
                if (null != merchantAppId) {
                    listReqLine += "&merchantAppId=" + merchantAppId.toString();
                }
                HttpResponse listRes = ctx.execute(new HttpGet(listReqLine));
                int listHttpCode = listRes.getStatusLine().getStatusCode();
                if (200 != loginHttpCode) {
                    log.warn("bad http code for audit list " + listHttpCode + " for " + user);
                    return null;
                }

                Map<String, Object> listBody = JSONUtils.parseObject(EntityUtils.toString(listRes.getEntity()), Map.class);
                String listCode = listBody.get("code").toString();
                if (!"0000".equals(listCode)) {
                    log.warn("bad business code for list " + listCode + " for " + user);
                    return null;
                }

                Map<String, Object> dataObj = (Map<String, Object>) listBody.get("data");
                if (null == dataObj) {
                    log.warn("no data node in listReq for " + user);
                    return null;
                }

                List result = (List) dataObj.get("result");
                if (null == result) {
                    log.warn("no data.result node in listReq for " + user);
                    return null;
                }

                List<Integer> batchIds = Lists.newArrayList();
                for (Object obj  : result) {
                    Map<String, Object> loan = (Map<String, Object>) obj;
                    int loanId = Integer.parseInt(loan.get("id").toString());
                    double loanAmount = Double.parseDouble(loan.get("loanAmount").toString());
                    if (loanAmount > LOAN_AMOUNT_MAX) {
                        log.warn("ignore too big loan " + loanId + " " + loanAmount);
                        continue;
                    }

                    int loanSt = Integer.parseInt(loan.get("status").toString());
                    if (loanSt != LoanStatus.CONTRACT_USER_APPROVED.getCode().intValue()) {
                        log.warn("ignore bad st loan " + loanId + " " + loanSt);
                        continue;
                    }

                    int loanAuditSt = Integer.parseInt(loan.get("auditStatus").toString());
                    if (loanAuditSt != queryAuditStatus) {
                        log.warn("ignore bad auditSt loan " + loanId + " " + loanAuditSt);
                        continue;
                    }

                    batchIds.add(loanId);
                }

                if (batchIds.size() == 0) {
                    return null;
                }

                String idsStr = String.join(",", batchIds.stream().map(i -> i.toString()).collect(Collectors.toList()));
                //send audit query
                HttpPut auditQuery = new HttpPut("/ssls/web/loanApplication");
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                formparams.add(new BasicNameValuePair("ids", idsStr));
                formparams.add(new BasicNameValuePair("auditResult", String.valueOf(auditResult)));
                formparams.add(new BasicNameValuePair("auditRemark", auditRemark));
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
                auditQuery.setEntity(formEntity);
                HttpResponse auditRes = ctx.execute(auditQuery);

                int auditHttpCode = auditRes.getStatusLine().getStatusCode();
                if (200 != auditHttpCode) {
                    log.warn("bad http code for audit " + auditHttpCode + " for " + user);
                    return null;
                }

                Map<String, Object> auditResBody = JSONUtils.parseObject(EntityUtils.toString(auditRes.getEntity()), Map.class);
                String auditCode = auditResBody.get("code").toString();
                if (!"0000".equals(auditCode)) {
                    log.warn("audit failed for " + user);
                    return null;
                }

                log.info("audit summit succ " + user + " " + idsStr);
            } catch (IOException e) {
                log.error("IOException during audit for " + user + "  " + e.getMessage(), e);
                return null;
            }
            return null;
        });
    }

}
