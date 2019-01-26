package com.jason.algorithmproblem;

/**
 * 回文系列
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(65433456));
    }

    /**
     *
     * @param n
     * @return
     * 构造一个回文数字与之前的比较
     */
    public static boolean isPalindromeNum(int n ){
        if(n<0){
            return false;
        }
        int orign=n;

        int roll=0;
        while(n!=0){
            roll =  roll* 10+n%10;
            n/=10;
        }
        return  roll ==orign;
    }


    public static boolean isPalindrome(int x) {
        int a = x, h = 1;
        if (a < 0) return false;
        while (a / h >= 10) {
            h = h * 10;
        }
        while (a > 0) {
            if (a / h != a % 10) return false;
            a = a % h;
            a = a / 10;
            h = h / 100;
        }
        return true;
    }

    public String getLong(String s ){
        if(s.length()==0) return "";
        String longstring ="";
        int start =0;
        int end=0;
        for(int i =0 ;i<s.length();i++ ){
            int len1 = expandAroundCenter(s,i,i);
            int len2 =expandAroundCenter(s,i,i+1);
            int len =Math.max(len1,len2);
            if(len> end-start){
                start=i-(len-1)/2;
                end =i + len/2;
            }
        }
        return longstring;
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }













}
