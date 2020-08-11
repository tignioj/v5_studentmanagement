package booleanTest;

import org.junit.Test;

public class TestBoolean {
    @Test
    public void testBoolean() {
        /**
         * || 表达式当遇到第一个true就不往下执行
         * | 表达式会执行所有代码块
         */
        boolean b = getTrue() & getFalse() & getFalse();

        System.out.println("----");
        System.out.println(b);


    }

    public boolean getTrue() {
        System.out.println(true);
        return true;
    }
    public boolean getFalse() {
        System.out.println(false);
        return false;
    }
}
