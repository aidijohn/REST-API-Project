package com.pezesha.myproject.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sourceAccountId")
    private Long sourceAccountId;

    @Column(name = "receivingAccountId")
    private Long receivingAccountId;

    @Column(name = "transferAmount")
    private Float transferAmount;


}
