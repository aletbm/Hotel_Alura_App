package hotel.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Nacionalidades {

	public Nacionalidades() {}
	
	public List<String> getNacionalidades(String url) {
		List<String> nacionalidades =  new ArrayList<>();
		try {
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(url));
			String nac_male = in.readLine();
			String nac_fem = in.readLine();
			while(nac_male != null && nac_fem != null) {
				nac_male = nac_male.substring(0, 1).toUpperCase() + nac_male.substring(1);
				nac_fem = nac_fem.substring(0, 1).toUpperCase() + nac_fem.substring(1);
				nacionalidades.add(nac_male + "/" + nac_fem);
				nac_male = in.readLine();
				nac_fem = in.readLine();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nacionalidades;
	}
}
