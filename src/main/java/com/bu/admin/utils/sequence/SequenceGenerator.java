package com.bu.admin.utils.sequence;


import com.bu.admin.utils.ServiceLocator;

/**
 * 序列生成器
 *
 * @author ghostWu
 * @date 15/8/18
 */
public class SequenceGenerator {

    private static final SequenceIncrementer sequenceIncrementer = ServiceLocator.getBean(SequenceIncrementer.class, "sequenceIncrementer");

    private SequenceGenerator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取序列值
     * @return
     */
    public static long nextValue(String seqName) {
        assert SequenceGenerator.sequenceIncrementer != null;
        return SequenceGenerator.sequenceIncrementer.nextValue(seqName);
    }
}
