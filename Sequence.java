package com.company;

import java.util.Arrays;

//можно обобщить, но для решения этой задачи и так пойдет
public class Sequence {
    int[] source;
    int length(){return source.length;}

    Sequence(int length){
        source = genSequence(length);
    }
    Sequence(String source) {
        this.source = stringToArray(source);
    }

    boolean contains(int key){
        return findIndx(key)!=-1;
    }
    int findIndx(int key) {
        return findIndx(source,key);
    }
    boolean haveRepetitions(){
        return haveRepetitions(source);
    }

    @Override
    public String toString() {
        return Arrays.toString(source);
    }
    static int[] stringToArray(String source){
        var arr = source.toCharArray();
        var result = new int[arr.length];
        for (var i=0;i<arr.length;i++){
            result[i] =  Integer.parseInt(""+arr[i]);
        }
        return result;
    }
    static boolean haveRepetitions(int[] source){
        for (var i=0;i<source.length;i++){
            for (var j=i+1;j<source.length;j++){
                if(source[i]==source[j]) return true;
            }
        }
        return false;
    }
    static int findIndx(int[] source, int key) {
        for (var i = 0; i < source.length; i++) {
            if (source[i] == key) return i;
        }
        return -1;
    }
    private int[] genSequence(int length){
        var result = new int[length];
        for(var i=0;i<result.length;i++){
            result[i]=-1;
        }
        for (var i=0;i<length;i++){
            var temp = genInt();
            while (findIndx(result,temp)!=-1){
                temp = temp == 9?0:++temp;
            }
            result[i]=temp;
        }
        return result;
    }
    private int genInt(){
        return (int)Math.floor(Math.random()*10);
    }
}
