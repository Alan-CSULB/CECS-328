import java.util.Random;
import java.util.Scanner;

public class MaxHeap {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int iteration = 100;
		
		System.out.print("Enter n: ");
		int n = in.nextInt();
		
		int a[] = generateRandom(n);
		
		System.out.println("Unsorted array: ");
		displayArray(a);
		heap_sort(a);
		System.out.println("Sorted array: ");
		displayArray(a);
		System.out.println("");
		
		//Heapsort average running time
		long heapSortTotalTime = 0;
		for(int i = 0; i < iteration; i++)
		{
			a = generateRandom(n);
			long heapSortStart = System.nanoTime();
			heap_sort(a);
			long heapSortEnd = System.nanoTime();
			heapSortTotalTime += heapSortEnd - heapSortStart;
		}
		long heapSortAverageTime = heapSortTotalTime / iteration;
		System.out.println("Heapsort average running time: " + heapSortAverageTime + " nanoseconds");

		//Quicksort average running time
		long quickSortTotalTime = 0;
		for(int i =0; i<iteration; i++) 
		{  
				a = generateRandom(n);
				long quickSortStart = System.nanoTime();
				quick_sort(a, 0, a.length-1);
				long quickSortEnd = System.nanoTime();
				quickSortTotalTime += quickSortEnd - quickSortStart;
		}
		long  quickSortAverageTIme = quickSortTotalTime / iteration;
		System.out.println("Quicksort average running time: " + quickSortAverageTIme + " nanoseconds");
		
		//Selectionsort  average running time
		long selectionSortTotalTime = 0;
		for(int i =0; i<iteration; i++) 
		{  
				a = generateRandom(n);
				long selectionSortStart = System.nanoTime();
				selection_sort(a);
				long selectionSortEnd = System.nanoTime();
				selectionSortTotalTime += selectionSortEnd - selectionSortStart;
		}
		long  selectionSortAverageTIme = selectionSortTotalTime / iteration;
		System.out.println("Selectionsort average running time: " + selectionSortAverageTIme + " nanoseconds");
	}
	
	public static void build_maxheap(int a[]) 
	{
		//a.length/2 is the last node that can have children --> all nodes n/2 + 1 are leaves
		//leaves don't have children --> no need to max_heapify since the children are max heaps themselves
		for (int i = a.length/2; i > 0; i--)
		{  
			max_heapify(a,i, a.length);
		}
	}
	
	public static void max_heapify(int a[], int index, int heapSize)
	{
		//index of left child of index
		int left = 2 * index; 
		//index of right child of index
		int right = (2 * index) + 1;
		//max index = default set at index
		int max = index;
		
		//Compare left child with index
		if (left <= heapSize && a[left - 1] > a[index -1])
			max = left;
		
		//Compare right child with index
		if (right <= heapSize && a[right-1] > a[max-1])
			max = right;
		
		//if index isn't the max index, swap with the current max index
		//recursively heapify the affected sub-tree until it is a max heap
		if (max != index) 
		{
			swap(a, index-1, max-1); 
			max_heapify(a,max,heapSize);
		}	
	}

	public static void heap_sort(int a[]) 
	{
		//build the max heap
		build_maxheap(a);
		
		for (int i = a.length - 1; i > 0; i--)
		{
			//largest item is stored at the root (0) of the heap, replace it with the last element of the array
			swap(a, 0 , i);
			//max_heapify the next index until heapsize is less than 1
			max_heapify(a,1,i); 
		}
	}
	
	public static void quick_sort(int[] a, int low, int high)
	{
		int pivot = a[low+(high-low)/2];
		int i = low;
		int j = high;
		while(i <= j)
		{
			while(a[i] < pivot)
				i++;
			while(a[j] > pivot)
				j--;
			if(i <= j)
			{
				swap(a,i,j);
				i++;
				j--;
			}
		}
		if(low < j)
			quick_sort(a, low, j);
		if(high > i)
			quick_sort(a, i, high);
	}
	
	public static void selection_sort(int a[])
	{
		int index;
		for (int i = 0; i < a.length - 1; i++) 
		{
			index = i;
			for (int j = i + 1; j < a.length; j++) 
			{ 
				if (a[j] < a[index]) 
					index = j;
			}
			swap(a,index,i);
		}
	}

	
	public static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static int[] generateRandom(int n)
	{
		Random rand = new Random();
		int a[] = new int[n];
		for (int i = 0; i < a.length; i++)
		{
			a[i] = rand.nextInt((10000 - (-10000)) + 1) + (-10000);
		}
		return a;
	}
	
	public static void displayArray(int[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + " ");
		}
		System.out.println("");
	}
}


