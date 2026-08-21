import java.util.NoSuchElementException;

public class VersionStack {

    //Node Structure
    private static class Node{
        VersionRecord data;
        Node next;

        Node(VersionRecord data){
            this.data=data;
            this.next=null;
        }
    }

    private Node top;  //head=top

    //Constructor of VersionStack.
    public VersionStack(){
        top=null;
    }

    //Push Method

    public void push(VersionRecord version){

        Node newNode = new Node(version);

        newNode.next=top;
        top=newNode;

    }

    //Pop Method - it returns value and delete it.

    public VersionRecord pop(){

        if (top == null) {

            throw new NoSuchElementException();
        }

        VersionRecord removedElement=top.data;

        top=top.next;   //LI - FO Last in First out.

        return removedElement;
    }

    //isEmpty Method

    public boolean isEmpty(){
        return top==null;
    }



}
