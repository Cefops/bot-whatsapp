package br.com.cefops.SendMenssage.events.entity;

import br.com.cefops.SendMenssage.events.model.CustomWpMessageEventModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "event_status")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomWpMessageEventEntity extends CustomWpMessageEventModel  implements Serializable {
    private static final long serialVersionUID = 2806421523585360625L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private int totalMessage;
    private int totalSend;
    private int totalError;
    private String lastNumber;
    private String token;
    private String userRecive;
    private String userSend;
    private int status;
    private Boolean finalized;

}