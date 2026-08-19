public class WP3Demo {
    public static void main(String[] args){

        Submission student1 = new Submission("S-0001","projectFirst.pdf",
                1150,80_000_800L,1,false);

        Submission student2 = new Submission("S-0002","projectSecond.pdf",
                1200,80_000_900L,1,true);

        Submission student3 = new Submission("S-0003","projectThird.pdf",
                1250,80_000_200L,1,false);

        Submission student4 = new Submission("S-0004","projectFourth.pdf",
                13000,80_000_700L,1,false);

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



    }
}
