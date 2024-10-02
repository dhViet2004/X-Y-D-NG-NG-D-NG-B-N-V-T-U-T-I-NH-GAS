package Main;

import Entity.Entity_NhanVien;
import Entity.User;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        
        Entity_NhanVien HoangViet = new Entity_NhanVien("NV01","Hoàng Việt","0364601530","Nghỉ việc","123456","Bến tre", LocalDate.parse("2020-12-31"),"Quản lý");
        System.out.println(HoangViet);
    }
}
