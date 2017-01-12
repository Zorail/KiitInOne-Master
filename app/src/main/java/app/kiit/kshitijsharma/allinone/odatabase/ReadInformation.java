package app.kiit.kshitijsharma.allinone.odatabase;

import java.util.HashMap;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.Map;

/**
 * Created by kshitij sharma on 1/7/2017.
 */

public class ReadInformation {
    public String name;
    public String  roll;
    public String branch;
    public String sem;
    public Map<String, Boolean> stars = new HashMap<>();
    public ReadInformation(){

    }

    public ReadInformation(String name, String roll, String branch, String sem) {
        this.name = name;
        this.roll = roll;
        this.branch = branch;
        this.sem = sem;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("roll", roll);
        result.put("branch",branch);
        result.put("sem",sem);
        result.put("stars", stars);

        return result;
    }
}
