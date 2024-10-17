package GUI;

import javax.swing.*;
import java.awt.*;

public class Loading extends JDialog {
    private JPanel contentPaneLoading;
    private JProgressBar progressBar;

    public Loading() {
        setContentPane(contentPaneLoading);
        setTitle("Loading");
        setMinimumSize(new Dimension(800, 492));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Bắt đầu tiến trình trong luồng riêng
        startLoading();
    }

    private void startLoading() {
        // Sử dụng SwingWorker để chạy tiến trình mà không chặn giao diện
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(40); // Giả lập quá trình tải
                    publish(i); // Cập nhật giá trị mới cho thanh tiến trình
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Lấy giá trị cuối cùng từ danh sách được gửi
                int value = chunks.get(chunks.size() - 1);
                progressBar.setValue(value);
            }

            @Override
            protected void done() {
                // Đóng dialog sau khi tiến trình hoàn tất
                dispose();
                FrmDangNhap dn = new FrmDangNhap();
                dn.setVisible(true);
            }
        };

        worker.execute(); // Bắt đầu thực thi SwingWorker
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Loading dialog = new Loading();
            dialog.setVisible(true);

        });
    }
}
