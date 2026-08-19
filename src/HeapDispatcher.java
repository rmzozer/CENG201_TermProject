public class HeapDispatcher {

    private Submission[] heap;
    private int size;


    //Constructor
    public HeapDispatcher(int capacity){

        heap = new Submission[capacity];
        this.size=0;

    }

    public boolean highPriority(Submission first, Submission second){

        if (first.hasAccommodation() && !second.hasAccommodation()) {
            return true;
        }

        if (!first.hasAccommodation() && second.hasAccommodation()) {
            return false;
        }

        return first.getTimestampMs() < second.getTimestampMs();
    }

    public void swap(int first, int second){
        Submission temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }

    public void submit (Submission sub){

        if (size == heap.length) {    //If it is full.
            return;
        }

        heap[size]=sub;            //adding new sub last side

        int index = size;         // new sub index.

        size++;

        while (index>0){

            int parentIndex = (index-1) / 2;

            if (highPriority(heap[index], heap[parentIndex])){

                swap(index,parentIndex);
                index=parentIndex;
            }else {
                break;
            }
        }

        //Shift - up Approach

    }

    public Submission next(){

        if (size == 0) {
            return null;
        }

        Submission result = heap[0];

        heap[0]=heap[size-1];
        heap[size-1]=null;

        size--;

        shiftDown(0);

        return result;
    }

    private void shiftDown(int index){
        while (true){

            int left = 2 * index+1;
            int right = 2 * index+2;

            int highestOne=index;

            if (left < size && highPriority(heap[left], heap[highestOne])) {

                highestOne=left;

            }

            if (right < size && highPriority(heap[right], heap[highestOne])) {

                highestOne = right;
            }

            if (highestOne == index) {
                break;
            }

            swap(index,highestOne);

            index = highestOne;

            //Shift - Down Approach
        }
    }

    public int size() {
        return size;
    }

    //Bottom up build heap

    public void loadBurst(Submission [] burst ){
        heap = new Submission[burst.length];

        for (int i = 0; i < burst.length; i++) {

            heap[i] = burst[i];
        }

        size = burst.length;

        for (int i = size / 2 - 1; i >=0 ; i--) {
            shiftDown(i);
        }
    }





}
