/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.geekbrains.javacommand.command.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.geekbrains.javacommand.command.util.FileUtilities;

/** @author Igor Popovich, email: popovichia@gmail.com, */
@Configuration
public class SpringConfiguration {
	@Bean(name = "modelMapper")
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	@Bean(name = "fileUtilities")
	public FileUtilities getFileUtilities() {
		return new FileUtilities();
	}
}
