import java.util.Random;

public class ScenarioGenerator {
    public static final int STUDENT_COUNT = 800;
    public static final long WINDOW_OPENS_MS = 79_200_000L;
    public static final int BURST1_SIZE = 800;
    public static final int BURST2_SIZE = 800;
    public static final int BURST3_SIZE = 900;

    public static final int TOTAL_UPLOADS = BURST1_SIZE + BURST2_SIZE + BURST3_SIZE;

    private final Random rng;
    private final boolean[] accommodation;
    private long clockMs = WINDOW_OPENS_MS;

    public ScenarioGenerator(long seed) {

        this.rng = new Random(seed);

        this.accommodation = new boolean[STUDENT_COUNT];

        for (int i = 0; i < STUDENT_COUNT; i++) {
            accommodation[i] = rng.nextInt(100) < 3;
        }
    }

    public String studentId(int i) {
        return String.format("S-%04d", i + 1);
    }
    public boolean hasAccommodation(int i) {
        return accommodation[i];
    }

    public Submission nextUpload(int i) {

        clockMs += 1 + rng.nextInt(2_000);

        int sizeKb = 200 + rng.nextInt(4_800);

        String fileName = studentId(i) + "_project.pdf";

        return new Submission(studentId(i), fileName, sizeKb, clockMs, 1, accommodation[i]);
    }

    private Submission createUpload(int studentIndex, long timestamp) {

        int sizeKb = 200 + rng.nextInt(4_800);

        String fileName =
                studentId(studentIndex) + "_project.pdf";

        return new Submission(studentId(studentIndex), fileName, sizeKb, timestamp, 1, accommodation[studentIndex]);
    }

    public Submission[] generateBurst1() {

        Submission[] burst = new Submission[BURST1_SIZE];

        for (int i = 0; i < BURST1_SIZE; i++) {

            clockMs += 1 + rng.nextInt(2_000);

            burst[i] = createUpload(i, clockMs);
        }
        return burst;
    }





    public static void main(String[] args) {

        ScenarioGenerator gen = new ScenarioGenerator(20260725L);

        for (int i = 0; i < 5; i++) {
            System.out.println(gen.nextUpload(i));
        }
    }

}
