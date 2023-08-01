package gitlet;

/** The init method.
 * @author Frank Cuoco
 * */
public class Init {

    /** The job of this method is to initialize all of the pathways
     *  needed inside the .gitlet folder (including .gitlet itself),
     *  create and serialize the staging area, and call an initial
     *  commit. Takes in a REPO and ARGS. */
    public void doCommand(Repo repo, String[] args) {
        if (args.length != 0) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
        repo.init();
    }
}
