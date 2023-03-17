package com.sparta.studybook.entity;

public enum LikeEnum {
    POST(0);

    private final int index;

    LikeEnum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
