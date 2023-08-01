package gitlet;

/** The merge method.
 * @author Frank Cuoco
 * */
public class Merge {

    /** Merges two branches and takes in a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        String toMerge = args[0];
        if (stage.getRemoved().isEmpty() || stage.getStaged().isEmpty()) {
            System.out.println("You have uncommitted changes.");
        }
        if (!stage.getBranches().containsKey(toMerge)) {
            System.out.println("A branch with that name already exists.");
        }
        if (stage.getCurrentBranch().equals(toMerge)) {
            System.out.println("Cannot merge a branch with itself.");
        }
    }
}
