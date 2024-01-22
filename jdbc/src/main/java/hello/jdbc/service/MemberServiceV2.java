package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;


    /**
     * 수동 커밋
     */
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection conn = dataSource.getConnection(); // SQLException

        // 트랜잭션 처리 Logic
        try {
            conn.setAutoCommit(false);  // 트랜잭션 시작

            busiLogic(conn, fromId, toId, money); // 순수 비즈니스 Logic

            conn.commit();   // 성공시 커밋
        } catch (Exception e) {
            conn.rollback(); // 실패시 롤백. SQLException
            throw new IllegalStateException(e); // throw e;
        } finally {
            release(conn);
        }

    }

    private void busiLogic(Connection conn, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(conn, fromId);
        Member toMember = memberRepository.findById(conn, toId);

        memberRepository.update(conn, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(conn, toId, toMember.getMoney() + money);
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }

    }

    private void release(Connection conn) {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);  // 커넥션 풀 고려
                conn.close();
            } catch (Exception e) {
                log.info("error", e);
            }

        }

    }


}
