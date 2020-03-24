package com.koju.insurance.services;

import java.util.List;

import com.koju.insurance.beans.Family;
import com.koju.insurance.beans.FamilyMembers;

public interface FamilyDisplayInterface {
	public List<Family> getAllFamilies();
	public Family getSingleFamily();
	public List<FamilyMembers> getFamilyMembers();
}
