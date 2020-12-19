package vn.hust.kstn.tkxdpm.bankSystem;

/**
 * Interface Interbank, định nghĩa các phương thức để kết nối thanh toán .
 */
public interface InterbankInterface {
    /**
     * Phương thức pay
     *
     * @param o thông tin thanh toán
     * @return Kết quả giao dịch
     */
    public String processPayTransaction(Object o) ;

    /**
     * Phương thức refund
     *
     * @param o thông tin thanh toán
     * @return Kết quả giao dịch
     */
    public String processReturnTransaction(Object o);
    /**
     * Hàm giải mã lỗi tương ứng
     *
     * @param code mã lỗi
     * @return giải thích lỗi tương ứng dạng String
     */
    public String codeToDetail(String code);
}
