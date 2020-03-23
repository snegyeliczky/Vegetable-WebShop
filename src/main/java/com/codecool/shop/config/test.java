package com.codecool.shop.config;

import java.util.Arrays;

public class test {

        public static String vowel2Index(String s) {
            String[] charlist = new String[]{"A","E","I","O","U","Y"};
            String[] sArray=s.split("");
            for (int i = 0; i < sArray.length; i++) {
                for (int j = 0; j < charlist.length; j++) {
                    if (sArray[i].toLowerCase().equals(charlist[j].toLowerCase())){
                        sArray[i] = (i+1)+"";
                    }

                }
            }

            System.out.println(s);
            System.out.println(Arrays.toString(sArray));
            System.out.println(String.join("",sArray));
            return String.join("",sArray);
    }

    public static void main(String[] args) {
        vowel2Index("HEllo bello te lo");
    }


}
