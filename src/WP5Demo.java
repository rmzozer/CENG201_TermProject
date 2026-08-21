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

    }
}
