package runlab.models.mongoDB;

import java.time.LocalDateTime;

public abstract class mongodbDocument {
    public LocalDateTime createdDateTime = LocalDateTime.now();
}
