# Selenium Automation Testing - Hệ thống Quản lý Khách sạn

Repository này chứa các test case tự động cho hệ thống quản lý khách sạn sử dụng Selenium WebDriver và Selenium IDE.

## 🚀 Cài đặt và chạy


### Chạy test bằng Selenium IDE:
1. Cài đặt Selenium IDE extension cho Chrome
2. Mở file `selenium ide/auto-qlks.side`
3. Chạy test cases

### Chạy test bằng Java WebDriver:
1. Import project vào Eclipse IDE
2. Cài đặt Selenium WebDriver dependencies
3. Chạy từng file Java tương ứng

## 📋 Các test case

### 1. Quản lý khách hàng (`qlkh.java`)
- Thêm khách hàng mới
- Tìm kiếm khách hàng
- Chỉnh sửa thông tin khách hàng
- Xóa khách hàng

### 2. Đăng ký đăng nhập (`dkdndx.java`)
- Test đăng ký tài khoản
- Test đăng nhập hệ thống

### 3. Sử dụng dịch vụ phòng (`sdp.java`)
- Test các chức năng liên quan đến phòng

### 4. Quản lý tài khoản (`qltk.java`)
- Test quản lý thông tin tài khoản

## 🔧 Cấu hình

### Thông tin đăng nhập test:
- Username: `nhanvien1`
- Password: `123456`

### URL hệ thống:
- Base URL: `http://localhost:8080/quanlykhachsan/`

## 📝 Lưu ý

- Đảm bảo web server PHP đang chạy trước khi thực thi test
- Cập nhật đường dẫn ChromeDriver nếu cần thiết
- Test data được tạo ngẫu nhiên để tránh xung đột

