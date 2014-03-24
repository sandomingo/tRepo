package com.springapp.base;

/**
 * 简单的字符串对
 * User: SanDomingo
 * Date: 3/22/14
 * Time: 2:22 PM
 */
public class SimplePair {
    private static final String EMPTY = "";
    private String s1;
    private String s2;

    public SimplePair(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public SimplePair(String s1) {
        this.s1 = s1;
        this.s2 = EMPTY;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    @Override
    public String toString() {
        return s1 + "\t" + s2;
    }
}
