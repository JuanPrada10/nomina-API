package com.ejemplo.nomina;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class NominaStorage {
	private static final String FILE_PATH = "nominadata.json";
	private static final ObjectMapper mapper = new ObjectMapper();

	public static List<Nomina> load() {
		System.out.println("ingresa a load: "+ FILE_PATH);

		try {
			File file = new File(FILE_PATH);
			if (!file.exists()) {
				System.out.println("ruta: "+ FILE_PATH);
				return new ArrayList<>();
			}
			return mapper.readValue(file, new TypeReference<List<Nomina>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public static void save(List<Nomina> nominas) {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), nominas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}