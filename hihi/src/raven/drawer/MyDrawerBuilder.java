package raven.drawer;

import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
//import raven.form.TestForm;
import raven.main.Main;
import raven.swing.AvatarIcon;
//import raven.tabbed.WindowsTabbed;
import javax.swing.SwingConstants;
/**
 *
 * @author RAVEN
 */
public class MyDrawerBuilder extends SimpleDrawerBuilder {

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        return new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource("/raven/image/tauHome.png"), 80, 80, 100))
                .setTitle("Ga tàu Gò Vấp");
                //.setDescription("raven@gmail.com");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
//        String menus[][] = {
//            //{"~MAIN~"},
//            {"Dashboard"},
//            //{"~WEB APP~"},
//            {"Email", "Inbox", "Read", "Compost"},
//            {"Chat"},
//            {"Calendar"},
//            //{"~COMPONENT~"},
//            {"Advanced UI", "Cropper", "Owl Carousel", "Sweet Alert"},
//            {"Forms", "Basic Elements", "Advanced Elements", "SEditors", "Wizard"},
//            //{"~OTHER~"},
//            {"Charts", "Apex", "Flot", "Sparkline"},
//            {"Icons", "Feather Icons", "Flag Icons", "Mdi Icons"},
//            {"Special Pages", "Blank page", "Faq", "Invoice", "Profile", "Pricing", "Timeline"},
//            {"Logout"}};
        String menus[][] = {
                {"Trang chủ"},
                {"Quản lý bán vé", "Đổi trả vé", "Sửa vé"},
                {"Quản lý nhân viên", "Thêm lịch làm việc", "Cập nhật lịch làm việc", "Thêm nhân viên", "Cập nhật nhân viên"},
                {"Quản lý khách hàng", "Thêm khách hàng", "Tìm khách hàng", "Cập nhật thông tin khách hàng"},
                {"Quản lý chuyến tàu", "Thêm chuyến tàu", "Cập nhật thông tin chuyến tàu"},
                {"Quản lý khuyến mãi", "Tạo chương trình khuyến mãi", "Cập nhật % khuyến mãi"},
                {"Tra cứu", "Tra cứu khách hàng", "Tra cứu vé", "Tra cứu tuyến", "Tra cứu chuyến", "Tra cứu hóa đơn"},
                {"Thống kê", "Thống kê khách hàng", "Thống kê vé", "Thống kê doanh thu", "Thống kê doanh thu theo ca"},
        };


        String icons[] = {
            "dashboard.svg",
            "email.svg",
            "chat.svg",
            "calendar.svg",
            "ui.svg",
            "forms.svg",
            "chart.svg",
            "icon.svg",
            "page.svg",
            "logout.svg"};

        return new SimpleMenuOption()
                .setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("raven/drawer/icon")
                .setIconScale(0.45f)
                /*.addMenuEvent(new MenuEvent() {
                    @Override
                  public void selected(MenuAction action, int index, int subIndex) {
                        if (index == 0) {
                            WindowsTabbed.getInstance().addTab("Test Form", new TestForm());
                        } else if (index == 9) {
                            Main.main.login();
                        }
                        System.out.println("Menu selected " + index + " " + subIndex);
                    }
                })*/
                .setMenuValidation(new MenuValidation() {
                    @Override
                    public boolean menuValidation(int index, int subIndex) {
//                        if(index==0){
//                            return false;
//                        }else if(index==3){
//                            return false;
//                        }
                        return true;
                    }

                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("Java Swing Drawer")
                .setDescription("Version 1.1.0");
    }

    @Override
    public int getDrawerWidth() {
        return 275;
    }
}
