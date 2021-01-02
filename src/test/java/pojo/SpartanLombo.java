package pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpartanLombo {
    private String name;
    private String gender;
    private long phone;
}