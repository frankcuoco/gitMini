package gitlet;

import java.util.Arrays;

/** Driver class for Gitlet, the tiny stupid version-control system.
 *  @author Frank Cuoco
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }
        String[] restOfArgs = Arrays.copyOfRange(args, 1, args.length);
        String directory = System.getProperty("user.dir");
        if (args[0].equals("init")) {
            new Init().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("add")) {
            new Add().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("commit")) {
            new Commit().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("checkout")) {
            new Checkout().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("rm")) {
            new Remove().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("log")) {
            new Log().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("global-log")) {
            new GlobalLog().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("find")) {
            new Find().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("status")) {
            new Status().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("branch")) {
            new Branch().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("rm-branch")) {
            new RemoveBranch().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("reset")) {
            new Reset().doCommand(new Repo(directory), restOfArgs);
        } else if (args[0].equals("merge")) {
            new Merge().doCommand(new Repo(directory), restOfArgs);
        } else {
            System.out.println("No command with that name exists.");
            System.exit(0);
        }
    }
}
