package com.tyiti.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ListSort {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		Calendar a = Calendar.getInstance();
		a.add(Calendar.DAY_OF_MONTH, 2);
		
		List<A> list = new ArrayList<A>();
		list.add(new A(1, c.getTime()));
		list.add(new A(3, a.getTime()));
		list.add(new A(4, new Date()));
		list.add(new A(2, new Date()));
		System.out.println(list.get(0).getDate().after(list.get(1).getDate()));
		Collections.sort(list, new Comparator<A>() {
            @Override  
            public int compare(A o1, A o2) {
                if (o1.getDate().after(o2.getDate())) {
                    return -1;
                }
                return 1;
            }  
        });
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
class A {
	private Integer id;
	private Date date;
	
	public A(Integer id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "A [id=" + id + ", date=" + date + "]";
	}
}
