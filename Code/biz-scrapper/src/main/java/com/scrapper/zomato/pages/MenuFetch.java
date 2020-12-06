package com.scrapper.zomato.pages;

import com.scrapper.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MenuFetch {

    /*
     * created by abhishek sharma
     * zomato menu scrapper
     *
     * At last we get a list of product list that we can
     * use according to our need
     *
     */

    public static void main(String[] args) {
        // give chrome driver path
        System.setProperty("webdriver.chrome.driver","C:\\Users\\ABHISHEK\\Downloads\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // give url to be scrapped
        String baseUrl = "https://www.zomato.com/ludhiana/meshi-vaishnu-dhaba-industrial-area/order";
        String actualTitle = "";
        driver.get(baseUrl);
        actualTitle = driver.getTitle();
        List<Product> productList = new ArrayList<>();
        Product product;

        // print all
        WebDriverWait wait = new WebDriverWait(driver, 35);
        WebElement catName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sc-clBsIJ")));
        List<WebElement> list = (List<WebElement>) catName.findElements(By.tagName("section"));

        System.out.println("Test Passed!  =  "+  actualTitle);
        System.out.println("outter"+list.size());
        for(WebElement section :list) {
            try {
                WebElement cat = section.findElement(By.className("sc-1hp8d8a-0"));
                String catname = "";
                if(StringUtils.isNotBlank(cat.getText())){
                    catname = cat.getText();
                }

                List<WebElement> innerDiv = (List<WebElement>) section.findElements(By.className("sc-1s0saks-18"));
                for(WebElement div : innerDiv){
                    try {
                        product = new Product();
                        String name = "";
                        String price = "";
                        String url = "";
                        Boolean urlFlag = true;

                        WebElement divChid1 = div.findElement(By.tagName("h4"));

                        WebElement divChid2 = div.findElement(By.className("sc-17hyc2s-1"));

                        WebElement divChid3 = null;
                        try {
                            divChid3 = div.findElement(By.tagName("img"));
                        }catch (Exception e){
                            urlFlag = false;
                            System.out.println("IMG TAG NOT PRESENT"+e);
                        }

                        if(StringUtils.isNotBlank(divChid1.getText())){
                            name = divChid1.getText();
                        }
                        if(StringUtils.isNotBlank(divChid2.getText())){
                            price = divChid2.getText();
                        }
                        if(urlFlag){
                            if(StringUtils.isNotBlank(divChid3.getAttribute("src"))){
                                url = divChid3.getAttribute("src");
                            }
                        }

                        product.setCatName(catname);
                        product.setName(name);
                        product.setPrice(price);
                        product.setUrl(url);

                        if(product!=null){
                            productList.add(product);
                        }

                    }catch (Exception e){
                        System.out.println("Inner loop Error"+e);
                    }
                }
            } catch (Exception e) {
                System.out.println("Outter loop Error"+e);
            }
        }

        // use product list  according to need
        System.out.println("Number Of Products In Menu "+productList.size());
        for(int i=0 ; i<productList.size();i++){
            System.out.println("cat = "+productList.get(i).getCatName());
            System.out.println("name = "+productList.get(i).getName());
            System.out.println("price = "+productList.get(i).getPrice());
            System.out.println("url = "+productList.get(i).getUrl());
        }

        driver.close();
    }
}
