package com.travel.reservation.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.reservation.domain.ApprovalStatus;
import com.travel.reservation.domain.entity.Approval;
import com.travel.reservation.exception.DataBaseException;
import com.travel.reservation.service.ApprovalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ApprovalService approvalService;

  @Autowired
  private ObjectMapper objectMapper;

  private final String RESERVATION_ID = "1";

  @Test
  void should_return_is_created_status_and_approval_response_when_reject_reservation_given_service_reject_success() throws Exception {
    Approval approval = buildRejectApproval();
    when(approvalService.reject(RESERVATION_ID)).thenReturn(approval);

    MvcResult mvcResult = mockMvc.perform(post("/travel-reservations/1/rejections"))
        .andExpect(status().isCreated())
        .andReturn();

    Approval actualApproval = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Approval.class);
    assertEquals(approval, actualApproval);
  }

  @Test
  void should_return_500_status_when_reject_reservation_given_service_throw_reservation_not_found_exception() throws Exception {
    when(approvalService.reject(RESERVATION_ID)).thenThrow(DataBaseException.class);

    mockMvc.perform(post("/travel-reservations/1/rejections"))
        .andExpect(status().isInternalServerError())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof DataBaseException));
  }

  private Approval buildRejectApproval() {
    return Approval.builder()
        .reservationId(RESERVATION_ID)
        .status(ApprovalStatus.REJECT)
        .build();
  }
}