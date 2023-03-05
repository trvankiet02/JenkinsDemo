package vn.iotstar.Service;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import vn.iotstar.Entity.Account;

public interface IAccountService {

	void delete(Account entity);

	void deleteById(Long id);

	long count();

	<S extends Account, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	<S extends Account> long count(Example<S> example);

	<S extends Account> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Account> findAllById(Iterable<Long> ids);

	List<Account> findAll();

	<S extends Account> S save(S entity);
	
	Account login(String email, String password);

}
