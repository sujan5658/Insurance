package com.koju.insurance.services;

import java.sql.SQLException;

import com.koju.insurance.beans.Message;

public interface FamilyRegistrationInterface {
	public Message registerFamily() throws SQLException;
	public Message registerMembers() throws SQLException;
}
