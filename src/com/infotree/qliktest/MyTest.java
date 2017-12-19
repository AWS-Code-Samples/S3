package com.infotree.qliktest;

import java.util.ArrayList;
import java.util.List;

public class MyTest {

	public static void main(String[] args) {
		
		Student s1=new Student();
		s1.setName("aaa");
		Student s2=new Student();
		s2.setName("bbb");
		List<Student> list=new ArrayList<Student>();
		list.add(s1);
		list.add(s2);
		//s2.setName("ccc");
		
		System.out.println(list);
		System.out.println(s1+"\t"+s2);
		s1.setName("satya");
		System.out.println(list);
		System.out.println(s1+"\t"+s2);

	}

}
