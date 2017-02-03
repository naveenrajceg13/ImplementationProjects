import java.util.Scanner;

/**
 * Created by Sid19 on 12/1/2016.
 */
public class MergeSort
{
    public void mergesort(int[] array)
    {
        int leftArrayLength, median, rightArrayLength;
        int[] resultArray = new int[array.length];
        int i = 0;
        if (array.length < 2)
        {
            return;
        }
        median = (array.length) / 2;
        leftArrayLength = median;
        rightArrayLength = (array.length) - median;
        int[] left = new int[leftArrayLength];
        int[] right = new int[rightArrayLength];
        for (i = 0; i < median; i++)
        {
            left[i] = array[i];
        }
        for (i = median; i < array.length; i++)
        {
            right[i - median] = array[i];
        }
        mergesort(left);
        mergesort(right);
        resultArray = merge(left, right, array);
        printArray(resultArray);
    }

    public int[] merge(int[] lArray, int[] rArray, int[] array)
    {
        int leftArrayLength = lArray.length;
        int rightArrayLength = rArray.length;
        int i = 0, j = 0, k = 0;
        while(i < leftArrayLength && j < rightArrayLength)
        {
            if (lArray[i] <= rArray[j])
            {
                array[k++] = lArray[i++];
            }
            else
            {
                array[k++] = rArray[j++];
            }
        }
        while(i < leftArrayLength)
        {
            array[k++] = lArray[i++];
        }
        while(j < rightArrayLength)
        {
            array[k++] = rArray[j++];
        }
        return(array);
    }

    public void printArray(int[] array)
    {
        int arrayLength = array.length;
        for (int i = 0; i < arrayLength; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        MergeSort mSort = new MergeSort();
        Scanner userInput = new Scanner(System.in);
        System.out.println("\n Enter the number of elements: ");
        int range = userInput.nextInt();

        int[] numberList = new int[range];
        System.out.println("\n Enter the elements of the Array:");
        for (int i = 0; i < numberList.length; i++)
        {
            numberList[i] = userInput.nextInt();
        }
        System.out.println("\n Now using Merge Sort on the Array");
        System.out.println("\n Sorted Array: \n");
        mSort.mergesort(numberList);
    }
}
