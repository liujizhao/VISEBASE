package application.base.com.base.request;

/**
 * Author Blank
 * Create on 2018/8/1 13:50
 * Description:
 */
public class LoginRequest {

    /**
     * userName : hanke
     * userPassWord : 123456
     */

    private String userName;
    private String userPassWord;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }
}
