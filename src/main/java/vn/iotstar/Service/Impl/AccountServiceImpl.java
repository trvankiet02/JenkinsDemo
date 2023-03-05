package vn.iotstar.Service.Impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import vn.iotstar.Entity.Account;
import vn.iotstar.Repository.AccountRepository;
import vn.iotstar.Service.IAccountService;

// 
@Service
public class AccountServiceImpl implements IAccountService{

	
	@Autowired
	AccountRepository accountRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bcryptpasswordencoder;
	

	public AccountServiceImpl(AccountRepository accountrepository) {
		super();
		this.accountRepository = accountrepository;
	}

	
	//Chi dung cho them moi
	@Override
	public <S extends Account> S save(S entity) {
		return accountRepository.save(entity);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public List<Account> findAllById(Iterable<Long> ids) {
		return accountRepository.findAllById(ids);
	}

	@Override
	public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
		return accountRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Account> long count(Example<S> example) {
		return accountRepository.count(example);
	}

	@Override
	public <S extends Account, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return accountRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return accountRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void delete(Account entity) {
		accountRepository.delete(entity);
	}


	@Override
	public Account login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
