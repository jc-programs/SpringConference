package io.bcn.springConference.repository;

import io.bcn.springConference.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, UUID> {}
