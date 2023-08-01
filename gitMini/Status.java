package gitlet;

/** The status method.
 * @author Frank Cuoco
 * */
public class Status {

    /** Returns the status that takes in a REPO and a string of ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 0) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        StagingArea stage = Utils.readObject(Utils.join(".gitlet",
                "stage", "staging.ser"), StagingArea.class);
        System.out.println("=== Branches ===");
        for (String branch: stage.getBranches().keySet()) {
            if (branch.equals(stage.getCurrentBranch())) {
                System.out.println("*" + branch);
                continue;
            }
            System.out.println(branch);
        }
        System.out.println("");
        System.out.println("=== Staged Files ===");
        for (String name: stage.getStaged().keySet()) {
            System.out.println(name);
        }
        System.out.println("");
        System.out.println("=== Removed Files ===");
        for (String name: stage.getRemoved().keySet()) {
            System.out.println(name);
        }
        System.out.println("");
        System.out.println("=== Modifications Not Staged For Commit ===");
        System.out.println("");
        System.out.println("=== Untracked Files ===");
        System.out.println("");
    }
}

