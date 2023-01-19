package review.steam_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SteamGameReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SteamGameReviewApplication.class, args);
	}

}
