package selenium_auto_1;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class qltk {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chorme-driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Random random = new Random();

        try {
            // Đăng nhập vào hệ thống
            driver.get("http://localhost:8080/quanlykhachsan/dang-nhap.php");
            driver.manage().window().setSize(new Dimension(1552, 840));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("sdt"))).sendKeys("nhanvien1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456");
            wait.until(ExpectedConditions.elementToBeClickable(By.name("dangnhap"))).click();
            System.out.println("Đăng nhập thành công.");

            // Truy cập vào quản lý tài khoản
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QL tài khoản"))).click();
            System.out.println("Đã truy cập vào quản lý tài khoản.");

            // Thêm, tìm kiếm, chỉnh sửa và xoá tài khoản trong vòng lặp
            for (int i = 501; i <= 504; i++) {
                String username = "taikhoan" + i;
                String password = "123456";
                String[] accountTypes = {"Quản trị", "Bình thường"};
                String accountType = accountTypes[random.nextInt(accountTypes.length)];

                // Mở form thêm tài khoản
                wait.until(ExpectedConditions.elementToBeClickable(By.id("submit"))).click();

                // Nhập thông tin tài khoản
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("taikhoan"))).sendKeys(username);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("matkhau"))).sendKeys(password);

                // Chọn loại tài khoản
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-selection"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select2-search__field"))).sendKeys(accountType, Keys.ENTER);

                // Lưu thông tin
                wait.until(ExpectedConditions.elementToBeClickable(By.name("update"))).click();

                // Xác nhận thông báo
                wait.until(ExpectedConditions.textToBe(By.id("swal2-html-container"), "Đã thêm thông tin tài khoản!"));
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
                System.out.println("Đã thêm tài khoản: " + username + " - Loại: " + accountType);

                // Tìm kiếm và chỉnh sửa tài khoản
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label > input"))).clear();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label > input"))).sendKeys(username);
                WebElement accountCell = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td:nth-child(2)")));
                if (accountCell.getText().equals(username)) {
                    System.out.println("Tài khoản tồn tại trong danh sách.");

                    // Chỉnh sửa tài khoản
                    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sửa"))).click();

                    // Chỉnh sửa mật khẩu và loại tài khoản
                    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("matkhau")));
                    passwordField.clear();
                    passwordField.sendKeys("newpassword" + i);

                    wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-loaitaikhoan-container"))).click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select2-search__field"))).sendKeys("Quản trị", Keys.ENTER);

                    // Lưu chỉnh sửa
                    wait.until(ExpectedConditions.elementToBeClickable(By.name("update"))).click();
                    wait.until(ExpectedConditions.textToBe(By.id("swal2-html-container"), "Đã cập nhật thông tin tài khoản!"));
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
                    System.out.println("Đã chỉnh sửa tài khoản: " + username);
                }

                // Tìm kiếm và xoá tài khoản
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label > input"))).clear();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label > input"))).sendKeys(username);
                WebElement deleteCell = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td:nth-child(2)")));
                if (deleteCell.getText().equals(username)) {
                    System.out.println("Tài khoản tồn tại trong danh sách.");

                    // Xoá tài khoản
                    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Xóa"))).click();

                    // Xác nhận xoá tài khoản
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn:nth-child(3)"))).click();
                    wait.until(ExpectedConditions.textToBe(By.id("swal2-html-container"), "Đã xóa thông tin tài khoản!"));
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
                    System.out.println("Đã xoá tài khoản: " + username);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Đã kết thúc chương trình.");
        }
    }
}
