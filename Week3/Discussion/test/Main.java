package test;
public class Main 
{
	public static int failure(int enteredNum)
	{
		//final int startNum = 53;
		
		int startNum = 12;//This variable is causing an error because the variable was considered final in main
		int total = startNum + enteredNum;
		return total;
	}
	
	public static void passing(int place)
	{
		if(place == 1) 
		{
            String placing = "1st"; 
            System.out.println(placing + "Place"); 
		}
		else if(place == 2) 
		{
            String placing = "2nd"; 
            System.out.println(placing + "Place"); 
		}
		else if(place == 3) 
		{
            String placing = "3rd"; 
            System.out.println(placing + "Place"); 
		}
		else
		{
            System.out.println("Did not place");
		}
	}
	
    public static void main(String[] args) 
	{
    	//test that show String variable "place" being used in more than once
    	System.out.println("Test that shows variable being used more than once.");
    	passing(2);

    	//test that show variable "startNum" being used in more than more place
    	System.out.println("Test that shows variable causing error being used more than once.");	
    	System.out.println(failure(89));
	}		
}//end public class Main 
