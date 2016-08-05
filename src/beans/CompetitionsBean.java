package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import main.entities.Jogos;

@ManagedBean

public class CompetitionsBean {

	private static final long serialVersionUID = 1L;
	 private List<String> cars;
     private String selectedCar;
	   
	    @PostConstruct
	    public void init() {
	        cars = Jogos.getCompetitions();
	        
	    }
	     
	    public List<String> getCars() {
	        return cars;
	    }

		public String getSelectedCar() {
			return selectedCar;
		}

		public void setSelectedCar(String selectedCar) {
			this.selectedCar = selectedCar;
		}
	 
	  
	}