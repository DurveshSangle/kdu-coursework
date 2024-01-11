package kdu.backend.hw4.q3;

import kdu.backend.hw4.logging;

import java.util.Arrays;

public class GenericMethod {

    /**
     * Returns array with swap positions
     *
     * @param <T> type of array
     * @param array array of type T
     * @param position1 position to be swapped with position2
     * @param position2 position to be swapped with position1
     *
     * */
    public <T> void swap(T[] array,int position1,int position2){
        T temporary = array[position1];
        array[position1] = array[position2];
        array[position2] = temporary;
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        logging log = new logging();

        Integer[] integerArray = {10,20,50,70};
        String[] stringArray = {"hello","cute","cat"};

        genericMethod.swap(integerArray,1,2);
        genericMethod.swap(stringArray,1,2);

        log.logInfo("Integer Array: {}", Arrays.toString(integerArray));
        log.logInfo("String Array: {}", Arrays.toString(stringArray));
    }
}
