package com.course.kafka;

import com.course.kafka.model.Employee;
import com.course.kafka.producer.EmployeeJsonProducer;
import com.course.kafka.producer.EmployeeJsonProducer2;
import com.course.kafka.producer.KafkaKeyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication  implements CommandLineRunner {

//	@Autowired
//	private KafkaKeyProducer keyProducer;

//	@Autowired
//	private EmployeeJsonProducer2 employeeJsonProducer;


	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

//	@Override
//	public void run(String... args) throws Exception {
//       for(int i = 135; i< 145;i++){
//		   var emp = new Employee("emp - " + i , "Employee " + i , LocalDate.now());
//		   employeeJsonProducer.sendMessage(emp);
//	   }
//	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		for(int i =0; i<300000; i++){
//			 var key = "key - " + (i % 4);
//			 var value = " value  " + i + " with key " + key;
//			keyProducer.send(key , value);
//		}
//	}
}
