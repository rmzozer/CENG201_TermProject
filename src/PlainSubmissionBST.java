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
    private int treeHeight;

    public PlainSubmissionBST(){
        root=null;
        treeHeight=0;
    }

    //Insertion process

    public void insert(Submission sub) {

        Node newNode = new Node(sub);

        if (root == null) {
            root = newNode;
            treeHeight = 1;
            return;
        }

        Node current = root;
        int depth = 1;

        while (true) {

            depth++;

            if (sub.getTimestampMs() < current.data.getTimestampMs()) {

                if (current.left == null) {
                    current.left = newNode;
                    break;
                }

                current = current.left;
            }

            else {

                if (current.right == null) {
                    current.right = newNode;
                    break;
                }

                current = current.right;
            }
        }

        if (depth > treeHeight) {
            treeHeight = depth;
        }
    }

    //Return tree height

    public int height(){
        return treeHeight;
    }





}
