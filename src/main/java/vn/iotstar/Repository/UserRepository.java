package vn.iotstar.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.Entity.Account;
import vn.iotstar.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	
	//Tim kiem noi dung ten from account where email
	
	List<Account> findByEmailContaining(String email);
	// Tìm kiếm và phân trang
	
	Page<Account> findByEmailContaining(String email, Pageable pageable);
	Optional<Account> findByEmail (String email);

}
