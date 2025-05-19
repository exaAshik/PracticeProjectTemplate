package com.bkash.loan.template.model;

public class AwsSecretManagerSecret {

    private String agentUserName;
    private String agentPassword;
    private String agentRole;

    private String dsoUserName;
    private String dsoPassword;
    private String dsoRole;

    private String merchantUserName;
    private String merchantPassword;
    private String merchantRole;


    public String getAgentUserName() {

        return agentUserName;
    }

    public void setAgentUserName(String agentUserName) {

        this.agentUserName = agentUserName;
    }

    public String getAgentPassword() {
        return agentPassword;
    }

    public void setAgentPassword(String agentPassword) {

        this.agentPassword = agentPassword;
    }

    public String getAgentRole() {

        return agentRole;
    }

    public void setAgentRole(String agentRole) {

        this.agentRole = agentRole;
    }

    public String getDsoUserName() {

        return dsoUserName;
    }

    public void setDsoUserName(String dsoUserName) {

        this.dsoUserName = dsoUserName;
    }

    public String getDsoPassword() {

        return dsoPassword;
    }

    public void setDsoPassword(String dsoPassword) {

        this.dsoPassword = dsoPassword;
    }

    public String getDsoRole() {

        return dsoRole;
    }

    public void setDsoRole(String dsoRole) {
        this.dsoRole = dsoRole;
    }

    public String getMerchantUserName() {
        return merchantUserName;
    }

    public void setMerchantUserName(String merchantUserName) {
        this.merchantUserName = merchantUserName;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public String getMerchantRole() {
        return merchantRole;
    }

    public void setMerchantRole(String merchantRole) {
        this.merchantRole = merchantRole;
    }
}
