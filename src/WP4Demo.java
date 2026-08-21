public class WP4Demo {

    public static void main(String[] args) {

        VersionStack stack = new VersionStack();

        VersionRecord v1 = new VersionRecord("project1.pdf", 1200, 80_000_000L, 1);

        VersionRecord v2 = new VersionRecord("project2.pdf", 1400, 81_000_000L, 2);


        stack.push(v1);
        stack.push(v2);


        VersionRecord first = stack.pop();

        System.out.println("Popped version: " + first.getVersion() + " File: " + first.getFileName()
        );


        VersionRecord second = stack.pop();

        System.out.println("Popped version: " + second.getVersion() + " File: " + second.getFileName()
        );


        System.out.println("Stack empty: " + stack.isEmpty()
        );
    }
}