public class WP7Demo {

    public static void main(String[] args) {

        ExamGateEngine engine = new ExamGateEngine(10);


        Submission upload1 = new Submission("S-0001", "project_v1.pdf", 1200,
                80_000_000L, 1, false);


        Submission upload2 = new Submission("S-0002", "homework.pdf", 1500,
                81_000_000L, 1, false);


        Submission upload3 = new Submission("S-0001", "project_v2.pdf", 1700,
                82_000_000L, 1, false);

        engine.acceptUpload(upload1);       //Send to queue
        engine.acceptUpload(upload2);
        engine.acceptUpload(upload3);

        engine.moveIntakeToDispatcher();    //queue->dispatcher

        engine.processDispatcher();

        System.out.println("\n---ROLLBACK TEST---");

        System.out.println("Before Rollback - S-0001 Version: " + engine.getStudentVersion("S-0001"));

        engine.rollbackStudent("S-0001");

        System.out.println("After Rollback - S-0001 Version: " + engine.getStudentVersion("S-0001"));

        engine.printCheckpoint("SMALL INTEGRATION CHECKPOINT");



        System.out.println("\n---WP-7 SMALL INTEGRATION TEST---");

        System.out.println("\nAccepted Uploads: " + engine.getAcceptedUploads());

        System.out.println("Registry Size: " + engine.getRegistrySize());

        System.out.println("Re-uploads: " + engine.getReuploads());

        System.out.println("S-0001 Active Version: " + engine.getStudentVersion("S-0001"));

        System.out.println("Timeline Records: " + engine.getTimelineCount());


    }
}
