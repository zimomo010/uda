package com.bu.admin.utils.worddeal;


import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCodes;
import com.deepoove.poi.xwpf.NiceXWPFDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashSet;
import java.util.List;


public class WordUtils {

    private WordUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = LoggerFactory.getLogger(WordUtils.class);


    public static void mergeWordFiles(String file0, String file1, String targetFileUrl) {
        logger.info("merge word files....[{}] and [{}],new file url is [{}]", file0, file1, targetFileUrl);
        try(NiceXWPFDocument doc0 = new NiceXWPFDocument(new FileInputStream(file0))) {
            NiceXWPFDocument doc1 = new NiceXWPFDocument(new FileInputStream(file1));
            NiceXWPFDocument doc2 = doc0.merge(doc1);

            handleCompatibility(doc2);

            FileOutputStream out = new FileOutputStream(targetFileUrl);
            doc0.write(out);
            out.close();
        } catch (IOException e1) {
            throw new BasicException(ErrorCodes.DataDeal.IO_ERROR);
        } catch (Exception e2) {
            logger.error("merge word file error,error message is :", e2);
            throw new BasicException(ErrorCodes.DataDeal.CONVERT_OBJ_ERROR);
        }
    }

    private static void handleCompatibility(NiceXWPFDocument doc) {
        List<IBodyElement> elements = doc.getBodyElements();
        HashSet<String> rids = new HashSet<>();
        for (IBodyElement be : elements) {
            if (be instanceof XWPFParagraph para) {
                if (para.getCTPPr().getSectPr() != null &&
                        para.getCTPPr().getSectPr().getFooterReferenceArray().length > 0) {
                    CTHdrFtrRef ref = para.getCTPPr().getSectPr().getFooterReferenceArray(0);
                    if (rids.contains(ref.getId())) {
                        logger.info("merge word files: remove reference [{}],", ref.getId());
                        para.getCTPPr().getSectPr().removeFooterReference(0);
                    } else {
                        rids.add(ref.getId());
                    }
                }else {
                    logger.info("footer is null..");
                }
            }
        }
    }
}
