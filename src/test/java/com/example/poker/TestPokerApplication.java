package com.example.poker;

import org.springframework.boot.SpringApplication;

public class TestPokerApplication {

	public static void main(String[] args) {
		SpringApplication.from(PokerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
