public class ReportService {

    //Swapping
    private static void swap(Submission[] heap, int first, int second){
        Submission temp = heap[first];

        heap[first]=heap[second];   //swap
        heap[second] = temp;
    }

    //shift-up

    private static void siftUp(Submission[] heap, int index){


        while (index>0) {

            int parent = (index-1)/2;                //finding index of parent.

            if (heap[index].getSizeKb() < heap[parent].getSizeKb()) {     //min-heap min size must be the root. heap[0].

                swap(heap, index, parent);

                index = parent;
            } else {

                break;
            }
        }
    }

    private static void siftDown(Submission[] heap, int size, int index){
        while (true){
            int left = 2*index+1;
            int right = 2*index+2;
            int smallest = index;

            if (left < size && heap[left].getSizeKb() < heap[smallest].getSizeKb()) {

                smallest=left;
            }

            if (right < size && heap[right].getSizeKb() < heap[smallest].getSizeKb()) {

                smallest=right;
            }

            if (smallest == index) {
                break;
            }

            swap(heap, index, smallest);
            index=smallest;
        }
    }

    public static Submission[] topKLargest(Submission[] all, int k){

        if (k <= 0 || all.length==0) {
            return new Submission[0];
        }

        if(k > all.length){
            k= all.length;
        }

        Submission[] heap= new Submission[k];

        int heapSize=0;

        //First k Elements.

        for (int i = 0; i <k ; i++) {
            heap[heapSize]=all[i];

            siftUp(heap,heapSize);

            heapSize++;
        }

        //Remaining elements

        for (int i = k; i < all.length ; i++) {

            if (all[i].getSizeKb() > heap[0].getSizeKb()) {

                heap[0]=all[i];

                siftDown(heap,heapSize,0);
            }

        }

        return heap;

    }

    //Insertion Sort by using timestampMS

    public Submission[] sortByTimeInsertion(Submission[] all){

        Submission[] result = new Submission[all.length];

        for (int i = 0; i < all.length; i++) {
            result[i]=all[i];
        }

        for (int i = 1; i < result.length ; i++) {    //i=1 because index 0 already ordered before.

            Submission current= result[i];

            int j = i-1;

            while (j >=0 && result[j].getTimestampMs() >current.getTimestampMs()){

                result[j+1]=result[j];

                j--;
            }

            result[j+1] = current;
        }

        return result;

    }

    // Merge Sort - Divide And Conquer

    private  Submission[] merge(Submission[] array1, Submission[] array2){

        Submission[] combined = new Submission[array1.length+ array2.length];

        int index = 0;
        int i=0;
        int j=0;

        while (i < array1.length && j < array2.length){

            if (array1 [i].getTimestampMs() < array2[j].getTimestampMs()) {
                combined[index] = array1[i];

                index++;

                i++;
            }else {

                combined[index] = array2[j];

                index++;
                j++;

            }
        }

        while (i< array1.length){

            combined[index] = array1[i];

            index++;
            i++;

        }

        while (j< array2.length){

            combined[index] = array2[j];

            index++;
            j++;

        }

        return combined;
    }

    private Submission [] mergeSort(Submission[] array){

        if (array.length <=1) {                       //Base Case   //1 element  already ordred.
            return array;
        }

        int middle = array.length/2;

        Submission[] left = new Submission[middle];

        Submission[] right = new Submission[array.length-middle];

        for (int i = 0; i < middle; i++) {
            left[i]=array[i];
        }

        for (int i = middle; i < array.length; i++) {
            right[i-middle] = array[i];
        }

        left= mergeSort(left);
        right=mergeSort(right);

        return merge(left,right);
    }

    public Submission[] sortByTimeFast(Submission[] all){

        Submission[] copy = new Submission[all.length];

        for (int i = 0; i < all.length; i++) {
            copy[i] = all[i];
        }

        return mergeSort(copy);
    }


    public static int findFirstAfter(Submission[] ascending, long deadlineMs){

        int left = 0;
        int right = ascending.length - 1;

        int result= -1;

        while(left<=right){

            int middle = (right+left)/2;

            if (ascending[middle].getTimestampMs() > deadlineMs) {

                result =middle;

                right = middle-1;
            }else {

                left=middle+1;
            }
        }

        return result;
    }

    public void printSheet(Submission[] ascending) {

        System.out.println(
                String.format("\n%-1s %-20s %-8s %-14s %-10s", "Student ID", "  File Name", " Version", "     Time", "    Late"));

        System.out.println("---------------------------------------------------------------------");


        for (int i = 0; i < ascending.length; i++) {

            Submission sub = ascending[i];

            String late;

            if (sub.isLate()) {
                late = "LATE";
            }
            else {
                late = "ON TIME";
            }

            System.out.println(String.format("%-10s %-24s %-6d %-14s %-10s", sub.getStudentId(), sub.getFileName(), sub.getVersion(), sub.clock(), late));
        }
    }
}
