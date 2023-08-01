package gitlet;

/** The branch method.
 * @author Frank Cuoco
 * */
public class Branch {

    /** Returns the branch and takes in a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        String newBranchName = args[0];
        if (stage.getBranches().containsKey(newBranchName)) {
            System.out.println("A branch with that name already exists.");
            return;
        }
        stage.addBranch(newBranchName, stage.getHead());
        Utils.writeObject(Utils.join(".gitlet", "branches",
                newBranchName), newBranchName);
        Utils.writeObject(Utils.join(".gitlet", "stage", "staging.ser"), stage);
    }
}

