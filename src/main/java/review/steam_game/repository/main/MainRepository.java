package review.steam_game.repository.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import review.steam_game.entity.game.App_id;

import java.util.List;
import java.util.Optional;

public interface MainRepository extends JpaRepository<App_id, Long> {

    //http://store.steampowered.com/api/appdetails/?appids=<<APPID>>&filters=categories
    @Query(nativeQuery = true, value="SELECT A.appid, A.name, A.views FROM APP_ID A WHERE A.name LIKE %:str1% UNION " +
            "SELECT B.appid, B.name, B.views FROM APP_ID B WHERE B.name LIKE %:str2% UNION " +
            "SELECT C.appid, C.name, C.name FROM APP_ID C WHERE C.name LIKE %:str3%")
    List<Optional<App_id>> findByName(@Param("str1") String str1, @Param("str2") String str2, @Param("str3") String str3);

    @Query(nativeQuery = true, value = "SELECT A.appid, A.name, A.views FROM APP_ID A WHERE A.name LIKE %:str1% UNION " +
            "SELECT B.appid, B.name, B.views FROM APP_ID B WHERE B.name LIKE %:str2%")
    List<Optional<App_id>> findByNameStr2(@Param("str1") String str1, @Param("str2") String str2);

    @Query(nativeQuery = true, value = "SELECT A.appid, A.name, A.views FROM APP_ID A WHERE A.name LIKE %:str1% UNION " +
            "SELECT C.appid, C.name, C.views FROM APP_ID C WHERE C.name LIKE %:str3%")
    List<Optional<App_id>> findByNameStr3(@Param("str1") String str1, @Param("str3") String str3);
}
