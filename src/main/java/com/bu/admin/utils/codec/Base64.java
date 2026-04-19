

package com.bu.admin.utils.codec;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public final class Base64 {

    private Base64() {
        throw new IllegalStateException("Utility class");
    }

    static final int BASELENGTH = 128;
    static final int LOOKUPLENGTH = 64;
    static final int TWENTYFOURBITGROUP = 24;
    static final int EIGHTBIT = 8;
    static final int SIXTEENBIT = 16;
    static final int FOURBYTE = 4;
    static final int SIGN = -128;
    static final char PAD = '=';
    static final boolean FDEBUG = false;
    static final byte[] base64Alphabet = new byte[BASELENGTH];
    static final char[] lookUpBase64Alphabet = new char[LOOKUPLENGTH];

    static {
        Arrays.fill(base64Alphabet, (byte) -1);
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i >= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }

        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }

        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;

        for (int i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (char) ('A' + i);
        }

        for (int i = 26, j = 0; i <= 51; i++, j++) {
            lookUpBase64Alphabet[i] = (char) ('a' + j);
        }

        for (int i = 52, j = 0; i <= 61; i++, j++) {
            lookUpBase64Alphabet[i] = (char) ('0' + j);
        }
        lookUpBase64Alphabet[62] = '+';
        lookUpBase64Alphabet[63] = '/';

    }

    private static boolean isWhiteSpace(char octect) {
        return (octect == 0x20 || octect == 0xd || octect == 0xa || octect == 0x9);
    }

    private static boolean isPad(char octect) {
        return (octect == PAD);
    }

    private static boolean isData(char octect) {
        return (octect < BASELENGTH && base64Alphabet[octect] != -1);
    }

    /**
     * Encodes hex octects into Base64
     *
     * @param binaryData Array containing binaryData
     * @return Encoded Base64 array
     */
    public static String encode(byte[] binaryData) {

        if (binaryData == null) {
            return null;
        }

        EncodeCal encodeCal = new EncodeCal(binaryData);
        if (encodeCal.lengthDataBits == 0) {
            return "";
        }

        if (FDEBUG) {
            log.info("number of triplets = " + encodeCal.numberTriplets);
        }

        setEncodeData(encodeCal, binaryData);

        // form integral number of 6-bit groups
        if (encodeCal.fewerThan24bits == EIGHTBIT) {
            encodeCal.b1 = binaryData[encodeCal.dataIndex];
            encodeCal.k = (byte) (encodeCal.b1 & 0x03);
            if (FDEBUG) {
                log.info("b1=" + encodeCal.b1);
                log.info("b1<<2 = " + (encodeCal.b1 >> 2));
            }
            byte val1 = ((encodeCal.b1 & SIGN) == 0) ? (byte) (encodeCal.b1 >> 2) : (byte) ((encodeCal.b1) >> 2 ^ 0xc0);
            encodeCal.encodedData[encodeCal.encodedIndex++] = lookUpBase64Alphabet[val1];
            encodeCal.encodedData[encodeCal.encodedIndex++] = lookUpBase64Alphabet[encodeCal.k << 4];
            encodeCal.encodedData[encodeCal.encodedIndex++] = PAD;
            encodeCal.encodedData[encodeCal.encodedIndex++] = PAD;
            log.debug(String.valueOf(encodeCal.encodedIndex));
        } else if (encodeCal.fewerThan24bits == SIXTEENBIT) {
            encodeCal.b1 = binaryData[encodeCal.dataIndex];
            encodeCal.b2 = binaryData[encodeCal.dataIndex + 1];
            encodeCal.l = (byte) (encodeCal.b2 & 0x0f);
            encodeCal.k = (byte) (encodeCal.b1 & 0x03);

            byte val1 = ((encodeCal.b1 & SIGN) == 0) ? (byte) (encodeCal.b1 >> 2) : (byte) ((encodeCal.b1) >> 2 ^ 0xc0);
            byte val2 = ((encodeCal.b2 & SIGN) == 0) ? (byte) (encodeCal.b2 >> 4) : (byte) ((encodeCal.b2) >> 4 ^ 0xf0);

            encodeCal.encodedData[encodeCal.encodedIndex++] = lookUpBase64Alphabet[val1];
            encodeCal.encodedData[encodeCal.encodedIndex++] = lookUpBase64Alphabet[(val2 & 0xff) | (encodeCal.k << 4)];
            encodeCal.encodedData[encodeCal.encodedIndex++] = lookUpBase64Alphabet[encodeCal.l << 2];
            encodeCal.encodedData[encodeCal.encodedIndex++] = PAD;
            log.debug(String.valueOf(encodeCal.encodedIndex));
        }

        return String.valueOf(encodeCal.encodedData);
    }

    private static void setEncodeData(EncodeCal encodeCal, byte[] binaryData) {
        for (int i = 0; i < encodeCal.numberTriplets; i++) {
            encodeCal.b1 = binaryData[encodeCal.dataIndex++];
            encodeCal.b2 = binaryData[encodeCal.dataIndex++];
            encodeCal.b3 = binaryData[encodeCal.dataIndex++];

            if (FDEBUG) {
                log.info("b1= " + encodeCal.b1 + ", b2= " + encodeCal.b2 + ", b3= " + encodeCal.b3);
            }

            encodeCal.l = (byte) (encodeCal.b2 & 0x0f);
            encodeCal.k = (byte) (encodeCal.b1 & 0x03);

            byte val1 = ((encodeCal.b1 & SIGN) == 0) ? (byte) (encodeCal.b1 >> 2) : (byte) ((encodeCal.b1) >> 2 ^ 0xc0);
            byte val2 = ((encodeCal.b2 & SIGN) == 0) ? (byte) (encodeCal.b2 >> 4) : (byte) ((encodeCal.b2) >> 4 ^ 0xf0);
            byte val3 = ((encodeCal.b3 & SIGN) == 0) ? (byte) (encodeCal.b3 >> 6) : (byte) ((encodeCal.b3) >> 6 ^ 0xfc);

            if (FDEBUG) {
                log.info("val2 = " + val2);
                log.info("k4   = " + (encodeCal.k << 4));
                log.info("vak  = " + ((val2 & 0xff) | (encodeCal.k << 4)));
            }

            encodeCal.encodedData[encodeCal.encodedIndex++] = lookUpBase64Alphabet[val1];
            encodeCal.encodedData[encodeCal.encodedIndex++] = lookUpBase64Alphabet[(val2 & 0xff) | (encodeCal.k << 4)];
            encodeCal.encodedData[encodeCal.encodedIndex++] = lookUpBase64Alphabet[(encodeCal.l << 2) | (val3 & 0xff)];
            encodeCal.encodedData[encodeCal.encodedIndex++] = lookUpBase64Alphabet[encodeCal.b3 & 0x3f];
        }
    }

    /**
     * Decodes Base64 data into octects
     *
     * @param encoded string containing Base64 data
     * @return Array containind decoded data.
     */
    public static byte[] decode(String encoded) {

        if (encoded == null) {
            return new byte[0];
        }

        char[] base64Data = encoded.toCharArray();
        // remove white spaces
        int len = removeWhiteSpace(base64Data);

        if (len % FOURBYTE != 0) {
            return new byte[0];//should be divisible by four
        }
        DecodeCal decodeCal = new DecodeCal(base64Data,len);

        if (decodeCal.numberQuadruple == 0) {
            return new byte[0];
        }

        setDecodeData(decodeCal);
        if(decodeCal.decodedData.length ==0){
            return new byte[0];
        }
        decodeCal.d1 = decodeCal.base64Data[decodeCal.dataIndex++];
        decodeCal.d2 = decodeCal.base64Data[decodeCal.dataIndex++];
        if (!isData(decodeCal.d1) || !isData(decodeCal.d2)) {
            return new byte[0];//if found "no data" just return null
        }

        decodeCal.b1 = base64Alphabet[decodeCal.d1];
        decodeCal.b2 = base64Alphabet[decodeCal.d2];

        decodeCal.d3 = decodeCal.base64Data[decodeCal.dataIndex++];
        decodeCal.d4 = decodeCal.base64Data[decodeCal.dataIndex++];
        log.debug(String.valueOf(decodeCal.dataIndex));
        setDecodeDataDetail(decodeCal);

        return decodeCal.decodedData;
    }

    private static void setDecodeData(DecodeCal decodeCal) {
        for (; decodeCal.i < decodeCal.numberQuadruple - 1; decodeCal.i++) {
            decodeCal.d1 = decodeCal.base64Data[decodeCal.dataIndex++];
            decodeCal.d2 = decodeCal.base64Data[decodeCal.dataIndex++];
            decodeCal.d3 = decodeCal.base64Data[decodeCal.dataIndex++];
            decodeCal.d4 = decodeCal.base64Data[decodeCal.dataIndex++];
            if (!isData(decodeCal.d1) || !isData(decodeCal.d2) || !isData(decodeCal.d3) || !isData(decodeCal.d4)) {
                decodeCal.decodedData = new byte[0];
            }//if found "no data" just return null

            decodeCal.b1 = base64Alphabet[decodeCal.d1];
            decodeCal.b2 = base64Alphabet[decodeCal.d2];
            decodeCal. b3 = base64Alphabet[decodeCal.d3];
            decodeCal.b4 = base64Alphabet[decodeCal.d4];

            decodeCal.decodedData[decodeCal.encodedIndex++] = (byte) (decodeCal.b1 << 2 | decodeCal.b2 >> 4);
            decodeCal.decodedData[decodeCal.encodedIndex++] = (byte) (((decodeCal.b2 & 0xf) << 4) | ((decodeCal.b3 >> 2) & 0xf));
            decodeCal.decodedData[decodeCal.encodedIndex++] = (byte) (decodeCal.b3 << 6 | (decodeCal.b4 & 0xff));
        }
    }

    private static void setDecodeDataDetail(DecodeCal decodeCal) {
        if (!isData((decodeCal.d3)) || !isData((decodeCal.d4))) {//Check if they are PAD characters
            if (isPad(decodeCal.d3) && isPad(decodeCal.d4)) {
                if ((decodeCal.b2 & 0xf) != 0)//last 4 bits should be zero
                {
                    decodeCal.decodedData = new byte[0];
                }
                byte[] tmp = new byte[decodeCal.i * 3 + 1];
                System.arraycopy(decodeCal.decodedData, 0, tmp, 0, decodeCal.i * 3);
                tmp[decodeCal.encodedIndex] = (byte) (decodeCal.b1 << 2 | decodeCal.b2 >> 4);
                decodeCal.decodedData = tmp;
            } else if (!isPad(decodeCal.d3) && isPad(decodeCal.d4)) {
                decodeCal.b3 = base64Alphabet[decodeCal.d3];
                if ((decodeCal.b3 & 0x3) != 0)//last 2 bits should be zero
                {
                    decodeCal.decodedData = new byte[0];
                }
                byte[] tmp = new byte[decodeCal.i * 3 + 2];
                System.arraycopy(decodeCal.decodedData, 0, tmp, 0, decodeCal.i * 3);
                tmp[decodeCal.encodedIndex++] = (byte) (decodeCal.b1 << 2 | decodeCal.b2 >> 4);
                tmp[decodeCal.encodedIndex] = (byte) (((decodeCal.b2 & 0xf) << 4) | ((decodeCal.b3 >> 2) & 0xf));
                decodeCal.decodedData = tmp;
            } else {
                decodeCal.decodedData = new byte[0];
            }
        } else { //No PAD e.g 3cQl
            decodeCal.b3 = base64Alphabet[decodeCal.d3];
            decodeCal.b4 = base64Alphabet[decodeCal.d4];
            decodeCal.decodedData[decodeCal.encodedIndex++] = (byte) (decodeCal.b1 << 2 | decodeCal.b2 >> 4);
            decodeCal.decodedData[decodeCal.encodedIndex++] = (byte) (((decodeCal.b2 & 0xf) << 4) | ((decodeCal.b3 >> 2) & 0xf));
            decodeCal.decodedData[decodeCal.encodedIndex++] = (byte) (decodeCal.b3 << 6 | (decodeCal.b4 & 0xff));
            log.debug(String.valueOf(decodeCal.encodedIndex));
        }
    }

    /**
     * remove WhiteSpace from MIME containing encoded Base64 data.
     *
     * @param data the byte array of base64 data (with WS)
     * @return the new length
     */
    private static int removeWhiteSpace(char[] data) {
        if (data == null) {
            return 0;
        }

        // count characters that's not whitespace
        int newSize = 0;
        int len = data.length;
        for (int i = 0; i < len; i++) {
            if (!isWhiteSpace(data[i])) {
                data[newSize++] = data[i];
            }
        }
        return newSize;
    }
}
