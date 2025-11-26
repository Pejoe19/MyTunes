package dk.easv.mytunes.BLL;

public class MusicException extends Exception {

    public MusicException(){
    }

    public MusicException(String message) { super(message); }

    public MusicException(String message, Throwable cause) { super(message, cause); }

    public MusicException(Throwable cause) { super(cause); }

    public MusicException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) { super(message, cause, enableSupression, writableStackTrace); }

}