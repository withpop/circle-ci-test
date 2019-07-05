package jp.docurain.circle.test

import org.junit.Assert.*
import org.junit.Test
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.By



class SeleniumAccessTest {

    private val driver: ChromeDriver
    private val options: ChromeOptions
    init {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver")
        options = ChromeOptions()
        options.setBinary("/usr/bin/google-chrome")
        options.addArguments("--headless")
        val driverService = ChromeDriverService.createDefaultService()
        driver = ChromeDriver(driverService, options)
    }

    @Test
    fun failTest(){
        fail("This test will always fail.")
    }

    @Test
    fun successTest(){
        driver.get("https://docurain.jp/")
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='今すぐ無料トライアル'])[3]/following::a[1]"))
            .click()
        try {
            assertEquals(
                "ドキュレインに関するお問い合わせ",
                driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ログイン'])[2]/following::h2[1]")).text
            )
        } catch (e: Error) {
            fail("exception thrown:" + e.message)
        }

    }
}