package by.bsuir.system.dto;

import java.util.List;

import lombok.Data;

@Data
public class GiftCertificateRequest {
    private String name;
    private String description;
    private double price;
    private int duration;
    private List<TagRequest> tags;
}
