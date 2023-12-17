import java.util.Arrays;
import java.util.List;

public class Assignment2 {

    public static LetterMatrix scoreMatrix;

    public static double[][]memoization;
    public static int[][]track;



    public static void main(String [] args){

        String a = "ATA"; // TCCCAGTTATGTCAGGGGACACGAGCATGCAGAGAC
        String b = "AA"; // AATTGCCGCCGTCGTTTTCAGCAGTTATGTCAGATC
        System.out.println("Score: "+sequenceAlignment(a,b));


    }

    public static double sequenceAlignment(String a, String b){

        int n =a.length()+1;
        int m = b.length()+1;

        // Initializing Score Matrix
        List<Character> dynamicIndices = Arrays.asList('A', 'G', 'T', 'C','-'); // Dynamic indices for Java 8
        scoreMatrix = new LetterMatrix(dynamicIndices);
        scoreMatrix.setValue('A','A',1.0);
        scoreMatrix.setValue('A','G',-0.8);
        scoreMatrix.setValue('A','T',-0.2);
        scoreMatrix.setValue('A','C',-2.3);
        scoreMatrix.setValue('A','-',-0.6);
        scoreMatrix.setValue('G','A',-0.8);
        scoreMatrix.setValue('G','G',1.0);
        scoreMatrix.setValue('G','T',-1.1);
        scoreMatrix.setValue('G','C',-0.7);
        scoreMatrix.setValue('G','-',-1.5);
        scoreMatrix.setValue('T','A',-0.2);
        scoreMatrix.setValue('T','G',-1.1);
        scoreMatrix.setValue('T','T',1.0);
        scoreMatrix.setValue('T','C',-0.5);
        scoreMatrix.setValue('T','-',-0.9);
        scoreMatrix.setValue('C','A',-2.3);
        scoreMatrix.setValue('C','G',-0.7);
        scoreMatrix.setValue('C','T',-0.5);
        scoreMatrix.setValue('C','C',1.0);
        scoreMatrix.setValue('C','-',-1.0);
        scoreMatrix.setValue('-','A',-0.6);
        scoreMatrix.setValue('-','G',-1.5);
        scoreMatrix.setValue('-','T',-0.9);
        scoreMatrix.setValue('-','C',-1.0);
        scoreMatrix.setValue('-','-',-10000.0);


        // Initializing DP Tables
        memoization = new double[n][m];
        track = new int[n][m];
        for( int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                memoization[i][j]=Double.MIN_VALUE;
            }
        }

        alignment(a, b);
        System.out.println("Sequence: ");
        reconstruct(a,b);
        return alignment(a,b);


    }

    public static double alignment(String a, String b){
        if(a.length()==0){
            double temp = 0;
            for(int i=0;i<b.length();i++){
                temp+= scoreMatrix.getValue('-',b.charAt(i));
            }
            memoization[0][b.length()]=temp;
            track[0][b.length()]=3;

            return temp;
        }
        if(b.length()==0){
            double temp = 0;
            for(int i=0;i<a.length();i++){
                temp+= scoreMatrix.getValue(a.charAt(i),'-');
            }
            track[a.length()][0]=2;
            return temp;
        }
        int aLastIndex = a.length()-1;
        int bLastIndex = b.length()-1;

        if(memoization[aLastIndex][bLastIndex] != Double.MIN_VALUE)return memoization[aLastIndex][bLastIndex];

        double temp1= scoreMatrix.getValue(a.charAt(aLastIndex),b.charAt(bLastIndex)) + alignment(a.substring(0,aLastIndex),b.substring(0,bLastIndex));
        double temp2= scoreMatrix.getValue(a.charAt(aLastIndex),'-') + alignment(a.substring(0,aLastIndex),b);
        double temp3= scoreMatrix.getValue('-',b.charAt(bLastIndex)) + alignment(a,b.substring(0,bLastIndex));

        memoization[aLastIndex][bLastIndex] = Math.max(temp1, Math.max(temp2,temp3));
        if(temp1>=temp2 && temp1>=temp3)track[aLastIndex+1][bLastIndex+1] =1;
        if(temp2>=temp1 && temp2>=temp3)track[aLastIndex+1][bLastIndex+1] =2;
        if(temp3>=temp1 && temp3>=temp2)track[aLastIndex+1][bLastIndex+1] =3;
        return memoization[aLastIndex][bLastIndex];
    }

    public static void reconstruct(String a, String b){

        int n = a.length();
        int m= b.length();

        String res1 = "";
        String res2 = "";
        while(!(n==0 && m==0)){
            switch(track[n][m]){
                case 3:
                    res1 = '-'+res1;
                    res2 = b.charAt(m-1) + res2;
                    m--;
                    break;
                case 2:
                    res1 = a.charAt(n-1)+res1;
                    res2 = '-' + res2;
                    n--;
                    break;
                case 1:
                    res1 = a.charAt(n-1)+res1;
                    res2 = b.charAt(m-1) + res2;
                    n--;
                    m--;
                    break;
            }

        }
        System.out.println(res1);
        System.out.println(res2);
        System.out.println();
    }

}
