package cloud.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Getter
@Setter
public class Image {
    private String uuid;
    @NotEmpty
    private String url;
    @NotEmpty
    @Size(min = 32, max = 32)
    private String belongTo;
    private short  valid;
}
