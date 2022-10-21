public class programmers_solution1 {
    public int[] solution(int[][] v){
        int[] answer ={};
        
        
        int[] x = new int[4];
        int[] y=new int[4];
        for(int i =0;i<3;i++){
            x[i]=v[i][0];
            y[i]=v[i][1];
        }
        if(x[0]!=x[1]){
            if(x[1]==x[2]) x[3]=x[0];
            else x[3]=x[1];
        }
        else x[3]=x[2];
        if(y[0]!=y[1]){
            if(y[1]==y[2]) y[3]=y[0];
            else y[3]=y[1];
        }
        else y[3]=y[2];
        System.out.println("["+x[3]+", "+y[3]+"]");

        return  answer;
    }
}
