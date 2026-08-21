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



}
