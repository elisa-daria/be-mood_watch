package elisa_daria.be_mood_watch.exceptions;

public class NotFoundEx extends RuntimeException {
    public NotFoundEx(long id){
        super ("Record with id: "+id+ " not found!");
    }
    public NotFoundEx(String msg){
        super(msg);
    }
}
