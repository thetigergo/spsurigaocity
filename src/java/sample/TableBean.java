/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;
/**
 *
 * @author Administrator
 */
@javax.faces.bean.ManagedBean(name = "tableBean")
@javax.faces.bean.RequestScoped
public class TableBean implements java.io.Serializable {  
  
    private final static String[] colors;  
  
    private final static String[] manufacturers;  
  
    private  java.util.ArrayList<Car> carsSmall;  
  
    static {  
        colors = new String[10];  
        colors[0] = "Black";  
        colors[1] = "White";  
        colors[2] = "Green";  
        colors[3] = "Red";  
        colors[4] = "Blue";  
        colors[5] = "Orange";  
        colors[6] = "Silver";  
        colors[7] = "Yellow";  
        colors[8] = "Brown";  
        colors[9] = "Maroon";  
  
        manufacturers = new String[10];  
        manufacturers[0] = "Mercedes";  
        manufacturers[1] = "BMW";  
        manufacturers[2] = "Volvo";  
        manufacturers[3] = "Audi";  
        manufacturers[4] = "Renault";  
        manufacturers[5] = "Opel";  
        manufacturers[6] = "Volkswagen";  
        manufacturers[7] = "Chrysler";  
        manufacturers[8] = "Ferrari";  
        manufacturers[9] = "Ford";  
    }  
  
    public TableBean() {  
        carsSmall = new java.util.ArrayList<Car>();  
  
        populateRandomCars(carsSmall, 9);  
    }  
  
    private void populateRandomCars(java.util.ArrayList<Car> list, int size) {  
        for(int i = 0 ; i < size ; i++){  
            list.add(new Car(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));
        }
    }  
  
    public java.util.ArrayList<Car> getCarsSmall() {  
        return carsSmall;  
    }  
  
    private int getRandomYear() {  
        return (int) (Math.random() * 50 + 1960);  
    }  
  
    private String getRandomColor() {  
        return colors[(int) (Math.random() * 10)];  
    }  
  
    private String getRandomManufacturer() {  
        return manufacturers[(int) (Math.random() * 10)];  
    }  
  
    private String getRandomModel() {  
        return java.util.UUID.randomUUID().toString().substring(0, 8);  
    }  
  
    public String[] getManufacturers() {  
        return manufacturers;  
    }  
  
    public String[] getColors() {  
        return colors;  
    }  
  
    public void onEdit(org.primefaces.event.RowEditEvent event) {  
        javax.faces.application.FacesMessage msg = new javax.faces.application.FacesMessage("Car Edited", ((Car) event.getObject()).getModel());  
  
        javax.faces.context.FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(org.primefaces.event.RowEditEvent event) {  
        javax.faces.application.FacesMessage msg = new javax.faces.application.FacesMessage("Car Cancelled", ((Car) event.getObject()).getModel());  
  
        javax.faces.context.FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
} 
