package com.wondertek.baiying.util;

/**
 * Created by wd on 2018/4/17.
 */
public class InnerClass {

    private Object[] items;
    private int next = 0;

    public InnerClass(int size) {
        this.items = new Object[size];
    }

    public void add(Object object) {
        if (next < items.length) {
            items[next++] = object;
        }
    }

    private class InnerClassSelector implements Selector {

        private int i = 0;

        /**
         * 内部类可以直接访问外部类中的成员变量
         * @return
         */
        @Override
        public Boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length) {
                i++;
            }
        }
    }

    public Selector selector() {
        return new InnerClassSelector();
    }

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass(10);
        for (int i = 0; 1<10; i++) {
            innerClass.add(Integer.toString(i));
        }
       //innerClass.selector();
    }
}
