package com.travel.reservation.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.travel.reservation.domain.ApprovalStatus;
import com.travel.reservation.domain.entity.Approval;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ApprovalRepositoryTest {
  @Autowired
  ApprovalRepository approvalRepository;

  @AfterEach
  public void cleanUp() {
    approvalRepository.deleteAll();
  }

  @Test
  void should_return_approval_entity_when_save_approval_entity_given_save_success() {
    Approval approval = approvalRepository.save(buildApprovalEntity());
    approvalRepository.save(buildApprovalEntity());

    Approval expectedApproval = buildApprovalEntity();
    assertEquals(expectedApproval, approval);
  }

  @Test
  void should_throw_exception_when_save_approval_entity_given_save_failed() {
    Approval approval = Approval.builder().build();
    assertThrows(RuntimeException.class, () -> approvalRepository.save(approval));
  }

  private Approval buildApprovalEntity() {
    return Approval.builder()
        .reservationId("1")
        .status(ApprovalStatus.REJECT)
        .build();
  }

}