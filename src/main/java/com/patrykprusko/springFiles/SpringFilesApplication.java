package com.patrykprusko.springFiles;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * web application -> narzędzie do wgrania z dysku plikow Excel, CSV, JSON, ktora pozniej wyswietla na GUI zawartosc wgranych danych rekordow,
 * jesli wgra sie nastepny jakis plik to UPDATE sie robi i aktualizuje lista wyswietlanych rekordow. W kazdym rekordzie dodalem 
 * pole jaki rodzaj pliku (file type) np. XLS/XLSX , CSV, JSON. Jesli puste pole to doda pusta zawartosc. Wszystkie wczytane rekordy sa dodawane 
 * do bazy danych MySQL -> baza danych-> readfiledata, tabela -> policy.
 * 
 * Aplikacja zbudowana z strony start.spring.io:
 * 1. Web
 * 2. JPA
 * 3. MySQL
 * 4. DevTools
 * 5. Thymeleaf
 * 
 * @author carnal
 *
 */
@SpringBootApplication
public class SpringFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFilesApplication.class, args);
	}

}
