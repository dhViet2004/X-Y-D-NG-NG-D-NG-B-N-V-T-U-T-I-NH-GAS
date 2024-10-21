select h.NgayHoaDon,k.TenKH,m.DoiTuongApDung,m.NoiDungKM, m.ChietKhau, h.TienKhuyenMai
from KhachHang k join HoaDon h on k.MaKH=h.MaKH join KhuyenMai m on h.KhuyenMaiMaKM = m.MaKM