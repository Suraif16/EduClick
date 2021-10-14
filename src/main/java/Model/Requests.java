package Model;

public class Requests {

    private String fromId;
    private String toId;
    private String type;

    /* Here fromId is always a userId , toId will depend on the type of the request,
    * if the request is a friend request they toId is a userId , else if the request is a
    * enroll request type then toId is a ClassroomId, Since we use Bigint for both userId and
    * ClassroomId it doesn't change anything.
    * Here fromId is the userId of the user who sends the request, and toId is to a user who receives
    * the request, or to a classroom which receives the request*/

    public Requests( String fromId, String toId, String type ) {

        this.fromId = fromId;
        this.toId = toId;
        this.type = type;

    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
