package ir.ac.kntu.db_project_backend.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.mappers.AdvertisementRowMapper;
import ir.ac.kntu.db_project_backend.models.Advertisement;
import jakarta.annotation.PostConstruct;

@Repository
public class AdvertisementRepository {

    @Autowired
    private JdbcTemplate template;

    private final AdvertisementRowMapper ROW_MAPPER = new AdvertisementRowMapper();

    private final String MAIN_QUERY = " SELECT * FROM Advertisements AS A JOIN AddStatus AS Ads ON Ads.ADDID = A.ADDID ";

    @PostConstruct
    public void create(){
        String sql = "CREATE TABLE  IF NOT EXISTS Advertisements (\r\n" + //
                        "    ADDID INT UNIQUE AUTO_INCREMENT,\r\n" + //
                        "    BID INT,\r\n" + //
                        "    AID INT NOT NULL,\r\n" + //
                        "    Description TEXT NOT NULL,\r\n" + //
                        "    Title VARCHAR(50) NOT NULL,\r\n" + //
                        "    Location VARCHAR(255) NOT NULL,\r\n" + //
                        "    DateModified DATE NOT NULL,\r\n" + //
                        "    Price INT NOT NULL,\r\n" + //
                        "    IsNew BIT NOT NULL,\r\n" + //
                        "    FOREIGN KEY (BID) REFERENCES Businesses(BID),\r\n" + //
                        "    FOREIGN KEY (AID) REFERENCES Accounts(AID)\r\n" + //
                        ");";
        template.update(sql);
    }

    public void updateAdvertisement(Advertisement advertisment,AddStatusRepository repository){
        String sql = "UPDATE Adertisements SET  Description = ? , Title = ?, DateModified = CURRENT_DATE(), Price = ?, IsNew = ?"
            + "WHERE AID = ?";
        template.update(sql, advertisment.getDescription(), advertisment.getTitle(),
            advertisment.getPrice(),advertisment.isNew(), advertisment.getId());
        repository.resetStatus(advertisment.getId());
    }

    public void addAdvertisement(Advertisement advertisment,AddStatusRepository repository){ 
        String sql = "INSERT INTO Advertisements (BID, AID, Description, Title, DateModified, Price, IsNew) VALUES " + 
            " (?,?,?,?,CURRENT_DATE(),?,?) ";
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

    public List<Advertisement> filter(int maxPrice, int minPrice,boolean isNew, String keyword){
        String sql = MAIN_QUERY + " WHERE Price >= ? AND Price <= ? AND A.IsNew = ? AND ( A.Title REGEXP ? OR A.Description REGEXP ? )";
        return template.query(sql, ROW_MAPPER, minPrice,maxPrice,isNew,keyword,keyword);
    }

    public Advertisement findById(int id){
        return template.queryForObject(MAIN_QUERY + " WHERE A.ADDID = ?", ROW_MAPPER, id);
    }

    public Advertisement findByBusiness(int id){
        return template.queryForObject(MAIN_QUERY + " WHERE A.BID = ?", ROW_MAPPER, id);
    }

    public Advertisement findByAccount(int id){
        return template.queryForObject(MAIN_QUERY + " WHERE A.AID = ?", ROW_MAPPER, id);
    }

    public void delete(int ADDID , AddStatusRepository repository){
        repository.delete(ADDID);
        template.update("DELETE FROM Advertisements WHERE ADDID = ?", ADDID);
    }
}