package br.com.cefops.SendMenssage.meta.whatsapp.data;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "wp_message")
@Data
public class WpMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private  String messageId;
    private  String messageToNumber;
    private  String messageTemplate;
    private  String messageStatus;
    private  String userSend;
    private  String eventId;
    private  String fileName;

    @CreationTimestamp
    private Date createdOn;




}