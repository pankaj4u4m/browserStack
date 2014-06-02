package com.browserstack.api;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;

import com.browserstack.api.client.BroswerStackClient;
import com.browserstack.api.object.APIStatus;
import com.browserstack.api.object.Browser;
import com.browserstack.api.object.WorkBuilder.Worker;
import com.browserstack.api.object.WorkerStatus;
import com.browserstack.exception.DependencyException;

public class RestBroswerStackAPI implements BrowserStackAPI {
    private static final String JSON = "json";

    private static final String XML = "xml";

    private static final String PNG = "png";

    private static final String BROWSER_VERSION = "browser_version";

    private static final String BROWSER = "browser";

    private static final class Methods {
        private static final String BROWSERS = "/browsers?flat=true";
        private static final String ALL_BROWSER = "/browsers?all=true";
        private static final String WORKER = "/worker";
        private static final String API_STATUS = "/status";
        private static final String SCREENSHOT = "/worker/%s/screenshot.%s";
        private static final String WORKER_STATUS = "/worker/%s";
        private static final String ALL_WORKER_STATUS = "/workers";

    }

    private final BroswerStackClient client;

    private final ObjectMapper mapper;

    public RestBroswerStackAPI(BroswerStackClient client) {
        super();
        this.client = client;
        mapper = new ObjectMapper();
    }

    @Override
    public List<Browser> getBroswers() {
        String json = client.get(Methods.BROWSERS);
        try {
            return Arrays.asList(mapper.readValue(json, Browser[].class));
        } catch (IOException e) {
            throw new DependencyException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Browser> getAllBroswers() {
        List<Browser> browsers = new ArrayList<>();
        String json = client.get(Methods.ALL_BROWSER);
        try {
            Map<String, Map<String, List<Map<String, String>>>> first = mapper.readValue(json, Map.class);
            for (Entry<String, Map<String, List<Map<String, String>>>> e : first.entrySet()) {
                Map<String, List<Map<String, String>>> second = e.getValue();
                for (Entry<String, List<Map<String, String>>> e1 : second.entrySet()) {
                    List<Map<String, String>> third = e1.getValue();
                    for (Map<String, String> t : third) {
                        Map<String, String> fourth = t;
                        Browser browser = new Browser();
                        browser.setOs(e.getKey());
                        browser.setOs_version(e1.getKey());
                        browser.setBrowser(fourth.get(BROWSER));
                        browser.setBrowser_version(fourth.get(BROWSER_VERSION));
                        browsers.add(browser);
                    }
                }
            }
            return browsers;
        } catch (IOException e) {
            throw new DependencyException(e);
        }
    }

    @Override
    public String submitWorker(Worker worker) {
        try {
            String json = client.post(Methods.WORKER, mapper.readValue(mapper.writeValueAsString(worker), Map.class));
            return mapper.readValue(json, Map.class).get("id").toString();
        } catch (IOException e) {
            throw new DependencyException(e);
        }

    }

    @Override
    public String getScreenShotXML(String id) {
        return client.get(String.format(Methods.SCREENSHOT, id, XML));
    }

    @Override
    public String getScreenShotJson(String id) {
        return client.get(String.format(Methods.SCREENSHOT, id, JSON));
    }

    @Override
    public ByteBuffer getScreenShot(String id) {
        return ByteBuffer.wrap(client.get(String.format(Methods.SCREENSHOT, id, PNG)).getBytes());
    }

    @Override
    public WorkerStatus getWorkerStatus(String id) {
        String json = client.get(String.format(Methods.WORKER_STATUS, id));
        try {
            return mapper.readValue(json, WorkerStatus.class);
        } catch (IOException e) {
            throw new DependencyException(e);
        }
    }

    @Override
    public List<WorkerStatus> getAllWorkersStatus() {
        String json = client.get(Methods.ALL_WORKER_STATUS);
        try {
            return Arrays.asList(mapper.readValue(json, WorkerStatus[].class));
        } catch (IOException e) {
            throw new DependencyException(e);
        }
    }

    @Override
    public APIStatus getAPIStatus() {
        String json = client.get(Methods.API_STATUS);
        try {
            return mapper.readValue(json, APIStatus.class);
        } catch (IOException e) {
            throw new DependencyException(e);
        }
    }

}
