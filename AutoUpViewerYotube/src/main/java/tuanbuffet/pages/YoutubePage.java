package tuanbuffet.pages;

import org.openqa.selenium.By;
import tuanbuffet.common.StringProcessing;
import tuanbuffet.common.WebUI;


public class YoutubePage {
    By timeView =  By.xpath("//div[@aria-label=\"Seek slider\"]");
    By playerVideoButton = By.xpath("//div[@id=\"movie_player\"]");
    /*int time = Integer.parseInt(WebUI.getAttributeElement(timeView,"aria-valuemax"));*/
    public void viewVideo(String url){
        WebUI.openURL(url);
        StringProcessing.sleep(2);
        WebUI.clickElement(By.xpath("//div[@id=\"movie_player\"]"));
        int time = Integer.parseInt(WebUI.getAttributeElement(timeView,"aria-valuemax"));
        System.out.println(time);
        StringProcessing.sleepRandom(50,75);
    }
}
