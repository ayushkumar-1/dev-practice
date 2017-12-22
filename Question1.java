package com.test.swiggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Question1 {
	
	public static void PrintMaxPrimeDigitsNum() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter wr = new PrintWriter(System.out);
        long inputData = Long.parseLong(br.readLine());
        long digit = 0;
        long temp;
        boolean chkDigit = true;
        long max = 0;
        /**LargetNumber */
        for(long i = 2; i <= inputData; i++){
        	//Dividing the number in its digits and checking if all digits are prime
        	temp = i;
        	while(temp != 0){
        		digit = temp%10;
        		
        		//System.out.println("digit: "+digit);
        		//System.out.println("temp: "+temp);
        		//check if prime
        		if(!isPrime(digit)){
        			//System.out.println("broke");
        			break;
        		};
        		temp = temp/10;
        	}
        	if(i >= 10 && temp == 0 && i > max){
        			max = i;
        			//System.out.println("max:"+max);
        	}
        	else if(temp == 0 && i > max && isPrime(digit)){
        			max = i;
        	}
        }
         System.out.println(max);

         br.close();
	}

	private static boolean isPrime(long digit) {
		
		Map<Long, Boolean> mapData = new HashMap<>();
		mapData.put((long) 1, false);
		mapData.put((long) 2, true);
		mapData.put((long) 3, true);
		mapData.put((long) 4, false);
		mapData.put((long) 5, true);
		mapData.put((long) 6, false);
		mapData.put((long) 7, true);
		mapData.put((long) 8, false);
		mapData.put((long) 9, false);
		mapData.put((long) 0, false);
				
		return mapData.get(digit);
	}
	
	public static void PrintMaxPrimeDigitsNumThreads() throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter wr = new PrintWriter(System.out);
        Long inputData = Long.parseLong(br.readLine());
        
        //threads to divide the numbers in 1000s of 5 threads
        ThreadPoolExecutor executorOne = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Long>> resultList = new ArrayList<>();
        
        
        //Divide data in 5 threads
        long data = inputData/5;
        
        long maxNum = 0;
        System.out.println("here");
        for(long i =2; i <= inputData; i = i+ data){
        	System.out.println("startsttt");
        	System.out.println("data" +
        			":"+ (i-2+data));
        	CheckForPrimes chkPrimes = new CheckForPrimes(i-2+data);
        	
        	Future<Long> chkPrime = executorOne.submit(chkPrimes);
        	//If all digit is prime, we return the data in future
        	//Now, add in list if maximum to the existing
				resultList.add(chkPrime);
				
			       	
        }
        
        	if(!resultList.isEmpty()){
        		for(Future<Long> future : resultList)
                {
                      try
                      {
                  		System.out.println(future.get());
              			maxNum = future.get();
                      }
                      catch (InterruptedException | ExecutionException e)
                      {
                          e.printStackTrace();
                      }
                  }
        	}
        	executorOne.shutdown();
        
        System.out.println(maxNum);
        
        
        
	}

}


class CheckForPrimes implements Callable<Long>
{
 
    private Long inputData;
 
    public CheckForPrimes(Long number) {
        this.inputData = number;
    }
 
    @Override
    public Long call() throws Exception {
        long result = 0;
        
        ThreadPoolExecutor executorTwo = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Boolean>> resultList = new ArrayList<>();
        //For bigger numbers, divide digits for prime checks
        int[] digits;
        
        
        long digit = 0;
        long temp;
        boolean chkDigit = true;
        long max = 0;
        /**LargetNumber */
        for(long i = 2; i <= inputData; i++){
        	//Dividing the number in its digits and checking if all digits are prime
        	temp = i;
        	List<Long> digitsList = new ArrayList<>();
        	long start = System.currentTimeMillis();
        	PrintWriter wr = new PrintWriter(System.out);
        	wr.println(start);
        	while(temp != 0){
        		digit = temp%10;
        		
        		//System.out.pridigitntln("digit: "+digit);
        		//System.out.println("temp: "+temp);
        		//check if prime
        		if(!digitsList.contains(digit)&&!isPrime(digit)){
        			//System.out.println("broke");
        			//digitsList.add(digit);
        			break;
        		}else{
        			digitsList.add(digit);
        		};
        		temp = temp/10;
        	}
        	if(i >= 10 && temp == 0 && i > max){
        			max = i;
        			//System.out.println("max:"+max);
        	}
        	else if(temp == 0 && i > max && isPrime(digit)){
        			max = i;
        	}
        }
        System.out.println("max: "+max);
        return max;
    }
    
private static boolean isPrime(long digit) {
		
		Map<Long, Boolean> mapData = new HashMap<>();
		mapData.put((long) 1, false);
		mapData.put((long) 2, true);
		mapData.put((long) 3, true);
		mapData.put((long) 4, false);
		mapData.put((long) 5, true);
		mapData.put((long) 6, false);
		mapData.put((long) 7, true);
		mapData.put((long) 8, false);
		mapData.put((long) 9, false);
		mapData.put((long) 0, false);
				
		return mapData.get(digit);
	}
}
