package com.jason.lock;

import java.util.Arrays;
import java.util.List;

//假设以后21个桃子，2个猴子
// 模拟线程安全消费
public class MonkeyStealsPeach {

    private  int numOfPeach =21;

    private long sleepTime =200;

    private static List<String> monkeys= Arrays.asList("猴子A","猴子B");

    private byte[] bytes=new byte[0];

    private void stealPeach(String monkeyName){
        if(numOfPeach>2){
            // 锁非指定对象，
            //为什么不用new object，0长度额byte数组对象比较经济，字节码需要3条，object则需要7条。
            synchronized (bytes){
                System.out.println(monkeyName + "开始偷桃子，此时桃子还剩"+numOfPeach + "个");
                if(numOfPeach>2){
                    numOfPeach-=2;
                    System.out.println(monkeyName+"偷完后，此时桃子还剩"+numOfPeach+"个");
                }else{
                    System.out.println(monkeyName+"没有继续偷，此时桃子还剩"+numOfPeach+"个");
                }
            }
            try{
                //模拟偷桃子需要时间
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(numOfPeach>2){
                stealPeach(monkeyName);
            }
        }else{
            System.out.println(monkeyName+"没有继续偷，此时桃子还剩"+numOfPeach+"个");
        }
    }

    public static void main(String[] args) {
        MonkeyStealsPeach monkeyStealsPeach = new MonkeyStealsPeach();
        for(String monkeyName : monkeys){
            Thread thread=new Thread(()->monkeyStealsPeach.stealPeach(monkeyName));
            thread.start();
        }
    }
}
