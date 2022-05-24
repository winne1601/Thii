package Thi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Person {
	private
		String name;
		Date birthday;
		byte gender;
		//constructor
		Person(){}
		Person(String name, Date birthday, byte gender){
			setName(name);
			setBirthday(birthday);
			setGender(gender);
		}
		//getter and setter
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		public byte getGender() {
			return gender;
		}
		public void setGender(byte gender) {
			this.gender = gender;
		}
		
}
