package selenium_auto_1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class dkdndx {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chorme-driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:8080/quanlykhachsan/dang-nhap.php");
        driver.manage().window().maximize();

        try {
            for (int i = 401; i <= 403; i++) {
                String username = i + "nhanvien";
                String password = "123456";

                // Đăng ký
                System.out.println("Đang đăng ký tài khoản: " + username);
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.name("sdt"))).sendKeys(username);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).sendKeys(password);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("nhaplaipassword"))).sendKeys(password);
                wait.until(ExpectedConditions.elementToBeClickable(By.name("dangky"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
                System.out.println("== Đăng ký thành công!");

                // Đăng nhập
                System.out.println("Đang đăng nhập với tài khoản: " + username);
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.name("sdt"))).sendKeys(username);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).sendKeys(password);
                wait.until(ExpectedConditions.elementToBeClickable(By.name("dangnhap"))).click();
                System.out.println("== Đăng nhập thành công!");

                // Đăng xuất
                System.out.println("Đang đăng xuất...");
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng xuất"))).click();
                System.out.println("== Đăng xuất thành công!");
                System.out.println("=====================================");

            }
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        } 
    }
}
