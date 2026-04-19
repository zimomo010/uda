package com.bu.admin.utils.sequence;


import com.bu.admin.utils.ServiceLocator;


public class SequenceGenerator {

    private static final SequenceIncrementer sequenceIncrementer = ServiceLocator.getBean(SequenceIncrementer.class, "sequenceIncrementer");

    private SequenceGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static long nextValue(String seqName) {
        assert SequenceGenerator.sequenceIncrementer != null;
        return SequenceGenerator.sequenceIncrementer.nextValue(seqName);
    }
}
