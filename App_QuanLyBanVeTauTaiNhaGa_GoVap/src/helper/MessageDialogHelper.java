/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ADMIN
 */
public class MessageDialogHelper {
    //hiển thị thông báo
    public static void showMessageDialog(Component parent, String content, String title){
        JOptionPane.showMessageDialog(parent, content, title, JOptionPane.INFORMATION_MESSAGE); 
    }
    //hiển thị thông báo lỗi
    public static void showErrorDialog(Component parent, String content, String title){
        JOptionPane.showMessageDialog(parent, content, title, JOptionPane.ERROR_MESSAGE);
    }
    //hiển thị xác thực 
    public static int showConfirmDialog(Component parent, String content, String title){
        int res = JOptionPane.showConfirmDialog(parent, content, title, JOptionPane.YES_NO_OPTION , 
                JOptionPane.QUESTION_MESSAGE);
        return res;
    }
}
