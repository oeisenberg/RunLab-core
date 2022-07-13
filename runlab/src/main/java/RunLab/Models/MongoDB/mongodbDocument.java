package RunLab.Models.MongoDB;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;

public abstract class mongodbDocument {
    @Id
    public String id; 
    public LocalDateTime createdDateTime = LocalDateTime.now();
}
