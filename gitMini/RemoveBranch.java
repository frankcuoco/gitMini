package gitlet;

/** The remove branch method.
 * @author Frank Cuoco
 * */
public class RemoveBranch {

    /** Removes a branch and takes in a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        String branchName = args[0];
        if (!stage.getBranches().containsKey(branchName)) {
            System.out.println("A branch with that name does not exist.");
        } else if (branchName.equals(stage.getCurrentBranch())) {
            System.out.println("Cannot remove the current branch.");
        } else {
            Utils.join(".gitlet", "branches", branchName).delete();
            stage.removeBranch(branchName);
        }
        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);
    }
}
