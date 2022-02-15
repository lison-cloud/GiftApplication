package by.bsuir.system.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class GiftCertificateEntity {

    private long id;
    private String name;
    private String description;
    private double price;
    private int duration;
    private Date createDate;
    private Date lastUpdateDate;
}
