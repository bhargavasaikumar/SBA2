package com.iiht.training.eloan.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDto registerClerk(UserDto userDto) throws InvalidDataException {

		if (userDto != null && userDto.isUserValid(userDto)) {
			if (usersRepository.isEmailExists(userDto.getEmail()) > 0) {
				throw new InvalidDataException("Email already in use, please choose other email");
			} else if (usersRepository.isMobileExists(userDto.getMobile()) > 0) {
				throw new InvalidDataException("Mobile number already in use, please choose other mobile number");
			} else {
				usersRepository.saveAndFlush(ConversionUtility.ConvertUserDtoToUsers(userDto, "Clerk"));
			}

		} else {
			throw new InvalidDataException("Clerk information provided is invalid");
		}

		return userDto;
	}

	@Override
	public UserDto registerManager(UserDto userDto) throws InvalidDataException {

		if (userDto != null && userDto.isUserValid(userDto)) {
			if (usersRepository.isEmailExists(userDto.getEmail()) > 0) {
				throw new InvalidDataException("Email already in use, please choose other email");
			} else if (usersRepository.isMobileExists(userDto.getMobile()) > 0) {
				throw new InvalidDataException("Mobile number already in use, please choose other mobile number");
			} else {
				usersRepository.saveAndFlush(ConversionUtility.ConvertUserDtoToUsers(userDto, "Manager"));
			}
		} else {
			throw new InvalidDataException("Manager information provided is invalid");
		}
		return userDto;
	}

	@Override
	public List<UserDto> getAllClerks() {

		return ConversionUtility.ConvertUsersToUserDto(usersRepository.findAllUsersByRole("Clerk"));
	}

	@Override
	public List<UserDto> getAllManagers() {

		return ConversionUtility.ConvertUsersToUserDto(usersRepository.findAllUsersByRole("Manager"));
	}

}
