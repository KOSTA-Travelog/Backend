package kosta.travelog.model;

import java.util.Date;
import java.util.Objects;

public class UsersVO {

    private String userId;  // uuid
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String profileImage;
    private String nickName;
    private Date registrationDate;
    private char userStatus;
    private String bio;

    public UsersVO(String userId, String password, String name, String email, String phoneNumber, String profileImage, String nickName, Date registrationDate, char userStatus, String bio) {
        super();
        setUserId(userId);
        setPassword(password);
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setProfileImage(profileImage);
        setNickName(nickName);
        setRegistrationDate(registrationDate);
        setUserStatus(userStatus);
        setBio(bio);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public char getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(char userStatus) {
        this.userStatus = userStatus;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersVO usersVO = (UsersVO) o;
        return userStatus == usersVO.userStatus && Objects.equals(userId, usersVO.userId) && Objects.equals(password, usersVO.password) && Objects.equals(name, usersVO.name) && Objects.equals(email, usersVO.email) && Objects.equals(phoneNumber, usersVO.phoneNumber) && Objects.equals(profileImage, usersVO.profileImage) && Objects.equals(nickName, usersVO.nickName) && Objects.equals(registrationDate, usersVO.registrationDate) && Objects.equals(bio, usersVO.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, password, name, email, phoneNumber, profileImage, nickName, registrationDate, userStatus, bio);
    }

    @Override
    public String toString() {
        return "UsersVO{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", nickName='" + nickName + '\'' +
                ", registrationDate=" + registrationDate +
                ", userStatus=" + userStatus +
                ", bio='" + bio + '\'' +
                '}';
    }
}
