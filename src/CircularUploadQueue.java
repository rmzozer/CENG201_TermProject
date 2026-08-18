import javax.sound.midi.SysexMessage;

public class CircularUploadQueue {
    private Submission[] queue;
    private int front;
    private int rear;
    private int size;


    //Constructor

    public CircularUploadQueue(int capacity){
        queue = new Submission[capacity];
        this.front=0;
        this.rear=-1;
        this.size=0;
    }

    public boolean enqueue(Submission sub){

        if (size == queue.length) {   //Queue is Full!
            return false;
        }


        rear=(rear+1) % queue.length; //To circle structure. (Wrap - Around)
        queue[rear]=sub;

        size++;

        return true;

    }

    public Submission dequeue(){

        if (size == 0) {         //Queue is Empty!
            return null;
        }

        Submission first= queue[front];

        queue[front] = null;

        front=(front+1)% queue.length;

        size--;

        return first;
    }

    //Naive queue takes O(N) to solving problem with shifting mechanism but circle uses wrap-around it takes O(1) time.

    public int size(){
        return  size;
    }

    void printState(){
        System.out.print("\nQueue: [");

        for (int i = 0; i < queue.length; i++) {

            if (queue[i] == null) {
                System.out.print("EMPTY!");
            }
            else{
                System.out.print(queue[i].getStudentId());      //Write Student ID after put comma(",")
            }

            if (i < queue.length-1) {
                System.out.print(", ");
            }

        }

        System.out.println("]");

        System.out.println("\nfront =" + front + " rear =" + rear + " size =" + size);



    }


}
