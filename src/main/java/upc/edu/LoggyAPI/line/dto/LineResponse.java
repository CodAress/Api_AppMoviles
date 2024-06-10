package upc.edu.LoggyAPI.line.dto;

import lombok.Data;

@Data
public class LineResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
}
