public class MergeSort{ //slightly modified merge sort method for sortiong edges based on their weight
    public static void sort(Edge[] arr, int maxSize){
        mergeSort(arr,0,maxSize);
    }

    public static void mergeSort(Edge[] arr, int left, int right){
        if(left<right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, right, mid);
        }
    }

    public static void merge(Edge[] arr, int left, int right, int mid){
        Edge[] leftArray = new Edge[mid-left+1];
        Edge[] rightArray = new Edge[right-mid];
        for(int i=0;i<mid-left+1;++i){
            leftArray[i] = arr[left+i];
        }
        for(int j=0;j<right-mid;++j){
            rightArray[j] = arr[j+mid+1];
        }
        int lIndex = 0;
        int rIndex = 0;
        int arrIndex = left;
        while(lIndex<mid-left+1 && rIndex<right-mid){
            if(leftArray[lIndex].compareTo(rightArray[rIndex])<1){
                arr[arrIndex] = leftArray[lIndex];
                lIndex++;
            }
            else if(leftArray[lIndex].compareTo(rightArray[rIndex])==1){
                arr[arrIndex] = rightArray[rIndex];
                rIndex++;
            }
            arrIndex++;
        }
        while(lIndex<mid-left+1){
            arr[arrIndex] = leftArray[lIndex];
            lIndex++;
            arrIndex++;
        }
        while(rIndex<right-mid){
            arr[arrIndex] = rightArray[rIndex];
            rIndex++;
            arrIndex++;
        }
    }
}
