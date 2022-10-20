import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;
class Main {
    static ArrayList<Integer> arr = new ArrayList<Integer>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());
        // ArrayList<Integer> arr = new ArrayList<Integer>();
        // System.out.println(br.readLine());
        for(int i=0;i<input;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()){
                case "PUSH":
                    arr.add(Integer.parseInt(st.nextToken()));
                    sort();
                    break;
                case "POP":
                    get();
                    break;
                case "SIZE":
                    System.out.println(arr.size());
            }

        }
        // System.out.println("Hello Goorm! Your input is " + input);
    }
    private static void sort(){
        Collections.sort(arr);
        int a=0;
        if(arr.size()==1 || arr.size()==2){
            System.out.println(arr.get(0));
            return;
        }
        if(arr.size()==0){
            System.out.println("NO ITEM");
            return;
        }
        if(arr.size()%2==1){ //배열 길이 홀수일 경우
            a= arr.get((arr.size()+1)/2-1);

        }
        else{
            a=arr.get(arr.size()/2-1); //짝수 
        }
        System.out.println(a);
    }
    private static void get(){
        int a=0;
        if(arr.size()==0){
            System.out.println("NO ITEM");
            return;
        }
        if(arr.size()==1){
            arr.remove(0);
        }
        else{
            if(arr.size()%2==1){
                arr.remove((arr.size()+1)/2-1);
            }
            else{
                arr.remove(arr.size()/2-1);
            }
        }
        sort();
    }
}