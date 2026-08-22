public class WP7Demo {

    public static void main(String[] args) {

        ScenarioGenerator generator = new ScenarioGenerator(20260725L);

        ExamGateEngine engine = new ExamGateEngine(850);


        // BURST 1
        Submission[] burst1 = generator.generateBurst1();

        for (int i = 0; i < burst1.length; i++) {
            engine.acceptUpload(burst1[i]);
        }


        // FIRST CHECKPOINT
        engine.printCheckpoint("AFTER BURST 1");


        // Queue -> Dispatcher -> Registry -> Timeline
        engine.moveIntakeToDispatcher();

        engine.processDispatcher();


        System.out.println("\nAfter processing Burst 1:");

        System.out.println("Registry Size: " + engine.getRegistrySize());

        System.out.println("Timeline Records: " + engine.getTimelineCount());
    }
}