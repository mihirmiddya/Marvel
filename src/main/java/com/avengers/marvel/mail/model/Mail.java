package com.avengers.marvel.mail.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblmaillog")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "to_address")
    private String toAddress;

    private String subject;

    private String body;

    @Column(name = "send_on")
    private LocalDateTime sendOn;

    public Mail(String toAddress, String subject, String body) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.body = body;
        this.sendOn = LocalDateTime.now();
    }
}
