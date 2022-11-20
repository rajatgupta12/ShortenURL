package com.project.URL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.URL.entity.ShortURL;

public interface ShortURLRepo extends JpaRepository<ShortURL, Long>
{
	
}
