/**
 * @author gudong
 * @since Date: Mar 16, 2014
 */
package bl.beans;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

/**
 * @author gudong
 * @since $Date:2013-03-16$
 */
@Entity(value = "volunteer_traincourse")
public class VolunteerTrainCourseBean extends Bean {
  private ObjectId volunteerId;
  private ObjectId traincourseId;
  private transient VolunteerBean volunteer;
  private transient TrainCourseBean trainCourse;
  private Integer status = 0; // 0=未通过,1=通过

  public ObjectId getVolunteerId() {
    return volunteerId;
  }

  public void setVolunteerId(ObjectId volunteerId) {
    this.volunteerId = volunteerId;
  }

  public ObjectId getTraincourseId() {
    return traincourseId;
  }

  public void setTraincourseId(ObjectId traincourseId) {
    this.traincourseId = traincourseId;
  }

  public VolunteerBean getVolunteer() {
    return volunteer;
  }

  public void setVolunteer(VolunteerBean volunteer) {
    this.volunteer = volunteer;
  }

  public TrainCourseBean getTrainCourse() {
    return trainCourse;
  }

  public void setTrainCourse(TrainCourseBean trainCourse) {
    this.trainCourse = trainCourse;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}
