public class ExamGateEngine {

    private SubmissionRegistry registry;        //WP1 Hash table
    private CircularUploadQueue intake;         //WP2
    private HeapDispatcher dispatcher;          //WP3 - MAX HEAP
    private RollbackService rollbackService;    //WP4 LL STACK
    private SubmissionTimeline timeline;        //WP5 AVL TREE
    private ReportService reportService;        //WP6 HEAP+SORT+BS

    //Checkpoints
    private int acceptedUploads;
    private int policyActivations;
    private int reuploads;
    private int rollbacks;
    private int lateCount;

    //Constructor

    public ExamGateEngine(int queueCapacity) {

        intake = new CircularUploadQueue(queueCapacity);

        dispatcher = new HeapDispatcher(3000);

        registry = new SubmissionRegistry();

        rollbackService = new RollbackService(registry);

        timeline = new SubmissionTimeline();

        reportService = new ReportService();

        acceptedUploads = 0;
        policyActivations = 0;
        reuploads = 0;
        rollbacks = 0;
        lateCount = 0;
    }

    public boolean acceptUpload(Submission upload) {

        boolean accepted = intake.enqueue(upload);

        if (accepted) {

            acceptedUploads++;       //on-time

            if (upload.isLate()) {
                lateCount++;        //late
            }
        }

        else {
            policyActivations++;     //if queue is full
        }

        return accepted;
    }

    public void moveIntakeToDispatcher() {

        while (intake.size() > 0) {

            Submission upload = intake.dequeue();

            dispatcher.submit(upload);
        }
    }

    public void processDispatcher() {

        while (dispatcher.size() > 0) {

            Submission upload = dispatcher.next();

            Submission current = registry.lookup(upload.getStudentId());


            //FIRST UPLOAD
            if (current == null) {

                registry.put(upload);
            }


            //RE-UPLOAD
            else {

                rollbackService.saveVersion(upload.getStudentId());

                registry.updateVersion(upload.getStudentId(), upload.getFileName(), upload.getSizeKb(), upload.getTimestampMs());

                reuploads++;
            }


            Submission active = registry.lookup(upload.getStudentId());


            Submission timelineRecord = new Submission(active.getStudentId(), active.getFileName(), active.getSizeKb(),
                            active.getTimestampMs(),
                            active.getVersion(),
                            active.hasAccommodation());


            timeline.insert(timelineRecord);
        }
    }

    public int getAcceptedUploads() {
        return acceptedUploads;
    }

    public int getReuploads() {
        return reuploads;
    }

    public int getRegistrySize() {
        return registry.size();
    }

    public int getStudentVersion(String studentId) {

        Submission sub = registry.lookup(studentId);

        if (sub == null) {
            return -1;
        }

        return sub.getVersion();
    }

    public int getTimelineCount() {

        Submission[] records = timeline.submittedBetween(0L, Long.MAX_VALUE);

        return records.length;
    }

    public void rollbackStudent(String studentId) {

        if (rollbackService.canRollback(studentId)) {

            rollbackService.rollback(studentId);

            rollbacks++;
        }
    }

    public void printCheckpoint(String title) {

        System.out.println("\n---" + title + "---");

        System.out.println("Queue Occupancy: " + intake.size());

        System.out.println("Accepted Uploads: " + acceptedUploads);

        System.out.println("Policy Activations: " + policyActivations);

        System.out.println("Re-uploads: " + reuploads);

        System.out.println("Rollbacks: " + rollbacks);

        System.out.println("Late Count: " + lateCount);
    }
}
