package com.atguigu.juc.recenter2;

import lombok.Getter;

public enum  CountryEnum {
    ONE(1, "齐国"), TWO(2, "楚国"), THREE(3, "燕国"), FOUR(4, "韩国"), FIVE(5, "赵国"), SIX(6, "魏国");
    @Getter private int retCode;
    @Getter private String value;

    CountryEnum(int retCode, String value) {
        this.retCode = retCode;
        this.value = value;
    }

    public static CountryEnum forEachCountry(int retCode) {
        CountryEnum[] countryEnums = CountryEnum.values();
        CountryEnum country = null;
        for (CountryEnum countryEnum : countryEnums) {
            if (retCode == countryEnum.retCode) {
                country = countryEnum;
            }
        }
        return country;
    }
}
