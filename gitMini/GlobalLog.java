package gitlet;

/** The global-log method.
 * @author Frank Cuoco
 * */
public class GlobalLog {

    /** Returns the global log and takes in a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 0) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        for (CommittedFile commit: stage.getCommitted().values()) {
            commit.print();
        }
        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);
    }
}

