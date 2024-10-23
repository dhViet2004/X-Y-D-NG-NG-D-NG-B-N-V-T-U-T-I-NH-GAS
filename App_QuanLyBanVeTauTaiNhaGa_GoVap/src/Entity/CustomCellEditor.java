package Entity;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class CustomCellEditor extends AbstractCellEditor implements TableCellEditor {
    private CustomPanel currentPanel;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Giả định rằng value là CustomPanel đã lưu trong mô hình dữ liệu
        currentPanel = (CustomPanel) value;
        return currentPanel;
    }

    @Override
    public Object getCellEditorValue() {
        return currentPanel; // Trả về panel đã chỉnh sửa
    }
}

