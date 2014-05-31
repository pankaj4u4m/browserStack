package browser;

import java.util.ArrayList;
import java.util.List;

import com.browserstack.api.BrowserStackAPI;
import com.browserstack.api.RestBroswerStackAPI;
import com.browserstack.api.client.RestBrowserStackClient;
import com.browserstack.api.object.WorkBuilder;
import com.browserstack.api.object.WorkBuilder.Worker;
import com.browserstack.api.object.WorkerStatus;

public class APITest {
    public static void main(String[] args) {
        BrowserStackAPI browserStackAPI =
                new RestBroswerStackAPI(new RestBrowserStackClient("pankajkumar7", "QwHiqzZbtZGGmMmJmYVP", "3"));
        Worker worker =
                new WorkBuilder("OS X", "Mavericks", "https://github.com/404").withBrowser("firefox")
                        .withBrowserVersion("16.0").build();
        System.out.println(browserStackAPI.getBroswers());
        System.out.println(browserStackAPI.getAllBroswers());
        System.out.println(browserStackAPI.getAPIStatus());
        System.out.println(browserStackAPI.submitWorker(worker));
        List<WorkerStatus> list = new ArrayList<>(browserStackAPI.getAllWorkersStatus());
        System.out.println(list);
        String id = list.get(0).getId();
        System.out.println(browserStackAPI.getWorkerStatus(id));
        System.out.println(browserStackAPI.getScreenShotJson(id));
        System.out.println(browserStackAPI.getScreenShotXML(id));
        System.out.println(browserStackAPI.getScreenShot(id));
    }
}
