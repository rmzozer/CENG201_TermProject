public class WP5Demo {

    public static void main(String[] args){

        //PLAIN SUBMISSION BST TEST

        PlainSubmissionBST bst = new PlainSubmissionBST();

        Submission student1 = new Submission("S-0001","project1.pdf",1150,1000L,1
                ,false);
        Submission student2 = new Submission("S-0002","project2.pdf",1250,2000L,1
                ,false);
        Submission student3 = new Submission("S-0003","project3.pdf",1350,3000L,1
                ,false);
        Submission student4 = new Submission("S-0004","project4.pdf",1450,4000L,1
                ,false);
        Submission student5 = new Submission("S-0005","project5.pdf",1550,5000L,1
                ,false);

        bst.insert(student1);
        bst.insert(student2);
        bst.insert(student3);
        bst.insert(student4);
        bst.insert(student5);

        System.out.println("---WP - 5 PLAIN BST TEST WITH 5 STUDENTS");
        System.out.println("\nPlain BST HEIGHT: "+ bst.height());


        SubmissionTimeline avl = new SubmissionTimeline();

        avl.insert(student1);
        avl.insert(student2);
        avl.insert(student3);
        avl.insert(student4);
        avl.insert(student5);

        System.out.println("AVL HEIGHT: " + avl.height());

        System.out.println("\n---AVL ROTATION TESTS---");


        //LL CASE
        SubmissionTimeline llTree = new SubmissionTimeline();

        llTree.insert(new Submission(
                "S-0010", "a.pdf", 1000, 3000L, 1, false));

        llTree.insert(new Submission(
                "S-0011", "b.pdf", 1000, 2000L, 1, false));

        llTree.insert(new Submission(
                "S-0012", "c.pdf", 1000, 1000L, 1, false));

        System.out.println("LL Case - Root: " + llTree.rootTimestamp());


        //RR CASE
        SubmissionTimeline rrTree = new SubmissionTimeline();

        rrTree.insert(new Submission(
                "S-0020", "a.pdf", 1000, 1000L, 1, false));

        rrTree.insert(new Submission(
                "S-0021", "b.pdf", 1000, 2000L, 1, false));

        rrTree.insert(new Submission(
                "S-0022", "c.pdf", 1000, 3000L, 1, false));

        System.out.println("RR Case - Root: " + rrTree.rootTimestamp());


        //LR CASE
        SubmissionTimeline lrTree = new SubmissionTimeline();

        lrTree.insert(new Submission(
                "S-0030", "a.pdf", 1000, 3000L, 1, false));

        lrTree.insert(new Submission(
                "S-0031", "b.pdf", 1000, 1000L, 1, false));

        lrTree.insert(new Submission(
                "S-0032", "c.pdf", 1000, 2000L, 1, false));

        System.out.println("LR Case - Root: " + lrTree.rootTimestamp());


        //RL CASE
        SubmissionTimeline rlTree = new SubmissionTimeline();

        rlTree.insert(new Submission(
                "S-0040", "a.pdf", 1000, 1000L, 1, false));

        rlTree.insert(new Submission(
                "S-0041", "b.pdf", 1000, 3000L, 1, false));

        rlTree.insert(new Submission(
                "S-0042", "c.pdf", 1000, 2000L, 1, false));

        System.out.println("RL Case - Root: " + rlTree.rootTimestamp());


        System.out.println("\n---WP-5 RANGE QUERY TEST---");

        SubmissionTimeline rangeTree = new SubmissionTimeline();

        Submission[] testSubmissions = new Submission[10];

        ScenarioGenerator generator =
                new ScenarioGenerator(20260725L);

        for (int i = 0; i < 10; i++) {

            testSubmissions[i] = generator.nextUpload(i);

            rangeTree.insert(testSubmissions[i]);
        }

        System.out.println("\n---MIDDLE WINDOW---");

        long middleStart = testSubmissions[3].getTimestampMs();  //4.Submission

        long middleEnd = testSubmissions[6].getTimestampMs();    //7.Submission

        Submission[] middle = rangeTree.submittedBetween(middleStart, middleEnd);

        for (int i = 0; i < middle.length; i++) {
            System.out.println(middle[i].getStudentId() + " Timestamp: " + middle[i].getTimestampMs()
            );
        }

        System.out.println("Visited Nodes: " + rangeTree.getVisitedNodes());

        System.out.println("\n---EMPTY WINDOW---");

        Submission[] empty = rangeTree.submittedBetween(0L, 1000L);

        for (int i = 0; i < empty.length; i++) {
            System.out.println(empty[i].getStudentId());
        }

        System.out.println("Results: " + empty.length);

        System.out.println("Visited Nodes: " + rangeTree.getVisitedNodes());


        System.out.println("\n---FULL WINDOW---");

        long fullStart = testSubmissions[0].getTimestampMs();

        long fullEnd = testSubmissions[9].getTimestampMs();

        Submission[] full = rangeTree.submittedBetween(fullStart, fullEnd);

        for (int i = 0; i < full.length; i++) {

            System.out.println(full[i].getStudentId() + " Timestamp: " + full[i].getTimestampMs());
        }

        System.out.println("Visited Nodes: " + rangeTree.getVisitedNodes());


        System.out.println("\n---10,000 INSERT HEIGHT TEST---");

        int n = 10_000;

        Submission[] heightTest = new Submission[n];

        ScenarioGenerator heightGenerator = new ScenarioGenerator(20260725L);

        for (int i = 0; i < n; i++) {

            heightTest[i] = heightGenerator.nextUpload(i % ScenarioGenerator.STUDENT_COUNT);
        }


        PlainSubmissionBST plainLarge = new PlainSubmissionBST();

        SubmissionTimeline avlLarge = new SubmissionTimeline();


        for (int i = 0; i < n; i++) {

            plainLarge.insert(heightTest[i]);

            avlLarge.insert(heightTest[i]);
        }


        System.out.println("Plain BST Height: " + plainLarge.height());

        System.out.println("AVL Height: " + avlLarge.height());

    }
}
