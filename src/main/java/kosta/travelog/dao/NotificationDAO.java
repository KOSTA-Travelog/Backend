package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.NotificationVO;

import java.util.Collection;

public interface NotificationDAO {
    /*커뮤니티 멤버초대 (대기상태 R로 저장)*/
    void addPendingCommunityMember(NotificationVO vo);

    /*커뮤니티 초대수락*/
    void acceptCommunityInvite(String notificationId, String userId) throws DatabaseQueryException;

    /*커뮤니티 초대거절*/
    void rejectCommunityInvite(String notificationId, String userId) throws DatabaseQueryException;

    /*수락 혹은 거절 누르면 알림 삭제하기*/
    void removeNotification(String notificationId, String userId) throws DatabaseQueryException;

    /*알림 내역 보여주기*/
    Collection<NotificationVO> NotificationList(String userId) throws DatabaseQueryException;
}
