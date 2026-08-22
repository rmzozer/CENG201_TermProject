public class ScalingStudy {

    public static void main(String[] args) {

        int[] sizes = {1_000, 10_000, 100_000};


        // JIT WARM-UP
        Submission[] warmData = createData(1_000, 20260725L);

        testWP2(warmData, false);
        testWP3(warmData, false);
        testEndToEnd(warmData, false);

        System.out.println("======= SCALING STUDY =======");


        for (int i = 0; i < sizes.length; i++) {
            int n = sizes[i];

            Submission[] data = createData(n, 20260725L);

            System.out.println("\n--------------------------");
            System.out.println("N = " + n);
            System.out.println("--------------------------");

            testWP2(data, true);
            testWP3(data, true);
            testEndToEnd(data, true);
        }
    }


    // DATA GENERATOR
    private static Submission[] createData(int n, long seed) {

        Submission[] data = new Submission[n];

        ScenarioGenerator generator = new ScenarioGenerator(seed);


        for (int i = 0; i < n; i++) {

            data[i] = generator.nextUpload(i % ScenarioGenerator.STUDENT_COUNT);
        }

        return data;
    }



    // WP2
    private static void testWP2(Submission[] data, boolean print) {

        int n = data.length;

        // NAIVE QUEUE
        NaiveUploadQueue naive = new NaiveUploadQueue(n);
        long startNaive = System.nanoTime();

        for (int i = 0; i < n; i++) {
            naive.enqueue(data[i]);
        }

        while (naive.size() > 0) {
            naive.dequeue();
        }

        long endNaive = System.nanoTime();

        // CIRCULAR QUEUE

        CircularUploadQueue circular = new CircularUploadQueue(n);

        long startCircular = System.nanoTime();

        for (int i = 0; i < n; i++) {
            circular.enqueue(data[i]);
        }

        while (circular.size() > 0) {
            circular.dequeue();
        }

        long endCircular = System.nanoTime();

        if (print) {
            double naiveMs = (endNaive - startNaive) / 1_000_000.0;
            double circularMs = (endCircular - startCircular) / 1_000_000.0;


            System.out.println("WP2 Naive Queue: " + naiveMs + " ms");

            System.out.println("WP2 Circular Queue: " + circularMs + " ms");
        }
    }



    // WP3
    private static void testWP3(Submission[] data, boolean print) {

        int n = data.length;

        // NAIVE DISPATCHER
        NaiveDispatcher naive = new NaiveDispatcher(n);
        long startNaive = System.nanoTime();

        for (int i = 0; i < n; i++) {
            naive.submit(data[i]);
        }

        while (naive.size() > 0) {

            naive.next();
        }

        long endNaive = System.nanoTime();

        // HEAP DISPATCHER
        HeapDispatcher heap = new HeapDispatcher(n);

        long startHeap = System.nanoTime();

        for (int i = 0; i < n; i++) {
            heap.submit(data[i]);
        }

        while (heap.size() > 0) {
            heap.next();
        }
        long endHeap = System.nanoTime();

        if (print) {
            double naiveMs = (endNaive - startNaive) / 1_000_000.0;
            double heapMs = (endHeap - startHeap) / 1_000_000.0;

            System.out.println("WP3 Naive Dispatcher: " + naiveMs + " ms");

            System.out.println("WP3 Heap Dispatcher: " + heapMs + " ms");
        }
    }



    // END-TO-END
    private static void testEndToEnd(Submission[] data, boolean print) {
        ExamGateEngine engine = new ExamGateEngine(data.length);
        long start = System.nanoTime();

        // Intake Queue
        for (int i = 0; i < data.length; i++) {
            engine.acceptUpload(data[i]);
        }
        // Queue -> Heap
        engine.moveIntakeToDispatcher();
        // Heap -> Registry -> Version Stack -> AVL
        engine.processDispatcher();
        long end = System.nanoTime();

        if (print) {
            double timeMs = (end - start) / 1_000_000.0;

            System.out.println("End-to-End: " + timeMs + " ms");
        }
    }
}