package kosta.travelog.model;

public interface UsersDAO {

    public UsersVO login(String username, String password);
    public boolean addUsers(UsersVO users);
    public boolean updateUsers(UsersVO users);
    public boolean deleteUsers(UsersVO users);
    public UsersVO getUsers();
    public String findId(String name, String password);
    public String findPassword(String name, String password);
    public boolean updatePassword(String userId, String newPassword);
}
