# Quản Lý Bán Vé Tàu

## Giới Thiệu

Dự án Quản Lý Bán Vé Tàu là hệ thống hiện đại hỗ trợ quản lý bán vé tàu, tra cứu thông tin, quản lý khách hàng, doanh thu và các chức năng liên quan đến chuyến tàu, vé và khuyến mại. Hệ thống được thiết kế nhằm mang lại trải nghiệm thân thiện và hiệu quả cho người dùng.

## Chức Năng Chính

### 1. Đăng Nhập
- Nhập tài khoản và mật khẩu để đăng nhập.
- Tùy chọn "Quên mật khẩu" hỗ trợ lấy lại mật khẩu qua email.
- Cho phép người dùng thoát và quay lại màn hình đăng nhập.

### 2. Màn Hình Bán Vé
- Tra cứu chuyến tàu theo các tiêu chí như ga đi, ga đến, ngày đi.
- Thêm chỗ ngồi vào giỏ vé với trạng thái hiển thị rõ ràng (trắng: chưa đặt, đỏ: đã đặt, vàng: đang chọn).
- Đổi vé, trả vé, và thống kê doanh thu theo ca.
- Quản lý chuyến tàu, khách hàng, chương trình khuyến mại.

### 3. Màn Hình Thanh Toán
- Nhập thông tin khách hàng, mã khuyến mại, và tính toán chi phí.
- Xem chi tiết hóa đơn trước khi thanh toán.
- Xuất hóa đơn và vé dưới dạng PDF, gửi vé qua email.

### 4. Màn Hình Tra Cứu
#### 4.1. Tra cứu vé
- Tìm kiếm vé theo mã vé, giấy tờ, họ tên, hoặc quét mã QR.
- Hiển thị chi tiết vé, in lại vé.
#### 4.2. Tra cứu khuyến mại
- Lọc theo đối tượng khách hàng, thời gian áp dụng.
#### 4.3. Tra cứu hóa đơn
- Lọc hóa đơn theo mã, thời gian, giá trị và trạng thái.
#### 4.4. Tra cứu tuyến
- Lọc tuyến tàu theo điểm đi/đến, ngày khởi hành.
#### 4.5. Tra cứu lịch trình tàu
- Lọc lịch trình theo giờ, tên tuyến, trạng thái tàu.

### 5. Quản Lý Vé
#### 5.1. Đổi vé
- Tìm kiếm và đổi vé theo mã vé, mã hóa đơn.
#### 5.2. Trả vé
- Tìm kiếm vé cần trả, hiển thị thông tin chi tiết và thực hiện trả vé.

### 6. Doanh Thu Theo Ca
- Thống kê doanh thu theo khoảng thời gian và hiển thị dưới dạng biểu đồ cột, tròn.

### 7. Quản Lý Chuyến Tàu
- Thêm, cập nhật, làm mới và quản lý danh sách tuyến tàu.

### 8. Quản Lý Khách Hàng
- Thêm mới khách hàng với kiểm tra dữ liệu hợp lệ.
- Cập nhật thông tin khách hàng và phân hạng thành viên theo điểm tích lũy.

### 9. Quản Lý Chương Trình Khuyến Mại
- Thêm, chỉnh sửa và tìm kiếm các chương trình khuyến mại.
- Xem danh sách hóa đơn và số tiền giảm giá áp dụng CTKM.

### 10. Quản Lý Doanh Thu
#### 10.1. Chức năng thống kê số lượng vé
- Chọn ngày bắt đầu và ngày kết thúc cho khoảng thời gian thống kê.
- Nhấn nút "Thống kê" để thực hiện thống kê số lượng vé bán ra.
- Hệ thống sẽ hiển thị số lượng vé bán ra theo từng ngày từ ngày bắt đầu đến ngày kết thúc.
- Hệ thống sẽ hiển thị ngày bán được số lượng vé nhiều nhất và ít nhất.

