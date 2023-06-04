package com.example.demotest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.headless = true;
        Configuration.timeout = 60;
    }

    @Test
    public void login() {
        open("http://3.91.176.67:8181/login");

        $(By.name("username")).setValue("pp8080");
        $(By.name("password")).setValue("pp8080");

        $(By.xpath("/html/body/section/section/div/form/div[3]/input")).click();

        String Url = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://3.91.176.67:8181/homepage", Url);

        webdriver().driver().clearCookies();
    }

    @Test
    public void signup(){
        open("http://3.91.176.67:8181/signup");

        // Create a random username
        String username = "abdullah" + (int)(Math.random() * 10000000);

        $(By.name("username")).setValue(username);
        $(By.name("confirm")).setValue(username);
        $(By.name("password")).setValue(username);

        $(By.xpath("/html/body/section/section/div/form/div[4]/input")).click();

        String Url = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://3.91.176.67:8181/login", Url);

        webdriver().driver().clearCookies();
    }

    @Test
    public void signupDuplicate(){
        open("http://3.91.176.67:8181/signup");

        // Create a random username
        String username = "pp8080";

        $(By.name("username")).setValue(username);
        $(By.name("confirm")).setValue(username);
        $(By.name("password")).setValue(username);
        $(By.xpath("/html/body/section/section/div/form/div[4]/input")).click();
        String error = $(By.xpath("/html/body/section/section/div/p")).getCssValue("display");

        assertEquals("block", error);

        webdriver().driver().clearCookies();
    }

    @Test
    public void buyAGame(){
        open("http://3.91.176.67:8181/signup");

        // Create a random username
        String username = "abdullah" + (int)(Math.random() * 10000000);

        $(By.name("username")).setValue(username);
        $(By.name("confirm")).setValue(username);
        $(By.name("password")).setValue(username);

        $(By.xpath("/html/body/section/section/div/form/div[4]/input")).click();

        $(By.name("username")).setValue(username);
        $(By.name("password")).setValue(username);

        $(By.xpath("/html/body/section/section/div/form/div[3]/input")).click();


        $(By.xpath("/html/body/section/section/div/div[1]/a")).click();
        $(By.xpath("/html/body/section/section[1]/div/div[2]/div/div/a")).click();
        $(By.xpath("/html/body/section/section/div/div[2]/div/a")).click();

        String Url = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://3.91.176.67:8181/homepage", Url);

        webdriver().driver().clearCookies();
    }


    @Test
    public void logout(){
        open("http://3.91.176.67:8181/login");

        $(By.name("username")).setValue("pp8080");
        $(By.name("password")).setValue("pp8080");

        $(By.xpath("/html/body/section/section/div/form/div[3]/input")).click();
        $(By.xpath("/html/body/nav/ul/li[7]/a")).click();

        String Url = webdriver().driver().getCurrentFrameUrl();
        assertEquals("http://3.91.176.67:8181/login", Url);

        webdriver().driver().clearCookies();
    }


}
