public class WP3Demo {
    public static void main(String[] args){

        Submission student1 = new Submission("S-0001","projectFirst.pdf",
                1150,80_000_800L,1,false);

        Submission student2 = new Submission("S-0002","projectSecond.pdf",
                1200,80_000_900L,1,true);

        Submission student3 = new Submission("S-0003","projectThird.pdf",
                1250,80_000_200L,1,false);

        Submission student4 = new Submission("S-0004","projectFourth.pdf",
                1300,80_000_700L,1,false);

        Submission student5 = new Submission("S-0005","projectFifth.pdf",
                1350,80_000_300L,1,true);

        Submission student6 = new Submission("S-0006","projectSixth.pdf",
                1400,80_000_400L,1,false);

        Submission student7 = new Submission("S-0007","projectSeventh.pdf",
                1450,80_000_100L,1,false);

        Submission student8 = new Submission("S-0008","projectEight.pdf",
                1500,80_000_600L,1,false);


        NaiveDispatcher naive= new NaiveDispatcher(8);

        naive.submit(student1);
        naive.submit(student2);
        naive.submit(student3);
        naive.submit(student4);
        naive.submit(student5);
        naive.submit(student6);
        naive.submit(student7);
        naive.submit(student8);


        System.out.println("---WP-3 Naıve Dıspatcher");

        while (naive.size()>0){

            Submission next = naive.next();       //return nextSub Prior one.

            System.out.println(next.getStudentId() + " |" + " Accomodation Flag: " + next.hasAccommodation() + " |" + " Timestamp MS: " + next.getTimestampMs());

            //Flagged first , within each group earlier timeStamp first.

        }

        System.out.println("\n---WP-3 Heap Dispatcher");

        HeapDispatcher heap = new HeapDispatcher(8);

        heap.submit(student1);
        heap.submit(student2);
        heap.submit(student3);
        heap.submit(student4);
        heap.submit(student5);
        heap.submit(student6);
        heap.submit(student7);
        heap.submit(student8);

        while (heap.size()>0){

            Submission next = heap.next();

            System.out.println(next.getStudentId() + " |" + " Accomodation Flag: " + next.hasAccommodation() + " |" + " Timestamp MS: " + next.getTimestampMs());

        }


        System.out.println("\n---WP-3 Load Burst");

        Submission[] burst = {student1,student2,student3,student4,student5,student6,student7,student8};

        HeapDispatcher heapBurst = new HeapDispatcher(8);

        heapBurst.loadBurst(burst);

        while (heapBurst.size()>0){

            Submission next = heapBurst.next();

            System.out.println(next.getStudentId() + " |" + " Accomodation Flag: " + next.hasAccommodation() + " |" + " Timestamp MS: " + next.getTimestampMs());

        }

        //WARM-UP

        int warmN = 1000;

        Submission[] warmUploads = new Submission[warmN];

        ScenarioGenerator warmGen =
                new ScenarioGenerator(20260725L);

        for (int i = 0; i < warmN; i++) {
            warmUploads[i] = warmGen.nextUpload(i % ScenarioGenerator.STUDENT_COUNT);
        }

        NaiveDispatcher warmNaive = new NaiveDispatcher(warmN);

        HeapDispatcher warmHeap = new HeapDispatcher(warmN);

        for (int i = 0; i < warmN; i++) {
            warmNaive.submit(warmUploads[i]);
            warmHeap.submit(warmUploads[i]);
        }

        while (warmNaive.size() > 0) {
            warmNaive.next();
        }

        while (warmHeap.size() > 0) {
            warmHeap.next();
        }

        System.out.println("\n---1,000 DISPATCHER BENCHMARK---");

        int n = 1_000;

        Submission[] uploads = new Submission[n];

        ScenarioGenerator benchmarkGen =
                new ScenarioGenerator(20260725L);

        for (int i = 0; i < n; i++) {

            uploads[i] =
                    benchmarkGen.nextUpload(
                            i % ScenarioGenerator.STUDENT_COUNT);
        }


        //NAIVE BENCHMARK

        NaiveDispatcher naiveBenchmark = new NaiveDispatcher(n);

        long naiveStart = System.nanoTime();

        for (int i = 0; i < n; i++) {
            naiveBenchmark.submit(uploads[i]);
        }

        while (naiveBenchmark.size() > 0) {
            naiveBenchmark.next();
        }

        long naiveEnd = System.nanoTime();

        long naiveTime = naiveEnd - naiveStart;


        //HEAP BENCHMARK

        HeapDispatcher heapBenchmark = new HeapDispatcher(n);

        long heapStart = System.nanoTime();

        for (int i = 0; i < n; i++) {
            heapBenchmark.submit(uploads[i]);
        }

        while (heapBenchmark.size() > 0) {
            heapBenchmark.next();
        }

        long heapEnd = System.nanoTime();

        long heapTime = heapEnd - heapStart;


        System.out.println("\nNaive Dispatcher: " + naiveTime / 1_000_000.0 + " ms");

        System.out.println("\nHeap Dispatcher: " + heapTime / 1_000_000.0 + " ms");


        System.out.println("\n---10,000 DISPATCHER BENCHMARK---");

        n = 10_000;

        uploads = new Submission[n];

        benchmarkGen = new ScenarioGenerator(20260725L);

        for (int i = 0; i < n; i++) {

            uploads[i] = benchmarkGen.nextUpload(i % ScenarioGenerator.STUDENT_COUNT);
        }


        //NAIVE BENCHMARK

        naiveBenchmark = new NaiveDispatcher(n);

        naiveStart = System.nanoTime();

        for (int i = 0; i < n; i++) {
            naiveBenchmark.submit(uploads[i]);
        }

        while (naiveBenchmark.size() > 0) {
            naiveBenchmark.next();
        }

        naiveEnd = System.nanoTime();

        naiveTime = naiveEnd - naiveStart;


        //HEAP BENCHMARK

        heapBenchmark = new HeapDispatcher(n);

        heapStart = System.nanoTime();

        for (int i = 0; i < n; i++) {
            heapBenchmark.submit(uploads[i]);
        }

        while (heapBenchmark.size() > 0) {
            heapBenchmark.next();
        }

        heapEnd = System.nanoTime();

        heapTime = heapEnd - heapStart;


        System.out.println("\nNaive Dispatcher: " + naiveTime / 1_000_000.0 + " ms");

        System.out.println("\nHeap Dispatcher: " + heapTime / 1_000_000.0 + " ms");
    }


}
