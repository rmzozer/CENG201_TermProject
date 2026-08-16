
//HASH

public class SubmissionRegistry {

    //Node Structure (Singly Linked List)
    private static class Node{
        Submission submission;   //submission mean head Exm (submission -> S-0042)
        Node next;              // next -> Node 2

        Node(Submission submission){
            this.submission=submission;
            this.next=null;
        }
    }

    private Node[] table; //Hash Table
    private int count;    //How many students have been submitted.

    private static final int INITIAL_CAPACITY = 8;
    private static final double LOAD_FACTOR = 0.75;

    public SubmissionRegistry(){              //Constructor
        table = new Node[INITIAL_CAPACITY];
        count=0;
    }

    private int hash(String studentId){
        int hashCode =studentId.hashCode() & 0x7fffffff; // to provide negative indexing.
        return hashCode % table.length;  // for [0-7] indexing
    }


    // put method

    public void put(Submission sub){

        if ((double) (count+1) / table.length > LOAD_FACTOR) {
            resize();

        }

        int indexing = hash(sub.getStudentId());

        Node newNode = new Node(sub);

        newNode.next=table[indexing];
        table[indexing]=newNode;

        count++;                                  //UPDATE

        //Collision chaning
        //We prepend because for the O(1).
    }

    private void resize(){
        Node[] oldTable = table;

        table = new Node[oldTable.length*2];

        for (int i = 0; i < oldTable.length; i++) {

            Node current = oldTable[i];

            while (current != null){
                Node nextNode = current.next;

                int newIndex = hash(current.submission.getStudentId());

                current.next = table[newIndex];
                table[newIndex]=current;

                current=nextNode;
            }

        }
        //Worst case O(n) time
    }

    public Submission lookUp(String studentId){
        int indexing = hash(studentId);

        Node current = table[indexing];

        while (current!=null){
            if (current.submission.getStudentId().equals(studentId)) {
                return current.submission;
            }

            current=current.next;
        }
        return null;                         //Unknowns ID returns null.

        //O(1) Expected - Worst Case O(n)
    }

    public int updateVersions(String studentId, String fileName, int sizeKb, long timeStampMs){

        Submission submission = lookUp(studentId);     //Finding Student (S-0001)

        if (submission == null) {
            return -1;
        }

        submission.replaceFile(fileName,sizeKb,timeStampMs);

        return submission.getVersion();

        //O(1) Expected - Worst Case O(n)

    }


    public int size(){            //When we apply put method, count increase.
        return count;
    }

    int bucketIndex(String studentId) {
        return hash(studentId);
    }

    int capacity() {
        return table.length;
    }







}
