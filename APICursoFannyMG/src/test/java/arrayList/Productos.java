package arrayList;

import java.util.ArrayList;
import java.util.HashSet;

public class Productos {

	public static void main(String[] args) {
		
		String[] productos = {"Producto A", "Producto B", "Producto C", "Producto A", "Producto C"};
		
		ArrayList<String> arrayProduts = new ArrayList<String>();
		
		// Se guardan en la array list
		for(int i=0; i<productos.length; i++) {
			arrayProduts.add(productos[i]);
		}
		
		HashSet<String> hProducts = new HashSet<String>(arrayProduts);
		
		System.out.println(hProducts);
		

	}

}
