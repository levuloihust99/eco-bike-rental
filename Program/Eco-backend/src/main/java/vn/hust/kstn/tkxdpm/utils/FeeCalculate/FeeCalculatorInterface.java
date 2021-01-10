package vn.hust.kstn.tkxdpm.utils.FeeCalculate;

import vn.hust.kstn.tkxdpm.entity.RenttransactionEntity;

public interface FeeCalculatorInterface {
    public Long calculateFee(RenttransactionEntity renttransactionEntity);
}
