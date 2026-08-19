public class NaiveDispatcher {
    private Submission[] submissions;
    private int size;

    //Constructor
    public NaiveDispatcher(int capacity){
        submissions = new Submission[capacity];
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

    public void submit(Submission sub){
        if( size == submissions.length){    //Array is full!
            return;
        }

        int i = size-1;                //Start with last element.

        while (i >= 0 && highPriority(submissions[i], sub )){          //Prior student will be the rightest side.
            submissions[i+1] = submissions[i];
            i--;

        }

        submissions [i+1] =sub;
        size++;

    }

    public Submission next(){
        if (size == 0) {
            return null;
        }

        Submission nextSub = submissions[size-1];            //size-1 means last element index and prior element.

        submissions[size-1]=null;
        size--;

        return nextSub;
    }

    public int size(){
        return size;
    }


}
