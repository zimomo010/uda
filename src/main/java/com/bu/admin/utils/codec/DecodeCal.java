package com.bu.admin.utils.codec;

import lombok.Data;

@Data
public class DecodeCal {
    static final int FOURBYTE = 4;
    char[] base64Data;
    int len;
    int numberQuadruple;
    int fewerThan24bits;
    int numberTriplets;
    int numberQuartet;
    byte[] decodedData;

    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;

    char d1 = 0;
    char d2 = 0;
    char d3 = 0;
    char d4 = 0;

    int i = 0;
    int encodedIndex = 0;
    int dataIndex = 0;

    public DecodeCal(char[] base64Data,int len){
        this.base64Data = base64Data;
        this.len = len;
        numberQuadruple = (len / FOURBYTE);
        decodedData = new byte[(numberQuadruple) * 3];
    }

}
