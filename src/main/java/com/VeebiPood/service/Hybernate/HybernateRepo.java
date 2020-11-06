package com.VeebiPood.service.Hybernate;

import com.VeebiPood.service.Hybernate.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HybernateRepo extends JpaRepository<Account, Long> {
}


/*
KONSPEKT:
AUTOWIRE JA KASUTA REPOT
NTX

categoryRepository.save(category);
categoryRepository.getOne(1l);
categoryRepository.delete(category);

NB Pärast save meetodi välja kutsumist tekib objektile automaatselt ID külge.
*/
