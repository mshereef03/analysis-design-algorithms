public class Main {

    public static void main(String [] args){
        System.out.println(powerIterative(2,0));
        System.out.println(powerRecursive(2,0));
    }

    public static int powerIterative(int a,int n){
        int ans = 1;
        for(int i=1;i<=n;i++){
            ans*=a;
        }
        return ans;
    }

    public static int powerRecursive(int a,int n){
        if (n==0) return 1;
        return a * powerRecursive(a,n-1);
    }
}
