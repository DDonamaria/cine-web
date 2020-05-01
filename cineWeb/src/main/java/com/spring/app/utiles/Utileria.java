package com.spring.app.utiles;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {

	/**
	 * Metodo que devuelve las fechas X fechas siguientes a la actual
	 * @param count
	 * @return
	 */
	public static List<String> getNextDays(int count) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		// Today's Date
		Date start = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, count); // Next N days from now
		Date endDate = cal.getTime();

		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(start);
		List<String> nextDays = new ArrayList<String>();
		while (!gcal.getTime().after(endDate)) {
			Date d = gcal.getTime();
			gcal.add(Calendar.DATE, 1);
			nextDays.add(sdf.format(d));
		}
		return nextDays;
	}

	/**
	 * Metodo para guardar una imagen en una ruta especificada
	 * @param multiPart
	 * @param request
	 * @return
	 */
	public static String guardarImagen(MultipartFile multiPart, HttpServletRequest request) {
		// Obtenemos el nombre original del archivo
		String nombreOriginal = multiPart.getOriginalFilename();
		// Cambiamos espacios por guion y añadimos 8 letras aleatorias para evitar
		// nombres duplicados
		String nombreFinal = cadenaAlphaAleatoria(8) + nombreOriginal.replace(" ", "-");
		// Obtenemos la ruta ABSOLUTA del directorio images
		// apache-tomcat/webapps/cineapp/resources/images/
		String rutaFinal = request.getServletContext().getRealPath("/resources/images/");

		// Para evitar la ruta qeu monta por estar bajo git
		// C:\Users\David\Mis
		// Docus\Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\cinemaWeb\resources
		String rutaReal = "C:\\Users\\David\\Mis Docus\\Workspace\\repository_Git\\cinemaWeb\\cinemaWeb\\webapp\\resources\\images\\";

		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(rutaFinal + nombreFinal);
			File imageFileReal = new File(rutaReal + nombreFinal);
			//Para crear en la ruta temporal del IDE eclipse
			new File(rutaFinal + nombreFinal);
			new File(rutaReal + nombreFinal);
			// Aqui se guarda fisicamente el archivo en el disco duro
			multiPart.transferTo(imageFile);
			multiPart.transferTo(imageFileReal);
			return nombreFinal;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}

	/**
	 * Metodo que genera una secuencia de letras aleatoria con la longitud dada
	 * @param length
	 * @return
	 */
	public static String cadenaAlphaAleatoria(long length) {
		String uuid = UUID.randomUUID().toString().toUpperCase();
		Stream<Character> alpha = uuid.chars().mapToObj(i -> (char) i).filter(Character::isAlphabetic).limit(length);
		String finalString = alpha.map(Object::toString).collect(Collectors.joining());
		;
		return finalString;
	}
}