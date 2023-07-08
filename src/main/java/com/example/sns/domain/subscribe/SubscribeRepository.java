package com.example.sns.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying  // INSERT, DELETE, UPDATE 같은 DB에 영향을 미치는 쿼리들은 Modifying 어노테이션을 붙여줘야 함
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createdAt) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(Long fromUserId, Long toUserId);  // 성공하면 1(변경된 행의 갯수), 실패하면 -1(이건 그녕 오류)이 return , 0이면 그냥 insert가 안된 것

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId=:toUserId", nativeQuery = true)
    void mUnSubscribe(Long fromUserId, Long toUserId); // 성공하면 1(변경된 행의 갯수), 실패하면 -1(이건 그녕 오류)이 return , 0이면 그냥 insert가 안된 것
}
