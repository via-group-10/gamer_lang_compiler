package wasm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Encoding {
    public static int[] concat(int[]... arrays)
    {
        int finalLength = 0;
        for (int[] array: arrays) {
            finalLength += array.length;
        }

        int[] dest = null;
        int destPos = 0;

        for (int[] array: arrays)
        {
            if (dest == null)
            {
                dest = Arrays.copyOf(array, finalLength);
                destPos = array.length;
            }
            else {
                System.arraycopy(array, 0, dest, destPos, array.length);
                destPos += array.length;
            }
        }
        return dest;
    }

    public static int[] encodeFloat(float value){
        int intValue = Float.floatToIntBits(value);
        int array[] = new int[4];
        array[3] = (intValue>>>24);
        array[2] = (intValue<<8)>>>24;
        array[1] = (intValue<<16)>>>24;
        array[0] = (intValue<<24)>>>24;
        return array;
    }

    public static int[] encodeString( String str){
        ArrayList<Integer> buffer = new ArrayList<>();
        int length = str.length();
        buffer.add(length);
        for (int i = 0; i < length; i++) {
            int ascii = (int) str.charAt(i);
            buffer.add(ascii);
        }
        return buffer.stream().mapToInt(Integer::intValue).toArray();
    }


    public static void writeToFile(int[] data, String filename){
        try{
            OutputStream outputStream = new FileOutputStream(filename);
            for (int b : data) {
                outputStream.write(b);
            }
            outputStream.close();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    public static void printHex(int[] data){
            for (int b : data) {
                System.out.print(String.format("%02X ", b));
            }
        System.out.println();
    }

    public static int[] unsignedLeb128(int n) {
        ArrayList<Integer> buffer = new ArrayList<>();
        do {
            int Byte = n & 0x7f;
            n >>>= 7;
            if (n != 0) {
                Byte |= 0x80;
            }
            buffer.add(Byte);
        } while (n != 0);
        return buffer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] signedLeb128(int n) {
        ArrayList<Integer> buffer = new ArrayList<>();
        boolean more = true;
        while (more) {
            int Byte = n & 0x7f;
            n >>>= 7;
            if ((n == 0 && (Byte & 0x40) == 0) || (n == -1 && (Byte & 0x40) != 0)) {
                more = false;
            } else {
                Byte |= 0x80;
            }
            buffer.add(Byte);
        }
        return buffer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static ArrayList<Integer> toIntegerList(int[] array){
        ArrayList<Integer> list = IntStream.of(array)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        return  list;
    }

    public static int[] toIntArray(List<int[]> list){
        int size = 0;
        for (int[] array : list) {
            size += array.length;
        }
        int[] result = new int[size];
        int pos = 0;
        for (int[] array : list) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    public static int[] flatten(int[][] array){
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] subArray : array) {
            list.addAll(toIntegerList(subArray));
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
