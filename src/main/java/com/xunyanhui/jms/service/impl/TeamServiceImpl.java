package com.xunyanhui.jms.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.xunyanhui.jms.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@PostConstruct
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("51bangban start");
	}

	@Override
	public void getAll() {
		// TODO Auto-generated method stub
		System.out.println("GetAll....");
	}

}
