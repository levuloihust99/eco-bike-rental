package vn.hust.kstn.tkxdpm.utils;

/**
 * Lớp đảm nhận nhiệm vụ chuyển loại xe từ code dạng int tương ứng
 */
public class BikeTypeUtils {
    /**
     * Hàm chuyển mã thành loại xe
     *
     * @param type the type
     * @return Loại xe tương ứng dạng String
     */
    public static String translateType(int type){
        if (type == 1 )
            return "Xe đạp đơn" ;
        if (type == 2 )
            return  "Xe đạp đôi";
        if (type == 3)
            return "Xe đạp điện";
        else return
        "Loại xe chưa định nghĩa" ;
    }
}
