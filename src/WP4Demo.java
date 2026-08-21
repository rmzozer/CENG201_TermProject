public class WP4Demo {

    public static void main(String[] args) {

        SubmissionRegistry registry = new SubmissionRegistry();

        RollbackService rollbackService = new RollbackService(registry);

        //Version - 1

        Submission student = new Submission("S-0001" , "projectV1.pdf",1200,80_000_000L,1
                ,false);

        registry.put(student);

        System.out.println("---WP - 4 VERION ROLLBACK ---");

        System.out.println("\nActive Version: " + registry.lookup("S-0001"));
        rollbackService.printStack("S-0001");

        //Upload Version - 2

        rollbackService.saveVersion("S-0001");

        registry.updateVersion("S-0001","projectV2.pdf",1400,81_000_000L);

        System.out.println("\nAfter UPLOAD Version 2: " );
        System.out.println("\nActive Version: " + registry.lookup("S-0001"));
        rollbackService.printStack("S-0001");


        //Upload Version - 3

        rollbackService.saveVersion("S-0001");

        registry.updateVersion("S-0001","projectV3.pdf",1500,82_000_000L);

        System.out.println("\nAfter UPLOAD Version 3: " );
        System.out.println("\nActive Version: " + registry.lookup("S-0001"));
        rollbackService.printStack("S-0001");

        //First Rollback
        System.out.println("\n--- First ROLLBACK" );

        rollbackService.rollback("S-0001");

        System.out.println("\nActive Version: " + registry.lookup("S-0001"));
        rollbackService.printStack("S-0001");

        //Second Rollback
        System.out.println("\n--- Second ROLLBACK" );

        rollbackService.rollback("S-0001");

        System.out.println("\nActive Version: " + registry.lookup("S-0001"));
        rollbackService.printStack("S-0001");

        //Third Rollback
        System.out.println("\n--- Third ROLLBACK" );

        rollbackService.rollback("S-0001");

        System.out.println("\nActive Version: " + registry.lookup("S-0001"));
        rollbackService.printStack("S-0001");

    }
}