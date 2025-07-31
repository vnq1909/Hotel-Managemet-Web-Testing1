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

public class qlkh {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chorme-driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Random random = new Random();

        try {
            // Đăng nhập
            driver.get("http://localhost:8080/quanlykhachsan/dang-nhap.php");
            driver.manage().window().setSize(new Dimension(1552, 840));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("sdt"))).sendKeys("nhanvien1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456");
            wait.until(ExpectedConditions.elementToBeClickable(By.name("dangnhap"))).click();
            System.out.println("Đăng nhập thành công.");

            // Truy cập vào quản lý khách hàng
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QL khách hàng"))).click();
            System.out.println("Đã truy cập vào quản lý khách hàng.");

            // Thêm, tìm kiếm, chỉnh sửa, và xoá khách hàng trong vòng lặp
            for (int i = 501; i <= 504; i++) {
                String customerName = "Khách hàng " + i;
                String phoneNumber = "09" + String.format("%08d", random.nextInt(100000000));
                String updatedName = "Khách hàng " + i + " mới";

                // Thêm khách hàng
                wait.until(ExpectedConditions.elementToBeClickable(By.id("submit"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hoten"))).sendKeys(customerName);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sdt"))).sendKeys(phoneNumber);

                // Chọn loại khách hàng ngẫu nhiên
                String[] customerTypes = {"bình thường", "thân thuộc", "vip"};
                String customerType = customerTypes[random.nextInt(customerTypes.length)];
                wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-loaikhachhang-container"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select2-search__field"))).sendKeys(customerType, Keys.ENTER);

                // Giới tính ngẫu nhiên
                String[] genders = {"nam", "nữ"};
                String gender = genders[random.nextInt(genders.length)];
                wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-gioitinh-container"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select2-search__field"))).sendKeys(gender, Keys.ENTER);

                /*// Ngày sinh ngẫu nhiên
                int day = random.nextInt(19) + 1; // Từ 1 đến 28
                int month = random.nextInt(12) + 1; // Từ 1 đến 12
                int year = random.nextInt(21) + 1990; // Từ 1990 đến 2010
                String birthDate = String.format("%02d%02d%d", day, month, year);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ngaysinh"))).sendKeys(birthDate);*/
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ngaysinh"))).sendKeys("10012002");

                
                // Địa chỉ
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("diachi"))).sendKeys("Hà Nội");
                wait.until(ExpectedConditions.elementToBeClickable(By.name("update"))).click();

                // Xác nhận thông báo thêm khách hàng
                wait.until(ExpectedConditions.textToBe(By.id("swal2-html-container"), "Đã thêm thông tin khách hàng!"));
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
                System.out.println("== Đã thêm khách hàng: " + customerName);

                // Tìm kiếm và chỉnh sửa khách hàng
                WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label > input")));
                searchBox.clear();
                searchBox.sendKeys(phoneNumber);
                WebElement phoneCell = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td:nth-child(2)")));
                if (phoneCell.getText().equals(phoneNumber)) {
                    System.out.println("Khách hàng tồn tại trong danh sách.");
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-pencil"))).click();
                    WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hoten")));
                    nameField.clear();
                    nameField.sendKeys(updatedName);
                    wait.until(ExpectedConditions.elementToBeClickable(By.name("update"))).click();

                    // Xác nhận thông báo chỉnh sửa
                    wait.until(ExpectedConditions.textToBe(By.id("swal2-html-container"), "Đã cập nhật thông tin khách hàng!"));
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
                    System.out.println("== Đã chỉnh sửa khách hàng thành: " + updatedName);
                }

                // Tìm kiếm và xoá khách hàng
                WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label > input")));
                searchBox2.clear();
                searchBox2.sendKeys(phoneNumber);
                WebElement phoneCell2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td:nth-child(2)")));
                if (phoneCell2.getText().equals(phoneNumber)) {
                    System.out.println("Khách hàng tồn tại trong danh sách.");
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-sm > .fa-trash"))).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn:nth-child(3)"))).click();

                    // Xác nhận thông báo xoá
                    wait.until(ExpectedConditions.textToBe(By.id("swal2-html-container"), "Đã xóa thông tin khách hàng!"));
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
                    System.out.println("== Đã xoá khách hàng: " + updatedName);
                    System.out.println("=====================================================");
                    System.out.println(" ");


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}