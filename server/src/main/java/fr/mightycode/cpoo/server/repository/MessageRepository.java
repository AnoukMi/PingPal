import org.springframework.data.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageDTO, Long>{
    List<MessageDTO> findByFromOrToIgnoreCaseOrderByTimestampDesc(String from, String to);
}