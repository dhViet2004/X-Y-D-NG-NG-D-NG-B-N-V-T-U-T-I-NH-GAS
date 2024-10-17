package GUI;

import javax.swing.*;

public class FrmBanVe extends JFrame{
    private JPanel JPanel_GDChinh;
    private JPanel contain;
    private JPanel main;

    public FrmBanVe() {
        setTitle("Bán Vé");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        FrmBanVe frm = new FrmBanVe();
        frm.setVisible(true);

    }
}
