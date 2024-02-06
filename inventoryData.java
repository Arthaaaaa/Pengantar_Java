/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coffeeshop;

import java.sql.Date;


/**
 *
 * @author user
 */
public class inventoryData {
    
    private Integer id;
    private String menu_id;
    private String menu_name;
    private String type;
    private Integer stock;
    private Double price;
    private String status;
    private String image;
    private Date date;
    
    public inventoryData(Integer id, String menu_id, String menu_name, String type, Integer stock, Double price, String status, String image, Date date){
        
        this.id = id;
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }
    
    public inventoryData(Integer id, String menu_id, String menu_name, Double price, String image){
        this.id = id;
        this.menu_id = menu_id;
        this.price = price;
        this.image = image;
    }
    
    public Integer getId(){
        return id;
    }
    
    public String getMenuId(){
        return menu_id;
    }
    
    public String getMenuName(){
        return menu_name;
    }
    
    public String getType(){
        return type;
    }
    
    public Integer getStock(){
        return stock;
    }
    
    public Double getPrice(){
        return price;
    }
    
    public String getStatus(){
        return status;
    }
    
    public String getImage(){
        return image;
    }
    
    public Date getDate(){
        return date;
    }
}
