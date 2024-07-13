package ir.ac.kntu.db_project_backend.repositories;

import ir.ac.kntu.db_project_backend.mappers.AdvertismentRowMapper;
import ir.ac.kntu.db_project_backend.models.Advertisment;
import ir.ac.kntu.db_project_backend.models.CommentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdvertisementRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private AddStatusRepository addStatusRepository;

    @InjectMocks
    private AdvertisementRepository advertisementRepository;

    private Advertisment sampleAdvertisment;

    @BeforeEach
    public void setUp() {
        sampleAdvertisment = new Advertisment();
        sampleAdvertisment.setId(1);
        sampleAdvertisment.setBussinessId(1);
        sampleAdvertisment.setAccountId(1);
        sampleAdvertisment.setDescription("Sample Description");
        sampleAdvertisment.setTitle("Sample Title");
        sampleAdvertisment.setPrice(100);
        sampleAdvertisment.setIsNew(true);
    }

    @Test
    public void testAddAdvertisement() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        doAnswer(invocation -> {
            PreparedStatement ps = invocation.getArgument(0);
            ps.execute();
            keyHolder.getKeyList().add(1); // Simulate generated key
            return null;
        }).when(jdbcTemplate).update(any(PreparedStatement.class), any(KeyHolder.class));

        advertisementRepository.addAdvertisement(sampleAdvertisment, addStatusRepository);

        verify(jdbcTemplate, times(1)).update(any(PreparedStatement.class), any(KeyHolder.class));
        verify(addStatusRepository, times(1)).createStatus(anyInt());
    }

    @Test
    public void testUpdateAdvertisement() {
        doNothing().when(jdbcTemplate).update(anyString(), any(Object[].class));
        doNothing().when(addStatusRepository).resetStatus(anyInt());

        advertisementRepository.updateAdvertisement(sampleAdvertisment, addStatusRepository);

        verify(jdbcTemplate, times(1)).update(anyString(), any(Object[].class));
        verify(addStatusRepository, times(1)).resetStatus(anyInt());
    }

    @Test
    public void testFindById() {
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenReturn(sampleAdvertisment);

        Advertisment advertisment = advertisementRepository.findById(1);

        assertNotNull(advertisment);
        assertEquals(sampleAdvertisment, advertisment);
    }

    @Test
    public void testFilter() {
        List<Advertisment> expectedAds = Arrays.asList(sampleAdvertisment);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), anyInt(), anyInt(), anyString(), anyString())).thenReturn(expectedAds);

        List<Advertisment> ads = advertisementRepository.filter(200, 50, true, "Sample");

        assertNotNull(ads);
        assertEquals(expectedAds, ads);
    }

    @Test
    public void testDelete() {
        doNothing().when(jdbcTemplate).update(anyString(), anyInt());

        advertisementRepository.delete(1);

        verify(jdbcTemplate, times(1)).update(anyString(), anyInt());
    }
}

