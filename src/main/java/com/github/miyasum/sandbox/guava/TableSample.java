package com.github.miyasum.sandbox.guava;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Table サンプル
 */
public class TableSample {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
		Table<Integer, String, Employee> table = HashBasedTable.create();
		employees.stream().forEach(employee -> {
			table.put(employee.getNo(), employee.getName(), employee);
		});

		table.row(0);
	}

	private static class Employee {
		private int no;
		private String name;

		public int getNo() {
			return no;
		}

		public String getName() {
			return name;
		}
	}
}