#### 10.2. Chức năng thống kê tỉ lệ đổi trả vé
- Cho phép người dùng chọn tiêu chí để thống kê theo tháng.
- Cho phép người dùng chọn tiêu chí để thống kê theo năm.
- Cho phép người dùng chọn thời gian theo tháng từ tháng bắt đầu.
- Cho phép người dùng chọn thời gian theo tháng đến tháng kết thúc.
- Cho phép người dùng chọn năm (có thể là năm để thống kê theo tháng hoặc năm bắt đầu để thống kê theo năm).
- Cho phép người dùng chọn năm kết thúc.
- Sau khi chọn các tiêu chí để thống kê, người dùng nhấn nút "Thống kê" để tiến hành thống kê.
- Cho phép người dùng xuất báo cáo bằng file Excel.

#### 10.3. Chức năng thống kê khách hàng
- Chọn một khoảng thời gian (hôm nay, hôm qua, tuần này, tháng này, tháng trước, năm nay, năm trước).
- Nhấn nút "Tổng quan" để thực hiện thống kê.
- Hệ thống sẽ chỉ hiển thị số lượng khách hàng phát sinh giao dịch trong thời gian đã chọn.
- Nhấn chi tiết: hệ thống sẽ hiển thị số lượng khách hàng và số lượng hóa đơn trong thời gian đã chọn.

#### 10.4. Chức năng thống kê doanh thu bán vé
- Cho phép người dùng chọn Ngày bắt đầu và kết thúc thống kê.
- Nếu chọn vào Thống kê theo loại thời gian sẽ có nhiều lựa chọn.
- Nếu chọn vào Tiêu chí sẽ thống kê theo tiêu chí và lấy ngày bắt đầu và ngày kết thúc.
- Nút thống kê.
- Hiển thị tổng quan Doanh thu.
- Có thể chọn dạng biểu đồ.
- Biểu đồ có thể tương tác.
- Thông tin chi tiết.

### 11. Quản Lý Nhân Viên
- Mã nhân viên sẽ tự phát sinh khi người dùng thêm nhân viên mới vào hệ thống.
- Số điện thoại nhân viên được người dùng nhập vào.
- CCCD nhân viên được người dùng nhập vào.
- Ngày vào làm được người dùng chọn.
- Cho phép người dùng thêm vào tất cả các thông tin của nhân viên mới vào hệ thống.
- Tên nhân viên được người dùng nhập vào.
- Trạng thái nhân viên được thể hiện trên comboBox.
- Chức vụ nhân viên được thể hiện trên comboBox.
- Địa chỉ được người dùng nhập vào.
- Hình ảnh của nhân viên được người dùng đưa vào hệ thống theo ảnh mà nhân viên cấp.
- Mã nhân viên được nhập vào để tìm kiếm nhân viên.
- Cho phép người dùng tìm kiếm thông tin nhân viên trong hệ thống.
- Cho phép người dùng thêm thông tin nhân viên vào hệ thống.
- Cho phép người dùng làm mới thông tin tìm kiếm nhân viên.
- Cho phép người dùng cập nhật thông tin tìm kiếm nhân viên.
- Bảng thể hiện thông tin chi tiết nhân viên tìm được trong hệ thống.

### 12. Quản Lý Lịch Làm Việc Của Nhân Viên
- Cho phép người dùng nhập mã nhân viên để tìm kiếm.
- Nút tìm kiếm lịch làm việc, dùng để tìm kiếm ca làm việc theo mã nhân viên.
- Combobox chọn ca làm việc (Ca 1, Ca 2, Ca 3), giúp lựa chọn ca làm việc.
- Mã lịch làm việc sẽ tự phát sinh khi người dùng thêm lịch làm việc mới vào hệ thống.
- Bộ chọn ngày cho phép người dùng chọn giờ bắt đầu.
- Bộ chọn ngày cho phép người dùng chọn ngày bắt đầu.
- Combobox chọn nhân viên, cho phép chọn nhân viên để gán lịch làm việc.
- Combobox chọn trạng thái, giúp lọc các lịch làm việc theo trạng thái (chưa làm, làm xong, trễ giờ,...).
- Bộ chọn ngày cho phép người dùng chọn giờ kết thúc.
- Bộ chọn ngày cho phép người dùng chọn ngày kết thúc.
- Bộ chọn ngày cho phép người dùng chọn ngày đầu để lọc.
- Bộ chọn ngày cho phép người dùng chọn ngày kết thúc để lọc.
- Cho phép người dùng chọn ca làm để lọc.
- Cho phép người dùng chọn trạng thái để lọc.
- Nút lọc, sử dụng để lọc dữ liệu theo các tiêu chí như ca làm việc, trạng thái, ngày bắt đầu hoặc kết thúc.
- Bảng hiện chi tiết thông tin lịch làm việc.
- Nút thêm, sử dụng để thêm một ca làm việc mới vào hệ thống.
- Nút làm mới, có thể sử dụng để làm mới dữ liệu hoặc giao diện (làm mới các thông tin về lịch làm việc).
- Nút sửa lịch làm việc, cho phép người dùng chỉnh sửa các thông tin của một ca làm việc hiện có.

