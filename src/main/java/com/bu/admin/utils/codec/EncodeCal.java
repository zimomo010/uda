package com.bu.admin.utils.codec;

import lombok.Data;

@Data
public class EncodeCal {
    static final int EIGHTBIT = 8;
    static final int TWENTYFOURBITGROUP = 24;

    public EncodeCal(byte[] binaryData){
        lengthDataBits  = binaryData.length * EIGHTBIT;
        fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;
        numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;
        numberQuartet = fewerThan24bits != 0 ? numberTriplets + 1 : numberTriplets;
        encodedData = new char[numberQuartet * 4];
    }

    int lengthDataBits;
    int fewerThan24bits;
    int numberTriplets;
    int numberQuartet;
    char[] encodedData;

    byte k = 0;
    byte l = 0;
    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;

    int encodedIndex = 0;
    int dataIndex = 0;
}
