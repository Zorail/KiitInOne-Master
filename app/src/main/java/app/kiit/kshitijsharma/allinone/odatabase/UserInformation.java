package app.kiit.kshitijsharma.allinone.odatabase;

/**
 * Created by kshitij sharma on 1/4/2017.
 */

public class UserInformation {
    public String name;
    public String  roll;
    public String branch;
    public String sem;

    public UserInformation(){

    }

    public UserInformation(String name, String roll, String branch, String sem) {
        this.name = name;
        this.roll = roll;
        this.branch = branch;
        this.sem = sem;
    }
}
