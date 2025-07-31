package selenium_auto_1;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sdp {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chorme-driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:8080/quanlykhachsan/dang-nhap.php");
        driver.manage().window().maximize();

        try {
            // Đăng nhập
            System.out.println("Bắt đầu đăng nhập...");
            wait.until(ExpectedConditions.elementToBeClickable(By.name("sdt"))).sendKeys("nhanvien1");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).sendKeys("123456");
            wait.until(ExpectedConditions.elementToBeClickable(By.name("dangnhap"))).click();
            System.out.println("Đăng nhập thành công!");

            // Thêm khách hàng
            for (int i = 401; i <= 402; i++) {
                String customerName = "khách hàng " + i;
                String phoneNumber = generateRandomPhoneNumber();
                String birthDate = generateRandomDate();
                String address = "khách hàng " + i + ", đường " + i + ", quận " + i + ", thành phố " + i;
                String customerType = generateRandomCustomerType();
                String gender = generateRandomGender();

                System.out.println("\nĐang thêm khách hàng: " + customerName);
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QL khách hàng"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.id("submit"))).click();

                // Nhập thông tin khách hàng
                wait.until(ExpectedConditions.elementToBeClickable(By.id("hoten"))).sendKeys(customerName);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("sdt"))).sendKeys(phoneNumber);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-loaikhachhang-container"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-search__field")))
                        .sendKeys(customerType + Keys.ENTER);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-gioitinh-container"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-search__field")))
                        .sendKeys(gender + Keys.ENTER);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("ngaysinh"))).sendKeys(birthDate);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("diachi"))).sendKeys(address);
                wait.until(ExpectedConditions.elementToBeClickable(By.name("update"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
                System.out.println("== Thêm khách hàng thành công!");

                // Sử dụng phòng
                System.out.println("Đang sử dụng phòng cho khách hàng...");
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Trang chủ"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".col-xl-2:nth-child(1) .card-img-top")))
                        .click();
                wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-khachhang-container"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-search__field")))
                        .sendKeys(customerName + Keys.ENTER);
                wait.until(ExpectedConditions.elementToBeClickable(By.name("submit"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();

                /* Chờ 5 phút trước khi thanh toán */
                System.out.println("Chờ 5 phút trước khi trả phòng...");
                Thread.sleep(2 * 60 * 1000);

                // Thanh toán
                System.out.println("Đang trả phòng...");
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".col-xl-2:nth-child(1) .card-body")))
                        .click();
                wait.until(ExpectedConditions.elementToBeClickable(By.name("submit"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
                System.out.println("== Trả phòng thành công!");

                // Tìm kiếm khách hàng trong hóa đơn
                System.out.println("Đang tìm kiếm khách hàng trong thống kê hóa đơn...");
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText("TK hóa đơn"))).click();
                WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label > input")));
                searchBox.clear();
                searchBox.sendKeys(phoneNumber);
                WebElement phoneCell = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td:nth-child(1)")));
                if (phoneCell.getText().equals(phoneNumber)) {
                    System.out.println("Khách hàng tồn tại trong danh sách.");
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private static String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phone = new StringBuilder("094");
        for (int i = 0; i < 7; i++) {
            phone.append(random.nextInt(10));
        }
        return phone.toString();
    }

    private static String generateRandomDate() {
        Random random = new Random();
        int day = random.nextInt(19) + 1;
        int month = random.nextInt(12) + 1;
        int year = random.nextInt(21) + 1990;
        return String.format("%02d%02d%d", day, month, year);
    }

    private static String generateRandomCustomerType() {
        String[] types = {"bình thường", "thân thuộc", "vip"};
        return types[new Random().nextInt(types.length)];
    }

    private static String generateRandomGender() {
        String[] genders = {"nam", "nữ"};
        return genders[new Random().nextInt(genders.length)];
    }
}
