package com.browserstack.api.object;

public class WorkBuilder {
    private final String os;
    private final String os_version;
    private final String url;
    private String browser;
    private String browser_version;
    private Long timeout;
    private String name;
    private String build;
    private String project;

    public WorkBuilder(String os, String os_version, String url) {
        super();
        this.os = os;
        this.os_version = os_version;
        this.url = url;
    }

    public Worker build() {
        Worker worker = new Worker(os, os_version, url);
        worker.browser = browser;
        worker.browser_version = browser_version;
        worker.build = build;
        worker.name = name;
        worker.project = project;
        worker.timeout = timeout;
        return worker;
    }

    public WorkBuilder withBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public WorkBuilder withBrowserVersion(String browserVersion) {
        this.browser_version = browserVersion;
        return this;
    }

    public WorkBuilder withTimeout(Long timeout) {
        this.timeout = timeout;
        return this;
    }

    public WorkBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public WorkBuilder withBuild(String build) {
        this.build = build;
        return this;
    }

    public WorkBuilder withProject(String project) {
        this.project = project;
        return this;
    }

    public class Worker implements Input {
        private final String os;
        private final String os_version;
        private final String url;
        private String browser;
        private String browser_version;
        private Long timeout;
        private String name;
        private String build;
        private String project;

        public Worker(String os, String os_version, String url) {
            super();
            this.os = os;
            this.os_version = os_version;
            this.url = url;
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

        public Long getTimeout() {
            return timeout;
        }

        public void setTimeout(Long timeout) {
            this.timeout = timeout;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBuild() {
            return build;
        }

        public void setBuild(String build) {
            this.build = build;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getOs() {
            return os;
        }

        public String getOs_version() {
            return os_version;
        }

        public String getUrl() {
            return url;
        }

    }

}
