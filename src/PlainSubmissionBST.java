public class PlainSubmissionBST {

    //Node Structure as a Tree (right and left)
    private static class Node {
        Submission data;
        Node left;
        Node right;

        Node(Submission data){
            this.data=data;
            this.right=null;
            this.left=null;
        }
    }

    private Node root;

    public PlainSubmissionBST(){
        root=null;
    }

    //Recursive insertion process

    public void insert(Submission sub){
        root=insertRecursive(root,sub);
    }

    private Node insertRecursive(Node current, Submission sub){
        if (current == null) {
            return new Node(sub);
        }

        if (sub.getTimestampMs() < current.data.getTimestampMs()) {
            current.left=insertRecursive(current.left,sub);

        }else {
            current.right=insertRecursive(current.right,sub);
        }

        return current;

    }


    //Recursive finding height process

    public int height(){
        return heightRecursive(root);
    }

    private int heightRecursive(Node current){

        if (current == null) {
            return 0;
        }

        int leftHeight= heightRecursive(current.left);
        int rightHeight= heightRecursive(current.right);

        return 1 + Math.max(leftHeight,rightHeight);
    }




}
