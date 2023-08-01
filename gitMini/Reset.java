package gitlet;

/** The reset method.
 * @author Frank Cuoco
 * */
public class Reset {

    /** Resets and takes in a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        String commitID = args[0];
        if (!stage.getCommitted().containsKey(commitID)) {
            System.out.println("No commit with that id exists.");
        }
    }
}
