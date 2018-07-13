/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Arrays;

/**
 *
 * @author Tomek
 */
public class Splitter {
        byte[] data = { 2, 3, 5, 7, 8, 9, 11, 12, 13 };
        int blockSize = 1;
        int blockCount = 0;
        
    public Splitter(byte[] d,int bs){
        data = d;
        blockSize = bs;
        this.blockCount = (data.length + blockSize - 1) / blockSize;
    }              

    public void setBlockSize(int s){
        this.blockSize = s;
        this.blockCount = (data.length + blockSize - 1) / blockSize;
    }

    public int getBlockCount(){
        return (data.length + blockSize - 1) / blockSize;
    }

    public byte[] getChunk(int i){   
        byte[] range = null;
        int idx = i * blockSize;
        range = Arrays.copyOfRange(data, idx, idx + blockSize);
        return range;
    }

    public byte[] getLastChunk(){
        int end = -1;
        if (data.length % blockSize == 0) {
                end = data.length;
        } else {
                end = data.length % blockSize + blockSize * (blockCount - 1);
        }

        byte[] range = Arrays.copyOfRange(data, (blockCount - 1) * blockSize, end);
        return range;

    }   

    public static void main(String[] args){
        byte[] data = { 2, 3, 5, 7, 8, 9, 11, 12, 13 };
        Splitter s = new Splitter(data,1);
        s.setBlockSize(9);
        
        //byte[] middle = s.getChunk(2);
        //byte[] last = s.getLastChunk();
        System.out.println(s.getBlockCount());
    }

}
