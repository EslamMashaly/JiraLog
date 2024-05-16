package pojo;

public class LoginInfo {
    private String failedLoginCount;
    private String loginCount;
    private String lastFailedLoginTime;
    private String previousLoginTime;

    public String getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(String failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public String getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(String loginCount) {
        this.loginCount = loginCount;
    }

    public String getLastFailedLoginTime() {
        return lastFailedLoginTime;
    }

    public void setLastFailedLoginTime(String lastFailedLoginTime) {
        this.lastFailedLoginTime = lastFailedLoginTime;
    }

    public String getPreviousLoginTime() {
        return previousLoginTime;
    }

    public void setPreviousLoginTime(String previousLoginTime) {
        this.previousLoginTime = previousLoginTime;
    }
}