# TeamOneQLPH
<img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/QuanLyPhongHoc/out/production/QLPH/image/qlphLogo.png">
Phần mềm quản lý phòng học là phần mềm được viết bằng ngôn ngữ Java sử dụng hệ cơ sở dữ liệu MySQL. Giúp cho quá trình mượn, trả và quản lý phòng học trong HVAN một cách dễ dàng và tiện lợi.

# I. Tài liệu hướng dẫn
Chi tiết tài liệu hướng dẫn của chúng tôi ở : ....

# II. Cài đặt
## 1. Cài đặt XAMP để sử dụng hệ quản trị cơ sở dữ liệu MySQL
- Thiết đặt cấu hình XAMP như sau để có thể truy vấn với dữ liệu có kí tự utf-8
```sh
port : 3306
database_link = "jdbc:mysql://localhost:3306/qlph?useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true"
```
- Mô hình cơ sở dữ liệu 

  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/ER--QLPH.png">
  
- Import file qlph.sql vào hệ quản trị MySQL để tạo cơ sở dữ liệu cho phần mềm. 

## 2. Cài đặt extension
- Để phần mềm có thể kết nối được với được với CSDL ta cần cài đặt jdbc driver mysql
- Để hỗ trợ thiết kế giao diện cho phần mềm chúng tôi sử dụng: 
```sh
- scene builder javafx 
- fontawesomefx-8.9
- jfoenix-8.0.1
```

# III. Đóng góp 
Phần mềm được xây dựng bởi các thành viên trong TeamOneQLPH gồm có:
- Phù Văn Quất
- Tạ Hồng Giang
- Nguyễn Trọng Tài 
- Lê Trọng Hùng
- Dương Thế Đăng
- Phạm Văn Hưng

# IV. Giao diện
## 1. Giao diện màn hình đăng nhập

  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/DangNhap.png">
  
## 2. Giao diện mượn trả phòng 

  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/MuonPhong.png">
  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/TraPhong.png">
  
## 3. Giao diện quản lý 

  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/ChinhSuaPhongHoc.png">
  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/GiangDay.png">
  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/GiaoVien.png">
  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/LopHoc.png">
  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/MenuPhongHoc.png">
  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/ThemMoiPhongHoc.png">
  <img src="https://github.com/c0ng4c0n/TeamOneQLPH/blob/master/Source/TaiKhoan.png">
  
# V. Liên hệ   
Mọi thắc mắc liên quan đến phần mềm liên hệ qua email quatphu97mdbg@gmail.com . (Có tính phí hỏi đáp :D) 
Thanks!
