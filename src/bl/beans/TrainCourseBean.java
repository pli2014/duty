package bl.beans;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Administrator on 14-3-14.
 */
@Entity(value = "traincourse")
public class TrainCourseBean extends Bean {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
