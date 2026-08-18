public class WP2Demo {
    public static void main(String[] args){

        ScenarioGenerator generator = new ScenarioGenerator(20260725L);

        Submission student1 = generator.nextUpload(0);
        Submission student2 = generator.nextUpload(1);
        Submission student3 = generator.nextUpload(2);
        Submission student4 = generator.nextUpload(3);
        Submission student5 = generator.nextUpload(4);
        Submission student6 = generator.nextUpload(5);
        Submission student7 = generator.nextUpload(6);
        Submission student8 = generator.nextUpload(7);


        CircularUploadQueue queue = new CircularUploadQueue(5);

        System.out.println("---WP2 QUEUE DEMO---");

        //ENQUEUE
        queue.enqueue(student1);
        queue.enqueue(student2);
        queue.enqueue(student3);
        queue.enqueue(student4);
        queue.enqueue(student5);

        queue.printState();

        //DEQUEUE
        System.out.println("\nRemoved: " + queue.dequeue().getStudentId());
        System.out.println("Removed: " + queue.dequeue().getStudentId());
        queue.printState();

        //WRAP -AROUND TESTING

        System.out.println("\n---Wrap - Around TEST---");

        System.out.println("S-0006 accepted enqueue: " + queue.enqueue(student6));
        System.out.println("S-0007 accepted enqueue: " + queue.enqueue(student7));

        queue.printState();

        //BUFFER CONTROL
        System.out.println("\nBuffer Check: ");

        boolean accept= queue.enqueue(student8);

        System.out.println("S-0008 accepted: " + accept);

        queue.printState();


        //DEQUEUE ORDER

        System.out.println("\n---DEQUEUE ORDER---");

        while (queue.size() > 0){
            Submission removedId = queue.dequeue();

            System.out.println(removedId.getStudentId());
        }


        System.out.println("\n---NAIVE VS CIRCULAR QUEUE---");


        NaiveUploadQueue naive = new NaiveUploadQueue(5);
        CircularUploadQueue circularQueue = new CircularUploadQueue(5);

        Submission[] test = {student1, student2, student3, student4, student5};

        for (int i = 0; i < test.length; i++) {
            naive.enqueue(test[i]);
            circularQueue.enqueue(test[i]);
        }

        System.out.print("\nNaive Order: ");

        while (naive.size() > 0) {
            System.out.print(naive.dequeue().getStudentId() + " ");
        }

        System.out.println();

        System.out.print("\nCircular Order: ");

        while (circularQueue.size() > 0) {
            System.out.print(circularQueue.dequeue().getStudentId() + " ");
        }

        System.out.println();


        System.out.println("\n---10,000 UPLOAD BENCHMARK---");

        int n = 10_000;

        Submission[] uploads = new Submission[n];

        ScenarioGenerator benchmarkGen = new ScenarioGenerator(20260725L);

        for (int i = 0; i < n; i++) {

            uploads[i] = benchmarkGen.nextUpload(i % ScenarioGenerator.STUDENT_COUNT);

        }

        //NAIVE BENCHMARK

        NaiveUploadQueue naiveBench = new NaiveUploadQueue(n);

        for (int i = 0; i < n; i++) {
            naiveBench.enqueue(uploads[i]);  //Takes O(N)
        }

        long naiveStart = System.nanoTime();

        while (naiveBench.size() > 0) {  //Takes O(N^2)
            naiveBench.dequeue();
        }

        long naiveEnd = System.nanoTime();

        long naiveTime = naiveEnd - naiveStart;


        //CIRCULAR BENCHMARK

        CircularUploadQueue circularBench = new CircularUploadQueue(n);

        for (int i = 0; i < n; i++) {
            circularBench.enqueue(uploads[i]);   //O(N)
        }

        long circularStart = System.nanoTime();

        while (circularBench.size() > 0) {
            circularBench.dequeue();
        }

        long circularEnd = System.nanoTime();

        long circularTime = circularEnd - circularStart;

        System.out.println("\nNaive Queue: " + naiveTime / 1_000_000.0 + " ms");

        System.out.println("\nCircular Queue: " + circularTime / 1_000_000.0 + " ms");
    }


}
