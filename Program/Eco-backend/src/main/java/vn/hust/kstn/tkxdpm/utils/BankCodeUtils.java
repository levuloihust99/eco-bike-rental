package vn.hust.kstn.tkxdpm.utils;

public class BankCodeUtils {
    public static String codeToDetail(int code) {
        String output;
        switch (code) {
            case 0:
                output = "Giao dịch thành công";
                break;
            case 1:
                output = "Thẻ không hợp lệ";
                break;
            case 2:
                output = "Thẻ không đủ số dư";
                break;
            case 3:
                output = "Internal Server Error";
                break;
            case 4:
                output = "Giao dịch thất bại";
                break;
            default:
                output = "Something wrong ! No matching code ..";
        }
        return output;
    }
}
