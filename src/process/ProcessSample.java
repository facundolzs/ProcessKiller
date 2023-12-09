package process;

import java.io.IOException;

public class ProcessSample {

    private static final Long PID = 1900L; // cmd -> tasklist -> the process' pid
    private static final String PATH = "C:\\Program Files\\Microsoft Games\\Chess\\Chess.exe"; // example path

    public static void main(String[] args) throws InterruptedException, IOException {

        killByPID(PID);

        // killByProcessPath(PATH);
    }

    /**
     * Ends the execution of a process by its ID
     *
     * @param pid {process' ID}
     * @throws InterruptedException
     */
    public static void killByPID(Long pid) throws InterruptedException {

        var process = ProcessHandle.of(pid).get();

        System.out.println("Information about the chosen process:\n" + process.info() + "\n");

        Thread.sleep(6000);
        process.destroyForcibly();

        process.onExit().defaultExecutor().execute(() -> System.out.println("The process has been successfully killed."));
    }

    /**
     * Ends the execution of a process by its path
     *
     * @param path {process' path}
     * @throws IOException
     * @throws InterruptedException
     */
    public static void killByProcessPath(String path) throws IOException, InterruptedException {

        var process = new ProcessBuilder(path).start();

        System.out.println("Information about the chosen process:\n" + process.info() + "\n");

        Thread.sleep(6000);
        process.destroyForcibly();

        process.onExit().defaultExecutor().execute(() -> System.out.println("The process has been successfully killed."));
    }

}
