package vn.hust.kstn.tkxdpm.bankSystem.InterbankSubsystem;

import lombok.Getter;
import lombok.Setter;

/**
 *  Lớp của dối tượng Inter bank transaction, chứa thông tin cần thiết phục vụ việc thanh toán.
 */
@Getter
@Setter
public class InterBankTransaction {
        /**
         * The Amount of money.
         */
        String amount ;
        /**
         * The Card code.
         */
        String cardCode ;
        /**
         * The Cvv code.
         */
        String cvvCode ;
        /**
         * The Date expired.
         */
        String dateExpired ;
        /**
         * The Owner.
         */
        String owner ;
        /**
         * The Transaction content.
         */
        String transactionContent ;
        /**
         * The Created at.
         */
        String createdAt;
        /**
         * The transaction command.
         */
        String command ;
}
