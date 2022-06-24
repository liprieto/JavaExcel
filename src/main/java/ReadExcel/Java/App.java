package ReadExcel.Java;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class App {

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {

		String direccion = "C:\\Users\\Luis Prieto\\Downloads\\AltaComodoro.xlsx";
		Workbook workbook = WorkbookFactory.create(new File(direccion));
		Iterator<Sheet> iteradorFilas = workbook.sheetIterator();

		while (iteradorFilas.hasNext()) {
			Sheet sheet = (Sheet) iteradorFilas.next();
			//System.out.println(sheet.getSheetName());
		}

		Sheet primero = workbook.getSheetAt(0);
		Iterator<Row> col = primero.rowIterator();

			
			while (col.hasNext()) {
			Row row = (Row) col.next();
			Iterator<Cell> celdas = row.cellIterator();
			while (celdas.hasNext()) {
				Cell celda = celdas.next();
				DataFormatter dataFormatter = new DataFormatter();
				String valor = dataFormatter.formatCellValue(celda);
				//System.out.println(valor);
			}

			//System.out.println();
		}
		String[][] matriz = toMatriz(workbook);
		for (int i = 0;
				i < matriz.length;
				
				i++) {	
			
			if(i == 1) {
				
				String base = "";
				
				String filial = (matriz[i][4]);
				
				switch (filial) {
				
				
				case "60": 
					base = "SIFOSOSDEMETRO";
					break;
				case "11":
					base = "SIFOSOSDEMENDOZA";
					break;
				case "2":
					base =  "SIFOSOSDECORDOBA";
					break;
				
				default:
					base = "SIFOSOSDENACIONAL";
					break;
				}
				System.out.print ("\n base: " + base +"\n");
				System.out.print ("\n filial: " + filial +"\n");
				
			}

		}
			
		}
	

	public static String[][] toMatriz(Workbook workbook) {

		Sheet primero = workbook.getSheetAt(0);
		Iterator<Row> col = primero.rowIterator();
		String[][] array = new String[getFilas(primero)][getCol(col.next())];
		int i = 0;
		int j = 0;
		while (col.hasNext()) {
			Row row = (Row) col.next();
			Iterator<Cell> celdas = row.cellIterator();
			while (celdas.hasNext()) {
				Cell celda = celdas.next();
				DataFormatter dataFormatter = new DataFormatter();
				String valor = dataFormatter.formatCellValue(celda);
				array[i][j] = valor;
				j++;
			}
			j = 0;
			i++;

		}

		return array;
	}

	public static int getFilas(Sheet sheet) {
		Iterator<Row> row = sheet.rowIterator();
		int cont = 0;
		while (row.hasNext()) {
			row.next();
			cont++;
		}
		return cont;
	}

	public static int getCol(Row row) {
		Iterator<Cell> celdas = row.cellIterator();
		int cont = 0;
		while (celdas.hasNext()) {
			
			Cell cell = (Cell) celdas.next();
			cont++;
		}
		return cont;
	}
}

