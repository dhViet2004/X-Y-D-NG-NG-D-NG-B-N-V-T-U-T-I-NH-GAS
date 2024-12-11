package Main;

import java.text.Normalizer;

public class Test {
    // Phương thức chuyển chuỗi có dấu thành chuỗi không dấu và thay đổi 'Đ' thành 'D'
    public static String removeDiacritics(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Loại bỏ dấu
        String result = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Thay 'Đ' thành 'D'
        result = result.replace('Đ', 'D').replace('đ', 'd');

        return result;
    }

    public static void main(String[] args) {
        String hoTen = "Đặng Hoàng Việt";
        String hoTenChuanHoa = removeDiacritics(hoTen);
        System.out.println("Chuỗi không dấu: " + hoTenChuanHoa);

        // Kiểm tra biểu thức chính quy
        if (hoTenChuanHoa.matches("^[A-Z][a-z]+(\\s[A-z][a-z]+)+$")) {
            System.out.println("Họ tên hợp lệ.");
        } else {
            System.out.println("Họ tên không hợp lệ.");
        }
    }
}
