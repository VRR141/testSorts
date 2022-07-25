import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        int[] temp = generateArray(20_000_000);

        int[] forBubble = generateArray(50_000);
        LocalTime n1 = LocalTime.now();
        bubble(forBubble);
        LocalTime n2 = LocalTime.now();
        long bubbleTime = ChronoUnit.MILLIS.between(n1, n2);
        System.out.println("BUBBLE " + forBubble.length/1000 + " thousands elements. Time: " + bubbleTime + " millis");

        int[] forMerge = Arrays.copyOf(temp, temp.length);
        LocalTime n3 = LocalTime.now();
        int[] resultMerge = mergeSort(forMerge);
        LocalTime n4 = LocalTime.now();
        long mergeTime = ChronoUnit.MILLIS.between(n3, n4);
        System.out.println("MERGE " + resultMerge.length/1000000 + " millions elements. Time: " + mergeTime + " millis");

        int[] forQuick = Arrays.copyOf(temp, temp.length);
        LocalTime n5 = LocalTime.now();
        quickSort(forQuick, 0, forQuick.length - 1);
        LocalTime n6 = LocalTime.now();
        long quickTime = ChronoUnit.MILLIS.between(n5, n6);
        System.out.println("QUICK " + forQuick.length/1000000 + " millions elements. Time: " + quickTime + " millis");

        int max = 1_000_000;
        int[] forLine = generateArray(20_000_000, max);
        LocalTime n7 = LocalTime.now();
        lineSort(forLine, max);
        LocalTime n8 = LocalTime.now();
        long lineTime = ChronoUnit.MILLIS.between(n7, n8);
        System.out.println("LINE, values from: " + 0 + " to " + max + ", " + forLine.length/1000000 + " millions elements. Time: " + lineTime + " millis");
    }

    public static void lineSort(int[] arr, int max) {
        int[] tempArr = new int[max];
        for (int i = 0; i < arr.length; i++) {
            tempArr[arr[i]]++;
        }
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            while (tempArr[c] == 0) {
                c++;
            }
            arr[i] = c;
            tempArr[c]--;
        }
    }

    public static void quickSort(int[] arr, int from, int to){
        if (from < to) {
            int divideIndex = partition(arr, from, to);
            quickSort(arr, from, divideIndex - 1);
            quickSort(arr, divideIndex, to);
        }
    }

    private static int partition(int[] arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;
        int pivot = arr[from];

        while (leftIndex <= rightIndex) {

            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }

            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                int temp = arr[leftIndex];
                arr[leftIndex] = arr[rightIndex];
                arr[rightIndex] = temp;
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    public static void bubble(int[] ints) {
        for (int j = ints.length; j > 0; j--) {
            for (int i = 0; i < ints.length - 1; i++) {
                if (ints[i] > ints[i + 1]) {
                    int temp = ints[i + 1];
                    ints[i + 1] = ints[i];
                    ints[i] = temp;
                }
            }
        }
    }

    public static int[] mergeSort(int[] ints){
        if (ints.length < 2) {
            return ints;
        }

        int[] left = Arrays.copyOfRange(ints, 0, ints.length/2);
        int[] right = Arrays.copyOfRange(ints, left.length, ints.length);
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    public static int[] merge(int[] A, int[] B){
        int[] res = new int[A.length + B.length];
        int a = 0, b = 0;
        for (int c =0; c < res.length; c++){
            if (a == A.length) {
                res[c] = B[b];
                b++;
            } else if (b == B.length){
                res[c] = A[a];
                a++;
            } else if (A[a] < B[b]){
                res[c] = A[a];
                a++;
            } else {
                res[c] = B[b];
                b++;
            }
        }
        return res;
    }

    public static int[] generateArray(int value){
        int[] bigArray = new int[value];
        for (int i = 0; i < bigArray.length; i++){
            bigArray[i] = (int) (Math.random()*value);
        }
        return bigArray;
    }

    public static int[] generateArray(int value, int max){
        int[] bigArray = new int[value];
        for (int i = 0; i < bigArray.length; i++){
            bigArray[i] = (int) (Math.random()*max);
        }
        return bigArray;
    }

}
