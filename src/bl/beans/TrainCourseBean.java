package bl.beans;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Administrator on 14-3-14.
 */
@Entity(value = "traincourse")
public class TrainCourseBean extends Bean {
    private String description;
    private int status; // 0 –创建  1-开始 （只有状态为1，志愿者才可以看见）,2 结束

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
