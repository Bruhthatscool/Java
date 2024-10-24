import java.util.Scanner;
public class Main
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        //input number
        int x = sc.nextInt();

        //call function to check if number is palindrome
        boolean ret = new Solution().isPalindrome(x);

        //display result
        System.out.print(ret);
    }
}

class Solution
{
    //method to check if number is palindrome
    boolean isPalindrome(int x)
    {
        if(x < 0)
        {
            return false;
        }
        int temp = x, pal = 0;
        while(temp != 0)
        {
            pal = pal*10 + temp%10;
            temp /= 10;
        }
        //returns true if input number is palindrome
        return x == pal;
    }
}
