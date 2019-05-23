package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class CommandTarget {
    private String target;
    private String message;
    private boolean ajaxRedirect;

    public CommandTarget(String target, String message) {
        this.target = target;
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public String getMessage() {
        return message;
    }
    
    public boolean getRedirect() {
        return ajaxRedirect;
    }
    
    public void setAjaxRedirect() {
        this.ajaxRedirect = true;
    }
}
