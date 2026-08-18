public class NaiveUploadQueue {

    private Submission[] queue;
    private int size;

    public NaiveUploadQueue(int capacity) {
        queue = new Submission[capacity];
        size = 0;
    }

    public boolean enqueue(Submission s) {

        if (size == queue.length) {
            return false;
        }

        queue[size] = s;
        size++;

        return true;
    }

    public Submission dequeue() {

        if (size == 0) {
            return null;
        }

        Submission first = queue[0];

        for (int i = 0; i < size - 1; i++) {  //Takes O(N) time
            queue[i] = queue[i + 1];
        }

        queue[size - 1] = null;
        size--;

        return first;
    }

    public int size() {
        return size;
    }
}