import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Sample {
    
    static void permute(char[] input,int n,int k,ArrayList<String> list)
    {
        if(k==0)
        {
            list.add(new String(input));   // i found the permutation
        }
        else
        {
            permute(input,n-1,k-1,list);    //ABCD 
            
            for(int i=0;i<n-1;i++)
            {
                input[i]=input[n-1];
                permute(input,n-1,k-1,list);   
                input[n-1]=input[i];
            }
        }
    }

/*
    ABC => A  {BC, CB}
           A (BC) =>  ABC, BAC, BCA         
           A (CB) =>  ACD, CAB, CBA
*/



    static String[] permu(String[] input) {
        
        ArrayList<String> list =new ArrayList<>();
        permute(input[0].toCharArray(),input[0].length(),input[0].length(),list);
        
        String[] returns = new String[list.size()];
        int index=0;
        for(String s: list)
        {
            returns[index]=s;
        }

        return returns;

    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String[] res;
        
        int _input_size = 0;
        _input_size = Integer.parseInt(in.nextLine().trim());
        String[] _input = new String[_input_size];
        String _input_item;
        for(int _input_i = 0; _input_i < _input_size; _input_i++) {
            try {
                _input_item = in.nextLine();
            } catch (Exception e) {
                _input_item = null;
            }
            _input[_input_i] = _input_item;
        }
        
        res = permu(_input);
        for(int res_i=0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }
        
        bw.close();
    }
}