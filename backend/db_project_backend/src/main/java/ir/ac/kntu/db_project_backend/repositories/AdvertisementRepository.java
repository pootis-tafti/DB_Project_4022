package ir.ac.kntu.db_project_backend.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.mappers.AdvertismentRowMapper;
import ir.ac.kntu.db_project_backend.models.Advertisment;

@Repository
public class AdvertisementRepository {

    @Autowired
    private JdbcTemplate template;

    private final AdvertismentRowMapper ROW_MAPPER = new AdvertismentRowMapper();

    private final String MAIN_QUERY = "SELECT * FROM Advertisements ";

    public void updateAdvertisement(Advertisment advertisment,AddStatusRepository repository){
        String sql = "UPDATE Adertisements SET  Description = ? , Title = ?, DateModified = CURRENT_DATE(), Price = ?, IsNew = ?"
            + "WHERE AID = ?";
        template.update(sql, advertisment.getDescription(), advertisment.getTitle(),
            advertisment.getPrice(),advertisment.isNew(), advertisment.getId());
        repository.resetStatus(advertisment.getId());
    }

    public void addAdvertisement(Advertisment advertisment,AddStatusRepository repository){ 
        String sql = "INSERT INTO Advertisements (BID, AID, Description, Title, DateModified, Price, IsNew) VALUES" + 
            "(?,?,?,?,CURRENT_DATE(),?,?)";
            template.update(sql,advertisment.getBussinessId(),advertisment.getAccountId(), advertisment.getDescription(), advertisment.getTitle(),
            advertisment.getPrice(),advertisment.isNew());
        KeyHolder ADDID = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, advertisment.getBussinessId());
            ps.setInt(2, advertisment.getAccountId());
            ps.setString(3, advertisment.getDescription());
            ps.setString(4, advertisment.getTitle());
            ps.setInt(5, advertisment.getPrice());
            ps.setBoolean(6, advertisment.isNew());
            return ps;
        }, ADDID);
        repository.createStatus(ADDID.getKey().intValue());
    }

    public List<Advertisment> filter(int maxPrice, int minPrice,boolean isNew, String keyword){
        String sql = MAIN_QUERY + "WHERE Price >= ? AND Price <= ? AND (Title REGEXP ? OR Description Title REGEXP ?)";
        return template.query(sql, ROW_MAPPER, minPrice,maxPrice,keyword,keyword);
    }

    public Advertisment findById(int id){
        return template.queryForObject(MAIN_QUERY + "WHERE ADDID = ?", ROW_MAPPER, id);
    }

    public Advertisment findByBusiness(int id){
        return template.queryForObject(MAIN_QUERY + "WHERE BID = ?", ROW_MAPPER, id);
    }

    public Advertisment findByAccount(int id){
        return template.queryForObject(MAIN_QUERY + "WHERE AID = ?", ROW_MAPPER, id);
    }

    public void delete(int ADDID){
        template.update("DELETE FROM Advertisements WHERE ADDID = ?", ADDID);
    }
}