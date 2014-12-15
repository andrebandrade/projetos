package br.com.ifactory.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulador {

	public static Properties getProperties() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/dados.properties");
		props.load(file);
		return props;
	} // fim do método getProperties
	
	public static void main(String[] args) throws IOException {
		String host;
		String login;
		String password;
		
		Properties prop = getProperties();
		
		host = prop.getProperty("prop.server.host");
		login = prop.getProperty("prop.server.login");
		password = prop.getProperty("prop.server.password");
		
		System.out.printf("Host: %s\nLogin: %s\nPassword: %s", host, login, password);
	} // fim de main
}
