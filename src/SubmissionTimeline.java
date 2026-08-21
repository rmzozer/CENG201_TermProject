public class SubmissionTimeline {

    //Node Structure as a AVL Tree (right and left)
    private static class Node {
        Submission data;
        Node left;
        Node right;
        int height;

        Node(Submission data){
            this.data=data;
            this.right=null;
            this.left=null;
            this.height=1;
        }
    }

    private Node root;
    private int visitedNodes;

    public SubmissionTimeline(){
        root=null;
    }

    //Finding Node Height

    public int getHeight(Node node){
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    //Update Node Height

    public void updateHeight(Node node){

        int leftHeight= getHeight(node.left);
        int rightHeight= getHeight(node.right);

        node.height= 1 + Math.max(leftHeight,rightHeight);  //1 is the root.

    }

    //Balance Factor

    private int getBalance(Node node){

        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right); //if +-2 then we need to do rotation.
    }

    //Right Rotation - LL Problem

    private Node rotateRight(Node y){

        Node x= y.left;
        Node temp=x.right;

        x.right = y;

        y.left=temp;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    //Left Rotation

    private Node rotateLeft(Node x){

        Node y= x.right;
        Node temp=y.left;

        y.left = x;

        x.right=temp;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    //Process will start firstly top of the tree means Root.

    public void insert(Submission sub) {
        root = insertRecursive(root, sub);
    }

    private Node insertRecursive(Node current, Submission sub){

        //It is same PlainSubmissionBst insert part. Normal BST Insertion operation.

        if (current == null) {
            return new Node(sub);
        }
        if (sub.getTimestampMs() < current.data.getTimestampMs()) {
            current.left = insertRecursive(current.left, sub);
        }
        else {
            current.right = insertRecursive(current.right, sub);
        }

        //Update Height

        updateHeight(current);

        //Check BALANCE
        int balanceScore=getBalance(current);

        //LL Case - Right rotation applying

        if (balanceScore > 1 && sub.getTimestampMs() < current.left.data.getTimestampMs()) {
            return rotateRight(current);
        }

        //RR Case - Left Rotation Applying

        if (balanceScore < -1 && sub.getTimestampMs() > current.right.data.getTimestampMs()) {
            return rotateLeft(current);
        }

        //LR Case

        if (balanceScore > 1 && sub.getTimestampMs() > current.left.data.getTimestampMs()) {

            current.left = rotateLeft(current.left);

            return rotateRight(current);
        }

        //RL Case
        if (balanceScore < -1 &&
                sub.getTimestampMs() < current.right.data.getTimestampMs()) {

            current.right = rotateRight(current.right);

            return rotateLeft(current);
        }

        return current;

    }

    public int height() {
        return getHeight(root);
    }

    long rootTimestamp() {

        if (root == null) {
            return -1;
        }

        return root.data.getTimestampMs();
    }


    private int countBetween(Node current, long t1, long t2) {

        if (current == null) {
            return 0;
        }

        long time = current.data.getTimestampMs();

        int count = 0;

        if (time > t1) {  //go left side
            count += countBetween(current.left, t1, t2);
        }

        if (time >= t1 && time <= t2) {
            count++;
        }

        if (time < t2) {  //go right side
            count += countBetween(current.right, t1, t2);
        }

        return count;
    }

    private int fillBetween(Node current, long t1, long t2, Submission[] result, int index) {

        if (current == null) {
            return index;
        }

        visitedNodes++;

        long time = current.data.getTimestampMs();

        if (time > t1) {index = fillBetween(current.left, t1, t2, result, index);
        }

        if (time >= t1 && time <= t2) {

            result[index] = current.data;
            index++;
        }

        if (time < t2) {
            index = fillBetween(current.right, t1, t2, result, index);
        }

        return index;
    }

    public Submission[] submittedBetween(long t1, long t2) {

        int count = countBetween(root, t1, t2);

        Submission[] result = new Submission[count];

        visitedNodes = 0;

        fillBetween(root, t1, t2, result, 0);

        return result;
    }

    public int getVisitedNodes() {
        return visitedNodes;
    }





}

