package com.spring;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        String str="ram is a good boy";
        Map<Character,Integer> map=new HashMap<>();
        char[] characters=str.toCharArray();
        for (char c:characters) {
            if(c==' ')
                continue;
            if(map.containsKey(c))
            {
                int value=map.get(c);
                map.put(c,value+1);
            }
            else{
                map.put(c,1);
            }
        }
        System.out.println(map);
    }
}
