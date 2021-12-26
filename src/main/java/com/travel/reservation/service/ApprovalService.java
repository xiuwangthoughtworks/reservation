package com.travel.reservation.service;

import com.travel.reservation.domain.ApprovalStatus;
import com.travel.reservation.domain.entity.Approval;
import com.travel.reservation.exception.DataBaseException;
import com.travel.reservation.repository.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovalService {
  private final ApprovalRepository approvalRepository;

  public Approval reject(String reservationId) {
    return saveRejectApproval(reservationId);
  }

  private Approval saveRejectApproval(String reservationId) {
    Approval approval = createRejectApproval(reservationId);
    try {
      return approvalRepository.save(approval);
    } catch (RuntimeException exception) {
      throw new DataBaseException();
    }
  }

  private Approval createRejectApproval(String reservationId) {
    return Approval.builder()
        .reservationId(reservationId)
        .status(ApprovalStatus.REJECT)
        .build();
  }
}
