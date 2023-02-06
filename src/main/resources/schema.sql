CREATE TABLE IF NOT EXISTS todo (
                                    id BIGSERIAL PRIMARY KEY,
                                    message TEXT,
                                    author CHAR(64)
    );
