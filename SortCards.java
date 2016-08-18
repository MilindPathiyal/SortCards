//Milind Pathiyal

//SortCards sorts cards in order numerically

import java.util.*;
public class SortCards
{
    // count and comment steps for:
    //      assignment statements
    //      comparisons
    //      method calls
    private long steps;

    public static void SortCards(String[] args)
    {
        SortCards a = new SortCards();
        a.go();
    }

    public void go()
    {   
        Scanner keys = new Scanner(System.in);
        System.out.println("enter number of items");
        int size = keys.nextInt();
        int[] list = generateList(size);
        print(list);
        sort(list);
        System.out.println("sorted list");
        print(list);
    }

    public SortCards()
    {
        steps = 0;
    }

    public int[] generateList(int size)
    {
        int[] list = new int[size];     
        for (int i = 0; i < size; i++) 
            list[i] = (int)(Math.random() * size * 5 + 1);
        return list;    
    }

    public void print(int[] list)
    {
        if (steps > 0)
            System.out.println("This sort took " + steps + " steps to sort " + list.length + " numbers");
        if (list.length <= 100)
        {
            for (int i = 0; i < list.length; i++)
                System.out.print(list[i] + " ");
        }       
        System.out.println();
    }
    //print code
    //comment method to explcombineFirstHalfn algorithm
    //count steps
    //comment each step counted
    //run of 20 items
    //#of steps for 1000 items
    //#of steps for 2000 items
    //pseudocode  

    public void sort(int[] list) 
    {
        int[] firstHalf = new int[list.length / 2];   //firstHalf' length is given list's length divided by 2
        int[] secondHalf = new int[list.length - firstHalf.length]; // secondHalf's length is the remcombineFirstHalfnng length of the cut

        if(list.length <= 1) 
        { 
            return; //If # of items is less than or equal to one-->returns the input
        }
        //Stores values from list into firstHalf and secondHalf
        for(int i = 0; i < list.length; i++) //i increases by 1 every loop and must be less than list's length 
        {
            if(i < firstHalf.length) //if i is less than fistHalf's length--> stores list at i into firstHalf at i
            {   
                firstHalf[i] = list[i];
            }
            else  //if i is greater than fistHalf's length--> stores list at i into secondHalf at i          
            {
                secondHalf[i - firstHalf.length] = list[i];//i - firstHalf.length because secondHalf needs to start from i > firstHalf.length
            }
            steps++;
        }

        sort(firstHalf);           // Recursively sort firstHalf
        sort(secondHalf);           // and second half

        int combineFirstHalf = 0;  // combine halves: combineFirstHalf, combineSecondHalf
        int combineSecondHalf = 0;   // track position in

        while(combineFirstHalf + combineSecondHalf < list.length) 
        {             //   in each half.
            if(combineSecondHalf >= secondHalf.length || (combineFirstHalf < firstHalf.length && firstHalf[combineFirstHalf] < secondHalf[combineSecondHalf])) 
            {
                list[combineFirstHalf + combineSecondHalf] = firstHalf[combineFirstHalf]; //copy element of first array over
                combineFirstHalf++;
            } 
            else 
            {
                list[combineFirstHalf + combineSecondHalf] = secondHalf[combineSecondHalf]; //copy element of second array over
                combineSecondHalf++;
            }
            steps++;
        }
    }

    
    public void sort(int[] list, int start, int end) 
    {
        steps++;
        for(int i = start; i <= end; i++)    //scans through list end times
        {
            int j = i;
            int smallestIndex = i;//mark a placement for i
            steps+=3;//counts steps
            steps++;//count steps
            while(j <= end)
            {
                steps+=3;//account for steps
                if(list[smallestIndex] > list[j]) //smallest index is greater than j
                {   
                    steps++;
                    smallestIndex = j;
                }
                j++;
            }
            int temp = list[smallestIndex];
            list[smallestIndex] = list[i];//substitute smallestIndex with i do set default smallestIndex
            list[i] = temp;
            steps +=3;//account for the three steps made
        }
    }
    public void merge(int[] list, int start, int mid, int end) 
    {                                                         
        int []temp = new int[end+1];
        int countX = start;
        int countY = mid + 1;
        for(int listCount = start;listCount <= end; listCount++)
        {
            if(countX <= mid && countY >= end)//if countX is less than or equal to mid and countY is greater than or equal to end
            {
                temp[listCount] = list[countX];
                countX++;
            }
            else if(countX >= mid && countY <= end)//if countX is greater than or equal to mid and countY is less than or equal to end
            {
                temp[listCount] = list[countY];
                countY++;
            }

            else 
            {
                if(list[countX] > list[countY])//if list at Countx is greater than list at countY
                {
                    temp[listCount] = list[countY];
                    countY++;
                }
                else
                {
                    temp[listCount] = list[countX];
                    countX++;
                }
            }
        }
        for(int i = start; i <= end; i++)
        {
            list[i] = temp[i];
        }
    }
    public void mergeSort(int[] list, int start, int end) 
    { 
        if (end != start) //if end is not equal to start
        {
            int mid = (start+end)/2;
            mergeSort(list, start, mid);//recursion calls to merge the ints
            mergeSort(list, mid+1, end);//recursion calls to merge the ints
            merge(list, start, mid, end);
        }
    }
}
/*
enter number of items
20
65 84 57 68 13 3 61 97 37 28 6 78 13 75 42 94 52 96 83 40 
sorted list
This sort took 176 steps to sort 20 numbers
3 6 13 13 28 37 40 42 52 57 61 65 68 75 78 83 84 94 96 97 

1000 items = This sort took 150669 steps to sort 1000 numbers
2000 items = This sort took 327357 steps to sort 2000 numbers

 */