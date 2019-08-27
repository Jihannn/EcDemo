package com.jihan.moni_ec.database;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Jihan
 * @date 2019/8/13
 */
@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id(autoincrement = true)
    private Long userId;
    private String account;
    private String passWord;
    @Generated(hash = 950159022)
    public UserProfile(Long userId, String account, String passWord) {
        this.userId = userId;
        this.account = account;
        this.passWord = passWord;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getAccount() {
        return this.account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
