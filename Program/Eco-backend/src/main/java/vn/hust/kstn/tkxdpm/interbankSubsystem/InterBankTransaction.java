package vn.hust.kstn.tkxdpm.interbankSubsystem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

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
