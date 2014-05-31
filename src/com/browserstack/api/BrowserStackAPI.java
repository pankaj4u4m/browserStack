package com.browserstack.api;

import java.nio.ByteBuffer;
import java.util.List;

import com.browserstack.api.object.APIStatus;
import com.browserstack.api.object.Browser;
import com.browserstack.api.object.WorkBuilder.Worker;
import com.browserstack.api.object.WorkerStatus;

public interface BrowserStackAPI {
    List<Browser> getBroswers();

    List<Browser> getAllBroswers();

    String submitWorker(Worker worker);

    String getScreenShotXML(String id);

    String getScreenShotJson(String id);

    ByteBuffer getScreenShot(String id);

    WorkerStatus getWorkerStatus(String id);

    List<WorkerStatus> getAllWorkersStatus();

    APIStatus getAPIStatus();
}
