package gitlet;

/** The find method.
 * @author Frank Cuoco
 * */
public class Find {

    /** Finds and takes in a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        String messageToFind = args[0];
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        int counter = 0;
        for (CommittedFile hash: stage.getCommitted().values()) {
            if (hash.getMessage().equals(messageToFind)) {
                counter++;
                System.out.println(hash.getSha1());
            }
        }
        if (counter == 0) {
            System.out.println("Found no commit with that message.");
        }
    }
}
