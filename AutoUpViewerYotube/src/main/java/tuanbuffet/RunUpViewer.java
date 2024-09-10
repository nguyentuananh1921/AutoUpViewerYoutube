package tuanbuffet;

import tuanbuffet.common.DcomManager;
import tuanbuffet.common.WebUI;
import tuanbuffet.data.DataLinksYoutube;
import tuanbuffet.data.DataListLinkYoutube;
import tuanbuffet.pages.YoutubePage;

import java.util.List;


public class RunUpViewer implements Runnable{

    YoutubePage youtubePage;
    DataListLinkYoutube dataListLinkYoutube;
    List<DataLinksYoutube> listData;
    int locator;
    int step;
    public RunUpViewer(List<DataLinksYoutube> listData,int locator, int step){
        this.listData = listData;
        this.locator = locator;
        this.step = step;
    }
    public void runUpViewerVideo(List<DataLinksYoutube> listData,int locator){
        youtubePage = new YoutubePage();
            WebUI.openBrowser();
            youtubePage.viewVideo(listData.get(locator).getLink());
            WebUI.closeBrowser();
    }

    public static void main(String[] args) {
        int numberThread = 3;
        DataListLinkYoutube dataListLinkYoutube;
        List<DataLinksYoutube> listData;
        Thread[] threads = new Thread[numberThread];
        dataListLinkYoutube = new DataListLinkYoutube();
        listData = dataListLinkYoutube.getDataListLinkExcel();
        for (int i = 0; i < numberThread; i++){
            threads[i] = new Thread(new RunUpViewer(listData, i,numberThread));
            threads[i].start();
        }
        for (Thread thread : threads){
            try {
                thread.join(); // Chờ luồng kết thúc
            } catch (InterruptedException ignored) {
            }
        }

    }

    @Override
    public void run() {
        for (int i =0; i<5;i++){
            DcomManager.resetIp();
            runUpViewerVideo(listData,locator);

        }
    }
}
