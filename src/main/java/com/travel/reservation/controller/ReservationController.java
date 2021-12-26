package com.travel.reservation.controller;

import com.travel.reservation.domain.entity.Approval;
import com.travel.reservation.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel-reservations")
@RequiredArgsConstructor
public class ReservationController {
  private final ApprovalService approvalService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/{rid}/rejection")
  public Approval rejectReservation(@PathVariable String rid) {
    return approvalService.reject(rid);
  }
}
