package com.atguigu.juc.recenter;

import lombok.Getter;

public enum  CountryEnum {
    ONE(1, "齐"),TWO(2, "楚"),THREE(3, "燕"),FOUR(4, "韩"),FIVE(5, "赵"),SIX(6, "魏");
    @Getter private Integer retCode;
    @Getter private String message;

    CountryEnum(Integer retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public static CountryEnum foreach_CountryEnum(Integer retCode) {
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum element : countryEnums) {
            if (retCode == element.getRetCode()) {
                return element;
            }
        }
        return null;
    }
}
