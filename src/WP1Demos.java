import java.util.Random;

public class WP1Demos {

    public static void main(String[] args) {

        SubmissionRegistry registry = new SubmissionRegistry();

        System.out.println("=== WP1 SUBMISSION REGISTRY DEMO ===");

        // Forced collision before resize
        System.out.println("\n--- Forced Collision ---");
        System.out.println("S-0001 bucket: " + registry.bucketIndex("S-0001"));
        System.out.println("S-0009 bucket: " + registry.bucketIndex("S-0009"));

        Submission s1 = new Submission(
                "S-0001", "project1.pdf", 1200,
                80_000_001L, 1, false);

        Submission s2 = new Submission(
                "S-0009", "project9.pdf", 1400,
                80_000_002L, 1, false);

        Submission s3 = new Submission(
                "S-0002", "project2.pdf", 1000,
                80_000_003L, 1, false);

        Submission s4 = new Submission(
                "S-0003", "project3.pdf", 1800,
                80_000_004L, 1, true);

        Submission s5 = new Submission(
                "S-0004", "project4.pdf", 2100,
                80_000_005L, 1, false);

        Submission s6 = new Submission(
                "S-0005", "project5.pdf", 1600,
                80_000_006L, 1, false);

        Submission s7 = new Submission(
                "S-0006", "project6.pdf", 1900,
                80_000_007L, 1, false);

        Submission s8 = new Submission(
                "S-0007", "project7.pdf", 2300,
                80_000_008L, 1, false);

        registry.put(s1);
        registry.put(s2);
        registry.put(s3);
        registry.put(s4);
        registry.put(s5);
        registry.put(s6);
        registry.put(s7);
        registry.put(s8);

        System.out.println("\n--- After 8 Inserts ---");
        System.out.println("Registry size: " + registry.size());
        System.out.println("Capacity: " + registry.capacity());

        System.out.println("\n--- Lookup All Students ---");
        System.out.println(registry.lookup("S-0001"));
        System.out.println(registry.lookup("S-0009"));
        System.out.println(registry.lookup("S-0002"));
        System.out.println(registry.lookup("S-0003"));
        System.out.println(registry.lookup("S-0004"));
        System.out.println(registry.lookup("S-0005"));
        System.out.println(registry.lookup("S-0006"));
        System.out.println(registry.lookup("S-0007"));

        System.out.println("\n--- Version Update ---");

        registry.updateVersion(
                "S-0001",
                "project1_final.pdf",
                1300,
                81_000_000L
        );

        registry.updateVersion(
                "S-0001",
                "project1_really_final.pdf",
                1500,
                82_000_000L
        );

        System.out.println(registry.lookup("S-0001"));

        System.out.println("\n--- Unknown Student ---");
        System.out.println(registry.lookup("S-9999"));

        System.out.println("\n--- 100,000 Seeded Lookups ---");

        String[] ids = {
                "S-0001", "S-0009", "S-0002", "S-0003",
                "S-0004", "S-0005", "S-0006", "S-0007"
        };

        Random rng = new Random(20260725L);

        long start = System.nanoTime();

        for (int i = 0; i < 100_000; i++) {
            String id = ids[rng.nextInt(ids.length)];
            registry.lookup(id);
        }

        long end = System.nanoTime();

        System.out.println("100,000 lookups completed.");
        System.out.println("Elapsed time: " + (end - start) + " ns");
    }
}