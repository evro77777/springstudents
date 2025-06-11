package ru.kors.springstudents.repository.university_details;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kors.springstudents.model.UniversityDetails;

import java.util.Optional;

public interface UniversityDetailsRepository extends JpaRepository<UniversityDetails, Long> {
    String query = """
               SELECT u.id,
                   u.university_name,
                   COUNT(s.id) AS count_students
               FROM
                   university u
               LEFT JOIN
                   students s ON u.id = s.university_id
               WHERE
               	u.id = :id
               GROUP BY
                   u.id
            """;

    @Query(value = query, nativeQuery = true)
    @NonNull
    Optional<UniversityDetails> findById(@NonNull Long id);
}
