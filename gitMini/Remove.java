package gitlet;

/** The remove method.
 * @author Frank Cuoco
 * */
public class Remove {

    /** Remove a file that takes in a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        int counter = 0;
        String filename = args[0];
        if (stage.getTracked().containsKey(filename)) {
            stage.removeFile(filename, stage.getTracked().get(filename));
            Utils.restrictedDelete(filename);
            counter++;
        }
        if (stage.getStaged().containsKey(filename)) {
            stage.unstageFile(filename);
            counter++;
        }
        if (!stage.getTracked().containsKey(filename)
                && !stage.getStaged().containsKey(filename) && counter == 0) {
            System.out.println("No reason to remove the file.");
        }
        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);
    }
}
