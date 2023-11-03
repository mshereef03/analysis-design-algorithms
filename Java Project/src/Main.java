import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String [] args){
        int a =2;
        for (int size = 1; size <= 1000000; size *=2) {

            long startTime = System.currentTimeMillis();
            powerRecursive(a,size);
            long endTime = System.currentTimeMillis();

            double executionTime = (endTime - startTime)/1000.0;

            System.out.print(size+" "+executionTime+" ");
        }
    }

    //Question 1

    public static int powerIterative(int a,int n){
        int ans = 1;
        for(int i=1;i<=n;i++){
            ans*=a;
        }
        return ans;
    }

    public static int powerRecursive(int a,int n){
        if (n==0) return 1;
        if (n==1) return a;
        if(n%2==0)return powerRecursive(a,n/2) * powerRecursive(a, n/2);
        else return powerRecursive(a,(n/2) ) * powerRecursive(a, (n/2)+1);
    }


    // Question 2

        public static List<int[]> findPairsWithSum(int[] set, int target) {
            List<int[]> result = new ArrayList<>();


            mergeSort(set, 0, set.length - 1);

            for (int i = 0; i < set.length; i++) {
                int complement = target - set[i];
                if (binarySearch(set, complement, i + 1, set.length - 1)) {
                    result.add(new int[]{set[i], complement});
                }
            }

            return result;
        }

        // Merge Sort algorithm
        private static void mergeSort(int[] arr, int left, int right) {
            if (left < right) {
                int mid = left + (right - left) / 2;
                mergeSort(arr, left, mid);
                mergeSort(arr, mid + 1, right);
                merge(arr, left, mid, right);
            }
        }

        private static void merge(int[] arr, int left, int mid, int right) {
            int n1 = mid - left + 1;
            int n2 = right - mid;

            int[] L = new int[n1];
            int[] R = new int[n2];

            for (int i = 0; i < n1; i++) {
                L[i] = arr[left + i];
            }
            for (int j = 0; j < n2; j++) {
                R[j] = arr[mid + 1 + j];
            }

            int i = 0, j = 0, k = left;

            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                arr[k] = L[i];
                i++;
                k++;
            }

            while (j < n2) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }

        // Binary Search
        private static boolean binarySearch(int[] arr, int target, int left, int right) {
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (arr[mid] == target) {
                    return true;
                } else if (arr[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return false;
        }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }

    }



