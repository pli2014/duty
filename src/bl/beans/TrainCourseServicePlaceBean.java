package bl.beans;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Index;

/**
 * Created by Administrator on 14-3-14.
 */
@Entity(value = "traincourse_serviceplace")
@Index(value = "trainCourseId,servicePlaceId")
public class TrainCourseServicePlaceBean extends Bean {
    private String trainCourseId;
    private String servicePlaceId;

    public String getTrainCourseId() {
        return trainCourseId;
    }

    public void setTrainCourseId(String trainCourseId) {
        this.trainCourseId = trainCourseId;
    }

    public String getServicePlaceId() {
        return servicePlaceId;
    }

    public void setServicePlaceId(String servicePlaceId) {
        this.servicePlaceId = servicePlaceId;
    }
}
