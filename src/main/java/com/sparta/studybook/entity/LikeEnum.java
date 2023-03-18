package com.sparta.studybook.entity;

public enum LikeEnum {
    POST(0);

    private final int seq;

    LikeEnum(int seq) {
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }
}
