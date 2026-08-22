public class WP6Demo {
    public static void main(String[] args){

        Submission[] all = new Submission[9];

        all[0] = new Submission("S-0001","project1.pdf", 1200, 80_000_000L,1,false);
        all[1] = new Submission("S-0002","project2.pdf", 4500, 81_000_000L,1,false);
        all[2] = new Submission("S-0003","project3.pdf", 2200, 82_000_000L,1,false);
        all[3] = new Submission("S-0004","project4.pdf", 7000, 83_000_000L,1,false);
        all[4] = new Submission("S-0005","project5.pdf", 1800, 84_000_000L,1,false);
        all[5] = new Submission("S-0006","project6.pdf", 9000, 85_000_000L,1,false);
        all[6] = new Submission("S-0007","project7.pdf", 3300,Submission.DEADLINE_MS,1,false);
        all[7] = new Submission("S-0008","project8.pdf", 6100, Submission.DEADLINE_MS+1000,1,false);
        all[8] = new Submission("S-0009","project9.pdf", 2700,Submission.DEADLINE_MS+2000,1,false);

        System.out.println("---WP-6  DEMO TOP-3 LARGEST FILES TEST---");

        Submission[] top3 = ReportService.topKLargest(all, 3);

        for (int i = 0; i < top3.length; i++) {

            System.out.println(top3[i].getStudentId() + " Size: " + top3[i].getSizeKb() + " KB");
        }


        System.out.println("\n---INSERTION SORT BY TIME---");

        ReportService reportService = new ReportService();

        Submission[] insertionSorted = reportService.sortByTimeInsertion(all);

        for (int i = 0; i < insertionSorted.length; i++) {

            System.out.println(insertionSorted[i].getStudentId() + " Timestamp: " + insertionSorted[i].getTimestampMs());
        }

        System.out.println("\n---MERGE SORT BY TIME---");

        Submission[] mergeSorted = reportService.sortByTimeFast(all);

        for (int i = 0; i < mergeSorted.length; i++) {

            System.out.println(mergeSorted[i].getStudentId() + " Timestamp: " + mergeSorted[i].getTimestampMs());
        }


        boolean sameOrder = true;

        for (int i = 0; i < all.length; i++) {

            if (!insertionSorted[i].getStudentId().equals(mergeSorted[i].getStudentId())) {
                sameOrder = false;
            }
        }

        System.out.println("\nInsertion and Merge same order: " + sameOrder);

        System.out.println("\n---BINARY SEARCH LATE TEST---");

        int firstLate = ReportService.findFirstAfter(mergeSorted, Submission.DEADLINE_MS);

        System.out.println("First Late Index: " + firstLate);

        if (firstLate != -1) {
            System.out.println("First Late Student: " + mergeSorted[firstLate].getStudentId());
        }


        System.out.println("\n---LATE LIST---");

        if (firstLate == -1) {
            System.out.println("No late submissions.");
        }

        else {

            for (int i = firstLate; i < mergeSorted.length; i++) {

                System.out.println(mergeSorted[i].getStudentId() + " Time: " + mergeSorted[i].getTimestampMs());
            }
        }

        System.out.println("\n---BINARY SEARCH EXTRA TESTS---");

        //Deadline before all
        System.out.println("Before all: " + ReportService.findFirstAfter(mergeSorted, 0L));


        //Deadline between two submissions
        System.out.println("Between two: " + ReportService.findFirstAfter(mergeSorted, 82_500_000L));


        //Deadline after all
        System.out.println("After all: " + ReportService.findFirstAfter(mergeSorted, 90_000_000L));




    }
}
