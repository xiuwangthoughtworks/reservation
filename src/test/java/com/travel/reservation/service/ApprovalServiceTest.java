package com.travel.reservation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.travel.reservation.domain.ApprovalStatus;
import com.travel.reservation.domain.entity.Approval;
import com.travel.reservation.exception.DataBaseException;
import com.travel.reservation.repository.ApprovalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApprovalServiceTest {
  @InjectMocks
  ApprovalService approvalService;

  @Mock
  ApprovalRepository approvalRepository;

  private final String RESERVATION_ID = "1";

  @Test
  void should_return_approval_when_reject_reservation_given_repository_save_success() {
    Approval approval = buildRejectApproval();
    when(approvalRepository.save(approval)).thenReturn(approval);

    Approval actualApproval = approvalService.reject(RESERVATION_ID);

    assertEquals(approval, actualApproval);
  }

  @Test
  void should_throw_database_exception_when_reject_reservation_given_repository_throw_exception() {
    Approval approval = buildRejectApproval();
    when(approvalRepository.save(approval)).thenThrow(RuntimeException.class);

    assertThrows(DataBaseException.class, () -> approvalService.reject(RESERVATION_ID));
  }

  private Approval buildRejectApproval() {
    return Approval.builder()
        .reservationId(RESERVATION_ID)
        .status(ApprovalStatus.REJECT)
        .build();
  }

}