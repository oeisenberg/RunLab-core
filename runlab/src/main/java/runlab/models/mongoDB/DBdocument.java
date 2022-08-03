package runlab.models.mongoDB;

import java.time.LocalDateTime;

public abstract class DBdocument {
    public LocalDateTime createdDateTime = LocalDateTime.now();
}
