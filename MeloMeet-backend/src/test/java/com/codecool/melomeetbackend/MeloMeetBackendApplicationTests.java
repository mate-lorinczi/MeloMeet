package com.codecool.melomeetbackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MeloMeetBackendApplicationTests {

    private final DataSource dataSource;

    @Autowired
    public MeloMeetBackendApplicationTests(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @DisplayName("Test if connection to PostgresQL database is established")
    @Test
    void testDatabaseConnection() throws SQLException {
        String expected = "PostgreSQL";

        assertEquals(expected, dataSource.getConnection().getMetaData().getDatabaseProductName());
    }
}
