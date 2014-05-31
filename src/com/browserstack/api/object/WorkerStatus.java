package com.browserstack.api.object;

public class WorkerStatus {
    private String id;
    private String status;
    private String browser;
    private String browser_version;
    private String os;
    private String os_version;
    private String device;
    private String browser_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowser_version() {
        return browser_version;
    }

    public void setBrowser_version(String browser_version) {
        this.browser_version = browser_version;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrowser_url() {
        return browser_url;
    }

    public void setBrowser_url(String browser_url) {
        this.browser_url = browser_url;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WorkerStatus [status=");
        builder.append(status);
        builder.append(", browser=");
        builder.append(browser);
        builder.append(", browser_version=");
        builder.append(browser_version);
        builder.append(", os=");
        builder.append(os);
        builder.append(", os_version=");
        builder.append(os_version);
        builder.append(", device=");
        builder.append(device);
        builder.append("]");
        return builder.toString();
    }

}
