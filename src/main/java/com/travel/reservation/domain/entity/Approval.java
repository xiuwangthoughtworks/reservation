package com.travel.reservation.domain.entity;

import com.travel.reservation.domain.ApprovalStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "approval")
public class Approval {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String reservationId;

  @Enumerated(EnumType.STRING)
  private ApprovalStatus status;
}