## Hướng Dẫn Cài Đặt
1. Clone repository về máy:
   ```bash
   git clone <repository-link>
   ```
2. Cài đặt các thư viện phụ thuộc:
   ```bash
   npm install
   ```
3. Chạy ứng dụng:
   ```bash
   npm start
   ```

## Công Nghệ Sử Dụng
- **Ngôn ngữ lập trình:** Java
- **Cơ sở dữ liệu:** MySQL
- **Giao diện người dùng:** JavaFX
- **Thư viện hỗ trợ:** JDBC, Apache POI (xuất báo cáo Excel), iText (xuất PDF)

## Đóng Góp
1. Fork repository.
2. Tạo branch mới:
## Cấu trúc thư mục

```plaintext
App_QuanLyBanVeTauTaiNhaGa_GoVap/
│
├── .idea/                     # Cấu hình IntelliJ IDEA
├── jar Libraries/             # Thư viện JAR được sử dụng
├── out/                       # Thư mục chứa file biên dịch
├── src/                       # Mã nguồn chính
│   ├── Anh_HeThong/           # Hình ảnh giao diện hệ thống
│   ├── com.raven.chart/       # Thư viện biểu đồ
│   ├── DAO/                   # Data Access Object - Truy cập dữ liệu
│   ├── Database/              # Các file liên quan cơ sở dữ liệu
│   ├── Entity/                # Các lớp thực thể
│   ├── GUI/                   # Giao diện người dùng
│   ├── helper/                # Các tiện ích hỗ trợ
│   ├── libs/                  # Thư viện bổ sung
│   └── Main/                  # Chương trình chính
│       └── Main.java          # Điểm khởi đầu của ứng dụng
├── META-INF/                  # Metadata ứng dụng
├── TestTK/                    # Thư mục kiểm tra, thống kê
├── thongKeDoanhThu/           # Thống kê doanh thu
├── WebUDBVTauTaiNhaGaLacHong/ # Thư mục web liên quan
├── .gitignore                 # Tệp cấu hình bỏ qua của Git
├── App_QuanLyBanVeTauTaiNhaGa_GoVap.iml # Tệp cấu hình IntelliJ
├── danh_sach_ve.pdf           # Báo cáo danh sách vé
├── hoa_don.pdf                # Mẫu hóa đơn
├── hoa_don_HD0912240002.pdf   # Hóa đơn cụ thể
├── hoadonTra.pdf              # Hóa đơn trả hàng
├── InLaiVe.pdf                # Báo cáo in lại vé
└── External Libraries/        # Các thư viện bên ngoài được thêm vào
```

## Hướng dẫn sử dụng

1. **Cài đặt môi trường**:
   - Sử dụng IntelliJ IDEA làm IDE.
   - Đảm bảo Java JDK 22 được cài đặt.

2. **Cấu hình dự án**:
   - Tải và mở dự án bằng IntelliJ IDEA.
   - Kiểm tra các thư viện trong `jar Libraries/` để đảm bảo đầy đủ phụ thuộc.

3. **Chạy ứng dụng**:
   - Chạy tệp `Main.java` trong thư mục `src/Main`.

## Liên hệ
Liên hệ nhóm phát triển qua email hoặc GitHub để báo lỗi hoặc đóng góp.

