package com.wondertek.baiying.test;

/**
 * Created by wd on 2018/11/26.
 */
public class Test{
    private static Test tester = new Test();
    private static int count1;
    private static int count2 = 2;
    public Test(){
        count1++;
        count2++;
        System.out.println("" + count1);
        System.out.println("" + count2);
    }
    public static Test getTester(){
        return tester;
    }
    public static void main(String[] args){
        Test.getTester();
        System.out.println("" + count1);
        System.out.println("" + count2); // 结果1,1,1,2
    }

}
class X {
    Y b = new Y();
    X() {
        System.out.print("X");
    }
}
class Y {
    Y() {
        System.out.print("Y");
    }
}
class Z extends X {
    Y y = new Y();

    Z() {
        System.out.print("Z");
    }

    public static void main(String[] args) {
        new Z();     //结果是 YXYZ
    }
}
