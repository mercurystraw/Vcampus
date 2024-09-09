package tech.mainCode.dao;

import tech.mainCode.entity.Manager;

public interface IManagerMapper {

	public Boolean verifyManager(Manager manager);

	public Manager getManagerDetailByCardNumber(String cardNumber);

}
