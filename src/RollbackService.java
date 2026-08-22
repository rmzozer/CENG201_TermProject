public class RollbackService {

    private SubmissionRegistry registry;
    private VersionStack[] stacks;


    public RollbackService(SubmissionRegistry registry){
        this.registry=registry;

        stacks = new VersionStack[ScenarioGenerator.STUDENT_COUNT]; //800

        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new VersionStack();              //stacks[0] - > S-0001 Stack (per student linked list stack)
        }

    }

    private VersionStack getStack(String studentId){

        int number = Integer.parseInt(studentId.substring(2));

        return stacks[number - 1];

        //S-0001 -> 0001 -> 1 -> stacks[0]
    }

    //Save Last Version

    public void saveVersion(String studentId){

        Submission current = registry.lookup(studentId);

        if (current == null) {
            return;
        }

        VersionRecord oldVersion = new VersionRecord(current.getFileName(),current.getSizeKb(),current.getTimestampMs(),current.getVersion());

        getStack(studentId).push(oldVersion);

    }

    //Rollback Method

    public void rollback(String studentId){
        Submission current = registry.lookup(studentId);

        if (current == null) {
            System.out.println("Student not found!");
            return;
        }

        VersionStack stack = getStack(studentId);

        if (stack.isEmpty()) {
            System.out.println("There is no earlier version!");
            return;
        }

        VersionRecord oldVersion = stack.pop();

        current.restoreFile(oldVersion.getFileName(), oldVersion.getSizeKb(), oldVersion.getTimestampMs(), oldVersion.getVersion());
    }
    public void printStack(String studentId) {

        getStack(studentId).printStack();
    }

    public boolean canRollback(String studentId) {

        Submission current = registry.lookup(studentId);

        if (current == null) {
            return false;
        }

        return !getStack(studentId).isEmpty();
    }

}
